/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package civitas;

//import java.util.ArrayList;

/**
 *
 * @author elena
 */
public class TestP1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {    
        //1. Comprobación método quienEmpieza
        Dado dado = Dado.getInstance();
        /*int tirada;
        
        for(int i = 0; i < 100; i++){
            tirada = dado.quienEmpieza(4);
            System.out.print(tirada);
            System.out.print(" ");
        }*/
        
        //2.Comprobación debug dado
        //debug true
        //dado.setDebug(false);
        /*for(int i = 0; i < 10; i++){            
            System.out.print(dado.tirar());
        }*/
        
        //3.Métodos getUltimoResultado y salgodelacarcel
        //System.out.print(dado.salgoDeLaCarcel());
        //System.out.print(dado.getUltimoResultado());
        
        //5.Mazo Sorpresas
        MazoSorpresas mazo = new MazoSorpresas();
        Sorpresa casilla1 = new Sorpresa("casa");
        Sorpresa casilla2 = new Sorpresa("hotel");
        Sorpresa siguiente = null;
        mazo.alMazo(casilla1);
        mazo.alMazo(casilla2);
        siguiente = mazo.siguiente();
        
    }
    
}
