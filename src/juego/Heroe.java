/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package juego;

import edu.epromero.util.Imagen;
import edu.epromero.util.Lienzo;
import static java.awt.event.KeyEvent.VK_A;
import static java.awt.event.KeyEvent.VK_D;
import static java.awt.event.KeyEvent.VK_RIGHT;
import static java.awt.event.KeyEvent.VK_LEFT;
import static java.awt.event.KeyEvent.VK_SPACE;

/**
 *
 * @author Alumno
 */
public class Heroe extends ElementoGrafico {

    private Bala bala;

    public Heroe() {
        setNomSprite(".\\src\\Heroe.png");
        inicia();
        this.miImagen = miImagen;
        this.miImagen.ponColorTransparente(Lienzo.BLANCO);
        bala = new Bala();

    }

    public void pinta(Lienzo canvas) {
        this.canvas = canvas;
        canvas.dibujo(getX(), getY(), this.miImagen);
        if (bala.getEstado() != Bala.INACTIVA) {
            bala.pinta(canvas);
        }
    }

    public void iniciarPosicion(Lienzo canvas) {
        this.canvas = canvas;
        x = this.canvas.pideLimiteXMax() / 2;
        y = this.canvas.pideLimiteYMin() + 200;
    }

    @Override
    public void mueve(Entrada e) {
        if (e.getMiCanvas().fuePulsadaTecla(VK_LEFT) == true
                || e.getMiCanvas().fuePulsadaTecla(VK_A) == true) {
            if (x >= canvas.pideLimiteXMin() + 60) {
                //System.out.println("Tecla arriba pulsada antes: " + x);
                x = x - 20;
            }
            //System.out.println("Tecla arriba pulsada despues: " + x);
        }
        if (e.getMiCanvas().fuePulsadaTecla(VK_RIGHT) == true
                || e.getMiCanvas().fuePulsadaTecla(VK_D) == true) {
            if (x <= canvas.pideLimiteXMax() - 60) {
                //System.out.println("Tecla abajo pulsada: " + x);
                x = x + 20;
            }
        }
        // Lógica del disparo: Revisamos el gatillo
        dispara(e);

        // ACTUALIZACIÓN DE LA BALA: Si la bala está activa, avanza UN paso en este frame
        if (bala.getEstado() == Bala.VUELO) {
            bala.mueve(e);
        }
    }

    public void dispara(Entrada e) {
        if (e.getMiCanvas().fuePulsadaTecla(VK_SPACE)) {
            // Regla fundamental: Solo puedes disparar si no hay otra bala tuya en vuelo
            if (bala.getEstado() == Bala.INACTIVA) {
                // La bala nace JUSTO en la posición actual de la nave
                bala.iniciarPosicion(this.x, this.y);
                // Le damos luz verde para que empiece a volar
                bala.setEstado(Bala.VUELO);
            }
        }
    }
}
