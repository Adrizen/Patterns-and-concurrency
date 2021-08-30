public class Usuario implements Runnable {

    private int dni;
    private String nombre;
    private String apellido;
    private Token untoken;
    //private SIU siu;

    public Usuario(String d, InterfazSIU i) {
        this.dni = d;
        this.interfazSIU = i;
    }
  
    public Usuario(int d, String a, String n, Token unt, InterfazSIU i) {
        this.dni = d;
        this.apellido=a;
        this.nombre=n;
        this.untoken=unt;
        this.interfazSIU = i;
    }
    public int gerUser(){
        return this.dni;
    }
    public Token getToken(){
        return this.untoken;
    }

    public void run() {
        System.out.println("Soy " + this.dni + " y me quiero loguear.");
        Token miToken = new Token(u, p);    // Genero mi token.
        if (interfazSIU.logIn(miToken)){
            // El usuario se pudo loguear.
            
        }
    }

}
