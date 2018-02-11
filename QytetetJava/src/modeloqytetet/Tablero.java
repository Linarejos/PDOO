/**
 * @author Elena Cantero Molina
 */
package modeloqytetet;
import java.util.*;

public class Tablero {
    private ArrayList<Casilla> casillas;    //Array para contener las casillas
    private Casilla carcel; //indica la cárcel
    
    public Tablero(){
        inicializar();
    }
    
    private void inicializar(){
        casillas = new ArrayList();
        
        casillas.add(new OtraCasilla(0, 0, TipoCasilla.SALIDA));
        casillas.add(new Calle(1, 300, new TituloPropiedad("Calle Tranvia", 50, 10, 250, 150)));
        casillas.add(new OtraCasilla(2, -100, TipoCasilla.IMPUESTO));
        casillas.add(new Calle(3, 400, new TituloPropiedad("Calle Vengala", 55, 16, 280, 300)));
        casillas.add(new Calle(4, 450, new TituloPropiedad("Calle Tren", 60, 18, 300, 450)));
        casillas.add(new OtraCasilla(5, 0, TipoCasilla.CARCEL));
        casillas.add(new Calle(6, 500, new TituloPropiedad("Calle Final", 60, 20, 350, 500)));
        casillas.add(new OtraCasilla(7, 0, TipoCasilla.SORPRESA));
        casillas.add(new Calle(8, 550, new TituloPropiedad("Calle Reinado", 65, 15, 400, 600)));
        casillas.add(new Calle(9, 650, new TituloPropiedad("Calle Europa", 70, 17, 450, 650)));
        casillas.add(new OtraCasilla(10, 0, TipoCasilla.PARKING));
        casillas.add(new Calle(11, 700, new TituloPropiedad("Calle Periodistas", 70, 14, 500, 700)));
        casillas.add(new Calle(12, 700, new TituloPropiedad("Calle Cabezas", 75, 16, 550, 750)));
        casillas.add(new OtraCasilla(13, 0, TipoCasilla.SORPRESA));
        casillas.add(new Calle(14, 750, new TituloPropiedad("Calle Molina", 80, 20, 600, 800)));
        casillas.add(new OtraCasilla(15, 0, TipoCasilla.JUEZ));
        casillas.add(new Calle(16, 800, new TituloPropiedad("Calle America", 90, 18, 650, 850)));
        casillas.add(new Calle(17, 900, new TituloPropiedad("Calle Humilladero", 95, 12, 700, 900)));
        casillas.add(new OtraCasilla(18, 0, TipoCasilla.SORPRESA));
        casillas.add(new Calle(19, 950, new TituloPropiedad("Calle Princesa", 100, 19, 750, 950)));
        
        carcel = casillas.get(5);
    }
    
    Casilla GetCarcel(){ return carcel; }
    
    boolean esCasillaCarcel(int numeroCasilla){
        return numeroCasilla == this.GetCarcel().GetNumeroCasilla();
    }    

    //precondicion -> numeroCasilla > 0 && numeroCasilla < 20
    Casilla obtenerCasillaNumero(int numeroCasilla){
        return this.casillas.get(numeroCasilla);
    }    

    Casilla obtenerNuevaCasilla(Casilla casilla, int desplazamiento){
        int numero_casilla = casilla.GetNumeroCasilla();
        numero_casilla = (numero_casilla + desplazamiento) % casillas.size();
        return this.casillas.get(numero_casilla);
    }
    
    @Override
    public String toString(){
        return "Tablero -> \n" + this.casillas + "\nCarcel -> " + this.GetCarcel() + "\n";
    }
}
