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
    private static int FactorEspeculador = 2;
    
    JugadorEspeculador(Jugador otro){
        super(otro);
        fianza = 100;
        
        for(TituloPropiedad p : otro.getPropiedades())
            p.actualizaPropietarioPorConversion(otro);
    }
    
    @Override
    int getCasasMax(){ return super.getCasasMax()*FactorEspeculador; }
    
    @Override
    int getHotelesMax(){ return super.getHotelesMax()*FactorEspeculador; }
    
    @Override
    protected boolean debeSerEncarcelado(){ 
        boolean debeser = false;
        
        if(!super.isEncarcelado()){
            if(!super.tieneSalvoconducto()){
                if(super.puedoGastar(fianza)){
                    debeser = false;
                    super.paga(fianza);
                }  
                else
                    debeser = true;
            }
            else{
                super.perderSalvoConducto();
                Diario.getInstance().ocurreEvento("El jugador se libra de la carcel");
                debeser = false;
            }
        }
        
        return debeser;
    }
    
    //Los Jugadores Especuladores pagan la mitad de los impuestos
    @Override
    boolean pagaImpuesto(float cantidad){
        if(!isEncarcelado()) return paga(cantidad/FactorEspeculador); 
        return false;
    }
    
    @Override
    public String toString(){
        return "\n SOY JUGADOR ESPECULADOR \n" + super.toString();
    }    
}
