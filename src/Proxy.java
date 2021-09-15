import java.util.HashMap;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import static java.util.concurrent.TimeUnit.*;

public class Proxy implements ServiceInterface {
    private HashMap<String, Token> tokenUsuarios; // Donde se tiene los usuario;password
    private Backup servicioBackup; // Realiza periodicamente un backup de los token.
    ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1); // scheduler que controla el servicio de backups.
    private SIU siu; // Real service (Patrón proxy).
    private ArrayList<Tramite> cache; // Simulamos el cache en el proxy.

    // Constructor.
    public Proxy() {
        this.siu = new SIU();
        this.cache = new ArrayList<Tramite>();
        int idMinimo = 0, idMaximo = 2; 
        inicializarCache(idMinimo, idMaximo);   // Se guardar en caché los trámites: Certificado de alumno regular, activo e historial académico.
        this.tokenUsuarios = new HashMap<String, Token>();
        this.servicioBackup = new Backup(tokenUsuarios);
        int delayInicial = 2, delaySubsecuente = 2; // Delay inicial y posteriores.
        backupPeriodicoTokenUsuarios(delayInicial, delaySubsecuente);
    }

    // Inicializa los datos que se encuentran en la caché.
    private void inicializarCache(int idMinimo, int idMaximo) {
        ArrayList<Tramite> tramites = siu.getTramites();
        for (int i = idMinimo; i <= idMaximo; i++) { // Los trámites con ID desde idMinimo+1 a idMáximo-1 están en caché.
            Tramite tramite = new Tramite(i);
            tramite = tramites.get(i);
            cache.add(tramite);
        }
    }

    // Utilizado para realizar el logueo de usuarios. Devuelve true si el usuario se puede loguear. (Patrón proxy)
    public boolean checkAccess(Token unToken) {
        boolean success = false;
        if (tokenUsuarios.containsKey(unToken.getUser())) {
            success = true;
        }
        return success;
    }

    // Cargar un usuario al HashMap de tokens. Devuelve true si el usuario a registrar NO existía antes.
    public boolean userLoad(String user, String contraseña) {
        boolean exito = false;
        Token token = new Token(user, contraseña);
        if (tokenUsuarios.put(user, token) == null) { // Si put devuelve null, la clave no existía. El usuario ingresado es nuevo.
            exito = true;
        }
        return exito;
    }

    // Servicio de backup automático para los token de usuarios.
    private void backupPeriodicoTokenUsuarios(int delayInicial, int delaySubsecuente) {
        //                                                        (Runnable command, long initialDelay, long delay, TimeUnit unit)
        ScheduledFuture<?> backup = scheduler.scheduleWithFixedDelay(servicioBackup, delayInicial, delaySubsecuente, SECONDS);
    }

    // Un usuario pide un trámite. Devuelve el trámite solicitado, ya sea desde la caché o la BD. (Patrón proxy)
    @Override
    public Tramite serviceRequired(Tramite tramite, Token unToken) {
        if (checkAccess(unToken)) {
            if (!recoverCache(tramite)) { // Si devuelve 'false' el trámite no está en caché y hay que solicitarlo a la BD (más lento).
                // No está en cache.
                tramite = siu.serviceRequired(tramite, unToken); // Buscar en la BD.
            }
        }
        return tramite;
    }

    // Se intenta recuperar el trámite desde caché. Devuelve true si el trámite está en caché.
    private boolean recoverCache(Tramite unTramite) {
        boolean exito = false;
        if (cache.contains(unTramite)) {
            exito = true;
            try {
                Thread.sleep((int) (2000 * Math.random() + 2000)); // Tiempo para recuperar de caché.
            } catch (InterruptedException e) { }
            Object documento = cache.get(cache.indexOf(unTramite)).getDocumento();  // Se obtiene el trámite desde la caché.
            unTramite.setDocumento(documento);  // Se completa el trámite.
        }
        return exito;
    }

}
