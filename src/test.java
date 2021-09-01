import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class test {

    // Colores.
    public static final String WHITE = "\033[0;37m";   // WHITE
    public static final String RESET = "\033[0m";  // Text Reset

    public static void main(String[] args) {
        InterfazSIU interfazSIU = new InterfazSIU();
        boolean seguir = false;
        String respuesta;
        Scanner escaner = new Scanner(System.in);
        try {
            cargarUsuarios(interfazSIU);
            Thread.sleep(200);  // Para que no se mezclen carteles xddddddddddddddd.
        } catch (Exception e) {
            System.err.println("Error leyendo o escribiendo en algun archivo.");
        }
        System.out.println("Se han cargado los usuarios a partir del txt.");
        do {
            System.out.println("¿Quiere cargar otro? Use Y/N ");
            respuesta = escaner.next();
            if (respuesta.equalsIgnoreCase("Y")){
                seguir = true;
                crearUsuarioNuevo(interfazSIU);
            } else {
                seguir = false;
            }
        } while (seguir);


    }

    // Lee el txt y carga los usuarios.
    public static void cargarUsuarios(InterfazSIU interfazSIU) throws IOException {
        FileReader lectorArchivo = new FileReader("Patterns and concurrency\\src\\data.txt");
        Scanner bufferLectura = new Scanner(lectorArchivo);
        bufferLectura.useDelimiter(";");
        String usuario, contraseña;
        while (bufferLectura.hasNext()) { // Iterar hasta que no haya más lineas de datos.
            usuario = bufferLectura.next();
            contraseña = bufferLectura.next();
            bufferLectura.nextLine(); // Bajo a la siguiente línea (siguiente user).
            interfazSIU.userLoad(usuario, contraseña); // Cargo los token.
            new Thread(new Usuario(usuario, contraseña, interfazSIU)).start(); // Creo e inicio los hilos.
        }
        bufferLectura.close();
    }

    // Carga un usuario manualmente.
    public static void crearUsuarioNuevo(InterfazSIU interfazSIU) {
        Scanner sc = new Scanner(System.in);
        String usuario, contraseña;
        System.out.println("Ingrese el nombre del usuario.");
        usuario = sc.next();
        System.out.println("Ingrese la contraseña");
        contraseña = sc.next();
        interfazSIU.userLoad(usuario, contraseña); // Cargo el token.
        new Thread(new Usuario(usuario, contraseña, interfazSIU)).start(); // Creo e inicio el hilo.
    }

}