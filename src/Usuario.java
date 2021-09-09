import java.util.Random;

public class Usuario implements Runnable {

    private String user;
    private InterfazSIU interfazSIU;
    private String password;

    // Colores.
    public static final String GREEN_BOLD = "\033[1;32m"; // GREEN
    public static final String RESET = "\033[0m"; // Text Reset

    public Usuario(String n, String p, InterfazSIU i) {
        this.user = n;
        this.password = p;
        this.interfazSIU = i;
    }

    public String getUser() {
        return this.user;
    }

    public void run() {
        System.out.println("Soy " + this.user + " y me quiero loguear.");
        if (interfazSIU.checkAccess(this.user, this.password)) {
            System.out.println(GREEN_BOLD + "Soy " + this.user + " y me pude loguear." + RESET);
            int idTramite = idTramiteAleatorio();
            System.out.println(GREEN_BOLD + "Soy " + this.user + " y voy a solicitar el trámite ID " + idTramite + RESET);
            Tramite tramite = interfazSIU.serviceRequired(idTramite);
            System.out.println(GREEN_BOLD + "Soy " + this.user + " y tengo mi trámite completado: " + tramite.getDocumento() + RESET);
        }
    }

    // Genera un ID aleatorio válido para un tramite.
    private int idTramiteAleatorio() {
        Random r = new Random();
        int inferior = 1, superior = 6; // inferior = inclusivo ; superior = exclusivo.
        int resultado = r.nextInt(superior - inferior) + inferior;
        return resultado;
    }

}
