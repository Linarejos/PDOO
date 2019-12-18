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
public class CasillaSorpresa extends Casilla{
    private MazoSorpresas mazo = null;
    
    CasillaSorpresa(MazoSorpresas mazo, String nombre){ 
        super(nombre);
        this.mazo = mazo;
    }
    @Override
    public boolean soyCasillaCalle(){ return false; }
    @Override
    void recibeJugador(int iactual, ArrayList<Jugador> todos){
        if(jugadorCorrecto(iactual, todos)){
            Sorpresa sorpresa = mazo.siguiente();
            super.informe(iactual, todos);
            sorpresa.aplicarAJugador(iactual, todos);
        }
    }
    
    @Override
    public String toString(){
        return "Nombre: " + super.getNombre() +
               "\nTipo Casilla: SORPRESA";
    }
}
