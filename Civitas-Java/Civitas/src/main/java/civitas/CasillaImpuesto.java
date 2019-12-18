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
public class CasillaImpuesto extends Casilla{
    private float importe = 0;
    
    CasillaImpuesto(float cantidad, String nombre){ 
        super(nombre);
        this.importe = cantidad;
    }
    @Override
    public boolean soyCasillaCalle(){ return false; }
    @Override
    void recibeJugador(int iactual, ArrayList<Jugador> todos){
        if(jugadorCorrecto(iactual, todos)){
            super.informe(iactual, todos);
            todos.get(iactual).pagaImpuesto(this.importe);
        }
    }
    
    @Override
    public String toString(){
        return "\nNombre: " + super.getNombre() +
               "\nTipo Casilla: IMPUESTO" +
               "\nImporte: " + this.importe;
    }
}
