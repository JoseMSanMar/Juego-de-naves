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
    }

}
