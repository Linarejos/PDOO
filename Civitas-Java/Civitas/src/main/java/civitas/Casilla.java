/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * @author elena
 * @date 20/09/2019
 */

package civitas;

import java.util.ArrayList;

public class Casilla {
    private String nombre; 
    
    Casilla(String nombre){
        this.nombre = nombre;
    }
    
    public String getNombre(){ return nombre; }
    
    void informe(int iactual, ArrayList<Jugador> todos){
        Diario diario = Diario.getInstance();
        diario.ocurreEvento("El jugador " + todos.get(iactual).getNombre() + " ha caido en la casilla " + toString());
    }
    
    public boolean jugadorCorrecto(int iactual, ArrayList<Jugador> todos){ return iactual < todos.size(); }
    
    void recibeJugador(int iactual, ArrayList<Jugador> todos){        
        informe(iactual, todos);
    }   
    
    @Override
    public String toString(){
        return "Nombre: " + this.nombre +
               "\nTipo Casilla: DESCANSO";
    }
}