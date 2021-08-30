public class test {

    public static void main(String[] args) throws Exception {

        InterfazSIU interfazSIU = new InterfazSIU();

        for (int i = 0; i < 5; i++) {
            new Thread(new Usuario("00" + i, interfazSIU)).start();
        }
    }

}
