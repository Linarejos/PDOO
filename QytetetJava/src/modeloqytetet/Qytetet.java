/**
 * @author Elena Cantero Molina
 */
package modeloqytetet;
import java.util.*;
import GUIQytetet.*;

public class Qytetet {
    public static int MAX_JUGADORES = 4;   //numero maximo de jugadores que pueden jugar
    static int MAX_CARTAS = 10; //numero maximo de cartas
    static int MAX_CASILLAS = 20;   //nuemro maximo de casillas del tablero
    static int PRECIO_LIBERTAD = 200;   //precio para salir de la carcel
    static int SALDO_SALIDA = 1000; //saldo que se da cada vez que se pasa por la salida
    private Sorpresa cartaActual;   //carta actual del mazo de las sorpresas
    private ArrayList<Sorpresa> mazo;   //mazo de sorpresas
    private ArrayList<Jugador> jugadores;   //jugadores
    private Jugador jugadorActual;  //jugador actual
    private Tablero tablero;    //tablero
    Dado dado;
    
    private Qytetet() { //Constructor
        this.dado = GUIQytetet.Dado.getInstance();
        this.cartaActual = null;
        this.mazo = new ArrayList();
        this.jugadores = new ArrayList();
    }
    
    public static Qytetet getInstance() { return QytetetHolder.INSTANCE; }    
    private static class QytetetHolder { private static final Qytetet INSTANCE = new Qytetet(); }
    
    //Consultor del maximo de jugadores
    public int GetMaxJugadores(){ return MAX_JUGADORES; }
    //Consultor del maximo de cartas
    public int GetMaxCartas(){ return MAX_CARTAS; }
    //Consultor del maximo de casillas
    public int GetMaxCasillas(){ return MAX_CASILLAS; }
    //Consultor del precio para salir de la carcel
    public int GetPrecioLibertad(){ return PRECIO_LIBERTAD; }
    //Consultor del saldo por pasar por la salida
    public int GetSaldoSalida(){ return SALDO_SALIDA; }
    //Consultor del tablero
    public Tablero GetTablero(){ return tablero; }
    //Consultor del dado
    public Dado GetDado(){ return dado; }
    //Consultor de la lista de jugadores
    public ArrayList<Jugador> getJugadores(){ return this.jugadores; }
    //Ejecuta la sorpresa en cuestion
    public boolean aplicarSorpresa(){
        boolean tienePropietario = false;
        
        if(cartaActual.GetTipoSorpresa() == TipoSorpresa.PAGARCOBRAR){
            jugadorActual.modificarSaldo(cartaActual.GetValor());
            tienePropietario = true;
        }
        else if(cartaActual.GetTipoSorpresa() == TipoSorpresa.IRACASILLA){
            boolean esCarcel = tablero.esCasillaCarcel(cartaActual.GetValor());
            if(esCarcel)
                this.encarcelarJugador();
            else{
                Casilla nuevaCasilla = tablero.obtenerCasillaNumero(cartaActual.GetValor());
                tienePropietario = jugadorActual.actualizarPosicion(nuevaCasilla);
            }
        }
        else if(cartaActual.GetTipoSorpresa() == TipoSorpresa.PORCASAHOTEL){
            jugadorActual.pagarCobrarPorCasaYHotel(cartaActual.GetValor());
            tienePropietario = true;
        }
        else if(cartaActual.GetTipoSorpresa() == TipoSorpresa.PORJUGADOR){
            for(Jugador juega: jugadores){
                if(juega != jugadorActual){
                    juega.modificarSaldo(cartaActual.GetValor());
                    jugadorActual.modificarSaldo(-cartaActual.GetValor());
                }
            }
            tienePropietario = true;
        }
        else if(cartaActual.GetTipoSorpresa() == TipoSorpresa.CONVERTIRME){
            jugadorActual.convertirme(cartaActual.GetValor());
        }
        
        if(cartaActual.GetTipoSorpresa() == TipoSorpresa.SALIRCARCEL)
            jugadorActual.setCartaLibertad(cartaActual);
        else
            mazo.add(cartaActual);

        return tienePropietario;
    }

