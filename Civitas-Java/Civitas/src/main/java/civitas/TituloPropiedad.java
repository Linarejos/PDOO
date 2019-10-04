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
        throw new UnsupportedOperationException("No implementado"); 
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
    
    private float getPrecioAlquiler(){
        throw new UnsupportedOperationException("No implementado"); 
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
    
    public String toString(){
        throw new UnsupportedOperationException("No implementado"); 
    }
}
