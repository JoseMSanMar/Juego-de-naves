/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package juego;

import edu.epromero.util.Imagen;
import edu.epromero.util.Lienzo;

/**
 *
 * @author user
 */
public class Fondo extends ElementoGrafico {

    private Imagen imagenFondo;

    public Fondo() {
        setNomSprite(".\\src\\Fondo.png");
        // Cargamos la imagen UNA SOLA VEZ aquí
        this.imagenFondo = new Imagen(getNomSprite());
        this.imagenFondo.ponColorTransparente(Lienzo.BLANCO);
    }

    public void pinta(Lienzo canvas) {
        this.canvas = canvas;

        // ELIMINAMOS: new Imagen, escalas y tamaños de aquí.
        // Solo dejamos el ciclo de dibujo
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                // Usamos la imagen que ya está cargada en memoria
                canvas.dibujo(i * 190, j * 100, this.imagenFondo);
            }
        }
    }

    @Override
    public void mueve(Entrada e) {

    }
}
