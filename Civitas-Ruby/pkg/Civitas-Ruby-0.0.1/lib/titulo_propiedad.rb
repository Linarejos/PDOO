# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

require_relative "jugador"

module Civitas
  class TituloPropiedad
    attr_reader :hipotecado, :nombre, :numCasas, :numHoteles, :precioCompra,
                :propietario, :hipotecaBase, :precioEdificar
    
    def initialize(nom, ab, fr, hb, pc, pe)
      @alquilerBase = ab
      @@factorInteresesHipoteca = 1.1
      @factorRevalorizacion = fr
      @hipotecaBase = hb
      @hipotecado = false
      @nombre = nom
      @numCasas = 0
      @numHoteles = 0
      @precioCompra = pc
      @precioEdificar = pe
      @propietario = nil
    end
        
    def actualizapropietarioporconversion(jugador)      
    end
    
    def cancelarhipoteca(jugador)      
    end
    
    def cantidadcasashoteles
      @numCasas + @numHoteles
    end
    
    def comprar(jugador)      
    end
    
    def construircasa(jugador)      
    end
    
    def construirhotel(jugador)      
    end
    
    def derruircasas(n, jugador)      
      if esesteelpropietario(jugador) and n <= @numCasas
        @numCasas = @numCasas - n
        true
      else
        false
      end
    end
    
    def getimportecancelarhipoteca
      @precioCompra + @precioEdificar*@factorRevalorizacion
    end    
        
    def hipotecar(jugador)      
    end
    
    def tienepropietario
      @propietario != nil
    end
    
    def tramitaralquiler(jugador)
      if tienepropietario and !esesteelpropietario(jugador)
        jugador.pagaAlquiler(getimportehipoteca)
        @propietario.recibe(getimportehipoteca)
      end
    end
    
    def vender(jugador)
      if esesteelpropietario(jugador) and !@hipotecado
        jugador.recibe(getprecioventa)
        @propietario = nil
        @numCasas = 0
        @numHoteles = 0
        true
      else
        false
      end
    end
    
    private
    def esesteelpropietario(jugador)
      @propietario.eql?(jugador)
    end
        
    def getprecioalquiler
      precioalquiler = @alquilerBase*(1+(@numCasas*0.5)+(@numHoteles*2.5));
                
      if @hipotecado || propietarioEncarcelado
        precioalquiler = 0;
      end
        
      precioalquiler
    end
      
    def getprecioventa
      @precioCompra + @precioEdificar*@factorRevalorizacion
    end
    
    def propietarioencarcelado
      @propietario.isencarcelado
    end
    
    public 
    def to_s
      'Nombre: ' + @nombre + 
      '\nAlquiler Base: ' + @alquilerBase +
      '\nFactor Revalorizacion: ' + @factorRevalorizacion +
      '\nHipoteca Base: ' + @hipotecaBase + 
      '\nPrecio Compra: ' + @precioCompra +
      '\nPrecio Edificar: ' + @precioEdificar +
      '\nPropietario: ' + @propietario +
      '\nNumero Casas: ' + @numCasas +
      '\nNumero Hoteles: ' + @numHoteles +
      '\nHipotecado: ' + @hipotecado
    end
  end
end
