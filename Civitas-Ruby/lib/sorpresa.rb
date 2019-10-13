# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

require_relative "diario"

module Civitas
  class Sorpresa    
    def initialize(tipo, tablero, valor, texto, mazo)
      @tipo = tipo
      @tablero = tablero
      @mazo = mazo
      @texto = texto
      @valor = valor
    end
    
    def self.sorpresa_ircarcel(tipo, tablero)
      init
      new(tipo, tablero)
    end
    
    def self.sorpresa_casilla(tipo, tablero, valor, texto)
      init
      new(tipo, tablero, valor, texto)
    end
    
    def self.sorpresa_evitarcarcel(tipo, mazo)
      init
      new(tipo, nil, -1, nil,mazo)
    end
    
    def self.sorpresas(tipo, valor, texto)
      init
      new(tipo, nil, valor, texto)      
    end
    
    private    
    def init
      @valor = -1
      @mazo = nil
      @tablero = nil
    end
    
    def informe(actual, todos)
      Diario.instance.ocurre_evento("Aplicando sorpresa a " + todos[actual].nombre)
    end
    #cambiar
    def aplicarajugador_casilla(actual, todos)
      
    end
    
    public
    def aplicarajugador(actual, todos)
      
    end
    
    def jugadorcorrecto(actual, todos)
      actual <= todos[].length
    end
    
    def salirdelmazo
      
    end
    
    def to_s
      
    end
    
    def usada
      
    end
  end
end