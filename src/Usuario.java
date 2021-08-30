public class Usuario implements Runnable {

    private String dni;
    private String nombre;
    private String apellido;
    private Token untoken;
    //private SIU siu;

    public Usuario(String d, InterfazSIU i) {
        this.dni = d;
        this.interfazSIU = i;
    }

    public void run() {
        System.out.println("Soy " + this.dni + " y me quiero loguear.");
        Token miToken = new Token(u, p);    // Genero mi token.
        if (interfazSIU.logIn(miToken)){
            // El usuario se pudo loguear.
            
        }
    }

}
