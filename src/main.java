public class main(){

public loadBD(){
    FileReader lectorArchivo = new FileReader("data.txt");
    BufferedReader bufferLectura = new BufferedReader(lectorArchivo);
    */
            while ((linea = bufferLectura.readLine()) != null) {
                System.out.println(linea);           // Escribe en la consola.
                
    
                ejercicio1(linea, bufferEscrituraSinEspacios);  // lineas sin espacios.
                ejercicio2(linea, bufferEscrituraLineasImpares, numeroLinea);   // lineas impares.
                numeroLinea++;
            }







}




















}