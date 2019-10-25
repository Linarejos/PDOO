/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package civitas;

/**
 * @author elena
 * @date 25/10/2019
 */
public class OperacionInmobiliaria {
    private int numPropiedad;
    private GestionesInmobiliarias gestion;
    
    public OperacionInmobiliaria(GestionesInmobiliarias gest, int ip){
        numPropiedad = ip;
        gestion = gest;
    }
    
    public GestionesInmobiliarias getGestion(){ return gestion; }    
    public int getNumPropiedad(){ return numPropiedad; }
}
