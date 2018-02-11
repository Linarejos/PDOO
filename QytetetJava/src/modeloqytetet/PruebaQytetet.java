/**
 * @author Elena Cantero Molina
 */
package modeloqytetet;
import java.util.*;

public class PruebaQytetet{
    //private static ArrayList<Sorpresa> mazo = new ArrayList();
   // private static Tablero tablero = new Tablero();
    
    /*private static void inicializarSorpresas(){
        mazo.add(new Sorpresa("Sales de fiesta y te encuentras en una esquina dinero, ¡la suerte está de tu parte!", 100, TipoSorpresa.PAGARCOBRAR));
        mazo.add(new Sorpresa("Tu coche se avería en medio de la autovía, debes pagar a la grúa y el mecánico.", 500, TipoSorpresa.PAGARCOBRAR));
        mazo.add(new Sorpresa("Has sido pillado copiando durante un examen, ¡debes ir a la carcel!", tablero.GetCarcel().GetNumeroCasilla() ,TipoSorpresa.IRACASILLA));
        mazo.add(new Sorpresa("¡Ha llegado un ataque zombie! Sales corriendo a la casilla 2 que es la única libre.", 6, TipoSorpresa.IRACASILLA));
        mazo.add(new Sorpresa("Un ovni ha llegado a la Tierra y después de succionarte el cerebro te dejan en la casilla 15", 15, TipoSorpresa.IRACASILLA));
        mazo.add(new Sorpresa("Has sido elegido alcalde, recibes una indemnización por cada propiedad que tengas", 100, TipoSorpresa.PORCASAHOTEL));
        mazo.add(new Sorpresa("Has sufrido fugas de agua en cada una de tus propiedades. Debes pagar por cada una de ellas.", 100, TipoSorpresa.PORCASAHOTEL));
        mazo.add(new Sorpresa("¡Felicidades! Hoy es el día de tu no cumpleaños, recibes un regalo de todos.", 200, TipoSorpresa.PORJUGADOR));
        mazo.add(new Sorpresa("Sin querer produciste un apagón en el vecindario. Debes pagar los daños producidos a cada jugador.", 100, TipoSorpresa.PORJUGADOR));
        mazo.add(new Sorpresa("Tienes un admirador secreto que ha pagado tu fianza. Sales de la cárcel.", 0, TipoSorpresa.SALIRCARCEL));
    }
    
    private static ArrayList Mayorque0(){
        ArrayList<Sorpresa> mayor = new ArrayList();
        
        for (Sorpresa mazo1 : mazo) {
            if(mazo1.GetValor() > 0){
                mayor.add(mazo1);
            }
        }
        return mayor;
    }
    
    private static ArrayList SorpresaIraCasilla(){
        ArrayList<Sorpresa> ircasilla = new ArrayList();
        
        for (Sorpresa mazo1 : mazo) {
            if(mazo1.GetTipoSorpresa() == TipoSorpresa.IRACASILLA){
                ircasilla.add(mazo1);
            }
        }
        return ircasilla;
    }
    
    private static ArrayList TipoSorpresaIndicado(TipoSorpresa tipo){
        ArrayList<Sorpresa> tiposorpresa = new ArrayList();
        
        for (Sorpresa mazo1 : mazo) {
            if(mazo1.GetTipoSorpresa() == tipo){
                tiposorpresa.add(mazo1);
            }
        }        
        return tiposorpresa;
    }*/
    
    public static void main(String[] args) {
        //inicializarSorpresas();
        
        //System.out.println(mazo.toString());
        //System.out.println(Mayorque0());
        //System.out.println(SorpresaIraCasilla());
        //System.out.println(TipoSorpresaIndicado(TipoSorpresa.SALIRCARCEL));
        //System.out.print(tablero.toString());
        Qytetet mic = Qytetet.getInstance();
        System.out.print(mic.toString());
    }
    
}
