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
public class SorpresaIRCASILLA extends Sorpresa{
    private Tablero tablero;
    
    SorpresaIRCASILLA(Tablero tablero, int valor, String texto){ 
        super(valor, texto);
        this.tablero = tablero;
    }
    
    @Override
    void aplicarAJugador(int actual, ArrayList<Jugador> todos){
        Jugador jugador = todos.get(actual);
        
        if(jugadorCorrecto(actual, todos)){
            informe(actual, todos);
            int casillaActual = jugador.getNumCasillaActual();
            
            int tirada = tablero.calcularTirada(casillaActual, valor);
            int nuevaPos = tablero.nuevaPosicion(casillaActual, tirada);
            jugador.moverACasilla(nuevaPos);
            tablero.getCasilla(nuevaPos).recibeJugador(valor, todos);
        }
    }
    
    @Override
    public String toString(){
        return super.toString() + 
               "\nTipo Sorpresa: IRCASILLA";
    }
}
