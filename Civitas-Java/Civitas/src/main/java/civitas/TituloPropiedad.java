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
    
    void actualizaPropietarioPorConversion(Jugador jugador){
        throw new UnsupportedOperationException("No implementado"); 
    }
    
    boolean cancelarHipoteca(Jugador jugador){
        throw new UnsupportedOperationException("No implementado"); 
    }
    
    int cantidadCasasHoteles(){
        throw new UnsupportedOperationException("No implementado"); 
    }
    
    boolean comprar(Jugador jugador){
        throw new UnsupportedOperationException("No implementado"); 
    }
    
    boolean construirCasa(Jugador jugador){
        throw new UnsupportedOperationException("No implementado"); 
    }
    
    boolean construirHotel(Jugador jugador){
        throw new UnsupportedOperationException("No implementado"); 
    }
    
    boolean derruirCasas(int n, Jugador jugador){
        throw new UnsupportedOperationException("No implementado"); 
    }
    
    private boolean esEsteElPropietario(Jugador jugador){
        throw new UnsupportedOperationException("No implementado"); 
    }
    
    public boolean getHipotecado(){
        throw new UnsupportedOperationException("No implementado"); 
    }
    
    float getImporteCancelarHipoteca(){
        return getImporteHipoteca()*factorInteresesHipoteca;
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
    
    private float getPrecioAlquiler(){
        float precioAlquiler = (float)(this.alquilerBase*(1+(this.numCasas*0.5)+(this.numHoteles*2.5)));
        
        //Si no tiene dinero, bancarrota y fin juego
        
        if(hipotecado || propietarioEncarcelado()){
            precioAlquiler = 0;
        }
        
        return precioAlquiler;
    }
    
    float getPrecioCompra(){
        throw new UnsupportedOperationException("No implementado"); 
    }
    
    float getPrecioEdificar(){
        throw new UnsupportedOperationException("No implementado"); 
    }
    
    private float getPrecioVenta(){
        throw new UnsupportedOperationException("No implementado"); 
    }
    
    Jugador getPropietario(){
        throw new UnsupportedOperationException("No implementado"); 
    }
    
    boolean hipotecar(Jugador jugador){
        throw new UnsupportedOperationException("No implementado"); 
    }
    
    private boolean propietarioEncarcelado(){
        throw new UnsupportedOperationException("No implementado"); 
    }
    
    boolean tienePropietario(){
        throw new UnsupportedOperationException("No implementado"); 
    }
    
    void tramitarAlquiler(Jugador jugador){
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
