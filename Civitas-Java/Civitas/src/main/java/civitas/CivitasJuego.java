/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package civitas;

import GUI.Dado;
import java.util.ArrayList;
import java.util.Collections;

/**
 * @author elena
 * @date 04/10/2019
 */

public class CivitasJuego {
    private int indiceJugador;
    private Tablero tablero;
    private MazoSorpresas mazo;
    private EstadosJuego estado;
    private GestorEstados gestorEstados;
    private ArrayList<Jugador> jugadores;
    private int i = 0;

    public CivitasJuego(ArrayList<String> nombres){
        jugadores = new ArrayList();
        
        for(String n : nombres){
            jugadores.add(new Jugador(n));
        }
        
        gestorEstados = new GestorEstados();
        estado = gestorEstados.estadoInicial();
        
        indiceJugador = Dado.getInstance().quienEmpieza(jugadores.size()-1);
        mazo = new MazoSorpresas();
        this.inicializarTablero(mazo);
        this.inicializarMazoSorpresas(tablero);
    }
    
    private void inicializarTablero(MazoSorpresas mazo){
        tablero = new Tablero(5);
        
        tablero.añadeCasilla(new Casilla("Salida"));
        tablero.añadeCasilla(new CasillaCalle(new TituloPropiedad("Calle Recogidas",
        50, 20, 100, 100, 50)));
        tablero.añadeCasilla(new CasillaCalle(new TituloPropiedad("Calle Tranvia",
        55, 20, 120, 150, 60)));
        tablero.añadeCasilla(new CasillaSorpresa(mazo, "Sorpresa1"));
        tablero.añadeCasilla(new CasillaCalle(new TituloPropiedad("Calle Aeropuerto",
        60, 20, 140, 200, 70)));
        tablero.añadeCasilla(new CasillaCalle(new TituloPropiedad("Calle Sana",
        65, 20, 160, 250, 80)));
        tablero.añadeCasilla(new CasillaCalle(new TituloPropiedad("Calle Ole",
        80, 20, 200, 300, 90)));
        tablero.añadeCasilla(new CasillaCalle(new TituloPropiedad("Calle Mesa",
        85, 20, 220, 350, 100)));
        tablero.añadeCasilla(new CasillaSorpresa(mazo, "Sorpresa2"));
        tablero.añadeCasilla(new CasillaCalle(new TituloPropiedad("Calle Trompeta",
        90, 20, 240, 400, 110)));
        tablero.añadeCasilla(new CasillaCalle(new TituloPropiedad("Calle Maracas",
        95, 20, 260, 450, 120)));
        tablero.añadeCasilla(new CasillaImpuesto(400, "Impuesto"));//impuesto
        tablero.añadeCasilla(new CasillaCalle(new TituloPropiedad("Calle Solida",
        110, 20, 300, 500, 130)));
        tablero.añadeCasilla(new CasillaCalle(new TituloPropiedad("Calle Coche",
        115, 20, 320, 550, 140)));
        tablero.añadeCasilla(new CasillaSorpresa(mazo, "Sorpresa3"));
        tablero.añadeCasilla(new CasillaCalle(new TituloPropiedad("Calle Pienso",
        120, 20, 340, 600, 150)));
        tablero.añadeCasilla(new CasillaCalle(new TituloPropiedad("Calle Tren",
        125, 20, 360, 650, 160)));
        tablero.añadeJuez();//juez
        tablero.añadeCasilla(new CasillaCalle(new TituloPropiedad("Calle Mal",
        140, 20, 400, 700, 170)));
        tablero.añadeCasilla(new CasillaCalle(new TituloPropiedad("Calle Guitarra",
        145, 20, 420, 750, 180)));
        tablero.añadeCasilla(new CasillaSorpresa(mazo, "Sorpresa4"));
        tablero.añadeCasilla(new Casilla("Parking"));
        tablero.añadeCasilla(new CasillaCalle(new TituloPropiedad("Calle Hora",
        155, 20, 440, 800, 190)));
        tablero.añadeCasilla(new CasillaCalle(new TituloPropiedad("Calle Diamante",
        170, 20, 460, 850, 200)));        
    }
    
