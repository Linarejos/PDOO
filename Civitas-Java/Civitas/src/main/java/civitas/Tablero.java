/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * @author elena
 * @date 20/09/2019
 */

package civitas;

import java.util.ArrayList;

public class Tablero {
    private int numCasillaCarcel = 1;
    private ArrayList<Casilla> casillas;
    private int porSalida;
    private boolean tieneJuez;
    
    //Constructor
    Tablero(int numCasillaCarcel){
        
         if(numCasillaCarcel >= 1)
            this.numCasillaCarcel = numCasillaCarcel;
        
        casillas = new ArrayList();
        Casilla Salida = new Casilla("Salida");
        casillas.add(Salida);
        
        porSalida = 0;
        tieneJuez = false;
    }
    
    private boolean correcto(){
        for(int i = 0; i < casillas.size(); i++){
            if(casillas.get(i).getNombre() == "Juez")
                tieneJuez = true;
        }
        //tieneJuez=true;
        
        if(casillas.size() > this.numCasillaCarcel && tieneJuez)
            return true;        
        
        return false;        
    }
    
    private boolean correcto(int numCasilla){
        if(correcto() && numCasilla < casillas.size())
            return true;
        
        return false;
    }
    
    int getCarcel(){ return this.numCasillaCarcel; }
    
    int getPorSalida(){
        int decremento_salida = porSalida;       
        
        if(porSalida > 0){
            porSalida--;
        }
        
        return decremento_salida;
    }
    
    void añadeCasilla (Casilla casilla){
        
        if(casillas.size() == numCasillaCarcel){
            Casilla carcel = new Casilla("Carcel");
            casillas.add(carcel); 
        }                       
        
        casillas.add(casilla);
        
        if(casillas.size() == numCasillaCarcel){
            Casilla carcel = new Casilla("Carcel");
            casillas.add(carcel);
        }
        
    }
    
    void añadeJuez(){        
        for(int i = 0; i < casillas.size(); i++){
            if(casillas.get(i).getNombre() == "Juez")
                tieneJuez = true;
        }
        
        if(!tieneJuez){
            Casilla juez = new Casilla("Juez");
            casillas.add(juez);
            tieneJuez = true;
        }            
    }
    
    Casilla getCasilla (int numCasilla){        
        if(correcto(numCasilla)){
            return casillas.get(numCasilla);
        }        
        return null;
    }    
    
    int nuevaPosicion (int actual, int tirada){
        int nueva;
        if(!correcto()) return -1;
        
        nueva = (actual + tirada)%casillas.size();
        
        if((actual + tirada) != nueva){
            porSalida++;
        }
        
        return nueva;
    }
    
    int calcularTirada (int origen, int destino){ 
        int tirada = destino - origen;
        
        if(tirada < 0){
            tirada += casillas.size();
        }
        
        return tirada;
    }
}
