/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

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
    private CivitasView vista;
    
    Controlador(CivitasJuego juego, CivitasView vista){
        this.juego = juego;
        this.vista = vista;
    }
    
    void juega(){
        vista.setCivitasJuego(juego);
        
        while(!juego.finalDelJuego()){
            vista.actualizarVista();
            //vista.pausa();           
            OperacionesJuego siguiente = juego.siguientePaso();
            
            vista.mostrarSiguienteOperacion(siguiente);
            if(siguiente != OperacionesJuego.PASAR_TURNO){
                vista.mostrarEventos();
            }
            
            if(!juego.finalDelJuego()){
                switch(siguiente){
                    case COMPRAR:
                        Respuestas r = vista.comprar();
                        
                        if(r == Respuestas.SI){
                            juego.comprar();                                                          
                        }
                        
                        juego.siguientePasoCompletado(siguiente);  
                        
                    break;
                    case GESTIONAR:
                        vista.gestionar();
                        OperacionInmobiliaria operacion_inmo = new OperacionInmobiliaria(GestionesInmobiliarias.values()[vista.getGestion()], vista.getPropiedad());     
                        
                        switch(operacion_inmo.getGestion()){
                            case TERMINAR:
                                juego.siguientePasoCompletado(siguiente);
                            break;
                            case CANCELAR_HIPOTECA:
                                juego.cancelarHipoteca(operacion_inmo.getNumPropiedad());
                            break;
                            case CONSTRUIR_CASA:
                                juego.construirCasa(operacion_inmo.getNumPropiedad());
                            break;
                            case CONSTRUIR_HOTEL:
                                juego.construirHotel(operacion_inmo.getNumPropiedad());
                            break;
                            case HIPOTECAR:
                                juego.hipotecar(operacion_inmo.getNumPropiedad());
                            break;
                            case VENDER:
                                juego.vender(operacion_inmo.getNumPropiedad());
                            break;
                        }
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
                //He cambiado la visibilidad del ranking de jugadores
                juego.ranking();
            }
        }
    }
}
