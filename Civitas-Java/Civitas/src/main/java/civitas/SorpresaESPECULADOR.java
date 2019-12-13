/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package civitas;

import java.util.ArrayList;

/**
 * @author elena
 * @date 13/12/2019
 */
public class SorpresaESPECULADOR extends Sorpresa{
    
    SorpresaESPECULADOR(int valor, String texto){   
        super(valor, texto);
    }

    @Override
    void aplicarAJugador(int actual, ArrayList<Jugador> todos) {
        if(jugadorCorrecto(actual, todos)){
            informe(actual, todos);
            
            JugadorEspeculador nuevo_jugador = new JugadorEspeculador(todos.get(actual));
            todos.remove(todos.get(actual));
            todos.add(actual, nuevo_jugador);
        }
    }
    
    @Override
    public String toString(){
        return super.toString() + 
               "\nTipo Sorpresa: Jugador Especulador";
    }
}

