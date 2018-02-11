#Elena Cantero Molina
#encoding: utf-8
#trig.rb

module ModeloQytetet
  class Especulador < Jugador
    attr_reader :fianza
    
    def initialize(jugador, fianza)
      super(jugador.nombre)
      @fianza = fianza
      @factor_especulador = 2
    end
    
    def factor_especulador
      return @factor_especulador
    end
    
    def pagar_impuestos(cantidad)
      modificar_saldo(-cantidad/2)
    end
    
    def convertirme(fianza)
      return self
    end
    
    def ir_a_carcel(casilla)
      if !pagar_fianza(@fianza)
        @casilla_actual = casilla
        @encarcelado = true
      end
    end
    
    def pagar_fianza(cantidad)
      if @saldo > @fianza
        modificar_saldo(-@fianza)
        return true
      end
      return false
    end
    
    def to_s
      super
    end
    
    protected :pagar_impuestos, :ir_a_carcel, :convertirme
    private :pagar_fianza
  end
end
