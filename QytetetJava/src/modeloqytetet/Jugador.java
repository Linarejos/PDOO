/**
 * @author Elena Cantero Molina
 */
package modeloqytetet;
import java.util.*;

public class Jugador{
    private boolean encarcelado;
    private String nombre; 
    private int saldo = 7500;   
    private Sorpresa cartaLibertad; 
    private Casilla casillaActual;  
    private ArrayList<TituloPropiedad> propiedades; 
    static int factorEspeculador = 1;
    
    protected Jugador(String nombre){
        this.encarcelado = false;
        this.nombre = nombre;
        this.cartaLibertad = null;
        this.casillaActual = null;
        this.propiedades = new ArrayList();
    }
    
    protected Jugador(Jugador otro){
        if(this != otro){
            this.encarcelado = otro.getEncarcelado();
            this.nombre = otro.getNombre();
            this.cartaLibertad = otro.cartaLibertad;
            this.casillaActual = otro.getCasillaActual();
            this.propiedades = otro.getPropiedades();
        }
    }
    
    int getFactorEspeculador(){ return factorEspeculador; }
    
    public String getNombre(){ return this.nombre; }
    
    public int getSaldo(){ return saldo; }

    public Casilla getCasillaActual(){ return this.casillaActual; }    

    public boolean getEncarcelado(){ return this.encarcelado; }    

    public ArrayList<TituloPropiedad> getPropiedades(){ return this.propiedades; }

    public boolean tengoPropiedades(){ return this.propiedades != null; }
    
    protected boolean actualizarPosicion(Casilla casilla){
        if(casilla.GetNumeroCasilla() < casillaActual.GetNumeroCasilla()){
            this.modificarSaldo(Qytetet.SALDO_SALIDA);
        }
        boolean tengoPropietario = false;
        this.setCasillaActual(casilla);
        
        if(casilla.soyEdificable()){
            tengoPropietario = ((Calle)casilla).tengoPropietario();
            if(((Calle)casilla).tengoPropietario()){
                encarcelado = ((Calle)casilla).propietarioEncarcelado();
                if(!encarcelado){
                    this.modificarSaldo(((Calle)casilla).cobrarAlquiler());
                }
            }
        }
        else if(casilla.GetTipo() == TipoCasilla.IMPUESTO){
            this.pagarImpuestos(casilla.GetCoste());
            return true;
        }
        
        return tengoPropietario;
    }
    
    protected void pagarImpuestos(int cantidad){
        modificarSaldo(-cantidad);
    }
    
    protected Especulador convertirme(int fianza){
        Especulador aux;
        aux = (Especulador)this;
        aux.fianza = fianza;
        return aux;
    }
           
    boolean comprarTitulo(){
        boolean puedoComprar = false;
        
        if(casillaActual.soyEdificable() && !((Calle)casillaActual).tengoPropietario()){
                int costeCompra = casillaActual.GetCoste();
                if(costeCompra <= saldo){
                    propiedades.add(((Calle)casillaActual).asignarPropietario(this));
                    this.modificarSaldo(-costeCompra);
                    puedoComprar = true;
                }
        }
        
        return puedoComprar;
    }
    
    Sorpresa devolverCartaLibertad(){
        Sorpresa aux = this.cartaLibertad;
        this.cartaLibertad = null;
        return aux;
    }
    
    void irACarcel(Casilla casilla){
        this.setCasillaActual(casilla);
        this.setEncarcelado(true);
    }
    
    void modificarSaldo(int cantidad){ saldo += cantidad; }
    
    int obtenerCapital(){
        int suma_propiedades = 0;
        for(TituloPropiedad tit: propiedades){
            if(tit.GetHipotecada() == true){
                suma_propiedades -= tit.GetHipotecaBase();
            }
            else{
                suma_propiedades += tit.GetCasilla().GetCoste() + ((((Calle)(tit.GetCasilla())).GetNumeroCasas() + ((Calle)(tit.GetCasilla())).GetNumeroHoteles())*((Calle)(tit.GetCasilla())).getPrecioEdificar());
            }
        }
        
        return saldo + suma_propiedades;
    }
    
