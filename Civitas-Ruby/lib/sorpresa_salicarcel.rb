# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.
require_relative "diario"
require_relative "tipo_sorpresa"
require_relative "mazo_sorpresas"
require_relative "tablero"
require_relative "jugador"

module Civitas
  class SorpresaSalicarcel < Sorpresa
    def initialize(mazo)
      super(-1, 'Evitando la carcel')
      @mazo = mazo
    end
    
    @Override
    def aplicarajugador(actual, todos)
      salvoconducto = false
      
      if jugadorcorrecto(actual, todos)
        informe(actual, todos)
        i = 0
        loop do
          if i < todos.length and !salvoconducto
            if todos[i].tienesalvoconducto
              salvoconducto = true
            end
          end
          i += 1
        end
        
        if !salvoconducto
          todos[actual].obtenersalvoconducto(self)
          salirdelmazo
        end
      end
    end
    
    def salirdelmazo
      @mazo.inhabilitarcartaespecial(self)
    end
    
    @Override
    def to_s
      super.to_s + '\nTipo Sorpresa: SALIRCARCEL'
    end
    
    def usada
      @mazo.habilitarcartaespecial(self)
    end
  
  end
end