    public boolean cancelarHipoteca(Casilla casilla){
        boolean cancelarHipoteca = false;
        
        if(((Calle)casilla).estaHipotecada() && jugadorActual.puedoPagarHipoteca(casilla)){
            int cantidadRecibida = casilla.GetCoste();
            jugadorActual.modificarSaldo(-cantidadRecibida);
            ((Calle)casilla).GetTitulo().SetHipotecada(false);
            cancelarHipoteca = true;
        }
        
        return cancelarHipoteca;
    }

    public boolean comprarTituloPropiedad(){ return this.jugadorActual.comprarTitulo(); }

    public boolean edificarCasa(Casilla casilla){        
        if(casilla.soyEdificable()){
            boolean sePuedeEdificar = ((Calle)casilla).sePuedeEdificarCasa();
            if(sePuedeEdificar && jugadorActual.puedoEdificarCasa(casilla)){
                    jugadorActual.modificarSaldo(-((Calle)casilla).edificarCasa());
                    return true;
                }
        }
        
        return false;
    }
    
    public boolean edificarHotel(Casilla casilla){        
        if(casilla.soyEdificable()){
            boolean sePuedeEdificar = ((Calle)casilla).sePuedeEdificarHotel();
            if(sePuedeEdificar && jugadorActual.puedoEdificarHotel(casilla)){
                    jugadorActual.modificarSaldo(-((Calle)casilla).edificarHotel());
                    return true;
             }
        }
        
        return false;
    }    

    public Sorpresa getCartaActual(){ return this.cartaActual; }    

    public Jugador getJugadorActual(){ return this.jugadorActual; }    

    public boolean hipotecarPropiedad(Casilla casilla){
        boolean puedoHipotecar = false;
        int cantidadRecibida = 0;
        
        if(casilla.soyEdificable() && !((Calle)casilla).estaHipotecada()){
                puedoHipotecar = jugadorActual.puedoHipotecar(casilla);
                if(puedoHipotecar){
                    cantidadRecibida = ((Calle)casilla).hipotecar();
                    jugadorActual.modificarSaldo(cantidadRecibida);
                }
        }
        
        return puedoHipotecar;
    }    

    public void inicializarJuego(ArrayList<String> nombres){
        inicializarJugadores(nombres);
        inicializarTablero();
        inicializarCartasSorpresa();
        salidaJugadores();
    }    

    public boolean intentarSalirCarcel(MetodoSalirCarcel metodo){
        boolean libre = false;
        
        if(metodo == MetodoSalirCarcel.TIRANDODADO){
            int valorDado = dado.nextNumber();
            libre = (valorDado > 5);
        }
        else if(metodo == MetodoSalirCarcel.PAGANDOLIBERTAD){
            boolean tengoSaldo = jugadorActual.pagarLibertad(-Qytetet.PRECIO_LIBERTAD);
            libre = tengoSaldo;
        }
        
        if(libre)
            jugadorActual.setEncarcelado(false);
        
        return libre;
    }    

    public boolean jugar(){
        int valorDado = GUIQytetet.Dado.getInstance().nextNumber();
        Casilla casillaPosicion = jugadorActual.getCasillaActual();
        Casilla nuevaCasilla = tablero.obtenerNuevaCasilla(casillaPosicion, valorDado);
        boolean tienePropietario = jugadorActual.actualizarPosicion(nuevaCasilla);
        
        if(!nuevaCasilla.soyEdificable()){
            if(nuevaCasilla.GetTipo() == TipoCasilla.JUEZ)
                this.encarcelarJugador();
            else if(nuevaCasilla.GetTipo() == TipoCasilla.SORPRESA)
                cartaActual = mazo.get(0);
        }
        
        return tienePropietario;
    }    

    public Map obtenerRanking(){
        Map<String, Integer> ranking = new LinkedHashMap<>();
        ArrayList<Jugador> listaOrdenada = new ArrayList(jugadores);
        listaOrdenada.sort((p1, p2) -> p2.obtenerCapital() - p1.obtenerCapital());
        listaOrdenada.forEach((jugador) -> ranking.put(jugador.getNombre(), jugador.obtenerCapital()));
        return ranking;
    }    

