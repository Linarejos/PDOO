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
public class SorpresaIRCARCEL extends Sorpresa{
    private Tablero tablero;
    
    SorpresaIRCARCEL (Tablero tablero){ 
        super(-1, "De camino a la carcel");
        this.tablero = tablero;
    }
    
    @Override
    void aplicarAJugador(int actual, ArrayList<Jugador> todos){       
        Jugador jugador = todos.get(actual);
        
        if(jugadorCorrecto(actual, todos)){
            super.informe(actual, todos);
            jugador.encarcelar(5);
        }
    }
    
    @Override
    public String toString(){
        return super.toString() + 
               "\nTipo Sorpresa: IRCARCEL";
    }
}
