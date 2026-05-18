/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package juego;

import edu.epromero.util.Imagen;
import edu.epromero.util.Lienzo;
import static juego.ElementoGrafico.DERECHA;

/**
 *
 * @author user
 */
public class LanzaMinas extends NaveEnemiga {

    private int componenteX;
    private int componenteY;

    public LanzaMinas() {
        super();
        setNomSprite(".\\src\\LanzaMinas.png"); // Pon la ruta de tu imagen enemiga
        this.miImagen = new Imagen(getNomSprite());
        this.miImagen.ponColorTransparente(Lienzo.BLANCO);
        //Se inicia la direccion de la nave
        this.componenteX = DERECHA;
    }

    @Override
    public void iniciarPosicion(Lienzo canvas) {
        this.canvas = canvas;
        Random();
        x = posRanX;
        y = this.canvas.pideLimiteYMax() - 60;
    }

    @Override
    public void mueve(Entrada e) {
        //Averiguar si se sale por la derecha
        if (x > (e.getMiCanvas().pideLimiteXMax()) - 120)//cambiar el sentido
        {
            setComponenteX(IZQUIERDA);
        }
        //Averiguar si se sale por la izq
        if (x < (e.getMiCanvas().pideLimiteXMin()) + 120) {
            setComponenteX(DERECHA);
        }
        x = x + componenteX * 30;
    }

    /**
     * @return the componenteX
     */
    public int getComponenteX() {
        return componenteX;
    }

    /**
     * @param componenteX the componenteX to set
     */
    public void setComponenteX(int componenteX) {
        this.componenteX = componenteX;
    }

    /**
     * @return the componenteY
     */
    public int getComponenteY() {
        return componenteY;
    }

    /**
     * @param componenteY the componenteY to set
     */
    public void setComponenteY(int componenteY) {
        this.componenteY = componenteY;
    }

}
