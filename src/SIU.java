import java.util.HashMap;
import Usuario;
import Tramite;

public class SIU(){
    private boolean status; // Esta variable nos idica el estado del SIU
    private HashMap tramites;// almaceno los tramites posibles

public SIU() {
    this.status=true;
    this.baseDeDatos= new HashMap();
    this.tramites= new HashMap();
}

    public void serverDown() {
        // Este metodo provoca la caida del servidor
        this.status = false;
    }

    public void serverUp() {
        // Este metodo provoca la reconexion del servidor
        this.status = true;
    }

    public boolean status() {
        // Este metodo informa el estado del servidor
        return this.status;
    }

    public boolean serviceLoad(Tramite unt) {
        // Cargo un Tramite a mi BDD
        return baseDeDatos.put(unt.getKey(), unT);
    }

    public Tramite requestService(int key_service) {
        Tramite a_service;
        // Solicitar el servicio
        a_service = (Tramite) tramite.get(key_service);
        return a_service;
    }

}
