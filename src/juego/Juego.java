package juego;

import edu.epromero.util.Lienzo;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.Color;

/**
 *
 * @author user
 */
public class Juego {

    private Lienzo canvas;
    private Fondo pantalla;
    private Destructor destructor;
    private AveDePresa aveDePresa;
    private LanzaMinas bombardero;
    private Kamikaze kami;
    private Heroe rebelde;
    private Entrada e;

    public Juego() {
        e = new Entrada();
        //Creamos el lienzo
        canvas = new Lienzo();

        // CONFIGURAMOS EL LIENZO
        canvas.ponTamanioLienzo(1950, 950);
        canvas.ponEscalaX(0, 1950);
        canvas.ponEscalaY(0, 950);

        // Inicializamos el resto de objetos
        //fondo
        pantalla = new Fondo();
        //Naves enemigas
        destructor = new Destructor();
        bombardero = new LanzaMinas();
        aveDePresa = new AveDePresa();
        kami = new Kamikaze();
        //Nave Rebelde
        rebelde = new Heroe();

        rebelde.iniciarPosicion(canvas);
        destructor.iniciarPosicion(canvas);
        aveDePresa.iniciarPosicion(canvas);
        bombardero.iniciarPosicion(canvas);
        kami.iniciarPosicion(canvas);
    }

    public void pinta() {
        if (!rebelde.isVisible()) {
            pantalla.gameOver(rebelde, canvas);
            canvas.mostrar(0);
        } else {
            //pintamos el lienzo canvas
            pantalla.pinta(canvas);
            pantalla.pintaPuntos(rebelde, canvas);
            pantalla.pintaVidas(rebelde, canvas);
            //Pintamos las naves
            //rebelde
            if (rebelde.isVisible()) {
                rebelde.pinta(canvas);
            }
            //lanzaminas
            if (bombardero.isVisible()) {
                bombardero.pinta(canvas);
            }
            //destructor
            if (destructor.isVisible()) {
                destructor.pinta(canvas);
            }
            //avedepresa
            if (aveDePresa.isVisible()) {
                aveDePresa.pinta(canvas);
            }
            //kamikaze
            if (kami.isVisible()) {
                kami.pinta(canvas);
            }
            canvas.mostrar(0);
        }
    }

