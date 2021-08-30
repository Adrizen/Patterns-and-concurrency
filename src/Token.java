

public class Tokens{
private int user;
private String password;

public Tokens(int u,String p){
    this.user=u;
    this.password=p;
}

public getUser(){
    return this.user;
}
public boolean equals (Token unT){
    return this.user==unT.user && this.password==unT.password;
}

}