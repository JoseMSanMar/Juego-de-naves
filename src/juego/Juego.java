/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package juego;

import static java.awt.event.KeyEvent.*;
import edu.epromero.util.*;
import edu.epromero.util.Imagen;
import java.awt.Color;
import java.awt.Font;

/**
 *
 * @author user
 */
public class Juego {

    private Lienzo canvas;
    private Fondo pantalla;
    private Destructor destructor;
    private AveDePresa aveDePresa;
    private LanzaMinas bombardero;
    private Heroe rebelde;
    private Entrada e;

    public Juego() {
        e = new Entrada();
        // 1. Creamos el lienzo
        canvas = new Lienzo();

        // 2. CONFIGURAMOS EL LIENZO AQUÍ (
        canvas.ponTamanioLienzo(1950, 950);
        canvas.ponEscalaX(0, 1950);
        canvas.ponEscalaY(0, 950);

        // Inicializamos el resto de objetos
        //fondo
        pantalla = new Fondo();
        //Naves enemigas
        destructor = new Destructor();
        bombardero = new LanzaMinas();
        aveDePresa = new AveDePresa();
        //Nave Rebelde
        rebelde = new Heroe();
        rebelde.iniciarPosicion(canvas);
        destructor.iniciarPosicion(canvas);
        aveDePresa.iniciarPosicion(canvas);
        bombardero.iniciarPosicion(canvas);
    }

    public void pinta() {
        pantalla.pinta(canvas);
        bombardero.pinta(canvas);
        destructor.pinta(canvas);
        rebelde.pinta(canvas);
        aveDePresa.pinta(canvas);

        canvas.mostrar(0);

    }

    public void mover() {

        e.setMiCanvas(canvas);
        rebelde.mueve(e);
        destructor.mueve(e);
        aveDePresa.mueve(e);
        bombardero.mueve(e);
    }
}
