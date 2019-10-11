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
    private TituloPropiedad tituloPropiedad;
    private Sorpresa sorpresa;  
    private MazoSorpresas mazo; 
    
    Casilla(String nombre){ //DESCANSO
        init();
        this.nombre = nombre;
    }
    
    Casilla(TituloPropiedad titulo){    //CALLE
        init();
        tipo = TipoCasilla.CALLE;
        tituloPropiedad = titulo;
    }
    
    Casilla(float cantidad, String nombre){ //IMPUESTO
        init();
        tipo = TipoCasilla.IMPUESTO;
        this.importe = cantidad;
        this.nombre = nombre;
    }
    
    Casilla(int numCasillaCarcel, String nombre){   //JUEZ
        init();
        tipo = TipoCasilla.JUEZ;
        this.carcel = numCasillaCarcel;
        this.nombre = nombre;
    }
    
    Casilla(MazoSorpresas mazo, String nombre){ //SORPRESA
        init();
        tipo = TipoCasilla.SORPRESA;
        this.nombre = nombre;
        this.mazo = mazo;
    }
    
    private void init(){
        carcel = -1;
        importe = 0;
        nombre = null;
        tipo = null;
        tituloPropiedad = null;
        sorpresa = null;
        mazo = null;
    }
    
    public String getNombre(){ return nombre; }
    
    TituloPropiedad getTituloPropiedad(){ return this.tituloPropiedad; }
    
    private void informe(int iactual, ArrayList<Jugador> todos){
        Diario diario = Diario.getInstance();
        diario.ocurreEvento("Jugador " + todos.get(iactual).getNombre() + "ha caido en la casilla " + toString());
    }
    
    private void recibeJugador_impuesto(int iactual, ArrayList<Jugador> todos){
        if(jugadorCorrecto(iactual, todos)){
            informe(iactual, todos);
            todos.get(iactual).pagaImpuesto(this.importe);
        }
    }
    
    private void recibeJugador_juez(int iactual, ArrayList<Jugador> todos){
        if(jugadorCorrecto(iactual, todos)){
            informe(iactual, todos);
            todos.get(iactual).encarcelar(this.carcel);
        }
    }
    
    public boolean jugadorCorrecto(int iactual, ArrayList<Jugador> todos){ return iactual < todos.size(); }
    
    void recibeJugador(int iactual, ArrayList<Jugador> todos){
        throw new UnsupportedOperationException("No implementado"); 
    }
    
    private void recibeJugador_calle(int iactual, ArrayList<Jugador> todos){
        throw new UnsupportedOperationException("No implementado"); 
    }   
    
    private void recibeJugador_sorpresa(int iactual, ArrayList<Jugador> todos){
        throw new UnsupportedOperationException("No implementado");
    }
    
    @Override
    public String toString(){
        switch(tipo){
            case IMPUESTO:
                return "\nNombre: " + this.nombre +
                       "\nTipo: " + this.tipo +
                       "\nImporte: " + this.importe;            
            case DESCANSO:
                return "\nNombre: " + this.nombre +
                       "\nTipo: " + this.tipo;            
            case JUEZ:
                return "\nNombre: " + this.nombre +
                       "\nTipo: " + this.tipo + 
                       "\nCarcel: " + this.carcel;
            case SORPRESA:
                return "\nNombre: " + this.nombre +
                       "\nTipo: " + this.tipo;                      
            default:
                return "\nNombre: " + this.nombre +
                   "\nTipo: " + this.tipo +
                   "\nTitulo Propiedad: " + this.getTituloPropiedad().getNombre();
        }
        
    }
}