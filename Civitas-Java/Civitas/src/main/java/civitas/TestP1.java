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
        //Dado dado = Dado.getInstance();
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
        
        //4.Mostrar enumerados
        /*System.out.print("Tipo Casilla -> ");
        System.out.print(TipoCasilla.CALLE);
        System.out.print("Tipo Sorpresa -> ");
        System.out.print(TipoSorpresa.IRCARCEL);*/        
        
        //5.Mazo Sorpresas
        /*MazoSorpresas mazo = new MazoSorpresas();
        Sorpresa casilla1 = new Sorpresa("casa");
        Sorpresa casilla2 = new Sorpresa("hotel");
        mazo.alMazo(casilla1);
        mazo.alMazo(casilla2);
        Sorpresa siguiente = null;
        siguiente = mazo.siguiente();
        mazo.inhabilitarCartaEspecial(siguiente);
        mazo.habilitarCartaEspecial(siguiente);*/
        
        //6.Probar Diario
        /*Diario diario = Diario.getInstance();
        System.out.print(diario.eventosPendientes());
        System.out.print(diario.leerEvento());*/

        //7.Creación tablero
        /*Tablero tablero = new Tablero(5);
        Casilla casa = new Casilla("casa");
        tablero.añadeCasilla(casa);
        tablero.añadeCasilla(new Casilla("hotel"));
        tablero.añadeCasilla(new Casilla("Juez"));
        tablero.añadeCasilla(new Casilla("moto"));
        tablero.añadeCasilla(new Casilla("avion"));
        tablero.añadeCasilla(new Casilla("barco"));
        for(int i = 0; i < 4; i++)
            System.out.print(tablero.getCasilla(i).getNombre());*/
        /*System.out.print(tablero.getCasilla(2).getNombre());
        System.out.print(tablero.getCasilla(3).getNombre());
        System.out.print(tablero.getCasilla(4).getNombre());*/
    }
    
}
