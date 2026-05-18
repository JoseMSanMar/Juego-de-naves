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
public class AveDePresa extends NaveEnemiga {

    private int componenteY;

    public AveDePresa() {

        super();
        setNomSprite(".\\src\\avepresa.png"); // Pon la ruta de tu imagen enemiga
        this.miImagen = new Imagen(getNomSprite());
        this.miImagen.ponColorTransparente(Lienzo.BLANCO);
        //Se inicia la direccion de la nave
        this.componenteY = ABAJO;
    }

    @Override
    public void mueve(Entrada e) {
        //Averiguar si se sale por la derecha
        if (y > (e.getMiCanvas().pideLimiteYMax()) - 120)//cambiar el sentido
        {
            setComponenteY(ABAJO);
        }
        //Averiguar si se sale por la izq
        if (y < (e.getMiCanvas().pideLimiteYMin()) + 120) {
            setComponenteY(ARRIBA);
        }
        y = y + getComponenteY() * 30;
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
