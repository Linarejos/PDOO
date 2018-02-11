/**
 * @author Elena Cantero Molina
 */
package modeloqytetet;

public abstract class Casilla {
    protected int numeroCasilla;
    protected int coste;  
    //private int numHoteles; 
    //private int numCasas;   
    protected TipoCasilla tipo;
    //private TituloPropiedad titulo;
    
    public Casilla(int numeroCasilla, int coste, TipoCasilla tipo){
        this.numeroCasilla = numeroCasilla;
        this.coste = coste;
        this.tipo = tipo;
    }    
    
    /*public Casilla(int numeroCasilla, int coste, TipoCasilla tipo, TituloPropiedad titulo){
        this.numeroCasilla = numeroCasilla;
        this.coste = coste;
        this.numCasas = 0;
        this.numHoteles = 0;
        this.tipo = tipo;
        this.titulo = titulo;
    }*/
        
    public int GetNumeroCasilla(){ return this.numeroCasilla; }

    public int GetCoste(){ return this.coste; }

    public TipoCasilla GetTipo(){ return this.tipo; }  

    boolean soyEdificable(){ return this.GetTipo() == TipoCasilla.CALLE; }    

    private void asignarTituloPropiedad(){
    }

    @Override
    public String toString() {
        return "Casilla -> " + numeroCasilla + "\ncoste -> " + coste + "\ntipo -> " + tipo + '\n';
    }
}