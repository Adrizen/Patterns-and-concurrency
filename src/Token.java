
public class Token {
    private int user;
    private String password;

    public Token(int u, String p) {
        this.user = u;
        this.password = p;
    }

public int getUser(){
    return this.user;
}

    public boolean equals(Token unT) {
        return this.user == unT.user && this.password == unT.password;
    }

}