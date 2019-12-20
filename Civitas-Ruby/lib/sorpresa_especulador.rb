# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.
require_relative "diario"
require_relative "tipo_sorpresa"
require_relative "mazo_sorpresas"
require_relative "tablero"
require_relative "jugador"
require_relative "jugador_especulador"

module Civitas
  class SorpresaEspeculador < Sorpresa
    def initialize(valor, texto)
      super(valor, texto)
    end
    
    @override
    def aplicarajugador(actual, todos)
      if jugadorcorrecto(actual, todos)
        informe(actual, todos)
        
        jugador = JugadorEspeculador.new(todos[actual])
        todos.delete(todos[actual])
        todos[actual] << jugador
      end
    end
    
    @override
    def to_s
      super.to_s + 
      '\nTipo Sorpresa: Jugador Especulador'
    end
  end
end
