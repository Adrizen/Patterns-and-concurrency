public class test {

    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 5; i++) {
            new Thread(new Usuario("00" + i)).start();
        }
    }

}