    private void inicializarMazoSorpresas(Tablero tablero){
        mazo.alMazo(new SorpresaIRCARCEL(tablero));
        mazo.alMazo(new SorpresaIRCARCEL(tablero));
        mazo.alMazo(new SorpresaIRCASILLA(tablero, 4, "Ve a la casilla 4"));
        mazo.alMazo(new SorpresaIRCASILLA(tablero, 12, "Ve a la casiila 12"));
        mazo.alMazo(new SorpresaPAGARCOBRAR(-500, "Paga a otro jugador"));
        mazo.alMazo(new SorpresaPAGARCOBRAR(500, "Recibes dinero *-*"));
        mazo.alMazo(new SorpresaPORCASAHOTEL(100, "Pagas por cada casa y hotel"));
        mazo.alMazo(new SorpresaPORJUGADOR(50, "Recibes dinero de los demas"));
        mazo.alMazo(new SorpresaSALIRCARCEL(mazo));
        mazo.alMazo(new SorpresaSALIRCARCEL(mazo));
        mazo.alMazo(new SorpresaPORJUGADOR(-50, "Dale dinero a los demás"));
        mazo.alMazo(new SorpresaPAGARCOBRAR(-100, "Pagas a la banca"));                           
    }
    
    private void contabilizarPasosPorSalida(Jugador jugadorActual){
        while(tablero.getPorSalida() > 0){
            jugadorActual.pasaPorSalida();
        }
    }
    
    private void pasarTurno(){ indiceJugador = (indiceJugador+1)%jugadores.size(); }
    
    public void siguientePasoCompletado(OperacionesJuego operacion){
        this.estado = gestorEstados.siguienteEstado(jugadores.get(indiceJugador), estado, operacion);
    }
    
    public boolean construirCasa(int ip){ return jugadores.get(indiceJugador).construirCasa(ip); }
    
    public boolean construirHotel(int ip){ return jugadores.get(indiceJugador).construirHotel(ip); }
    
    public boolean vender(int ip){ return jugadores.get(indiceJugador).vender(ip); }
      
    public boolean hipotecar(int ip){ return jugadores.get(indiceJugador).hipotecar(ip); }
    
    public boolean cancelarHipoteca(int ip){ return jugadores.get(indiceJugador).cancelarHipoteca(ip); }
    
    public boolean salirCarcelPagando(){ return jugadores.get(indiceJugador).salirCarcelPagando(); }
    
    public boolean salirCarcelTirando(){ return jugadores.get(indiceJugador).salirCarcelTirando(); }
    
    public boolean finalDelJuego(){ 
        for(Jugador j : jugadores){
            if(j.enBancarrota()) return true;
        }
        return false;               
    }
    
    //Visibilidad cambiada
    public ArrayList<Jugador> ranking(){ 
        Collections.sort(jugadores);
        return jugadores;
    }
    
    public Casilla getCasillaActual(){ return tablero.getCasilla(getJugadorActual().getNumCasillaActual()); }
    
    public Jugador getJugadorActual(){ return jugadores.get(indiceJugador); }    
    
    public String infoJugadorTexto(){ return jugadores.get(indiceJugador).toString(); }   
        
    private void avanzaJugador(){
        Jugador jugadorActual = jugadores.get(indiceJugador);   //1.1
        
        int posicionActual = jugadorActual.getNumCasillaActual();  //1.2
        int tirada = Dado.getInstance().tirar();    //1.3
        int posicionNueva = tablero.nuevaPosicion(posicionActual, tirada);  //1.4
        Casilla casilla = tablero.getCasilla(posicionNueva);    //1.5
        this.contabilizarPasosPorSalida(jugadorActual); //1.6
        jugadorActual.moverACasilla(posicionNueva); //1.7
        casilla.recibeJugador(indiceJugador, jugadores);    //1.8
        this.contabilizarPasosPorSalida(jugadorActual); //1.9
    }      
    
    public boolean comprar(){ 
        boolean res = false;
        Jugador jugadorActual = jugadores.get(indiceJugador);
        int numCasillaActual = jugadorActual.getNumCasillaActual();
        
        if(tablero.getCasilla(numCasillaActual).soyCasillaCalle()){
            CasillaCalle casilla = (CasillaCalle) tablero.getCasilla(numCasillaActual);
            TituloPropiedad titulo = casilla.getTituloPropiedad();
            res = jugadorActual.comprar(titulo);
        }               
        return res;
    }   
    
    public OperacionesJuego siguientePaso(){ 
        Jugador jugadorActual = jugadores.get(indiceJugador);
        OperacionesJuego operacion = gestorEstados.operacionesPermitidas(jugadorActual, estado);
        
        if(operacion == OperacionesJuego.PASAR_TURNO){
            pasarTurno();
            siguientePasoCompletado(operacion);
        }
        else if(operacion == OperacionesJuego.AVANZAR){
            avanzaJugador();
            siguientePasoCompletado(operacion);
        }
        
        return operacion;
    }
}
