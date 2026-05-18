package juego;

import edu.epromero.util.Imagen;
import edu.epromero.util.Lienzo;

/**
 *
 * @author user
 */
public class NaveEnemiga extends ElementoGrafico {

    protected int posRanX;
    protected int posRanY;
    protected int puntos;

    public NaveEnemiga() {
        super();
        setNomSprite(".\\src\\enemigo.png");
        inicia();
        this.miImagen.ponColorTransparente(Lienzo.BLANCO);
    }

    public void pinta(Lienzo canvas) {
        this.canvas = canvas;
        canvas.dibujo(x, y, this.miImagen);
    }

    public void Random() {
        int buffer;
        int i;
        //ciclo para hacer random a @param y
        do {
            buffer = (int) (Math.random() * (this.canvas.pideLimiteYMax()
                    - this.canvas.pideLimiteYMin()));
        } while (buffer < this.canvas.pideLimiteYMax() / 2 && buffer < (this.canvas.pideLimiteYMax() - 220));
        posRanY = buffer;
        //ciclo para hacer random a @param x
        do {
            buffer = (int) (Math.random() * (this.canvas.pideLimiteXMax()
                    - this.canvas.pideLimiteXMin()));
        } while (buffer > this.canvas.pideLimiteXMax() && buffer < (this.canvas.pideLimiteXMax()));
        posRanX = buffer;
    }

    public void iniciarPosicion(Lienzo canvas) {
        this.canvas = canvas;
        Random();
        x = posRanX;
        y = posRanY;
    }

    @Override
    public void mueve(Entrada e) {

    }

    /**
     * @return the puntos
     */
    public int getPuntos() {
        return puntos;
    }

    /**
     * @param puntos the puntos to set
     */
    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }
}
