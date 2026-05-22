package juego;

import edu.epromero.util.Lienzo;

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
}
