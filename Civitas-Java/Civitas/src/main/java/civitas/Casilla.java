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
    private static int carcel;
    private float importe;
    private String nombre;
    
    private TipoCasilla tipo;
    private TituloPropiedad tituloPropiedad; //tipo = CALLE
    private Sorpresa sorpresa;  //tipo = Sorpresa
    private MazoSorpresas mazo; //tipo = Sorpresa
    
    Casilla(String nombre){
        this.nombre = nombre;
    }
    
    Casilla(TituloPropiedad titulo){
        throw new UnsupportedOperationException("No implementado"); 
    }
    
    Casilla(float cantidad, String nombre){
        throw new UnsupportedOperationException("No implementado"); 
    }
    
    Casilla(int numCasillaCarcel, String nombre){
        throw new UnsupportedOperationException("No implementado"); 
    }
    
    Casilla(MazoSorpresas mazo, String nombre){
        throw new UnsupportedOperationException("No implementado"); 
    }
    
    public String getNombre(){ return nombre; }
    
    TituloPropiedad getTituloPropiedad(){
        throw new UnsupportedOperationException("No implementado"); 
    }
    
    private void informe(int iactual, ArrayList<Jugador> todos){
        throw new UnsupportedOperationException("No implementado"); 
    }
    
    private void init(){
        throw new UnsupportedOperationException("No implementado"); 
    }
    
    public boolean jugadorCorrecto(int iactual, ArrayList<Jugador> todos){
        throw new UnsupportedOperationException("No implementado"); 
    }
    
    void recibeJugador(int iactual, ArrayList<Jugador> todos){
        throw new UnsupportedOperationException("No implementado"); 
    }
    
    private void recibeJugador_calle(int iactual, ArrayList<Jugador> todos){
        throw new UnsupportedOperationException("No implementado"); 
    }
    
    private void recibeJugador_impuesto(int iactual, ArrayList<Jugador> todos){
        throw new UnsupportedOperationException("No implementado"); 
    }
    
    private void recibeJugador_juez(int iactual, ArrayList<Jugador> todos){
        throw new UnsupportedOperationException("No implementado");
    }
    
    private void recibeJugador_sorpresa(int iactual, ArrayList<Jugador> todos){
        throw new UnsupportedOperationException("No implementado");
    }
    
    @Override
    public String toString(){
        throw new UnsupportedOperationException("No implementado");
    }
}
