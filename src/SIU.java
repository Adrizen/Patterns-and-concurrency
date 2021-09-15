import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class SIU implements ServiceInterface {
    private ArrayList<Tramite> tramites; // Contiene todos los trámites posibles.

    // Contructor.
    public SIU() {
        this.tramites = new ArrayList<Tramite>();
        try {
            inicializarBaseDeDatos(tramites);
        } catch (IOException e) {
            System.out.println("Hubo un error al leer/escribir un archivo.");
        }
    }

    public ArrayList<Tramite> getTramites() {
        return this.tramites;
    }

    public void inicializarBaseDeDatos(ArrayList<Tramite> tramite) throws IOException {
        FileReader lectorArchivo = new FileReader("Patterns and concurrency\\src\\tramites.txt");
        Scanner bufferLectura = new Scanner(lectorArchivo);
        bufferLectura.useDelimiter(";");
        int id;
        String documento;
        while (bufferLectura.hasNext()) { // Iterar hasta que no haya más lineas de datos.
            id = Integer.parseInt(bufferLectura.next());
            documento = bufferLectura.next();
            bufferLectura.nextLine();
            tramite.add(new Tramite(id, documento));
        }
    }

    // Como desde la caché no se pudo recuperar el trámite, se accede a la BD.
    public Tramite serviceRequired(Tramite tramite, Token unToken) {
        if (tramites.contains(tramite)) {
            try {
                Thread.sleep((int) (4000 * Math.random() + 4000)); // Tiempo para recuperar de la BD
            } catch (InterruptedException e) { }
            Object documento = tramites.get(tramites.indexOf(tramite)).getDocumento();  // Se obtiene el trámite desde la BD.
            tramite.setDocumento(documento);    // Se completa el trámite.
        }
        return tramite;
    }

}
