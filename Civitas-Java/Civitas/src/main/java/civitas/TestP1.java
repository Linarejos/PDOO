/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package civitas;

import java.util.ArrayList;
import java.io.*;

/**
 *
 * @author elena
 */
public class TestP1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {  
        ArrayList<String> nombres = new ArrayList();
        nombres.add("Elena");
        nombres.add("Pepe");
        nombres.add("Juan");
        nombres.add("Laura");
        
        CivitasJuego juego = new CivitasJuego(nombres);
        
        System.out.print(juego.infoJugadorTexto());
        System.out.print("\n");
        //System.out.print(juego.getCasillaActual().toString());
        /*System.out.print("\n\n");
        System.out.print(juego.finalDelJuego());        
        System.out.print("\n");*/
        //juego.pasarTurno();
        //System.out.print(juego.vender(1));
        
    }
    
}
