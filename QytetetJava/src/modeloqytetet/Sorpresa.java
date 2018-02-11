/**
 * @author ELena Cantero Molina
 */
package modeloqytetet;

public class Sorpresa {
    private String texto;   //Describe la sorpresa
    private TipoSorpresa tipo;  //Indica el tipo de Sorpresa
    private int valor;  //valor de la sorpresa
    
    public Sorpresa(String texto, int valor, TipoSorpresa tipo){
        this.texto = texto;
        this.valor = valor;
        this.tipo = tipo;
    }
    
    //Consultor Texto de la Sorpresa
    String GetTexto(){ return texto; }
    //Consultor del valor de la sorpresa
    int GetValor(){ return valor; }
    //Consultor del tipo de sorpresa
    public TipoSorpresa GetTipoSorpresa(){ return tipo; }
    
    @Override
    public String toString(){   //Sobrecarga metodo toString
        return "Sorpresa -> " + texto + "\nvalor -> " + Integer.toString(valor) + " \ntipo -> " + tipo + "\n";
    }
}
