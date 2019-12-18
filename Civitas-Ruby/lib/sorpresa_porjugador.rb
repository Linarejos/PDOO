# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.
require_relative "diario"
require_relative "tipo_sorpresa"
require_relative "mazo_sorpresas"
require_relative "tablero"
require_relative "jugador"

module Civitas
  class SorpresaPorjugador < Sorpresa
    def initialize(valor, texto)
      super(valor, texto)
    end
    
    @Override
    def aplicarajugador(actual, todos)
      if jugadorcorrecto(actual, todos)
        sorpresa = SorpresaPagarcobrar.new(super.valor*-1, 'Cobra el jugador actual')
        
        i = 0
        loop do
          if i == todos.length
            if todos[i] != todos[actual]
              sorpresa.aplicarajugador(i, todos)
            end
          end
          i+=1
        end
        
        sorpresa1 = SorpresaPagarCobrar.new(super.valor*(todos.length - 1), 'Paga jugador actual')
        sorpresa1.aplicarajugador(actual, todos)
      end
    end
    
    @Override
    def to_s
      super.to_s + '\nTipo Sorpresa: PORJUGADOR'
    end    
  end
end
