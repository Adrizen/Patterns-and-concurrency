import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class SIU implements ServiceInterface{
    private ArrayList<Tramite> tramites; // Almacena los tramites posibles

    public SIU() {
        this.tramites = new ArrayList<Tramite>();
        try {
            inicializarBaseDeDatos(tramites);
        } catch (IOException e) {
            System.out.println("Hubo un error al leer/escribir un archivo.");
        }
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
        bufferLectura.close();
    }

    @Override
    public Tramite serviceRequired(Tramite tramite) {
        // Solicitar el servicio
        if (tramites.contains(tramite)) {
            Object documento = tramites.get(tramites.indexOf(tramite)).getDocumento();
            tramite.setDocumento(documento);
        }
        return tramite;
    }

}
