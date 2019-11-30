/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package civitas;

/**
 * @author elena
 * @date 29/11/2019
 */
public class JugadorEspeculador extends Jugador{
    private int fianza;
    private static int FactorEspecualdor = 2;
    
    JugadorEspeculador(Jugador otro){
        super(otro);
        fianza = 500;
    }
    
}