    public void mover() {
        // Le damos a la entrada el canvas que estamos usando
        e.setMiCanvas(canvas);
        //actualiza el tiempo de espera de la naves
        kami.actualizarTemporizador();
        bombardero.actualizarTemporizador();
        // Movemos las naves
        //movemos rebelde
        if (rebelde.isVisible()) {
            rebelde.Mueve(e);
        }
        //movemos destructor
        if (destructor.isVisible()) {
            destructor.Mueve(e);
        }
        //movemos avedepresa
        if (aveDePresa.isVisible()) {
            aveDePresa.Mueve(e);
        }
        //movemos lanzaminas
        if (bombardero.isVisible()) {
            bombardero.Mueve(e);
        }
        //movemos kami
        if (kami.isVisible() && !kami.isEsperandoReaparecer()) {
            kami.Mueve(e);
        }
        // **********COLISIONES: BALAS ENEMIGAS GOLPEAN AL HÉROE**********
        if (rebelde.isVisible()) {
            //REVISAR EL CARGADOR DEL DESTRUCTOR
            Bala[] balasDestructor = destructor.getCargador();
            for (int i = 0; i < balasDestructor.length; i++) {
                if (balasDestructor[i].getEstado() == Bala.VUELO
                        && rebelde.hayColision(balasDestructor[i])) {
                    //Reduce las vidas y evalua si la nave ya fue destruida
                    rebelde.recibirDanio();
                    rebelde.destruir(rebelde.isDanioFatal());
                    System.out.println("¡Impacto al Héroe por Destructor!");
                    balasDestructor[i].setEstado(Bala.INACTIVA);
                }
            }
            //REVISAR EL CARGADOR DEL AVE DE PRESA
            Bala[] balasAve = aveDePresa.getCargador();
            for (int i = 0; i < balasAve.length; i++) {
                if (balasAve[i].getEstado() == Bala.VUELO
                        && rebelde.hayColision(balasAve[i])) {
                    //Reduce las vidas y evalua si la nave ya fue destruida
                    rebelde.recibirDanio();
                    rebelde.destruir(rebelde.isDanioFatal());
                    System.out.println("¡Impacto al Héroe por Ave de Presa!");
                    balasAve[i].setEstado(Bala.INACTIVA);
                }
            }
            //REVISAR EL CARGADOR DEL BOMBARDERO (LanzaMinas)
            Bala[] balasBombardero = bombardero.getCargador();
            for (int i = 0; i < balasBombardero.length; i++) {
                if (balasBombardero[i].getEstado() == Bala.VUELO
                        && rebelde.hayColision(balasBombardero[i])) {
                    //Reduce las vidas y evalua si la nave ya fue destruida
                    rebelde.recibirDanio();
                    rebelde.destruir(rebelde.isDanioFatal());
                    System.out.println("¡Impacto al Héroe por Bombardero!");
                    balasBombardero[i].setEstado(Bala.INACTIVA);
                }
            }
            // El Kamikaze explota al estrellarse
            if (kami.isVisible() && rebelde.hayColisionNave(kami)) {
                rebelde.setVidas(0);
                rebelde.recibirDanio();
                rebelde.destruir(rebelde.isDanioFatal());
                kami.recibirDanio();
                System.out.println("¡Impacto al Héroe por Kamikaze!");
                kami.iniciarEsperaReaparicion();
            }
        }
        // **********COLISIONES INVERSAS**********
        // LA BALA DEL HÉROE GOLPEA A LOS ENEMIGOS
        if (rebelde.getBala().getEstado() == Bala.VUELO) {
            // 1. Héroe -> Destructor
            if (destructor.isVisible()
                    && destructor.hayColision(rebelde.getBala())) {
                rebelde.getBala().setEstado(Bala.INACTIVA);
                //revisa si la nave fue destruida y almacena los puntos
                destructor.recibirDanio();
                //reaparece la nave
                if (destructor.isDanioFatal()) {
                    rebelde.ponPuntos(destructor.getPuntos());
                    destructor.reaparecer();
                }
            }
            // 2. Héroe -> AveDePresa
            if (aveDePresa.isVisible()
                    && aveDePresa.hayColision(rebelde.getBala())) {
                rebelde.getBala().setEstado(Bala.INACTIVA);
                //revisa si la nave fue destruida y almacena los puntos
                aveDePresa.recibirDanio();
                //reaparece la nave
                if (aveDePresa.isDanioFatal()) {
                    rebelde.ponPuntos(aveDePresa.getPuntos());
                    aveDePresa.reaparecer();
                }
            }
            // 3. Héroe -> Bombardero (LanzaMinas)
            if (bombardero.isVisible()
                    && bombardero.hayColision(rebelde.getBala())) {
                rebelde.getBala().setEstado(Bala.INACTIVA);
                //revisa si la nave fue destruida y almacena los puntos
                bombardero.recibirDanio();
                //reaparece la nave
                if (bombardero.isDanioFatal()) {
                    rebelde.ponPuntos(bombardero.getPuntos());
                    bombardero.iniciarEsperaReaparicion();
                }
            }
            if (kami.isVisible()
                    && kami.hayColision(rebelde.getBala())) {
                rebelde.getBala().setEstado(Bala.INACTIVA);
                //revisa si la nave fue destruida y almacena los puntos
                kami.recibirDanio();
                //reaparece la nave
                if (kami.isDanioFatal()) {
                    rebelde.ponPuntos(kami.getPuntos());
                    kami.iniciarEsperaReaparicion();
                }
            }
        }
    }
}
