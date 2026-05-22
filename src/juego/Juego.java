package juego;

import edu.epromero.util.Lienzo;

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
    private Heroe rebelde;
    private Entrada e;

    public Juego() {
        e = new Entrada();
        //Creamos el lienzo
        canvas = new Lienzo();

        // CONFIGURAMOS EL LIENZO
        canvas.ponTamanioLienzo(1200, 600);
        canvas.ponEscalaX(0, 1950);
        canvas.ponEscalaY(0, 950);

        // Inicializamos el resto de objetos
        //fondo
        pantalla = new Fondo();
        //Naves enemigas
        destructor = new Destructor();
        bombardero = new LanzaMinas();
        aveDePresa = new AveDePresa();
        //Nave Rebelde
        rebelde = new Heroe();
        rebelde.iniciarPosicion(canvas);
        destructor.iniciarPosicion(canvas);
        aveDePresa.iniciarPosicion(canvas);
        bombardero.iniciarPosicion(canvas);
    }

    public void pinta() {
        //pintamos el lienzo canvas
        pantalla.pinta(canvas);
        //Pintamos las naves
        if (rebelde.isVisible()) {
            rebelde.pinta(canvas);
        }
        if (bombardero.isVisible()) {
            bombardero.pinta(canvas);
        }
        if (destructor.isVisible()) {
            destructor.pinta(canvas);
        }
        if (aveDePresa.isVisible()) {
            aveDePresa.pinta(canvas);
        }
        canvas.mostrar(0);

    }

    public void mover() {
        //Le damos a la entrada el canvas que estamos usando
        e.setMiCanvas(canvas);
        //movemos las naves
        if (rebelde.isVisible()) {
            rebelde.Mueve(e);
        }
        if (destructor.isVisible()) {
            destructor.Mueve(e);
        }
        if (aveDePresa.isVisible()) {
            aveDePresa.Mueve(e);
        }
        if (bombardero.isVisible()) {
            bombardero.Mueve(e);
        }
        //COLISION ENTRE DESTRUCTOR Y REBELDE
        // COLISIÓN: La bala del destructor le dio al héroe
        //  Solo checar si la bala viene en VUELO y existe la colision
        if (rebelde.isVisible()) {
            if (destructor.getBala().getEstado() == Bala.VUELO
                    && rebelde.hayColision(destructor.getBala())) {
                rebelde.perderVida();
                rebelde.recibirDanio();
                rebelde.destruir(rebelde.isDanioFatal());
                System.out.println("¡Impacto al Héroe!");
                // Hacemos que la bala desaparezca inmediatamente
                //(o pase a EXPLOTANDO)
                destructor.getBala().setEstado(Bala.INACTIVA);
                //  rebelde.restarVida();
                // Fin del juego si se acaban las vidas
            }
        }
        // COLISIÓN INVERSA: La bala del héroe le dio al destructor
        if (destructor.isVisible()) {
            if (rebelde.getBala().getEstado() == Bala.VUELO
                    && destructor.hayColision(rebelde.getBala())) {
                destructor.perderVida();
                destructor.recibirDanio();
                destructor.destruir(destructor.isDanioFatal());
                //Destruir la nave
                System.out.println("¡Enemigo destruido!");
                //heroe.ponpuntos(destructor.getpuntos)
                rebelde.getBala().setEstado(Bala.INACTIVA);
                rebelde.ponPuntos(destructor.getPuntos());
                //Revisa que este destruido
                if (destructor.recibirDanio()) {
                    System.out.println("¡Destructor destruido! Reapareciendo...");
                    // ¡ACTIVAMOS EL RETORNO!
                    destructor.reaparecer();
                }

            }
        }
        //COLISION ENTRE AVEDEPRESA Y REBELDE
        // COLISIÓN: La bala del destructor le dio al héroe
        //  Solo checar si la bala viene en VUELO y existe la colision
        if (rebelde.isVisible()) {
            if (aveDePresa.getBala().getEstado() == Bala.VUELO
                    && rebelde.hayColision(aveDePresa.getBala())) {
                rebelde.perderVida();
                rebelde.recibirDanio();
                rebelde.destruir(rebelde.isDanioFatal());
                // Fin del juego si se acaban las vidas
                System.out.println("¡Impacto al Héroe!");
                // Hacemos que la bala desaparezca inmediatamente
                //(o pase a EXPLOTANDO)
                aveDePresa.getBala().setEstado(Bala.INACTIVA);

            }
        }
        // COLISIÓN INVERSA: La bala del héroe le dio al destructor
        if (aveDePresa.isVisible()) {
            if (rebelde.getBala().getEstado() == Bala.VUELO
                    && aveDePresa.hayColision(rebelde.getBala())) {
                aveDePresa.perderVida();
                aveDePresa.recibirDanio();
                aveDePresa.destruir(aveDePresa.isDanioFatal());
                System.out.println("¡Enemigo destruido!");

                rebelde.getBala().setEstado(Bala.INACTIVA);
                //Destruir la nave
                if (aveDePresa.recibirDanio()) {
                    System.out.println("¡aveDePresa destruido! Reapareciendo...");
                    // ¡ACTIVAMOS EL RETORNO!
                    aveDePresa.reaparecer();
                }
            }
            //COLISION ENTRE BOMBARDERO Y REBELDE
            // COLISIÓN: La bala del destructor le dio al héroe
            //  Solo checar si la bala viene en VUELO y existe la colision
            if (rebelde.isVisible()) {
                if (bombardero.getBala().getEstado() == Bala.VUELO
                        && rebelde.hayColision(bombardero.getBala())) {
                    rebelde.perderVida();
                    rebelde.recibirDanio();
                    rebelde.destruir(rebelde.isDanioFatal());
                    System.out.println("¡Impacto al Héroe!");
                    // Hacemos que la bala desaparezca inmediatamente
                    //(o pase a EXPLOTANDO)
                    bombardero.getBala().setEstado(Bala.INACTIVA);
                    //  rebelde.restarVida();
                    // Fin del juego si se acaban las vidas
                }
            }
            // COLISIÓN INVERSA: La bala del héroe le dio al destructor
            if (bombardero.isVisible()) {
                if (rebelde.getBala().getEstado() == Bala.VUELO
                        && bombardero.hayColision(rebelde.getBala())) {
                    bombardero.perderVida();
                    bombardero.recibirDanio();
                    bombardero.destruir(bombardero.isDanioFatal());
                    System.out.println("¡Enemigo destruido!");
                    rebelde.getBala().setEstado(Bala.INACTIVA);
                    if (bombardero.recibirDanio()) {
                        System.out.println("¡aveDePresa destruido! Reapareciendo...");
                        // ¡ACTIVAMOS EL RETORNO!
                        bombardero.reaparecer();
                    }
                }
            }

        }
    }
}
