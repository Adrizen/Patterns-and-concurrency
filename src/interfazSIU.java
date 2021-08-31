import java.util.HashMap;
import java.util.ArrayList;

public class InterfazSIU {
    private HashMap<String, Token> tokenUsuarios; // Donde se tiene los usuario;password
    private SIU service;
    private ArrayList cache; // Simulamos el cache

    public InterfazSIU() {
        this.tokenUsuarios = new HashMap<String, Token>();
        cache = new ArrayList();
    }

    public boolean logIn(String user, String password) {
        boolean success = false;
        Token token = new Token(user, password);
        if (token.equals(tokenUsuarios.get(user)))
            success = true;
        return success;
    }

    public boolean userLoad(String user, String contraseña) {
        boolean exito = false;
        Token token = new Token(user, contraseña);
        if (tokenUsuarios.put(user, token) != null) {
            exito = true;
        }
        return exito;
    }

    //private Tramite recoverCache(Tramite unT) {
    //Tramite unt;
    // if(cache.contains(unT))
    //unt = cache.get(cache.indexOf(unT));
    //return unt;
    //}

    //public Tramite serviceRequired(Tramite t) {
    //Tramite unt;
    //if (service.getStatus()) {
    //if (cache.contains(t)) {
    //  unt = recoverCache(t);
    //} else {
    //  unt = service.requestService(t.getKey());
    //}
    //}
    //return unt;
    //}
}