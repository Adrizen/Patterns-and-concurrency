
public class Token {
    private String user;
    private String password;

    // Los Token permiten diferenciar un usuario de otro.
    // Se realiza un backup periódico de los token.

    public Token(String u, String p) {
        this.user = u;
        this.password = p;
    }

    public String getUser(){
        return this.user;
    }

    public String getPassword(){
        return this.password;
    }

    public String toString(){
        return this.user + ";" + this.password;
    }

    public boolean equals(Token unToken){
        return (this.user.equals(unToken.getUser()) && this.password.equals(unToken.getPassword()));
    }

}