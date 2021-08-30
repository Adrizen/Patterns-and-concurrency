public class Usuario implements Runnable {

    private String dni;
    private String nombre;
    private String apellido;
    //private SIU siu;

    public Usuario(String d){
        this.dni = d;
        //this.nombre = n;
        //this.apellido = a;
        //this.siu = s;
    }

    public void run() {
        System.out.println("Soy un usuario, mi DNI es: " + this.dni);
        if (siu.logIn()){
            Tramite tramite = siu.requestService(0);    // Trámite por defecto es id 0 pero podría ser aleatorio.
            System.out.println("Acabo de solicitar mi trámite");
            // Acá se podría imprimir el trámite o guardarlo.
        }
    }

}
