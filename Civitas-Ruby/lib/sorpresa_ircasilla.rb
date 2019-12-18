# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.
require_relative "diario"
require_relative "tipo_sorpresa"
require_relative "mazo_sorpresas"
require_relative "tablero"
require_relative "jugador"

module Civitas
  class SorpresaIrcasilla < Sorpresa
    def initialize(tablero, valor, texto)
      super(valor, texto)
      @tablero = tablero
    end
    
    @override
    def aplicarajugador(actual, todos)
      if jugadorcorrecto(actual, todos)
        informe(actual, todos)
        casillaactual = todos[actual].numCasillaActual
        tirada = @tablero.calculartirada(casillaactual, @valor)
        nuevapos = @tablero.nuevaposicion(casillaactual, tirada)
        todos[actual].moveracasilla(nuevapos)
        @tablero.getcasilla(nuevapos).recibejugador(@valor, todos)
      end
    end
    
    @override
    def to_s
      super.to_s + 
      '\nTipo Sorpresa: IRCASILLA'
    end
  end
end
