import java.util.HashMap;
import java.util.ArrayList;

public class InterfazSIU {
    private HashMap baseDeDatos;// Donde se tiene los usuario;password
    private SIU service;
    private ArrayList cache;// simulamos el cache

    public InterfazSIU() {
        this.baseDeDatos = new HashMap();
        cache = new ArrayList();
    }

    public boolean logIn(Token unt) {
        boolean success = false;
        Object user = (Usuario) baseDeDatos.get(unt.getUser());
        if (user != null) { // Verifico si el user existe
            if (user.getToken().equals(unt)) // Comparo si coinciden los token
                success = true;
        }
        return success;
    }
    return unt;
}

//Falta el metodo que seleccione el tramite a realizar





    public boolean userLoad(Usuario user) {
        // Cargo un usuario a mi BDD
        return baseDeDatos.put(user.getUser(), user);
    }

    private Tramite recoverCache(Tramite unT) {
        Tramite unt;
        // if(cache.contains(unT))
        unt = cache.get(cache.indexOf(unT));
        return unt;
    }

    public Tramite serviceRequired(Tramite t) {
        Tramite unt;
        if (service.getStatus()) {
            if (cache.contains(t)) {
                unt = recoverCache(t);
            } else {
                unt = service.requestService(t.getKey());
            }
        }
        return unt;
    }

}