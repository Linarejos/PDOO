/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package civitas;

/**
 * @author elena
 * @date 27/09/2019
 */

public class TituloPropiedad {    
    private static float factorInteresesHipoteca = (float)1.1;
    private float alquilerBase;
    private float factorRevalorizacion;
    private float hipotecaBase;
    private boolean hipotecado;
    private String nombre;
    private int numCasas;
    private int numHoteles;
    private float precioCompra;
    private float precioEdificar;
    private Jugador propietario;
    
    TituloPropiedad(String nom, float ab, float fr, float hb, float pc,float pe){
        this.nombre = nom;
        this.alquilerBase = ab;
        this.factorRevalorizacion = fr;
        this.hipotecaBase = hb;
        this.precioCompra = pc;
        this.precioEdificar = pe;
        this.propietario = null;
        this.numCasas = 0;
        this.numHoteles = 0;
        this.hipotecado = false;
    }
         
    float getImporteCancelarHipoteca(){
        return getImporteHipoteca()*factorInteresesHipoteca;
    }
    
    private float getPrecioAlquiler(){
        float precioAlquiler = (float)(this.alquilerBase*(1+(this.numCasas*0.5)+(this.numHoteles*2.5)));
                
        if(hipotecado || propietarioEncarcelado()){
            precioAlquiler = 0;
        }
        
        return precioAlquiler;
    }
    
    private float getPrecioVenta(){
        return getPrecioCompra() + this.getPrecioEdificar()*factorRevalorizacion;
    }     
    
    void tramitarAlquiler(Jugador jugador){
        if(tienePropietario() && !esEsteElPropietario(jugador)){
            float precio = getPrecioAlquiler();
            jugador.pagaAlquiler(precio);
            propietario.recibe(precio);
        }
    }
    
    private boolean propietarioEncarcelado(){ return propietario.isEncarcelado(); }
    
    int cantidadCasasHoteles(){ return numCasas + numHoteles; }
    
    boolean derruirCasas(int n, Jugador jugador){
        if(esEsteElPropietario(jugador) && getNumCasas() >= n){
            numCasas -= n;
            return true;
        }        
        return false;
    }   
    
    boolean construirCasa(Jugador jugador){
        boolean result = false;
        
        if(esEsteElPropietario(jugador)){
            propietario.paga(precioEdificar);
            numCasas += 1;
            result = true;
        }
        
        return result;
    }
    
    boolean construirHotel(Jugador jugador){
        boolean result = false;
        
        if(esEsteElPropietario(jugador)){
            propietario.paga(precioEdificar);
            numHoteles += 1;
            result = true;
        }
        
        return result;
    }
    
    boolean comprar(Jugador jugador){
        boolean result = false;
        
        if(!tienePropietario()){
            propietario = jugador;
            result = true;
            propietario.paga(precioCompra);
        }
        
        return result;
    }
    
    boolean hipotecar(Jugador jugador){
        boolean salida = false;
        
        if(!hipotecado && esEsteElPropietario(jugador)){
            propietario.recibe(getImporteHipoteca());
            hipotecado = true;
            salida = true;
        }
        
        return salida;
    }
    
    void actualizaPropietarioPorConversion(Jugador jugador){
        propietario = jugador;
    }
    
    boolean cancelarHipoteca(Jugador jugador){
        boolean result = false;
        
        if(this.getHipotecado()){
            if(this.esEsteElPropietario(jugador)){
               propietario.paga(this.getImporteCancelarHipoteca());
               hipotecado = false;
               result = true;
            }
        }
        
        return result;
    }
       
    private boolean esEsteElPropietario(Jugador jugador){ return jugador == propietario; }
    
    public boolean getHipotecado(){ return this.hipotecado; }
        
    private float getImporteHipoteca(){ return this.hipotecaBase; }
    
    public String getNombre(){ return this.nombre; }
    
    //Cambiada visibilidad
    public int getNumCasas(){ return this.numCasas; }
    
    //Cambiada visibilidad
    public int getNumHoteles(){ return this.numHoteles; }
    
    float getPrecioCompra(){ return this.precioCompra; }
    
    float getPrecioEdificar(){ return this.precioEdificar; }
            
    Jugador getPropietario(){ return this.propietario; }   
    
    boolean tienePropietario(){ return propietario != null; }
           
    boolean vender(Jugador jugador){
        if(esEsteElPropietario(jugador) && !getHipotecado()){
            jugador.recibe(getPrecioVenta());
            propietario = null;
            numCasas = 0;
            numHoteles = 0;
            return true;
        }
        
        return false;
    }
    
    @Override
    public String toString(){
        return "Nombre : " + this.nombre + 
                "\nAlquiler Base : " + this.alquilerBase +
                "\nFactor Revalorización : " + this.factorRevalorizacion +
                "\nHipoteca Base : " + this.hipotecaBase + 
                "\nPrecio Compra : " + this.precioCompra +
                "\nPrecio Edificar : " + this.precioEdificar +
                "\nPropietario : " + this.propietario +
                "\nNumero Casas : " + this.numCasas +
                "\nNumero Hoteles : " + this.numHoteles +
                "\nHipotecado : " + this.hipotecado;
        
    }
}
