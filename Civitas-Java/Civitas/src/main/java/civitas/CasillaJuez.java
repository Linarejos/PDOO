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
public class CasillaJuez extends Casilla{
    private static int carcel = 5;
    
    CasillaJuez(int numCasillaCarcel, String nombre){
        super(nombre);
        this.carcel = numCasillaCarcel;
    }
    @Override
    public boolean soyCasillaCalle(){ return false; }
    @Override
    void recibeJugador(int iactual, ArrayList<Jugador> todos){
        if(jugadorCorrecto(iactual, todos)){
            super.informe(iactual, todos);
            todos.get(iactual).encarcelar(this.carcel);
        }
    }
    
    @Override
    public String toString(){
        return "Nombre: " + super.getNombre() +
               "\nTipo Casilla: JUEZ" +
               "Carcel: " + this.carcel;
    }
}
