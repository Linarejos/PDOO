# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

require_relative "diario"
require_relative "tipo_sorpresa"
require_relative "jugador"

module Civitas
  class Sorpresa    
    def initialize(tipo, tablero, valor, texto, mazo)
      @tipo = tipo
      @tablero = tablero
      @mazo = mazo
      @texto = texto
      @valor = valor
    end
    
    def self.new_sorpresa_ircarcel(tipo, tablero)
      init
      new(tipo, tablero)
    end
    
    def self.new_sorpresa_casilla(tipo, tablero, valor, texto)
      init
      new(tipo, tablero, valor, texto)
    end
    
    def self.new_sorpresa_evitarcarcel(tipo, mazo)
      init
      new(tipo, nil, -1, nil,mazo)
    end
    
    def self.new_sorpresas(tipo, valor, texto)
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
    
    def aplicarajugador_iracasilla(actual, todos)
      if jugadorcorrecto(actual, todos)
        informe(actual, todos)
        casillaactual = todos[actual].numCasillaActual
        tirada = @tablero.calculartirada(casillaactual, @valor)
        nuevapos = @tablero.nuevaposicion(casillaactual, tirada)
        todos[actual].moveracasilla(nuevapos)
        @tablero.getcasilla(nuevapos).recibejugador(@valor, todos)
      end
    end
    
    def aplicarajugador_ircarcel(actual, todos)
      if jugadorcorrecto(actual, todos)
        informe(actual, todos)
        todos[actual].encarcelar(5)
      end
    end
    
    def aplicarajugador_pagarcobrar(actual, todos)
      if jugadorcorrecto(actual,todos)
        informe(actual, todos)
        todos[actual].modificasaldo(valor)
      end
    end
    
    def aplicarajugador_porcasahotel(actual, todos)
      if jugadorcorrecto(actual, todos)
        informe(actual, todos)
        todos[actual].modificasaldo(valor*todos[actual].cantidadcasashoteles)
      end
    end
    
    def aplicarajugador_porjugador(actual, todos)
      if jugadorcorrecto(actual, todos)
        sorpresa = new_sorpresas(Civitas::TipoSorpresa::PAGARCOBRAR, @valor*-1, 'Cobra el jugador actual')
        
        i = 0
        loop do
          if i == todos.length
            if todos[i] != todos[actual]
              aplicarajugador(i, todos)
            end
          end
          i+=1
        end
        
        sorpresa1 = new_sorpresas(Civitas::TipoSorpresa::PAGARCOBRAR, @valor*(todos.length - 1), 'Paga jugador actual')
        aplicarajugador(actual, todos)
      end
    end
    
    def aplicarajugador_salircarcel(actual, todos)
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
    
    public
    def aplicarajugador(actual, todos)
      case @tipo
        when IRCARCEL
          aplicarajugador_ircarcel(actual, todos)
        when IRCASILLA
          aplicarajugador_iracasilla(actual, todos)
        when PAGARCOBRAR
          aplicarajugador_pagarcobrar(actual, todos)
        when PORCASAHOTEL
          aplicarajugador_porcasahotel(actual, todos)
        when PORJUGADOR
          aplicarajugador_porjugador(actual, todos)
        when SALIRCARCEL
          aplicarajugador_salircarcel(actual, todos)
      end      
    end
    
    def jugadorcorrecto(actual, todos)
      actual <= todos[].length
    end
    
    def salirdelmazo
      if @tipo == Civitas::TipoSorpresa::SALIRCARCEL
        @mazo.inhabilitarcartaespecial(self)
      end
    end
    
    def to_s
      'Sorpresa: ' + @texto + '\nValor: ' + @valor +
      '\nTipo: ' + @tipo
    end
    
    def usada
      if @tipo == Civitas::TipoSorpresa::SALIRCARCEL
        @mazo.habilitarcartaespecial(self)
      end
    end
  end
end