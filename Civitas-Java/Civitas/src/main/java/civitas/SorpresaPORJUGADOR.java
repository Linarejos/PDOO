/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package civitas;

import java.util.ArrayList;

/**
 *
 * @author elena
 */
public class SorpresaPORJUGADOR extends Sorpresa{
    
    SorpresaPORJUGADOR(int valor, String texto){   //Resto de sorpresas
        super(valor, texto);
    }
    
    @Override
    void aplicarAJugador(int actual, ArrayList<Jugador> todos){
                
        if(jugadorCorrecto(actual, todos)){
            Sorpresa aux, aux1;
            
            informe(actual, todos);
            
            aux = new SorpresaPAGARCOBRAR(valor*-1, "Cobra jugador actual");
            
            for(int i = 0; i < todos.size(); i++){
                if(i != actual)
                    aplicarAJugador(i, todos);
            }            
            
            aux1 = new SorpresaPAGARCOBRAR(valor*(todos.size()-1), "Paga jugador actual");           
            aplicarAJugador(actual, todos);
        }
    }
    
    @Override
    public String toString(){
        return super.toString() + 
               "\nTipo Sorpresa: PORJUGADOR";
    }
}
