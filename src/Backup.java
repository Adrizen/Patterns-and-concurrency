import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class Backup implements Runnable {

    private HashMap<String, Token> tokenUsuarios;
    private int numeroBackup;

    public Backup(HashMap<String, Token> tokU) {
        this.numeroBackup = 0;
        this.tokenUsuarios = tokU;
    }

    // Realiza backups de los Token (usuario;contraseña) en backup.txt
    public void run() {
        try {
            FileWriter escritorArchivo = new FileWriter("Patterns and concurrency\\src\\backup.txt");
            BufferedWriter bufferEscritura = new BufferedWriter(escritorArchivo);
            bufferEscritura.write("Backup número: " + this.numeroBackup + "\n" + tokenUsuarios.toString());
            this.numeroBackup++;
            bufferEscritura.close();
        } catch (IOException e) {
            System.err.println("Error leyendo o escribiendo en algun archivo.");
        }

    }

}
