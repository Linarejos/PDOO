/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package civitas;

/**
 * @author elena
 * @date 26/09/2019
 */

import java.util.ArrayList;
import java.util.Random;

public class MazoSorpresas {
    private ArrayList<Sorpresa> sorpresas;
    private boolean barajada;
    private int usadas;
    private boolean debug;
    private ArrayList<Sorpresa> cartasEspeciales;
    private Sorpresa ultimaSorpresa;
    Diario diariomazo = Diario.getInstance();
    
    private void init(){
        sorpresas = new ArrayList();
        cartasEspeciales = new ArrayList();
        barajada = false;
        usadas = 0;
    }
    
    MazoSorpresas(boolean debug){
        this.debug = debug;
        init();       
        
        if(debug)
            diariomazo.ocurreEvento("Debug");
    }
    
    MazoSorpresas(){
        init();
        this.debug = false;
    }
    
    void alMazo(Sorpresa s){
        if(!barajada)
            sorpresas.add(s);        
    }
    
    Sorpresa siguiente(){
        if(!barajada || usadas == sorpresas.size()){
            if(!debug){
                barajar();
                usadas = 0;
                barajada = true;
            }                
        }
        
        usadas++;
        ultimaSorpresa = sorpresas.get(0);
        sorpresas.remove(0);
        sorpresas.add(ultimaSorpresa);
        
        return ultimaSorpresa;
    }
    
    void barajar(){   
        Random rand = new Random();

        for(int i = 0; i < sorpresas.size(); i++){ 
            int segunda = rand.nextInt(sorpresas.size());

            Sorpresa temp = sorpresas.get(i);
            sorpresas.set(i, sorpresas.get(segunda));
            sorpresas.set(segunda, temp);
        } 
    }
    
    void inhabilitarCartaEspecial(Sorpresa sorpresa){
        for(int i = 0; i < sorpresas.size(); i++){
            if(sorpresa == sorpresas.get(i)){
                sorpresas.remove(i);
                cartasEspeciales.add(sorpresa);
                diariomazo.ocurreEvento("Inhabilitar carta especial");
            }
        }
    }
    
    void habilitarCartaEspecial(Sorpresa sorpresa){
        for(int i = 0; i < cartasEspeciales.size(); i++){
            if(sorpresa == cartasEspeciales.get(i)){
                cartasEspeciales.remove(i);
                sorpresas.add(sorpresa);
                diariomazo.ocurreEvento("Habilitar carta especial");
            }
        }
    }
}
