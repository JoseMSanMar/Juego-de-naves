package juego;

import edu.epromero.util.Lienzo;
import static java.awt.event.KeyEvent.VK_F2;
import static java.awt.event.KeyEvent.VK_SPACE;

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
    //Estados del juego
    public static final int ESTADO_MENU = 0;
    public static final int ESTADO_JUGANDO = 1;
    public static final int ESTADO_GAMEOVER = 2;
    private int estadoActual;
    private boolean partidaIniciada;
    private int framesParpadeo = 0;
    private int framesParpadeonor = 0;

    public Juego() {
        this.estadoActual = ESTADO_MENU;
        this.partidaIniciada = false;
        e = new Entrada();
        //Creamos el lienzo
        canvas = new Lienzo();

        // CONFIGURAMOS EL LIENZO
        canvas.ponTamanioLienzo(1200, 650);
        canvas.ponEscalaX(0, 2000);
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

        if (estadoActual == ESTADO_MENU) {
            pantalla.menu(rebelde, canvas);
            if (framesParpadeonor % 20 < 10) {
                pantalla.pintaanuncio(canvas);
            }
            canvas.mostrar(0);
            return;
        }
        if (!rebelde.isVisible()) {
            pantalla.gameOver(rebelde, canvas);
            if (framesParpadeonor % 20 < 10) {
                pantalla.textoReaparicion(canvas);
            }
            canvas.mostrar(0);
            return;
        }
        //pintamos el lienzo canvas
        pantalla.pinta(rebelde, canvas);
        if (framesParpadeonor % 20 < 10) {
            pantalla.alertadanio(rebelde, canvas);
        }
        if (estadoActual == ESTADO_JUGANDO && !partidaIniciada) {
            if (framesParpadeo % 30 < 20) {
                pantalla.alertainicio(canvas);

            }
        }
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

    public void mover() {
        // Le damos a la entrada el canvas que estamos usando
        e.setMiCanvas(canvas);
        // CONTROLLER DE ESTADOS
        //*****CONTROL DE REAPARICION*****
        if (!rebelde.isVisible()) {
            framesParpadeonor++;
            // Si presiona F2 en la pantalla de Game Over, limpia el tablero
            if (e.getMiCanvas().fuePulsadaTecla(VK_F2)) {
                reiniciarPartida();
            }
            return; // Bloquea el resto del juego mientras estemos muertos
        }
        if (estadoActual == ESTADO_MENU) {
            framesParpadeonor++;
            // SI ESTAMOS EN EL MENÚ: No movemos nada, solo esperamos la tecla de inicio.
            // NOTA: Ajusta "e.isSeleccion()" o "e.pideTecla()" según los métodos reales de tu clase Entrada
            if (e.getMiCanvas().fuePulsadaTecla(VK_F2)) {
                this.estadoActual = ESTADO_JUGANDO; // Cambiamos el switch para iniciar la acción
                this.partidaIniciada = false; // Nos aseguramos de que espere el disparo
            }
            return; // Rompemos el método aquí para que no se mueva ninguna nave de fondo
        }

        //******CONTROL DE PREPARACION DE JUEGO******
        framesParpadeonor++;
        if (estadoActual == ESTADO_JUGANDO && !partidaIniciada) {
            framesParpadeo++;
            // El héroe se puede mover lateralmente para posicionarse antes de la guerra
            if (rebelde.isVisible()) {
                rebelde.Mueve(e);
            }

            // Si el jugador presiona la Barra Espaciadora (VK_SPACE), dispara y activa el juego
            if (e.getMiCanvas().fuePulsadaTecla(VK_SPACE)) {
                this.partidaIniciada = true; // ¡Se desata la invasión enemiga!
            }

            return; // Bloquea todo lo de abajo (las naves no se mueven ni disparan todavía)
        }
        //******Estado de juego********
        //actualiza el tiempo de espera de la naves
        kami.actualizarTemporizador();
        bombardero.actualizarTemporizador();
        // Movemos las naves
        //movemos rebelde
        if (rebelde.isVisible()) {
            rebelde.Mueve(e);
            if (rebelde.getBala() != null) {
                rebelde.getBala().Mueve(e);
            }
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
                    if (rebelde.isDanioFatal()) {
                        balasDestructor[i].setEstado(Bala.EXPLOTANDO);
                    } else {
                        balasDestructor[i].setEstado(Bala.INACTIVA);
                    }
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
                    if (rebelde.isDanioFatal()) {
                        balasAve[i].setEstado(Bala.EXPLOTANDO);
                    } else {
                        balasAve[i].setEstado(Bala.INACTIVA);
                    }
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
                    if (rebelde.isDanioFatal()) {
                        balasBombardero[i].setEstado(Bala.EXPLOTANDO);
                    } else {
                        balasBombardero[i].setEstado(Bala.INACTIVA);
                    }
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
            // Héroe -> Destructor
            if (destructor.isVisible()
                    && destructor.hayColision(rebelde.getBala())) {
                rebelde.getBala().setEstado(Bala.INACTIVA);
                //revisa si la nave fue destruida y almacena los puntos
                destructor.recibirDanio();
                //reaparece la nave
                if (destructor.isDanioFatal()) {
                    rebelde.getBala().setEstado(Bala.EXPLOTANDO);
                    rebelde.ponPuntos(destructor.getPuntos());
                    destructor.reaparecer();
                }
            }
            // Héroe -> AveDePresa
            if (aveDePresa.isVisible()
                    && aveDePresa.hayColision(rebelde.getBala())) {
                rebelde.getBala().setEstado(Bala.INACTIVA);
                //revisa si la nave fue destruida y almacena los puntos
                aveDePresa.recibirDanio();
                //reaparece la nave
                if (aveDePresa.isDanioFatal()) {
                    rebelde.getBala().setEstado(Bala.EXPLOTANDO);
                    rebelde.ponPuntos(aveDePresa.getPuntos());
                    aveDePresa.reaparecer();
                }
            }
            // Héroe -> Bombardero (LanzaMinas)
            if (bombardero.isVisible()
                    && bombardero.hayColision(rebelde.getBala())) {
                rebelde.getBala().setEstado(Bala.INACTIVA);
                //revisa si la nave fue destruida y almacena los puntos
                bombardero.recibirDanio();
                //reaparece la nave
                if (bombardero.isDanioFatal()) {
                    rebelde.getBala().setEstado(Bala.EXPLOTANDO);
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
                    rebelde.getBala().setEstado(Bala.EXPLOTANDO);
                    rebelde.ponPuntos(kami.getPuntos());
                    kami.iniciarEsperaReaparicion();
                }
            }
        }
    }

    public void reiniciarPartida() {
        //Devolvemos el estado al Menú Principal y congelamos la física
        this.estadoActual = ESTADO_MENU;
        this.partidaIniciada = false;

        //Revivimos al Héroe y restauramos sus estadísticas a cero
        rebelde.setVisible(true);
        rebelde.setVidas(3);            // Tus 3 vidas iniciales
        rebelde.setPuntosObtenidos(0);   // Marcador en cero
        rebelde.iniciarPosicion(canvas);

        //¡LIMPIEZA DE BALAS!
        // Apagamos por completo la bala del héroe para que no flote al iniciar
        if (rebelde.getBala() != null) {
            rebelde.getBala().setEstado(Bala.INACTIVA);
            // La mandamos a zona neutral
            rebelde.getBala().iniciarPosicion(0, 0, 0, 0);
        }

        // Limpiamos el cargador del Destructor
        if (destructor != null) {
            destructor.iniciarPosicion(canvas);
            destructor.setVisible(true);

            // Recorremos su arreglo de balas y las desactivamos todas
            Bala[] balasDestructor = destructor.getCargador();
            if (balasDestructor != null) {
                for (int i = 0; i < balasDestructor.length; i++) {
                    balasDestructor[i].setEstado(Bala.INACTIVA);
                    //Teletransportamos la bala al origen para que no spawnee sobre el héroe
                    balasDestructor[i].iniciarPosicion(0, 0, 0, 0);
                }
            }
        }

        // Limpiamos el cargador del Ave de Presa
        if (aveDePresa != null) {
            aveDePresa.iniciarPosicion(canvas);
            aveDePresa.setVisible(true);

            Bala[] balasAve = aveDePresa.getCargador();
            if (balasAve != null) {
                for (int i = 0; i < balasAve.length; i++) {
                    // Evita el spawn kill
                    balasAve[i].setEstado(Bala.INACTIVA);
                    balasAve[i].iniciarPosicion(0, 0, 0, 0);
                }
            }
        }

        // Limpiamos el cargador del Bombardero (LanzaMinas)
        if (bombardero != null) {
            bombardero.iniciarPosicion(canvas);
            bombardero.setVisible(true);

            Bala[] balasBombardero = bombardero.getCargador();
            if (balasBombardero != null) {
                for (int i = 0; i < balasBombardero.length; i++) {
                    // Evita el spawn kill
                    balasBombardero[i].setEstado(Bala.INACTIVA);
                    balasBombardero[i].iniciarPosicion(0, 0, 0, 0);
                }
            }
        }

        // El Kamikaze es reseteado en su esquina externa
        if (kami != null) {
            kami.iniciarPosicion(canvas);
            kami.setVisible(true);
        }

        System.out.println("¡Universo reseteado por completo!");
    }
}
