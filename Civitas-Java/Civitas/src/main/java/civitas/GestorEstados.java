/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package civitas;

/**
 *
 * @author elena
 */
public enum GestorEstados {
    EstadosJuego estadoInicial(){ throw new UnsupportedOperationException("No implementado"); }
    OperacionesJuego operacionesPermitidas(Jugador jugador, EstadosJuego estado){ throw new UnsupportedOperationException("No implementado"); }
    EstadosJuego siguienteEstado(Jugador jugador, EstadosJuego estado, OperacionesJuego operacion){ throw new UnsupportedOperationException("No implementado"); }
}
