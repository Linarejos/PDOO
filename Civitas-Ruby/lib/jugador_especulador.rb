# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.
require_relative "jugador"
require_relative "titulo_propiedad"
require_relative "diario"

module Civitas
  class JugadorEspeculador < Jugador
    def initialize
      @fianza = 100      
      @@factorEspeculador = 2
    end
    
    private_method :new
    
    def nuevoEspeculador(jugador, fianza)
      especulador = new
      
      especulador = Jugador.new_jugador(jugador)
      
      jugador.propiedades.each do |j|
        j.actualizapropietarioporconversion(jugador)
      end
      
      especulador
    end
    
    @override
    def es_especulador
      true
    end
    
    def casas_max
      super.casas_max*@@factorEspeculador
    end
    
    def hoteles_max
      super.hoteles_max*@@factorEspeculador
    end
    
    def debeserencarcelado
      if !super.isencarcelado
        if !super.tienesalvoconducto
          if super.puedogastar(@fianza)
            super.paga(@fianza)
            false
          else
            true
          end
        else
          super.perdersalvoconducto
          Diario.instance.ocurre_evento('El jugador se libra de la carcel')
          false
        end
      else
        false
      end
    end
    
    def pagaimpuesto(cantidad)
      if !super.isencarcelado
        super.paga(cantidad/@@factorEspeculador)
      else
        false
      end
    end
    
    def to_s
      puts '\nSOY JUGADOR ESPECULADOR\n' + super.to_s
    end
  end
end
