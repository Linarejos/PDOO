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
    
    Diario midiario = Diario.getInstance();
    Dado midado = Dado.getInstance();
    
    //Constructor
    Tablero(int numCasillaCarcel){
        
         if(numCasillaCarcel >= 1)
            this.numCasillaCarcel = numCasillaCarcel;
        
        casillas = new ArrayList<Casilla>();
        Casilla Salida = new Casilla("Salida");
        casillas.add(Salida);
        
        porSalida = 0;
        tieneJuez = false;
    }
    
    /* Devuelve true si el número de elementos en casillas es mayor 
    que el índice de la casilla de la cárcel (numCasillaCarcel) y 
    que se dispone de una casilla tipo Juez. Si se cumplen todas las
    condiciones el tablero es correcto y puede usarse para jugar. */
    private boolean correcto(){
        for(int i = 0; i < casillas.size(); i++){
            if(casillas.get(i).getNombre() == "Juez")
                tieneJuez = true;
        }
        
        if(casillas.size() > this.numCasillaCarcel && tieneJuez)
            return true;        
        
        return false;        
    }
    
    /* Devuelve true si el método anterior también lo hace y además
    su parámetro es un índice válido para acceder a elementos de 
    casillas.*/
    private boolean correcto(int numCasilla){
        if(correcto() && numCasilla < casillas.size())
            return true;
        
        return false;
    }
    
    //Consultor numCasillaCarcel
    int getCarcel(){ return this.numCasillaCarcel; }
    
    /* Si el valor de porSalida es mayor que 0, decrementa su valor 
    en una unidad y devuelve el valor que tenía porSalida antes de 
    ser decrementado. En caso contrario, simplemente devuelve el valor
    de porSalida*/
    int getPorSalida(){
        int decremento_salida = porSalida;       
        
        if(porSalida > 0){
            porSalida--;
        }
        
        return decremento_salida;
    }
    
    /* Si el tamaño de casillas es igual a numCasillaCarcel, se añade
    primero a casillas una casilla denominada “Cárcel”. En cualquier 
    caso, después se añade a casillas la casilla pasada como parámetro, 
    y se comprueba si el tamaño de casillas es igual a numCasillaCarcel, 
    en cuyo caso se añade a casillas una casilla denominada “Cárcel”. */
    void añadeCasilla (Casilla casilla){
        Casilla carcel = new Casilla("Carcel");
        
        if(casillas.size() == numCasillaCarcel)
            casillas.add(carcel);
        
        casillas.add(casilla);
        
        if(casillas.size() == numCasillaCarcel)
            casillas.add(carcel);
    }
    
    /* Si aún no se ha añadido una casilla juez, se añade y se actualiza 
    el atributo tieneJuez. Se impide por tanto que se puedan añadir varias
    casillas de este tipo.*/
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
    
    /* Devuelve la casilla de la posición numCasilla si este índice es 
    válido. Devuelve null en otro caso. Utiliza internamente el método
    Boolean correcto (intnumCasilla).*/
    Casilla getCasilla (int numCasilla){        
        if(correcto(numCasilla)){
            return casillas.get(numCasilla);
        }        
        return null;
    }    
    
    /* Si el tablero no es correcto devuelve -1. En caso contrario se 
    calcula la nueva posición en el tablero asumiendo que se parte de la
    posición actual y se avanza una tirada de unidades. Esta nueva posición
    se devuelve. Adicionalmente, el método incrementa el atributo porSalida 
    si se ha producido un nuevo paso por la salida. Este hecho puede 
    comprobarse fácilmente: si la nueva posición no es el resultado de sumar 
    los parámetros actual y tirada, necesariamente se ha terminado una vuelta
    al tablero y pasado de nuevo por la salida.*/
    int nuevaPosicion (int actual, int tirada){
        int nueva;
        if(!correcto()) return -1;
        
        nueva = (actual + tirada)%casillas.size();
        
        if((actual + tirada) != nueva){
            porSalida++;
        }
        
        return nueva;
    }
    
    /* Devuelve la tirada de dado que se habría tenido que obtener para ir desde
    la casilla número origen a la casilla número destino. En la mayor parte de 
    los casos el cálculo necesario se limita a restar el origen del destino. 
    Sin embargo, si de esta resta se obtiene una valor negativo, quiere decir
    que se ha producido un paso por la salida y al resultado anterior es 
    necesario sumarle el número de casillas del tablero para obtener el valor
    correcto.*/
    int calcularTirada (int origen, int destino){ 
        int tirada = destino - origen;
        
        if(tirada < 0){
            tirada += casillas.size();
        }
        
        return tirada;
    }
}
