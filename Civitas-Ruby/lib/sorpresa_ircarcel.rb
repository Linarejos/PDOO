# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.
require_relative "diario"
require_relative "tipo_sorpresa"
require_relative "mazo_sorpresas"
require_relative "tablero"
require_relative "jugador"

module Civitas
  class SorpresaIrcarcel
    def initialize(tablero)
      super(-1, 'De camino a la carcel')
      @tablero = tablero
    end
    
    @override
    def aplicarajugador(actual, todos)
      if jugadorcorrecto(actual, todos)
        informe(actual, todos)
        todos[actual].encarcelar(5)
      end
    end
    
    @override
    def to_s
      super.to_s + 
      '\nTipo Sorpresa: IRCASILLA'
    end
  end
end
