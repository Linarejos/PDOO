/**
 * @author Elena Cantero Molina
 */
package modeloqytetet;

public class TituloPropiedad {
    private String nombre;  //nombre de la propiedad
    private boolean hipotecada; //indica si un titulo de propiedad esta hipotecado o no
    private int alquilerBase;   //precio base de la propiedad sin edificaciones
    private float factorRevalorizacion; //Revalorización de la propiedad a lo largo del tiempo
    private int hipotecaBase;   //indica el valor base de la hipoteca
    private int precioEdificar; //indica cuánto cuesta edificar casas y hoteles
    Jugador propietario;    //propietario del titulo de propiedad
    Casilla casilla;    //Casilla del titulo de propiedad
    
    //Constructor
    public TituloPropiedad(String nombre, int alquilerBase, float factorRevalorizacion, int hipotecaBase, int precioEdificar){
        this.nombre = nombre;
        this.hipotecada = false;
        this.alquilerBase = alquilerBase;
        this.factorRevalorizacion = factorRevalorizacion;
        this.hipotecaBase = hipotecaBase;
        this.precioEdificar = precioEdificar;
        this.propietario = null;
        this.casilla = null;
    }
    
    //Consultor del nombre de la propiedad
    public String GetNombre(){ return this.nombre; }
    //Consultor para saber si la propiedad esta hipotecada
    boolean GetHipotecada(){ return this.hipotecada; }
    //Consultor del alquiler base de la propiedad
    int GetAlquilerBase(){ return this.alquilerBase; }
    //Consultor del factor de revalorización de la propiedad
    float GetFactorRevalorizacion(){ return this.factorRevalorizacion; }
    //Consultor de la hipoteca base de la propiedad
    int GetHipotecaBase(){ return this.hipotecaBase; }
    //Consultor del precio para edificar casas u hoteles
    int GetPrecioEdificar(){ return this.precioEdificar; }
    //Consultor de la casilla
    public Casilla GetCasilla(){ return this.casilla; }
    //Consultor del propietario
    public Jugador getPropietario(){ return this.propietario; }
    
    //Modificador de si la propiedad esta hipotecada o no
    void SetHipotecada(boolean hipotecada){ this.hipotecada = hipotecada; }
    //Modificador de la casilla
    void setCasilla(Casilla casilla){ this.casilla = casilla; }
    //Modificador del propietario de un titulo
    void setPropietario(Jugador propietario){ this.propietario = propietario; }
    
    //Modifica el saldo del jugador
    void cobrarAlquiler(int coste){ propietario.modificarSaldo(-coste); }    
    //devuelve true si el propietario esta encarcelado
    boolean propietarioEncarcelado(){ return propietario.getEncarcelado(); }    
    //true si el propietario no es nulo
    boolean tengoPropietario(){ return this.propietario != null; }
    
    //Método toString
    @Override
    public String toString(){
        return "Propiedad -> " + this.GetNombre() + "\nhipotecada -> " + this.GetHipotecada() + "\nalquiler base -> " + this.GetAlquilerBase() + 
                "\nfactor revalorizacion -> " + this.GetFactorRevalorizacion() + "\nhipoteca base -> " + this.GetHipotecaBase() + 
                "\nprecio edificar -> " + this.GetPrecioEdificar();
    }
}
