
public class Tramite {
    private int id;
    private Object documento;

    public Tramite(int id){
        this.id = id;
    }

    public Tramite(int id, Object d){
        this.id = id;
        this.documento = d;
    }

    public void setDocumento(Object d){
        this.documento = d;
    }

    public Object getDocumento(){
        return this.documento;
    }

    public int getID(){
        return this.id;
    }

    public String toString(){
        return this.id + " " + this.documento;
    }

   
    public boolean equals(Object unTramite){    // Si aca en vez de Object pones Tramite el contains de ArrayList ignora este equals.
        return this.id == ((Tramite) unTramite).getID();
    }

}