    public ArrayList<Casilla> propiedadesHipotecadasJugador(boolean hipotecadas){
        ArrayList<Casilla> micasilla = new ArrayList();
        ArrayList<TituloPropiedad> propiedad = jugadorActual.obtenerPropiedadesHipotecadas(hipotecadas);
        
        for(TituloPropiedad prop: propiedad){
            micasilla.add(prop.GetCasilla());        
        }
        
        return micasilla;
    }    

    public Jugador siguienteJugador(){
        jugadorActual = jugadores.get((jugadores.indexOf(jugadorActual)+1)%jugadores.size());
        return jugadorActual;
    }    

    public boolean venderPropiedad(Casilla casilla){
        boolean puedoVender = false;
        
        if(casilla.soyEdificable()){
            puedoVender = jugadorActual.puedoVenderPropiedad(casilla);
            if(puedoVender){
                jugadorActual.venderPropiedad(casilla);
            }
        }
        
        return puedoVender;
    }    

    private void encarcelarJugador(){
        if(!jugadorActual.tengoCartaLibertad()){
            Casilla casillaCarcel = tablero.GetCarcel();
            jugadorActual.irACarcel(casillaCarcel);
            Sorpresa carta = jugadorActual.devolverCartaLibertad();
            mazo.add(carta);
        }
    }    

    private void inicializarCartasSorpresa(){ 
        mazo.add(new Sorpresa("Sales de fiesta y te encuentras en una esquina dinero, ¡la suerte está de tu parte!", 100, TipoSorpresa.PAGARCOBRAR));
        mazo.add(new Sorpresa("Tu coche se avería en medio de la autovía, debes pagar a la grúa y el mecánico.", -500, TipoSorpresa.PAGARCOBRAR));
        mazo.add(new Sorpresa("Has sido pillado copiando durante un examen, ¡debes ir a la carcel!", tablero.GetCarcel().GetNumeroCasilla() ,TipoSorpresa.IRACASILLA));
        mazo.add(new Sorpresa("¡Ha llegado un ataque zombie! Sales corriendo a la casilla 2 que es la única libre.", 6, TipoSorpresa.IRACASILLA));
        mazo.add(new Sorpresa("Un ovni ha llegado a la Tierra y después de succionarte el cerebro te dejan en la casilla 15", 15, TipoSorpresa.IRACASILLA));
        mazo.add(new Sorpresa("Has sido elegido alcalde, recibes una indemnización por cada propiedad que tengas", 100, TipoSorpresa.PORCASAHOTEL));
        mazo.add(new Sorpresa("Has sufrido fugas de agua en cada una de tus propiedades. Debes pagar por cada una de ellas.", -100, TipoSorpresa.PORCASAHOTEL));
        mazo.add(new Sorpresa("¡Felicidades! Hoy es el día de tu no cumpleaños, recibes un regalo de todos.", 200, TipoSorpresa.PORJUGADOR));
        mazo.add(new Sorpresa("Sin querer produciste un apagón en el vecindario. Debes pagar los daños producidos a cada jugador.", -100, TipoSorpresa.PORJUGADOR));
        mazo.add(new Sorpresa("Tienes un admirador secreto que ha pagado tu fianza. Sales de la cárcel.", 0, TipoSorpresa.SALIRCARCEL));
        mazo.add(new Sorpresa("Conviertemee en especulador de 3000", 3000, TipoSorpresa.CONVERTIRME));
        mazo.add(new Sorpresa("Conviertemee en especulador de 5000", 5000, TipoSorpresa.CONVERTIRME));
    }

    private void inicializarJugadores(ArrayList<String> nombres){
        for(String n: nombres){
            jugadores.add(new Jugador(n));
        }
    }

    private void inicializarTablero(){
        tablero = new Tablero();
    }    

    private void salidaJugadores(){
        Random rand = new Random();
        for(Jugador juega: jugadores){
            juega.setCasillaActual(tablero.obtenerCasillaNumero(0));
        }
        jugadorActual = jugadores.get(rand.nextInt(jugadores.size()));
    }
    
    @Override
    public String toString() {
        return "Qytetet: \n" + "cartaActual -> " + cartaActual  + "\njugadorActual -> " + jugadorActual + "\n" + tablero + "\n";
    }
}
