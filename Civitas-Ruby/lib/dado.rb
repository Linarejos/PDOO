# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.
require 'singleton'
require_relative "diario"

module Civitas
  class Dado
    include Singleton

    def initialize
      @random = 0
      @ultimoResultado = 0
      @debug = false
      @@SalidaCarcel = 5
    end
    
    public
    def tirar
      if !@debug
        @ultimoResultado = rand(4)
      else
        @ultimoResultado = 1
      end
      
      @ultimoResultado
    end
    
    def salgodelacarcel
      tirar >= 5
    end
    
    def quienempieza(n)
      rand(n)
    end
    
    def setdebug(d)
      @debug = d
      diariodado = Diario.instance
      if d
        diariodado.ocurre_evento("Debug activo")
      else
        diariodado.ocurre_evento("Debug desactivado")
      end
    end
    
    attr_reader :ultimoResultado
  end
end
