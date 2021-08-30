public class Usuario implements Runnable {

    private String dni;
<<<<<<< HEAD
    private String nombre;
    private String apellido;
    private Token untoken;
    //private SIU siu;
=======
    private InterfazSIU interfazSIU;
>>>>>>> 0e164a8aa522c926a8bd9f6a900a562f283ec60b

    public Usuario(String d, InterfazSIU i) {
        this.dni = d;
        this.interfazSIU = i;
    }

    public void run() {
        System.out.println("Soy " + this.dni + " y me quiero loguear.");
    }

}
