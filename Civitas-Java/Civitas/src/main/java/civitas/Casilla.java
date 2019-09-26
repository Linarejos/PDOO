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

public class Casilla {
    private String nombre;
    
    Casilla(String nombre){
        this.nombre = nombre;
    }
    
    String getNombre(){ return nombre; }
}
