import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class test {

    // Colores.
    public static final String WHITE_BOLD = "\033[1;37m";  // WHITE
    public static final String RESET = "\033[0m";  // Text Reset

    public static void main(String[] args) {
        Proxy interfazSIU = new Proxy();
        try {
            cargarUsuarios(interfazSIU);
            Thread.sleep(200);
        } catch (IOException e) {
            System.err.println("Error leyendo o escribiendo en algun archivo.");
        } catch (InterruptedException e){ }
        menu(interfazSIU);
    }

    public static void menu(Proxy interfazSIU){
        boolean salir = false;
        Scanner sc = new Scanner(System.in);
        String respuestaAdmin;
        while (!salir){
            System.out.println(WHITE_BOLD + "Desea agregar un usuario nuevo? Y/N" + RESET);
            respuestaAdmin = sc.next();
            if (respuestaAdmin.equalsIgnoreCase("N")){  // Ya no se quiere ingresar usuarios nuevos.
                salir = true;   // Sale del bucle.
            } else {
                crearUsuarioNuevo(interfazSIU); // Ingreso manual de datos de usuarios nuevos.
            }
        }
    }

    // Lee el txt y carga los usuarios.
    public static void cargarUsuarios(Proxy interfazSIU) throws IOException {
        FileReader lectorArchivo = new FileReader("Patterns and concurrency\\src\\usuarios.txt");
        Scanner bufferLectura = new Scanner(lectorArchivo);
        bufferLectura.useDelimiter(";");
        String usuario, contraseña;
        while (bufferLectura.hasNext()) { // Iterar hasta que no haya más lineas de datos.
            usuario = bufferLectura.next();
            contraseña = bufferLectura.next();
            bufferLectura.nextLine(); // Bajo a la siguiente línea (siguiente user).
            interfazSIU.userLoad(usuario, contraseña); // Cargo los token.
            new Thread(new Usuario(usuario, contraseña, interfazSIU)).start(); // Creo e inicio el hilo del usuario.
        }
        bufferLectura.close();
    }

    // Carga un usuario manualmente.
    public static void crearUsuarioNuevo(Proxy interfazSIU) {
        Scanner sc = new Scanner(System.in);
        String usuario, contraseña;
        System.out.println(WHITE_BOLD + "Ingrese el nombre del usuario." + RESET);
        usuario = sc.nextLine();
        System.out.println(WHITE_BOLD + "Ingrese la contraseña" + RESET);
        contraseña = sc.nextLine();
        if (interfazSIU.userLoad(usuario, contraseña)){
            new Thread(new Usuario(usuario, contraseña, interfazSIU)).start(); // Creo e inicio el hilo.
        } else {
            System.err.println("El usuario " + usuario + " ya existe.");
        }
    }

}