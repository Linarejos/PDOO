/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package civitas;

import java.util.Random;

/**
 *
 * @author elena
 */
public class Dado {    
    static final private Dado instance = new Dado();
    static private int SalidaCarcel;
    private int random;
    private int ultimoResultado;
    private boolean debug;
      
    static Dado getInstance(){
        return instance;
    }
  
    private Dado(){
        SalidaCarcel = 5;
        ultimoResultado = 0;
        random = 0;
        debug = false;
    }
    
    int tirar(){ 
        Random rand = new Random();
        if(!debug){
            ultimoResultado = rand.nextInt(7);
        }
        else
            ultimoResultado = 1;
        
        return ultimoResultado;
    }
    
    boolean salgoDeLaCarcel(){
        int dado = tirar();
        
        return dado >= 5;
    }
    
    int quienEmpieza(int n){
        Random rand = new Random();
        return rand.nextInt(n);
    }
    
    void setDebug(boolean d){
        Diario diariodado = Diario.getInstance();
        debug = d;
        
        if(d)   diariodado.ocurreEvento("Debug activo");
        else    diariodado.ocurreEvento("Debug desactivado");
    }
    
    int getUltimoResultado(){
        return ultimoResultado;
    }
}
