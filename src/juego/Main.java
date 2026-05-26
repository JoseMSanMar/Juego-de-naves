package juego;

import edu.epromero.util.FabricaAudio;

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
        FabricaAudio miAudio = new FabricaAudio();
        miAudio.reproducir(".//src//music.wav");
        while (true) {

            play.mover();
            play.pinta();
            try {
                Thread.sleep(40);
                //play.mover();
            } catch (InterruptedException ex) {
                System.getLogger(Main.class.getName()).
                        log(System.Logger.Level.ERROR, (String) null, ex);
            }
        }
    }

}
