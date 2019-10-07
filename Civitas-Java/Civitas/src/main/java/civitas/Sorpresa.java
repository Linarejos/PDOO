/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package civitas;

import java.util.ArrayList;

/**
 * @author elena
 * @date 07/10/2019
 */
public class Sorpresa {
    private String texto;
    private int valor;
    private MazoSorpresas mazo;
    private TipoSorpresa tipo;
    private Tablero tablero;
    
    Sorpresa(TipoSorpresa tipo, Tablero tablero){ //Construir la sorpresa que envía la cárcel
        init();
        this.tipo = tipo;
        this.tablero = tablero;
        this.texto = "De camino a la carcel";
    }
    
    Sorpresa(TipoSorpresa tipo, Tablero tablero, int valor, String texto){  //Sorpresa que envía el jugador a otra casilla
        init();
        this.tipo = tipo;
        this.tablero = tablero;
        this.texto = texto;
        this.valor = valor;
    }
    
    Sorpresa(TipoSorpresa tipo, int valor, String texto){   //Resto de sorpresas
        init();
        this.tipo = tipo;
        this.texto = texto;
        this.valor = valor;
    }
    
    Sorpresa(TipoSorpresa tipo, MazoSorpresas mazo){    //Sorpresa para evitar la cárcel
        init();
        this.tipo = tipo;
        this.texto = "Evitando la carcel";
        this.mazo = mazo;
    }
    
    private void init(){ 
        this.valor = -1;
        this.mazo = null;
        this.tablero = null;
    }

    public boolean jugadorCorrecto(int actual, ArrayList<Jugador> todos){
        return todos.size() > actual;
    }
    
    private void informe(int actual, ArrayList<Jugador> todos){
        
    }
    
    void aplicarAJugador(int actual, ArrayList<Jugador> todos){ throw new UnsupportedOperationException("No implementado"); }    
    private void aplicarAJugador_irACasilla(int actual, ArrayList<Jugador> todos){ throw new UnsupportedOperationException("No implementado"); }
    private void aplicarAJugador_irCarcel(int actual, ArrayList<Jugador> todos){ throw new UnsupportedOperationException("No implementado"); }    
    private void aplicarAJugador_pagarCobrar(int actual, ArrayList<Jugador> todos){ throw new UnsupportedOperationException("No implementado"); }    
    private void aplicarAJugador_porCasaHotel(int actual, ArrayList<Jugador> todos){ throw new UnsupportedOperationException("No implementado"); }    
    private void aplicarAJugador_porJugador(int actual, ArrayList<Jugador> todos){ throw new UnsupportedOperationException("No implementado"); }    
    private void aplicarAJugador_salirCarcel(int actual, ArrayList<Jugador> todos){ throw new UnsupportedOperationException("No implementado"); }    
       
    void salirDelMazo(){ throw new UnsupportedOperationException("No implementado"); }
        
    @Override
    public String toString(){ throw new UnsupportedOperationException("No implementado"); }
    
    void usada(){ throw new UnsupportedOperationException("No implementado"); }
}
