
public class Token {
    private String user;
    private String password;

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