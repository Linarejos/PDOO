/**
 * @author Elena Cantero Molina
 */
package modeloqytetet;

public class OtraCasilla extends Casilla{
    public OtraCasilla(int nnumeroCasilla,int ncoste, TipoCasilla ntipo){
        super(nnumeroCasilla, ncoste, ntipo);
    }
    public TipoCasilla getTipo(){
        return tipo;
    }
}
