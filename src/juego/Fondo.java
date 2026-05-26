package juego;

import edu.epromero.util.Imagen;
import edu.epromero.util.Lienzo;
import java.awt.Color;
import java.awt.Font;

/**
 *
 * @author user
 */
public class Fondo extends ElementoGrafico {

    private Imagen fondo;
    private Imagen menu;
    private Imagen gameover;

    public Fondo() {
        setNomSprite("/resources/Fondo.png");
        // Cargamos la imagen UNA SOLA VEZ aquí
        inicia();
        this.fondo = getMiImagen();
        setNomSprite("/resources/menu.png");
        inicia();
        this.menu = getMiImagen();
        setNomSprite("/resources/over.png");
        inicia();
        this.gameover = getMiImagen();
    }

    public void pinta(Heroe heroe, Lienzo canvas) {
        setCanvas(canvas);

        setCanvas(canvas);
        // Dibujamos la imagen del menú en la esquina superior izquierda (0,0)
        getCanvas().dibujo(970, 500, fondo);
        //Pinta vidas
        Font fuente = new Font("Arial", Font.BOLD, 36);
        canvas.ponFuente(fuente);
        canvas.ponColorLapiz(Color.BLACK);
        canvas.texto(canvas.pideLimiteXMax() - 260, canvas.pideLimiteYMin()
                + 160, "Vidas: " + heroe.getVidasActuales());

        //pinta puntos
        canvas.ponFuente(fuente);
        canvas.ponColorLapiz(Color.BLACK);
        canvas.texto(canvas.pideLimiteXMin() + 300, canvas.pideLimiteYMin()
                + 160, "Puntaje: " + heroe.getPuntosObtenidos());
    }

    @Override
    public void Mueve(Entrada e) {

    }

    public void gameOver(Heroe heroe, Lienzo canvas) {
        setCanvas(canvas);
        // Dibujamos la imagen del menú en la esquina superior izquierda (0,0)
        getCanvas().dibujo(970, 400, gameover);

        // 1. Limpiamos dibujando el fondo negro o el tapiz de nuevo
        // 2. Calculamos los centros exactos del mapa
        double centroX = (canvas.pideLimiteXMin() + canvas.pideLimiteXMax() / 2);
        double centroY = (canvas.pideLimiteYMin() + canvas.pideLimiteYMax()) / 2;

        // 3. Letrero de GAME OVER
        Font fuenteGameOver = new Font("Arial", Font.BOLD, 72);
        canvas.ponFuente(fuenteGameOver);
        canvas.ponColorLapiz(Color.red);
        canvas.texto(centroX - 300, centroY + 50, "GAME OVER"); // Un pequeño ajuste en X para centrar el texto largo

        // 4. Mostrar el puntaje final obtenido abajo del letrero
        Font fuentePuntos = new Font("Arial", Font.BOLD, 36);
        canvas.ponFuente(fuentePuntos);
        canvas.ponColorLapiz(Color.BLACK);
        canvas.texto(centroX - 300, centroY - 50, "Puntaje Final: " + heroe.getPuntosObtenidos());
        canvas.mostrar(0);
    }

    public void menu(Heroe heroe, Lienzo canvas) {
        setCanvas(canvas);
        // Dibujamos la imagen del menú en la esquina superior izquierda (0,0)
        getCanvas().dibujo(970, 600, menu);

        double centroX = (canvas.pideLimiteXMin() + canvas.pideLimiteXMax()) / 2;
        double centroY = (canvas.pideLimiteYMin() + canvas.pideLimiteYMax()) / 2;

        // Título Principal
        Font fuenteTitulo = new Font("Impact", Font.BOLD, 80);
        canvas.ponFuente(fuenteTitulo);
        canvas.ponColorLapiz(Color.CYAN);
        canvas.texto(centroX + 530, centroY + 20, "SNOOPY ");
        canvas.texto(centroX + 430, centroY - 100, "ADVENTURES");

    }

    public void alertainicio(Lienzo canvas) {
        double centroX = (canvas.pideLimiteXMin() + canvas.pideLimiteXMax()) / 2;
        double centroY = (canvas.pideLimiteYMin() + canvas.pideLimiteYMax()) / 2;

        java.awt.Font fuentePrep = new java.awt.Font("Impact", java.awt.Font.PLAIN, 36);
        canvas.ponFuente(fuentePrep);
        canvas.ponColorLapiz(java.awt.Color.BLACK);
        canvas.texto(centroX, centroY, "¡PREPÁRATE! PRESIONA ESPACIO PARA DISPARAR");
    }

    public void alertadanio(Heroe heroe, Lienzo canvas) {
        Font fuentePuntos = new Font("Arial", Font.BOLD, 36);
        canvas.ponFuente(fuentePuntos);
        if (heroe.getVidasActuales() == 2) {
            canvas.ponColorLapiz(Color.yellow);
            canvas.texto(canvas.pideLimiteXMax() - 300, canvas.pideLimiteYMin()
                    + 220, "¡CUIDADO!");
        }
        if (heroe.getVidasActuales() == 1) {
            canvas.ponColorLapiz(Color.red);
            canvas.texto(canvas.pideLimiteXMax() - 300, canvas.pideLimiteYMin()
                    + 220, "¡¡PELIGRO!!");

        }
    }

    public void pintaanuncio(Lienzo canvas) {
        double centroX = (canvas.pideLimiteXMin() + canvas.pideLimiteXMax()) / 2;
        double centroY = (canvas.pideLimiteYMin() + canvas.pideLimiteYMax()) / 2;
        // Subtítulo parpadeante / Instrucción
        Font fuenteInstruccion = new Font("Arial", Font.BOLD, 28);
        canvas.ponFuente(fuenteInstruccion);
        canvas.ponColorLapiz(Color.RED);
        canvas.texto(centroX + 530, centroY - 190, "PRESIONA F2");
        canvas.texto(centroX + 530, centroY - 240, "PARA INICIAR");

        canvas.mostrar(0);
    }

    public void textoReaparicion(Lienzo canvas) {
        // Mostrar texto de reaparicion
        double centroX = (canvas.pideLimiteXMin() + canvas.pideLimiteXMax()) / 2;
        double centroY = (canvas.pideLimiteYMin() + canvas.pideLimiteYMax()) / 2;
        java.awt.Font fuenteReiniciar = new java.awt.Font("Arial", java.awt.Font.BOLD, 24);
        canvas.ponFuente(fuenteReiniciar);
        canvas.ponColorLapiz(java.awt.Color.yellow);
        canvas.texto(centroX - 300, centroY - 150, "PRESIONA F2 PARA VOLVER AL MENÚ");
    }
}
