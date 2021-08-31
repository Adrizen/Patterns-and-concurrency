import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class test {

    public static void main(String[] args) {
        InterfazSIU interfazSIU = new InterfazSIU();
        
        try {
            cargarUsuarios(interfazSIU);
        } catch (IOException e) {
            System.err.println("Error leyendo o escribiendo en algun archivo.");
        }

    }

    // Lee el txt y genera los usuarios.
    public static void cargarUsuarios(InterfazSIU interfazSIU) throws IOException {
        FileReader lectorArchivo = new FileReader("Patterns and concurrency\\src\\data.txt");
        Scanner bufferLectura = new Scanner(lectorArchivo);
        bufferLectura.useDelimiter(";");
        String usuario, contraseña;
        while (bufferLectura.hasNext()) {   // Iterar hasta que no haya más lineas de datos.
            usuario = bufferLectura.next();
            contraseña = bufferLectura.next();
            bufferLectura.nextLine();       // Bajo a la siguiente línea (siguiente user).
            interfazSIU.userLoad(usuario, contraseña);  // Cargo los token.
            new Thread(new Usuario(usuario, contraseña, interfazSIU)).start();  // Creo e inicio los hilos.
        }
        bufferLectura.close();
    }

}