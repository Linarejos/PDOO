/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juegoTexto;

import civitas.CivitasJuego;
import civitas.GestionesInmobiliarias;
import civitas.OperacionInmobiliaria;
import civitas.OperacionesJuego;
import civitas.SalidasCarcel;

/**
 * @author elena
 * @date 25/10/2019
 */

public class Controlador {
    private CivitasJuego juego;
    private VistaTextual vista;
    
    Controlador(CivitasJuego juego, VistaTextual vista){
        this.juego = juego;
        this.vista = vista;
    }
    
    void juega(){
        vista.setCivitasJuego(juego);   //No se si esto es asi
        
        while(!juego.finalDelJuego()){
            vista.actualizarVista();
            vista.pausa();
            vista.mostrarSiguienteOperacion(juego.siguientePaso());
            
            OperacionesJuego siguiente = juego.siguientePaso();
            if(siguiente != OperacionesJuego.PASAR_TURNO){
                vista.mostrarEventos();
            }
            
            if(!juego.finalDelJuego()){
                switch(siguiente){
                    case COMPRAR:
                        Respuestas r = vista.comprar();
                        
                        if(r == Respuestas.SI){
                            boolean compra = juego.comprar();
                            
                            if(compra)
                                juego.siguientePasoCompletado(siguiente);                            
                        }
                    break;
                    case GESTIONAR:
                        vista.gestionar();
                        OperacionInmobiliaria operacion_inmo = new OperacionInmobiliaria(vista.getGestion(), vista.getPropiedad());      
                        if(operacion_inmo.getGestion() == GestionesInmobiliarias.TERMINAR)
                            juego.siguientePasoCompletado(siguiente);
                    break;
                    case SALIR_CARCEL:                        
                        if(vista.salirCarcel() == SalidasCarcel.PAGANDO)
                            juego.salirCarcelPagando();                        
                        else if(vista.salirCarcel() == SalidasCarcel.TIRANDO)
                            juego.salirCarcelTirando();
                        
                        juego.siguientePasoCompletado(siguiente);
                    break;
                }
            }
            else{
                //mostrar ranking jugadores
            }
        }
    }
}
