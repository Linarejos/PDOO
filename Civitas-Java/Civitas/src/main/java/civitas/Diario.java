/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * @author elena
 * @date 26/09/2019
 */

package civitas;

import java.util.ArrayList;

public class Diario {
    private static final Diario instance = new Diario();  
    private ArrayList<String> eventos;
  
    public static Diario getInstance() {
        return instance;
    }
  
    public Diario () {
        eventos = new ArrayList<>();
    }
  
    void ocurreEvento (String e) {
        eventos.add (e);
    }
  
    public boolean eventosPendientes () {
        return !eventos.isEmpty();
    }
  
    public String leerEvento () {
        String salida = "";
        if (!eventos.isEmpty()) {
          salida = eventos.remove(0);
        }
        return salida;
    }
}
