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
public class Sorpresa {
    private String texto;
    private int valor;
    private MazoSorpresas mazo;
    private TipoSorpresa tipo;
    private Tablero tablero;
            
    void aplicarAJugador(int actual, ArrayList<Jugador> todos){ throw new UnsupportedOperationException("No implementado"); }    
    private void aplicarAJugador_irACasilla(int actual, ArrayList<Jugador> todos){ throw new UnsupportedOperationException("No implementado"); }
    private void aplicarAJugador_irCarcel(int actual, ArrayList<Jugador> todos){ throw new UnsupportedOperationException("No implementado"); }    
    private void aplicarAJugador_pagarCobrar(int actual, ArrayList<Jugador> todos){ throw new UnsupportedOperationException("No implementado"); }    
    private void aplicarAJugador_porCasaHotel(int actual, ArrayList<Jugador> todos){ throw new UnsupportedOperationException("No implementado"); }    
    private void aplicarAJugador_porJugador(int actual, ArrayList<Jugador> todos){ throw new UnsupportedOperationException("No implementado"); }    
    private void aplicarAJugador_salirCarcel(int actual, ArrayList<Jugador> todos){ throw new UnsupportedOperationException("No implementado"); }    
    private void informe(int actual, ArrayList<Jugador> todos){ throw new UnsupportedOperationException("No implementado"); }    
    private void init(){ throw new UnsupportedOperationException("No implementado"); }
    
    public boolean jugadorCorrecto(int actual, ArrayList<Jugador> todos){ throw new UnsupportedOperationException("No implementado"); }
    void salirDelMazo(){ throw new UnsupportedOperationException("No implementado"); }
    
    Sorpresa(TipoSorpresa tipo, Tablero tablero){ throw new UnsupportedOperationException("No implementado"); }
    Sorpresa(TipoSorpresa tipo, Tablero tablero, int valor, String texto){ throw new UnsupportedOperationException("No implementado"); }
    Sorpresa(TipoSorpresa tipo, int valor, String texto){ throw new UnsupportedOperationException("No implementado"); }
    Sorpresa(TipoSorpresa tipo, MazoSorpresas mazo){ throw new UnsupportedOperationException("No implementado"); }

    @Override
    public String toString(){ throw new UnsupportedOperationException("No implementado"); }
    
    void usada(){ throw new UnsupportedOperationException("No implementado"); }
}
