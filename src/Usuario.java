
public class Usuario implements Runnable {

    private String user;
    private InterfazSIU siu;
    private String password;

    // Colores.
    public static final String GREEN_BOLD = "\033[1;32m";   // GREEN
    public static final String RESET = "\033[0m";           // Text Reset

    public Usuario(String n, String p, InterfazSIU i) {
        this.user = n;
        this.password = p;
        this.siu = i;
    }

    public String getUser() {
        return this.user;
    }

    public void run() {
        System.out.println("Soy " + this.user + " y me quiero loguear.");
        if (siu.logIn(this.user,this.password)) {
            System.out.println(GREEN_BOLD + "Soy " + this.user + " y me pude loguear." + RESET);
        }
    }

}
