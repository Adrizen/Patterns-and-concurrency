public class main(){

public static void cargarInicial(InterfazSIU i_siu) throws FileNotFoundException, IOException {
    //CargaInicial
    BufferedReader entrada = null;
    entrada = new BufferedReader(new FileReader("data.txt"));
    String linea;
    String[] lineaSplit;
    int x = 0;
    while ((linea = entrada.readLine()) != null) {
        lineaSplit = linea.split(";");
        switch (lineaSplit[0]) {
            case "U":
                guardar("Usuario");  //TIPO|Users|Password|Apellido|Name|2Name === String d, String a, String n, Token unt, InterfazSIU i)
                Token untoken=new Token(lineaSplit[1],lineaSplit[2])
                Usuario nuevoU = new Usuario(lineaSplit[1], lineaSplit[3], lineaSplit[4],untoken,i_siu);
                siu.userLoad(Usuario)
                break;
            }
     }

 }






}




















}