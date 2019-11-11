/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juegoTexto;

import civitas.CivitasJuego;
import civitas.Diario;
import civitas.OperacionesJuego;
import civitas.SalidasCarcel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import civitas.Casilla;
import civitas.Jugador;
import civitas.TituloPropiedad;

/**
 * @author elena
 * @date 25/10/2019
 */
class VistaTextual {
  
    CivitasJuego juegoModel; 
    int iGestion=-1;
    int iPropiedad=-1;
    private static String separador = "=====================";
  
    private Scanner in;
  
    VistaTextual(){
        in = new Scanner (System.in);
    }
  
    void mostrarEstado(String estado){
        System.out.println (estado);
    }
              
    void pausa(){
        System.out.print ("Pulsa una tecla");
        in.nextLine();
    }

    int leeEntero(int max, String msg1, String msg2) {
        boolean ok;
        String cadena;
        int numero = -1;
        do{
            System.out.print (msg1);
            cadena = in.nextLine();
        try{  
            numero = Integer.parseInt(cadena);
            ok = true;
        } catch(NumberFormatException e){ // No se ha introducido un entero
            System.out.println (msg2);
            ok = false;  
        }
        if(ok && (numero < 0 || numero >= max)){
            System.out.println (msg2);
            ok = false;
        }
        } while (!ok);

    return numero;
  }

    int menu(String titulo, ArrayList<String> lista) {
        String tab = "  ";
        int opcion;
        System.out.println(titulo);
        for (int i = 0; i < lista.size(); i++) {
            System.out.println(tab+i+"-"+lista.get(i));
        }

        opcion = leeEntero(lista.size(), "\n"+tab+"Elige una opción: ",
                           tab+"Valor erróneo");
        return opcion;
    }

    SalidasCarcel salirCarcel() {
        int opcion = menu ("Elige la forma para intentar salir de la carcel",
        new ArrayList<> (Arrays.asList("Pagando","Tirando el dado")));
        return (SalidasCarcel.values()[opcion]);
    }

    Respuestas comprar(){
        int opcion = menu("¿Desea comprar esta calle?", 
                    new ArrayList<> (Arrays.asList("Si","No")));
        return(Respuestas.values()[opcion]);
    }

    void gestionar(){
        ArrayList<String> propiedades = new ArrayList();
        
        //He modificado la visibilidad del método getPropiedades y getNombre
        for(TituloPropiedad t : juegoModel.getJugadorActual().getPropiedades()){
            propiedades.add(t.getNombre());
        }
        
        iGestion = menu("¿Qué gestión inmobiliaria desea hacer?", 
                        new ArrayList<> (Arrays.asList("Vender","Hipotecar",
                        "Cancelar Hipoteca", "Construir Casa", "Construir hotel",
                        "Terminar")));
        
        if(iGestion != 5){
            iPropiedad = menu("¿Qué propiedad desea gestionar?", propiedades);
        }
                
    }

    public int getGestion(){ return iGestion; }
    public int getPropiedad(){ return iPropiedad; }

    void mostrarSiguienteOperacion(OperacionesJuego operacion){
        System.out.println(operacion);
    }

    void mostrarEventos(){
        while(Diario.getInstance().eventosPendientes()){
            System.out.println(Diario.getInstance().leerEvento());
        }
    }
  
    public void setCivitasJuego(CivitasJuego civitas){ 
        juegoModel=civitas;
        this.actualizarVista();
    }
  
    void actualizarVista(){
        Casilla casilla = juegoModel.getCasillaActual();
        Jugador jugadorActual = juegoModel.getJugadorActual();
        System.out.println("\n" + casilla.toString() + "\n" + jugadorActual.toString());
    } 
}

