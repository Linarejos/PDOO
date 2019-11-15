/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package civitas;

import java.util.Random;

/**
 * @author elena
 * @date 26/09/2019
 */

public class Dado {    
    private static final Dado instance = new Dado();
    private static int SalidaCarcel = 5;
    private int random;
    private int ultimoResultado;
    private boolean debug;
      
    public static Dado getInstance(){
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
            ultimoResultado = rand.nextInt(4);
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
    
    public void setDebug(boolean d){
        Diario diariodado = Diario.getInstance();
        debug = d;
        
        if(d)   diariodado.ocurreEvento("Debug activo");
        else    diariodado.ocurreEvento("Debug desactivado");
    }
    
    int getUltimoResultado(){
        return ultimoResultado;
    }
}
