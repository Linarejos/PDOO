/**
 * @author Elena Cantero Molina
 */
package modeloqytetet;

public class Calle extends Casilla{
    private int numHoteles;
    private int numCasas;
    TituloPropiedad titulo = null;
    
    Calle(int numeroCasilla, int coste, TituloPropiedad titulo){
        super(numeroCasilla, coste, TipoCasilla.CALLE);
        numHoteles = 0;
        numCasas = 0;
        SetTituloPropiedad(titulo);
        this.titulo.casilla = this;
    }
    
    public TituloPropiedad getTitulo(){ return titulo; }
    
    public int GetNumeroCasas(){ return this.numCasas; }

    public int GetNumeroHoteles(){ return this.numHoteles; }
    
    public TituloPropiedad GetTitulo(){ return this.titulo; }
    
    public void SetNumCasas(int numCasas){ this.numCasas = numCasas; }

    public void SetNumHoteles(int numHoteles){ this.numHoteles = numHoteles; }

    private void SetTituloPropiedad(TituloPropiedad titulo){ this.titulo = titulo; }
    
    TituloPropiedad asignarPropietario(Jugador jugador){
        titulo.setPropietario(jugador);
        titulo.propietario = jugador;
        return titulo;
    }
    
    int calcularValorHipoteca(){ return titulo.GetHipotecaBase()  + (int)(numCasas*0.5 * titulo.GetHipotecaBase() + numHoteles * titulo.GetHipotecaBase()); }

    int cancelarHipoteca(){
        titulo.SetHipotecada(false);
        return ((int) (1.1 * calcularValorHipoteca()));
    }

    int cobrarAlquiler(){
        int costeAlquilerBase = titulo.GetAlquilerBase();
        titulo.cobrarAlquiler(costeAlquilerBase + (int)(numCasas*0.5 + numHoteles*2));
        return costeAlquilerBase;
    }    

    int edificarCasa(){
        this.SetNumCasas(numCasas+1);
        return titulo.GetPrecioEdificar();
    }

    int edificarHotel(){
        this.SetNumHoteles(numHoteles+1);
        return titulo.GetPrecioEdificar();
    }    
    
    boolean estaHipotecada(){ return this.titulo.GetHipotecada(); }
    
    int getPrecioEdificar(){ return titulo.GetPrecioEdificar(); }    
    
    int hipotecar(){ 
        titulo.SetHipotecada(true);
        return this.calcularValorHipoteca(); 
    }
    
    int precioTotalComprar(){
        int precio = GetCoste() + (numCasas + numHoteles)*titulo.GetPrecioEdificar();
        precio = precio + (int) (precio*titulo.GetFactorRevalorizacion());
        return precio;
    }    
    
    boolean propietarioEncarcelado(){ return this.titulo.propietarioEncarcelado(); }

    public boolean sePuedeEdificarCasa(){        
        if (Jugador.factorEspeculador == 2){
            return numCasas < 8;
        }
        else{
            return numCasas < 4;
        }
    }

    boolean sePuedeEdificarHotel(){
        if (Jugador.factorEspeculador == 2){
            return numCasas==8 && numHoteles < 8;
        }
        else{
            return numCasas==4 && numHoteles < 4;
        }
    }  
    
    boolean tengoPropietario(){ return this.titulo.tengoPropietario(); }    

    int venderTitulo(){
        int precioComprar = GetCoste() + (numCasas + numHoteles)*titulo.GetPrecioEdificar();
        titulo.setPropietario(null);
        this.SetNumCasas(0);
        this.SetNumHoteles(0);
        return precioComprar + (int)(titulo.GetFactorRevalorizacion()*precioComprar);
    }
    
    @Override
    public String toString() {
        return "Calle -> " + "\ntitulo -> " + this.GetTitulo()+ "\nnumHoteles -> " + this.GetNumeroHoteles() + "\nnumCasas -> " + this.GetNumeroCasas() + '\n';
    }

}
