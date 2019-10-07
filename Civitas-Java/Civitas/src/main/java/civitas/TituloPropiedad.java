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
        
        //Si no tiene dinero, bancarrota y fin juego
        
        if(hipotecado || propietarioEncarcelado()){
            precioAlquiler = 0;
        }
        
        return precioAlquiler;
    }
    
    private float getPrecioVenta(){
        return getPrecioCompra() + this.getPrecioEdificar()*factorRevalorizacion;
    }
    
    boolean cancelarHipoteca(Jugador jugador){
        if(!getHipotecado() && esEsteElPropietario(jugador)){
            jugador.paga(getImporteCancelarHipoteca());
            hipotecado = false;
            return true;
        }        
        return false;
    }
    
    boolean hipotecar(Jugador jugador){
        if(!getHipotecado() && esEsteElPropietario(jugador)){
            jugador.recibe(this.getImporteHipoteca());
            hipotecado = true;
            return true;
        }
        
        return false;
    }
    
    void tramitarAlquiler(Jugador jugador){
        if(!getHipotecado() && !esEsteElPropietario(jugador)){
            jugador.pagaAlquiler(this.getImporteHipoteca());
            propietario.recibe(getImporteHipoteca());
        }
    }
    
    private boolean propietarioEncarcelado(){ return propietario.encarcelado; }
    
    int cantidadCasasHoteles(){ return numCasas + numHoteles; }
    
    boolean derruirCasas(int n, Jugador jugador){
        if(esEsteElPropietario(jugador) && getNumCasas() >= n){
            numCasas -= n;
            return true;
        }
        
        return false;
    }   
    
    boolean construirCasa(Jugador jugador){
        boolean construido = false;
        
        if(esEsteElPropietario(jugador)){
            jugador.paga(getPrecioCompra());
            numCasas++;
            construido = true;
        }
        
        return construido;
    }
    
    boolean construirHotel(Jugador jugador){
        boolean construido = false;
        
        if(esEsteElPropietario(jugador)){
            jugador.paga(getPrecioCompra());
            numHoteles++;
            construido = true;
        }
        
        return construido;
    }
    
    boolean comprar(Jugador jugador){ return propietario == null; }
    
    void actualizaPropietarioPorConversion(Jugador jugador){
        throw new UnsupportedOperationException("No implementado"); 
    }
       
    private boolean esEsteElPropietario(Jugador jugador){
        throw new UnsupportedOperationException("No implementado"); 
    }
    
    public boolean getHipotecado(){
        throw new UnsupportedOperationException("No implementado"); 
    }
        
    private float getImporteHipoteca(){
        throw new UnsupportedOperationException("No implementado"); 
    }
    
    String getNombre(){
        throw new UnsupportedOperationException("No implementado"); 
    }
    
    int getNumCasas(){
        throw new UnsupportedOperationException("No implementado"); 
    }
    
    int getNumHoteles(){
        throw new UnsupportedOperationException("No implementado"); 
    }
    
    float getPrecioCompra(){
        throw new UnsupportedOperationException("No implementado"); 
    }
    
    float getPrecioEdificar(){
        throw new UnsupportedOperationException("No implementado"); 
    }
            
    Jugador getPropietario(){
        throw new UnsupportedOperationException("No implementado"); 
    }   
    
    boolean tienePropietario(){
        throw new UnsupportedOperationException("No implementado"); 
    }
           
    boolean vender(Jugador jugador){
        throw new UnsupportedOperationException("No implementado"); 
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
