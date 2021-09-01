import java.util.ArrayList;

public class SIU {
    private ArrayList<Tramite> tramites; // Almacena los tramites posibles

    public SIU() {
        this.tramites = new ArrayList<Tramite>();
        inicializarBaseDeDatos();
    }

    public void inicializarBaseDeDatos() {
        tramites.add(new Tramite(1, "Certificado alumno regular."));
        tramites.add(new Tramite(2, "Certificado alumno activo."));
        tramites.add(new Tramite(3, "Historial académico."));
        tramites.add(new Tramite(4, "Formulario para cambio de carrera."));
        tramites.add(new Tramite(5, "Formulario para cursado paralelo."));
    }

    public Tramite requestService(Tramite unTramite) {
        // Solicitar el servicio
        if (tramites.contains(unTramite)) {
            Object documento = tramites.get(tramites.indexOf(unTramite)).getDocumento();
            unTramite.setDocumento(documento);
        }
        return unTramite;
    }

}
