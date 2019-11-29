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
public class SorpresaSALIRCARCEL extends Sorpresa{
    private MazoSorpresas mazo; 
    
    SorpresaSALIRCARCEL(MazoSorpresas mazo){    //Sorpresa para evitar la cárcel
        super(-1, "Evitando la carcel");
        this.mazo = mazo;
    }   
    
    @Override
    void aplicarAJugador(int actual, ArrayList<Jugador> todos){
        Jugador jugador = todos.get(actual);
        boolean salvoconducto = false;
        
        if(jugadorCorrecto(actual, todos)){
            informe(actual, todos);
            for(int i = 0; i < todos.size() && !salvoconducto; i++){
                if(todos.get(i).tieneSalvoconducto())
                    salvoconducto = true;                
            }
            
            if(!salvoconducto){
                jugador.obtenerSalvoconducto(this);
                salirDelMazo();
            }
        }
    }
    
    void salirDelMazo(){ mazo.inhabilitarCartaEspecial(this); }
    
    void usada(){ mazo.habilitarCartaEspecial(this); }
    
    @Override
    public String toString(){
        return super.toString() + 
               "\nTipo Sorpresa: SALIRCARCEL";
    }
}
