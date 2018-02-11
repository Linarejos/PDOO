#Elena Cantero Molina
#encoding: utf-8
#trig.rb

module ModeloQytetet
  class Calle < Casilla
    attr_accessor :numero_hoteles, :numero_casas, :titulo
    
    def initialize(numero_casilla, coste, titulo)
      super(numero_casilla, coste, TipoCasilla::CALLE)
      @numero_hoteles = 0
      @numero_casas = 0
      @titulo = titulo
      
      if !@titulo.nil?
        @titulo.casilla = self
      end
    end
    
    def asignar_propietario(jugador)
      @titulo.propietario = jugador
    end 
    
    def calcular_valor_hipoteca
      return (@titulo.hipotecaBase + (@numero_casas*0.5*@titulo.hipotecaBase + @numero_hoteles*@titulo.hipotecaBase))
    end
    
    def cancelar_hipoteca
      @titulo.hipotecada = false
      return 1.1*calcular_valor_hipoteca
    end
    
    def cobrar_alquiler
      coste_alquiler_base = @titulo.alquilerBase
      @titulo.cobrar_alquiler(coste_alquiler_base + @numero_casas*0.5 + @numero_hoteles*2)
      return coste_alquiler_base
    end
    
    def edificar_casa
      @numero_casas = @numero_casas + 1
      return @titulo.precioEdificar
    end
    
    def edificar_hotel
      @numero_hoteles = @numero_hoteles + 1
      return @titulo.precioEdificar
    end
    
    def esta_hipotecada
      return @titulo.hipotecada.eql?(true)
    end
    
    def get_precio_edificar
      return @titulo.precioEdificar
    end
    
    def hipotecar
      @titulo.hipotecada = true
      return calcular_valor_hipoteca
    end
    
    def precio_total_comprar
        precio = @coste + (@numero_casas + @numero_hoteles)*@titulo.precioEdificar
        precio += precio*@titulo.factorRevalorizacion
        return precio;
    end
    
    def propietario_encarcelado
      return @titulo.propietario_encarcelado
    end
    
    def se_puede_edificar_casa(factor_especulador)
      if (factor_especulador == 1)
        return @numero_casas < 4
      else
        return @numero_casas < 8
      end
    end
    
    def se_puede_edificar_hotel(factor_especulador)
      if (factor_especulador == 1)
        return (@numero_hoteles < 4 and @numero_casas == 4)
      else
        return (@numero_hoteles < 8 and @numero_casas == 8)
      end
    end
    
    def tengo_propietario
      return @titulo.tengo_propietario
    end 
    
    def vender_titulo
      precio_comprar = @coste + (@numero_casas + @numero_hoteles)*@titulo.precioEdificar
      @propietario = nil
      @numero_casas = 0
      @numero_hoteles = 0
      return precio_comprar + (@titulo.factorRevalorizacion*precio_comprar)
    end
    
    def to_s
      "#{super.to_s}\nNumero Casas -> #{@num_casas}\nNumero Hoteles -> #{@num_hoteles}\nTitulo Propiedad -> \n#{@titulo}"
    end
  end
end
