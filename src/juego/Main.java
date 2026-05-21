package juego;

/**
 *
 * @author user
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Juego play;
        play = new Juego();

        while (true) {
            play.mover();
            play.pinta();
            try {
                Thread.sleep(20);
                //play.mover();
            } catch (InterruptedException ex) {
                System.getLogger(Main.class.getName()).
                        log(System.Logger.Level.ERROR, (String) null, ex);
            }
        }
    }

}
