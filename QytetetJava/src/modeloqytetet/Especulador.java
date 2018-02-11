/**
 * @author Elena Cantero Molina
 */
package modeloqytetet;

public class Especulador extends Jugador{
    static int factorEspeculador = 2;
    int fianza;
        
    protected Especulador(Jugador jugador, int fianza){
        super(jugador);
        this.fianza = fianza;
    }
    
    @Override
    protected void pagarImpuestos(int cantidad){
        modificarSaldo(-(cantidad/2));
    }
    
    @Override
    protected void irACarcel(Casilla casilla){
        if(pagarFianza(fianza)){
            modificarSaldo(-fianza);
        }
        else{
            setCasillaActual(casilla);
            setEncarcelado(true);
        }
    }
    
    @Override
    protected Especulador convertirme(int fianza){ return this; }
    
    boolean pagarFianza(int cantidad){return cantidad < getSaldo(); }

    @Override
    public String toString() {
        return "Especulador{" +super.toString()+ "fianza=" + fianza + "}\n";
    }
}
