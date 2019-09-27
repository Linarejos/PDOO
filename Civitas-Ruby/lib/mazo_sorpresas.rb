# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

require_relative "sorpresa"
require_relative "diario"

module Civitas
  class MazoSorpresas
    def initialize
      @sorpresas = Array.new()
      @barajada = false
      @usadas = 0
      @debug = false
      @cartasEspeciales = Array.new()
      @ultimaSorpresa = nil
      @diariomazo = Diario.instance      
    end
    
    attr_accessor :debug
    
    def almazo(s)
      if !@barajada
        @sorpresas << s
      end
    end
    
    def siguiente
      if !@barajada or @usadas.eql?(@sorpresas.length)
        if !@debug
          @sorpresas.shuffle
          @usadas = 0
          @barajada = true
        end
      end
      
      @usadas = @usadas + 1
      @ultimaSorpresa = @sorpresas[0]
      @sorpresas.delete_at(0)
      @sorpresas << @ultimaSorpresa
      
      return @ultimaSorpresa
    end
    
    def inhabilitarcartaespecial(sorpresa)
      for s in @sorpresas do
        if s.nombre == sorpresa.nombre
          @sorpresas.delete(sorpresa)
          @cartasEspeciales << sorpresa
          @diariomazo.ocurre_evento("Inhabilitar carta especial")
        end
      end
    end
    
    def habilitarcartaespecial(sorpresa)
      for s in @cartasEspeciales do
        if s.nombre == sorpresa.nombre
          @cartasEspeciales.delete(sorpresa)
          @sorpresas << sorpresa
          @diariomazo.ocurre_evento("Habilitar carta especial")
        end
      end
    end
  end
end
