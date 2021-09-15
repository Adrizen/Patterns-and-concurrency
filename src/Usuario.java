import java.util.Random;

public class Usuario implements Runnable {
    private String user;
    private ServiceInterface interfazSIU;
    private String password;

    // Colores.
    public static final String GREEN_BOLD = "\033[1;32m"; // GREEN
    public static final String RESET = "\033[0m"; // Text Reset

    public Usuario(String n, String p, Proxy i) {
        this.user = n;
        this.password = p;
        this.interfazSIU = i;
    }

    public void run() {
        long tiempoInicial = System.currentTimeMillis();
        System.out.println(GREEN_BOLD + "Soy " + this.user + " y quiero usar el SIU Guaraní para unos trámites." + RESET);
        int idTramite = idTramiteAleatorio();       // El usuario genera un ID de trámite aleatorio.
        Tramite unTramite = new Tramite(idTramite); // Se crea el trámite.
        System.out.println("Soy " + this.user + " y voy a solicitar el trámite ID " + idTramite);
        Token unToken = new Token(user, password);  // Genero mi token de autenticación.
        // El usuario se comunica con la interfaz enviando su token y el trámite a ser completado.
        unTramite = interfazSIU.serviceRequired(unTramite, unToken);
        long tiempoFinal = (System.currentTimeMillis() - tiempoInicial) / 1000;
        System.out.println(GREEN_BOLD + "Soy " + this.user + " y tengo mi trámite ID " + idTramite + " completado: " + unTramite.getDocumento() + ". Tardé " + tiempoFinal + " segundos." + RESET);
    }

    // Genera un ID aleatorio válido para un tramite.
    private int idTramiteAleatorio() {
        Random r = new Random();
        int inferior = 1, superior = 6; // inferior = inclusivo ; superior = exclusivo.
        int resultado = r.nextInt(superior - inferior) + inferior;
        return resultado;
    }

}