# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

require_relative "diario"
require_relative "tipo_sorpresa"
require_relative "mazo_sorpresas"
require_relative "tablero"
require_relative "jugador"

module Civitas
  class Sorpresa    
    def initialize(valor, texto)
      @tablero = nil
      @mazo = nil
      @texto = texto
      @valor = valor
    end
    
    def informe(actual, todos)
      Diario.instance.ocurre_evento("Aplicando sorpresa a " + todos[actual].nombre)
    end
    
    private
    def aplicarajugador(actual, todos)     
    end
    
    public
    def jugadorcorrecto(actual, todos)
      actual < todos.length
    end
    
    def to_s
      'Sorpresa: ' + @texto.to_s + '\nValor: ' + @valor.to_s
    end
  end
end