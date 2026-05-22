package juego;

import edu.epromero.util.Destruible;
import edu.epromero.util.Lienzo;

/**
 *
 * @author user
 */
public class NaveEnemiga extends ElementoGrafico implements Destruible {

    /**
     * @return the miImagen
     */
    private int posRanX;
    private int posRanY;
    private int puntos;
    private Bala bala;
    private boolean danioFatal;
    private int vidas;

    public NaveEnemiga() {
        super();
        setNomSprite("/resources/enemigo.png");
        inicia();
        getMiImagen().ponColorTransparente(Lienzo.BLANCO);
        bala = new Bala();

        aparecer();
    }

    public void pinta(Lienzo canvas) {
        //asignamos el canvas a local
        setCanvas(canvas);
        //if(isVisible()){
        // dibujamos la NaveEnemiga
        canvas.dibujo(getColumna(), getRenglon(), this.getMiImagen());
        //Revisamos si la bala esta activa
        if (getBala().getEstado() != Bala.INACTIVA) {
            //Se dibuja la bala
            getBala().pinta(getCanvas());
        }
    }

    @Override
    public boolean recibirDanio() {
        if (getVidasActuales() > 0) {
            setDanioFatal(false);
        } else {
            setDanioFatal(true);
        }
        return isDanioFatal();
    }

    public void perderVida() {
        setVidas(getVidasActuales() - 1);
    }

    public void destruir(boolean danio) {
        if (danio) {
            setVisible(false);
        }
    }

    public void reaparecer() {
        //Se vuelve a iniciar la nave
        Random();

        //Posición inicial: X aleatoria, pero Y ARRIBA del límite del techo
        setColumna(getPosRanX());
        setRenglon(getCanvas().pideLimiteYMax() + 100);

        //Restauramos la salud y visibilidad de la nave según su tipo
        setVisible(true);
        setDanioFatal(false);

        // Aquí le devolvemos la vida base.
        // De forma generica se se asigna su vida de acuerdo a los puntos
        if (getPuntos() == 50) {
            setVidas(2);  // Destructor
        }
        if (getPuntos() == 100) {
            setVidas(2);  // AveDePresa
        }
        if (getPuntos() == 150) {
            setVidas(4);  // LanzaMinas
        }
    }

    public void Random() {
        int buffer;
        //ciclo para hacer random a @param renglon
        do {
            buffer = (int) (Math.random() * (getCanvas().pideLimiteYMax()
                    - getCanvas().pideLimiteYMin()));
        } while (buffer < getCanvas().pideLimiteYMax() / 2
                && buffer < (getCanvas().pideLimiteYMax() - 220));
        setPosRanY(buffer);
        //ciclo para hacer random a @param columna
        do {
            buffer = (int) (Math.random() * (getCanvas().pideLimiteXMax()
                    - getCanvas().pideLimiteXMin()));
        } while (buffer > getCanvas().pideLimiteXMax()
                && buffer < (getCanvas().pideLimiteXMax()));
        setPosRanX(buffer);
    }

    public void iniciarPosicion(Lienzo canvas) {
        setCanvas(canvas);
        Random();
        setColumna(getPosRanX());
        setRenglon(getPosRanY());
        aparecer();
    }

    @Override
    public void Mueve(Entrada e) {
        //if(isVisible()){
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

    public Bala getBala() {
        return bala;
    }

    /**
     * @param bala the bala to set
     */
    public void setBala(Bala bala) {
        this.bala = bala;
    }

    /**
     * @return the vidas
     */
    public int getVidasActuales() {
        return vidas;
    }

    /**
     * @param vidas the vidas to set
     */
    public void setVidas(int vidas) {
        this.vidas = vidas;
    }

    /**
     * @return the posRanX
     */
    public int getPosRanX() {
        return posRanX;
    }

    /**
     * @param posRanX the posRanX to set
     */
    public void setPosRanX(int posRanX) {
        this.posRanX = posRanX;
    }

    /**
     * @return the posRanY
     */
    public int getPosRanY() {
        return posRanY;
    }

    /**
     * @param posRanY the posRanY to set
     */
    public void setPosRanY(int posRanY) {
        this.posRanY = posRanY;
    }

    /**
     * @return the danioFatal
     */
    public boolean isDanioFatal() {
        return danioFatal;
    }

    /**
     * @param danioFatal the danioFatal to set
     */
    public void setDanioFatal(boolean danioFatal) {
        this.danioFatal = danioFatal;
    }
}
