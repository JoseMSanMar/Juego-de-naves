package juego;

import edu.epromero.util.Lienzo;
import java.awt.Color;
import java.awt.Font;

/**
 *
 * @author user
 */
public class Fondo extends ElementoGrafico {

    public Fondo() {
        setNomSprite("/resources/Fondo.png");
        // Cargamos la imagen UNA SOLA VEZ aquí
        inicia();
    }

    public void pinta(Lienzo canvas) {
        setCanvas(canvas);

        // ELIMINAMOS: new Imagen, escalas renglon tamaños de aquí.
        // Solo dejamos el ciclo de dibujo
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                // Usamos la imagen que ya está cargada en memoria
                getCanvas().dibujo(i * 190, j * 100, getMiImagen());
            }
        }
    }

    @Override
    public void Mueve(Entrada e) {

    }

    public void pintaPuntos(Heroe heroe, Lienzo canvas) {
        Font fuente = new Font("Arial", Font.BOLD, 36);
        canvas.ponFuente(fuente);
        canvas.ponColorLapiz(Color.yellow);
        canvas.texto(canvas.pideLimiteXMin() + 120, canvas.pideLimiteYMin()
                + 40, "Puntaje: " + heroe.getPuntosObtenidos());
    }

    public void pintaVidas(Heroe heroe, Lienzo canvas) {
        Font fuente = new Font("Arial", Font.BOLD, 36);
        canvas.ponFuente(fuente);
        canvas.ponColorLapiz(Color.yellow);
        canvas.texto(canvas.pideLimiteXMax() - 120, canvas.pideLimiteYMin()
                + 40, "Vidas: " + heroe.getVidasActuales());
        if (heroe.getVidasActuales() == 2) {
            canvas.ponColorLapiz(Color.yellow);
            canvas.texto(canvas.pideLimiteXMax() - 140, canvas.pideLimiteYMin()
                    + 120, "¡CUIDADO!");
        }
        if (heroe.getVidasActuales() == 1) {
            canvas.ponColorLapiz(Color.red);
            canvas.texto(canvas.pideLimiteXMax() - 160, canvas.pideLimiteYMin()
                    + 80, "¡¡PELIGRO!!");
        }
    }

    public void gameOver(Heroe heroe, Lienzo canvas) {
        setCanvas(canvas);

        // 1. Limpiamos dibujando el fondo negro o el tapiz de nuevo
        pinta(canvas);

        // 2. Calculamos los centros exactos del mapa
        double centroX = (canvas.pideLimiteXMin() + canvas.pideLimiteXMax() / 2);
        double centroY = (canvas.pideLimiteYMin() + canvas.pideLimiteYMax()) / 2;

        // 3. Letrero de GAME OVER
        Font fuenteGameOver = new Font("Arial", Font.BOLD, 72);
        canvas.ponFuente(fuenteGameOver);
        canvas.ponColorLapiz(Color.red);
        canvas.texto(centroX + 50, centroY + 50, "GAME OVER"); // Un pequeño ajuste en X para centrar el texto largo

        // 4. Mostrar el puntaje final obtenido abajo del letrero
        Font fuentePuntos = new Font("Arial", Font.BOLD, 36);
        canvas.ponFuente(fuentePuntos);
        canvas.ponColorLapiz(Color.yellow);
        canvas.texto(centroX + 50, centroY - 50, "Puntaje Final: " + heroe.getPuntosObtenidos());
    }

}
