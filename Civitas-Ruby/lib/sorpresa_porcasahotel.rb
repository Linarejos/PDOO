# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.
require_relative "diario"
require_relative "tipo_sorpresa"
require_relative "mazo_sorpresas"
require_relative "tablero"
require_relative "jugador"

module Civitas
  class SorpresaPorcasahotel < Sorpresa
    def initialize(valor, texto)
      super(valor, texto)
    end
    
    @Override
    def aplicarajugador(actual, todos)
      jugador = todos[actual]
      
      if jugadorcorrecto(actual, todos)
        informe(actual, todos)
        jugador.modificarsaldo(super.valor*jugador.cantidadcasashoteles)
      end
    end
    
    @Override
    def to_s
      super.to_s + '\nTipo Sorpresa: PORCASAHOTEL'
    end 
  end
end
