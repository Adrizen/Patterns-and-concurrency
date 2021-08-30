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
  
    public Usuario(String d, String a, String n, Token unt, InterfazSIU i) {
        this.dni = d;
        this.apellido=a;
        this.nombre=n;
        this.untoken=unt;
        this.interfazSIU = i;
    }

    public void run() {
        System.out.println("Soy " + this.dni + " y me quiero loguear.");
    }

}