    ArrayList<TituloPropiedad> obtenerPropiedadesHipotecadas(boolean hipotecada){
        ArrayList<TituloPropiedad> mistitulos = new ArrayList();
        for(TituloPropiedad mt: propiedades){
            if(mt.GetHipotecada() == hipotecada){
                mistitulos.add(mt);
            }
        }
        return mistitulos;
    }
    
    void pagarCobrarPorCasaYHotel(int cantidad){
        int numeroTotal = this.cuantasCasasHotelesTengo();
        this.modificarSaldo(numeroTotal*cantidad);
    }
    
    boolean pagarLibertad(int cantidad){
        boolean tengoSaldo = this.tengoSaldo(cantidad);
        
        if(tengoSaldo)
            this.modificarSaldo(cantidad);
        
        return tengoSaldo;
    }
    
    boolean puedoEdificarCasa(Casilla casilla){
        boolean esMia = this.esDeMipropiedad(casilla);
        boolean tengoSaldo = true;
        
        if(esMia){
            int costeEdificarCasa = ((Calle)casilla).getPrecioEdificar();
            tengoSaldo = this.tengoSaldo(costeEdificarCasa);
        }
        
        return (esMia && tengoSaldo);
    }

    boolean puedoEdificarHotel(Casilla casilla){
        boolean esMia = this.esDeMipropiedad(casilla);
        boolean tengoSaldo = true;
        
        if(esMia){
            int costeEdificarHotel = ((Calle)casilla).getPrecioEdificar();
            tengoSaldo = this.tengoSaldo(costeEdificarHotel);
        }
        
        return esMia && tengoSaldo;
    }    

    boolean puedoHipotecar(Casilla casilla){ return this.esDeMipropiedad(casilla); }

    boolean puedoPagarHipoteca(Casilla casilla){ return ((Calle)casilla).GetTitulo().GetHipotecaBase() < saldo; }

    boolean puedoVenderPropiedad(Casilla casilla){
        return this.esDeMipropiedad(casilla) && !((Calle)casilla).estaHipotecada();
    }
    
    void setCartaLibertad(Sorpresa carta){ this.cartaLibertad = carta; }    

    void setCasillaActual(Casilla casilla){ this.casillaActual = casilla; }    

    void setEncarcelado(boolean encarcelado){ this.encarcelado = encarcelado; }    

    public boolean tengoCartaLibertad(){ return this.cartaLibertad != null; }    

    void venderPropiedad(Casilla casilla){
        int precioVenta = ((Calle)casilla).venderTitulo();
        this.modificarSaldo(-precioVenta);
        this.eliminarDeMisPropiedades(casilla);
    }    

    private int cuantasCasasHotelesTengo(){
        int total = 0;
        for(TituloPropiedad mt: propiedades){
            total += ((Calle)mt.GetCasilla()).GetNumeroCasas() + ((Calle)mt.GetCasilla()).GetNumeroHoteles();
        }
        return total;
    }    

    private void eliminarDeMisPropiedades(Casilla casilla){
        TituloPropiedad titulo = ((Calle)casilla).GetTitulo();
        int index;

        if(this.propiedades.contains(titulo)){
            index = this.propiedades.indexOf(titulo);
            this.propiedades.remove(index);
        }
    }    

    private boolean esDeMipropiedad(Casilla casilla){
        for(TituloPropiedad mt: propiedades){
            if(mt == ((Calle)casilla).GetTitulo()){
                return true;
            }
        }
        return false;
    }    

    private boolean tengoSaldo(int cantidad){ return this.saldo >= cantidad; }
    
    @Override
    public String toString() {
        return nombre + "\nSaldo -> " + saldo + "\nEncarcelado -> " + encarcelado + "\nPropiedades -> \n" + propiedades + 
                "\nCarta Libertad -> " + cartaLibertad + "\nCasilla Actual -> " + casillaActual + "\n";
    }
}
