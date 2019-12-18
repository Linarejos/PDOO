/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package civitas;

import GUI.Dado;
import java.util.ArrayList;

/**
 * @author elena
 */
public class Jugador implements Comparable<Jugador>{
    protected static int CasasMax = 4;
    protected static int CasasPorHotel = 4;
    protected boolean encarcelado;
    protected static int HotelesMax = 4;
    private String nombre;
    private int numCasillaActual;
    protected static float PasoPorSalida = 1000;
    protected static float PrecioLibertad = 500;
    private boolean puedeComprar;
    private float saldo;
    private static float SaldoInicial = 7500;
    private ArrayList<TituloPropiedad> propiedades;
    private SorpresaSALIRCARCEL salvoconducto; //tipo = SALIRCARCEL
    
    Jugador(String nombre){ 
        this.nombre = nombre;
        numCasillaActual = 0;
        encarcelado = false;
        puedeComprar = true;
        saldo = SaldoInicial;
        propiedades = new ArrayList();
        salvoconducto = null;
    }
    
    protected Jugador(Jugador otro){ 
        this.nombre = otro.nombre;
        this.numCasillaActual = otro.numCasillaActual;
        this.encarcelado = otro.encarcelado;
        this.puedeComprar = otro.puedeComprar;
        this.saldo = otro.saldo;
        this.propiedades = otro.propiedades;
        this.salvoconducto = otro.salvoconducto;
    }
    
    public boolean esEspeculador(){ return false; }
    
    protected boolean debeSerEncarcelado(){ 
        boolean debeser = false;
        
        if(!this.isEncarcelado()){
            if(!tieneSalvoconducto()){
                debeser = true;
            }
            else{
                perderSalvoConducto();
                Diario.getInstance().ocurreEvento("El jugador se libra de la carcel");
                debeser = false;
            }
        }
        
        return debeser;
    }
    
    boolean encarcelar(int numCasillaCarcel){
        if(debeSerEncarcelado()){
            moverACasilla(numCasillaCarcel);
            encarcelado = true;
            Diario.getInstance().ocurreEvento("El jugador va directo a la carcel");
        }
        
        return encarcelado;
    }
    
    boolean obtenerSalvoconducto(SorpresaSALIRCARCEL sorpresa){
        if(!isEncarcelado()){
            salvoconducto = sorpresa;
            return true;
        }        
        return false;
    }
    
    public void perderSalvoConducto(){
        salvoconducto.usada();
        salvoconducto = null;
    }
    
    boolean tieneSalvoconducto(){ return salvoconducto != null; }
    
    boolean puedeComprarCasilla(){
        puedeComprar = !isEncarcelado();
        
        return puedeComprar;
    }
    
    boolean paga(float cantidad){ return modificarSaldo(cantidad*-1); }
    
    boolean pagaImpuesto(float cantidad){
        if(!isEncarcelado()){ return paga(cantidad); }
        return false;
    }
    
    boolean pagaAlquiler(float cantidad){
        if(!isEncarcelado()){ return paga(cantidad); }
        return false;
    }
    
    boolean recibe(float cantidad){ 
        if(!isEncarcelado()){
            return modificarSaldo(cantidad);
        }
        return false;
    }
    
    boolean modificarSaldo(float cantidad){
        saldo += cantidad;
        Diario.getInstance().ocurreEvento("Modificando saldo del jugador actual");
        return true;
    }
    
    boolean moverACasilla(int numCasilla){
        if(!isEncarcelado()){
            this.numCasillaActual = numCasilla;
            puedeComprar = true;
            Diario.getInstance().ocurreEvento("Moviendose a la casilla " + numCasilla);
            return true;
        }
        return false;
    }
    
    boolean puedoGastar(float precio){
        if(!isEncarcelado()) return this.saldo >= precio;
        return false;
    }    
    
    boolean vender(int ip){
        if(!isEncarcelado()){
            if(existeLaPropiedad(ip)){
                boolean correcto = propiedades.get(ip).vender(this);
                if(correcto){
                    propiedades.remove(ip);
                    Diario.getInstance().ocurreEvento("Venta de la propiedad actual");
                    return true;
                }
                else return false;
            }
            else return false;
        }
        return false;
    }
    
    boolean tieneAlgoQueGestionar(){ return propiedades != null; }
    
    private boolean puedeSalirCarcelPagando(){ return saldo >= PrecioLibertad; }
    
    boolean salirCarcelPagando(){
        if(isEncarcelado() && puedeSalirCarcelPagando()){
            paga(PrecioLibertad);
            encarcelado = false;
            Diario.getInstance().ocurreEvento("Salir de la carcel pagando");
            return true;
        }        
        return false;
    }
    
    boolean salirCarcelTirando(){
        if(Dado.getInstance().salgoDeLaCarcel()){
            encarcelado = false;
            Diario.getInstance().ocurreEvento("Salir de la carcel tirando");
            return true;            
        }
        return false;
    }
    
    boolean pasaPorSalida(){
        modificarSaldo(getPremioPasoSalida());
        Diario.getInstance().ocurreEvento("Pasar por salida");
        return true;
    }
    
    public int compareTo(Jugador otro){
        if(Float.compare(saldo, otro.getSaldo()) == 0){
            return 0;   //Ambos saldo iguales
        }
        else if(Float.compare(saldo, otro.getSaldo()) > 0){
            return 1;   //Saldo del jugador mayor que el otro jugador
        }
        else{
            return 2;   //Saldo del otro jugador mayor que el jugador
        }
    }        
    
    int cantidadCasasHoteles(){
        int numCasasHoteles = 0;
        for(TituloPropiedad p : propiedades){
            numCasasHoteles += p.cantidadCasasHoteles();
        }
        
        return numCasasHoteles;
    }
    
