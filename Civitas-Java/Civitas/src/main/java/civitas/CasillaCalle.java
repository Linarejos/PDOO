/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package civitas;

import java.util.ArrayList;

/**
 * @author elena
 * @date 29/11/2019
 */
public class CasillaCalle extends Casilla{
    private TituloPropiedad tituloPropiedad = null;
    
    CasillaCalle(TituloPropiedad titulo){ 
        super(titulo.getNombre());
        tituloPropiedad = titulo;
    }
    
    TituloPropiedad getTituloPropiedad(){ return this.tituloPropiedad; }
    
    @Override
    void recibeJugador(int iactual, ArrayList<Jugador> todos){
        if(jugadorCorrecto(iactual, todos)){
           super.informe(iactual, todos);
           Jugador jugador = todos.get(iactual);
            
           if(!tituloPropiedad.tienePropietario())
                jugador.puedeComprarCasilla();
           else
                tituloPropiedad.tramitarAlquiler(jugador);
        }
    } 
    
    @Override
    public String toString(){
        return "Nombre: " + this.getTituloPropiedad().getNombre() +
               "\nTipo Casilla: CALLE";
    }
}
