package juego;

import edu.epromero.util.Destruible;
import edu.epromero.util.FabricaAudio;
import edu.epromero.util.Lienzo;

/**
 *
 * @author user
 */
public class NaveEnemiga extends ElementoGrafico implements Destruible {

    /**
     * @return the miImagen
     */
    private double posRanX;
    private double posRanY;
    private int puntos;
    private Bala[] cargador;
    private boolean danioFatal;
    private int vidas;
    private boolean esperandoReaparecer = false;
    private int framesDeEspera = 0;

    public NaveEnemiga() {
        super();
        setNomSprite("/resources/enemigo.png");
        inicia();
        getMiImagen().ponColorTransparente(Lienzo.BLANCO);
        cargador = new Bala[3];
        for (int i = 0; i < cargador.length; i++) {
            cargador[i] = new Bala();
        }
        setVisible(false);
    }

    public void pinta(Lienzo canvas) {
        setCanvas(canvas);
        canvas.dibujo(getColumna(), getRenglon(), this.getMiImagen());

        // Ciclo tradicional: recorremos desde 0 hasta el límite del cargador
        Bala[] listaBalas = getCargador();
        for (int i = 0; i < listaBalas.length; i++) {
            if (listaBalas[i].getEstado() != Bala.INACTIVA) {
                listaBalas[i].pinta(getCanvas());
            }
        }
    }

    @Override
    public boolean recibirDanio() {
        setDanioFatal(false);
        if (getVidasActuales() - 1 <= 0) {
            perderVida();
            setDanioFatal(true);
            if (isDanioFatal()) {
                FabricaAudio miAudio = new FabricaAudio();
                miAudio.reproducir(".//src//explosion.wav");
                setVisible(false);
            }
        } else {
            perderVida();
        }

        return isDanioFatal();
    }

    public void perderVida() {
        setVidas(getVidasActuales() - 1);
    }

    public void reaparecer() {
        //Se vuelve a iniciar la nave
        Random();
        FabricaAudio miAudio = new FabricaAudio();
        miAudio.reproducir(".//src//avion.wav");
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
        // --- LÍMITES PARA EL EJE X (Columnas) ---
        // Queremos que aparezca entre el límite mínimo y el límite máximo
        double minX = getCanvas().pideLimiteXMin() + 180;
        double maxX = getCanvas().pideLimiteXMax() - 180;
        double xAleatoria = minX + (Math.random() * (maxX - minX));
        setPosRanX(xAleatoria);

        // --- LÍMITES PARA EL EJE Y (Renglones) ---
        // Para que aparezcan en la mitad SUPERIOR de la pantalla:
        double minY = getCanvas().pideLimiteYMax() / 2;
        double maxY = getCanvas().pideLimiteYMax() - 180;
        double yAleatoria = minY + (int) (Math.random() * (maxY - minY));
        setPosRanY(yAleatoria);
    }

    public void iniciarPosicion(Lienzo canvas) {
        setCanvas(canvas);
        Random();
        setColumna(getPosRanX());
        setRenglon(getPosRanY());
        aparecer();
    }

    public void iniciarEsperaReaparicion() {
        this.esperandoReaparecer = true;
        this.framesDeEspera = 60;
        // Nos aseguramos de que no se vea ni colisione
        setVisible(false);
    }

    // Método que actualiza el reloj en cada frame
    public void actualizarTemporizador() {
        if (esperandoReaparecer) {
            framesDeEspera--; // Restamos un frame en cada ciclo

            if (framesDeEspera <= 0) {
                esperandoReaparecer = false;
                // ¡Despierta y se posiciona arriba automáticamente!
                reaparecer();
            }
        }
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
    public double getPosRanX() {
        return posRanX;
    }

    /**
     * @param posRanX the posRanX to set
     */
    public void setPosRanX(double posRanX) {
        this.posRanX = posRanX;
    }

    /**
     * @return the posRanY
     */
    public double getPosRanY() {
        return posRanY;
    }

    /**
     * @param posRanY the posRanY to set
     */
    public void setPosRanY(double posRanY) {
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

    /**
     * @return the cargador
     */
    public Bala[] getCargador() {
        return cargador;
    }

    public boolean isEsperandoReaparecer() {
        return esperandoReaparecer;
    }
}