    boolean enBancarrota(){ return saldo < 0; }
    
    private boolean existeLaPropiedad(int ip){
        for(int i = 0; i < propiedades.size(); i++){
            if(propiedades.get(i) == propiedades.get(ip)){
                return true;
            }
        }
        return false;
    }
    
    int getCasasMax(){ return CasasMax; }
    
    int getCasasPorHotel(){ return CasasPorHotel; }   
    
    int getHotelesMax(){ return HotelesMax; }
    
    public String getNombre(){ return nombre; }
    
    public int getNumCasillaActual(){ return numCasillaActual; }
    
    private float getPrecioLibertad(){ return PrecioLibertad; }
    
    private float getPremioPasoSalida(){ return PasoPorSalida; }
    //Cambiada visibilidad
    public ArrayList<TituloPropiedad> getPropiedades(){ return propiedades; }    
    
    boolean getPuedeComprar(){ return puedeComprar; }
    
    public float getSaldo(){ return saldo; }
    
    public boolean isEncarcelado(){ return encarcelado; }
       
    private boolean puedoEdificarCasa(TituloPropiedad propiedad){ 
        float precio = propiedad.getPrecioEdificar();
        boolean puedoEdificarCasa = false;
        
        if(puedoGastar(precio) && propiedad.getNumCasas() < getCasasMax())
            puedoEdificarCasa = true;
        
        return puedoEdificarCasa;
    }
    
    private boolean puedoEdificarHotel(TituloPropiedad propiedad){ 
        boolean puedoEdificarHotel = false;        
        float precio = propiedad.getPrecioEdificar();
        
        if(puedoGastar(precio)){
            if(propiedad.getNumHoteles() < getHotelesMax()){
                if(propiedad.getNumCasas() >= getCasasPorHotel()){
                    puedoEdificarHotel = true;
                }
            }
        }     
        
        return puedoEdificarHotel;
    }
    
    boolean cancelarHipoteca(int ip){
        boolean result = false;
        
        if(this.isEncarcelado())
            return result;  //2        
        
        if(this.existeLaPropiedad(ip)){
            TituloPropiedad propiedad = propiedades.get(ip);    //3
            float cantidad = propiedad.getImporteCancelarHipoteca();    //4
            boolean puedoGastar = this.puedoGastar(cantidad);  //5
            
            if(puedoGastar){
                result = propiedad.cancelarHipoteca(this);
                
                if(result){
                    Diario.getInstance().ocurreEvento("El jugador " + nombre + " cancela la hipoteca de la propiedad " + ip);
                }
            }
        }
        
        return result;
    }
    
    boolean comprar(TituloPropiedad titulo ){
        boolean result = false;
        
        if(isEncarcelado())
            return result;
        
        if(puedeComprar){
            float precio = titulo.getPrecioCompra();
            
            if(puedoGastar(precio)){
                result = titulo.comprar(this);
                
                if(result){
                    propiedades.add(titulo);
                    Diario.getInstance().ocurreEvento("El jugador " + nombre + " compra la propiedad " + titulo.toString());
                }
                
                puedeComprar = false;
            }
        }
        
        return result;
    }    
    
    boolean construirCasa(int ip){
        boolean result = false;
        
        if(isEncarcelado())
            return result;
        
        if(existeLaPropiedad(ip)){
            TituloPropiedad propiedad = propiedades.get(ip);
            boolean puedoEdificarCasa = this.puedoEdificarCasa(propiedad);
            
            float precio = propiedad.getPrecioEdificar();
            
            if(puedoGastar(precio) && propiedad.getNumCasas()<getCasasMax()){
                puedoEdificarCasa = true;
            }
            
            if(puedoEdificarCasa){
                result = propiedad.construirCasa(this);
            }
            
            Diario.getInstance().ocurreEvento("El jugador " + nombre + " construye casa en la propiedad " + ip);
        }
        
        return result;
    }    
    
    boolean construirHotel(int ip){
        boolean result = false;
        
        if(isEncarcelado())
            return result;
        
        if(existeLaPropiedad(ip)){
            TituloPropiedad propiedad = propiedades.get(ip);
            boolean puedoEdificarHotel = this.puedoEdificarHotel(propiedad);
            
            float precio = propiedad.getPrecioEdificar();
            
            if(puedoGastar(precio) && propiedad.getNumHoteles()<getHotelesMax() && propiedad.getNumCasas()>=getCasasPorHotel()){
                puedoEdificarHotel = true;
            }
            
            if(puedoEdificarHotel){
                result = propiedad.construirHotel(this);
                int casasPorHotel = getCasasPorHotel();
                propiedad.derruirCasas(casasPorHotel, this);
            }
            
            Diario.getInstance().ocurreEvento("El jugador " + nombre + " construye hotel en la propiedad " + ip);
        }
        
        return result;
    }    
    
    boolean hipotecar(int ip){ 
        boolean result = false;
        
        if(isEncarcelado())
            return result;
        
        if(existeLaPropiedad(ip)){
            TituloPropiedad propiedad = propiedades.get(ip);
            result = propiedad.hipotecar(this);            
        }
        if(result){
            Diario.getInstance().ocurreEvento("El jugador " + nombre + " hipoteca la propiedad " + ip);
        }
        
        return result;
    }            
              
    @Override
    public String toString(){
        return "\nNombre: " + this.nombre +
               "\nSaldo: " + this.saldo +
               "\nEncarcelado: " + this.encarcelado +
               "\nCasilla Actual: " + this.numCasillaActual +
               "\nPropiedades: " + this.propiedades.size() +
               "\nPuede Comprar: " + this.puedeComprar +               
               "\nSalvo Conducto: " + this.tieneSalvoconducto();
    }    
}
