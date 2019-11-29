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

abstract class Sorpresa {
    protected String texto;
    protected int valor;
    private MazoSorpresas mazo;
    private Tablero tablero;
       
    Sorpresa(int valor, String texto){   //Resto de sorpresas
        this.mazo = null;
        this.tablero = null;
        this.texto = texto;
        this.valor = valor;
    }

    public boolean jugadorCorrecto(int actual, ArrayList<Jugador> todos){ return todos.size() > actual; }
    
    void informe(int actual, ArrayList<Jugador> todos){
        Diario diario = Diario.getInstance();
        diario.ocurreEvento("\nAplicando sorpresa, jugador " + todos.get(actual).getNombre());        
    }
    
    abstract void aplicarAJugador(int actual, ArrayList<Jugador> todos);
         
    @Override
    public String toString(){
        return "Sorpresa : " + this.texto + 
                "\nValor : " + this.valor;
    }
}