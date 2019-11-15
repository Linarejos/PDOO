/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juegoTexto;

import civitas.CivitasJuego;
import civitas.Dado;
import java.util.ArrayList;

/**
 * @author elena
 * @date 25/10/2019
 */
public class JuegoTexto {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        VistaTextual mivista = new VistaTextual();
        
        ArrayList<String> nombres = new ArrayList();
        nombres.add("Elena");
        nombres.add("Pepe");
        nombres.add("Juan");
        nombres.add("Laura");
        
        CivitasJuego mijuego = new CivitasJuego(nombres);
        
        Dado dado = Dado.getInstance();
        //Modificada la visibilidad del debug del dado
        dado.setDebug(true);
        
        Controlador con = new Controlador(mijuego, mivista);
        
        con.juega();
    }
    
}
