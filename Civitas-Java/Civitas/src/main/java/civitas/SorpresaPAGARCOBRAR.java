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

public class SorpresaPAGARCOBRAR extends Sorpresa{
    
    SorpresaPAGARCOBRAR(int valor, String texto){   //Resto de sorpresas
        super(valor, texto);
    }
    
    @Override
    void aplicarAJugador(int actual, ArrayList<Jugador> todos){
        Jugador jugador = todos.get(actual);
        
        if(jugadorCorrecto(actual, todos)){
            informe(actual, todos);
            jugador.modificarSaldo(valor);
        }
    }
    
    @Override
    public String toString(){
        return super.toString() + 
               "\nTipo Sorpresa: PAGARCOBRAR";
    }
}
