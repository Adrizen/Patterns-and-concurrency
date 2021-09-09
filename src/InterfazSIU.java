import java.util.HashMap;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import static java.util.concurrent.TimeUnit.*;

public class InterfazSIU {
    private HashMap<String, Token> tokenUsuarios; // Donde se tiene los usuario;password
    private Backup servicioBackup; // Realiza periodicamente un backup de los token.
    ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(0); // scheduler que controla el servicio de backups.
    private SIU siu; // siu real.
    private ArrayList<Tramite> cache; // Simulamos el cache en el proxy.

    // Constructor.
    public InterfazSIU() {
        this.siu = new SIU();
        this.cache = new ArrayList<Tramite>();
        inicializarCache();
        this.tokenUsuarios = new HashMap<String, Token>();
        this.servicioBackup = new Backup(tokenUsuarios);
        backupPeriodicoTokenUsuarios();
    }

    // Inicializa los datos que se encuentran en la caché.
    private void inicializarCache() {
        for (int i = 1; i < 4; i++) {
            Tramite tramite = new Tramite(i);
            tramite = siu.requestService(tramite);
            cache.add(tramite);
        }
    }

    // Utilizado para realizar el logueo de usuarios.
    public boolean logIn(String user, String password) {
        boolean success = false;
        Token token = new Token(user, password);
        if (token.equals(tokenUsuarios.get(user)))
            success = true;
        return success;
    }

    // Cargar un usuario al HashMap de tokens.
    public boolean userLoad(String user, String contraseña) {
        boolean exito = false;
        Token token = new Token(user, contraseña);
        if (tokenUsuarios.put(user, token) != null) {
            exito = true;
        }
        return exito;
    }

    // Servicio de backup automático para los token de usuarios.
    private void backupPeriodicoTokenUsuarios() {
        // Tal como está puesto el backup se realiza cada X tiempo sin cortar nunca.
        ScheduledFuture<?> backup = scheduler.scheduleWithFixedDelay(servicioBackup, 2, 2, SECONDS);
    }

    public Tramite serviceRequired(int idTramite) {
        Tramite tramite = new Tramite(idTramite);
        if (!recoverCache(tramite)) {
            // No está en cache.
            try {
                Thread.sleep((int) (5000 * Math.random() + 5000)); // Tiempo para recuperar de la BD.

            } catch (InterruptedException e) {
            }
            tramite = siu.requestService(tramite); // Buscar en la BD.
        }
        return tramite;
    }

    private boolean recoverCache(Tramite unTramite) {
        boolean exito = false;
        if (cache.contains(unTramite)) {
            exito = true;
            try {
                Thread.sleep(100); // Tiempo para recuperar de caché.
            } catch (InterruptedException e) {
            }
            Object documento = cache.get(cache.indexOf(unTramite)).getDocumento();
            unTramite.setDocumento(documento);
        }
        return exito;
    }

}
