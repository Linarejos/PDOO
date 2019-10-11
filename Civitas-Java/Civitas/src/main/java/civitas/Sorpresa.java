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
        Diario diario = Diario.getInstance();
        diario.ocurreEvento("Aplicando sorpresa, jugador " + todos.get(actual).getNombre());        
    }
    
    void aplicarAJugador(int actual, ArrayList<Jugador> todos){
        switch (tipo){
            case IRCARCEL: 
                aplicarAJugador_irCarcel(actual, todos);
            break;
            case PAGARCOBRAR: 
                aplicarAJugador_pagarCobrar(actual, todos);
            break;
            case PORCASAHOTEL:
                aplicarAJugador_porCasaHotel(actual, todos);
            break;
            case PORJUGADOR:
                aplicarAJugador_porJugador(actual, todos);
            break;
            case SALIRCARCEL:
                aplicarAJugador_salirCarcel(actual, todos);
            break;
            default:
                aplicarAJugador_irACasilla(actual, todos);
        }                
    }
    
    private void aplicarAJugador_irCarcel(int actual, ArrayList<Jugador> todos){
        Jugador jugador = todos.get(actual);
        
        if(actual < todos.size()){
            informe(actual, todos);
            jugador.encarcelar(5);
        }
    }
    
    private void aplicarAJugador_irACasilla(int actual, ArrayList<Jugador> todos){
        Jugador jugador = todos.get(actual);
        
        if(actual < todos.size()){
            informe(actual, todos);
            int casillaActual = jugador.getNumCasillaActual();
            
            int tirada = tablero.calcularTirada(casillaActual, valor);
            int nuevaPos = tablero.nuevaPosicion(casillaActual, tirada);
            jugador.moverACasilla(nuevaPos);
            tablero.getCasilla(nuevaPos).recibeJugador(valor, todos);
        }
    }
    
    private void aplicarAJugador_pagarCobrar(int actual, ArrayList<Jugador> todos){
        Jugador jugador = todos.get(actual);
        
        if(actual < todos.size()){
            informe(actual, todos);
            jugador.modificarSaldo(valor);
        }
    }
    
    private void aplicarAJugador_porCasaHotel(int actual, ArrayList<Jugador> todos){
        Jugador jugador = todos.get(actual);
        
        if(actual < todos.size()){
            informe(actual, todos);
            jugador.modificarSaldo(valor*jugador.cantidadCasasHoteles());
        }
    }
    
    private void aplicarAJugador_porJugador(int actual, ArrayList<Jugador> todos){
                
        if(actual < todos.size()){
            Sorpresa aux, aux1;
            
            informe(actual, todos);
            
            aux = new Sorpresa(TipoSorpresa.PAGARCOBRAR, valor*-1, "Cobra jugador actual");
            
            for(int i = 0; i < todos.size(); i++){
                if(i != actual)
                    aplicarAJugador(i, todos);
            }
            
            
            aux1 = new Sorpresa(TipoSorpresa.PAGARCOBRAR, valor*(todos.size()-1), "Cobra jugador actual");           
            aplicarAJugador(actual, todos);
        }
    }    
    
    private void aplicarAJugador_salirCarcel(int actual, ArrayList<Jugador> todos){
        Jugador jugador = todos.get(actual);
        boolean salvoconducto = false;
        
        if(actual < todos.size()){
            informe(actual, todos);
            for(int i = 0; i < todos.size() && salvoconducto; i++){
                if(todos.get(i).tieneSalvoconducto())
                    salvoconducto = true;                
            }
            
            if(!salvoconducto){
                jugador.obtenerSalvoconducto(this);
                salirDelMazo();
            }
        }
    }    
       
    void salirDelMazo(){
        if(tipo == TipoSorpresa.SALIRCARCEL)
            mazo.inhabilitarCartaEspecial(this);        
    }
    
    void usada(){
        if(tipo == TipoSorpresa.SALIRCARCEL)
            mazo.habilitarCartaEspecial(this);   
    }
        
    @Override
    public String toString(){
        return "Sorpresa : " + this.texto + 
                "\nValor : " + this.valor +
                "\nTipo : " + this.tipo;
    }
}