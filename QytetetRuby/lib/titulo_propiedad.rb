#Elena Cantero Molina
#encoding: utf-8
#trig.rb

module ModeloQytetet
  class TituloPropiedad
    attr_reader :nombre, :alquilerBase, :factorRevalorizacion, :hipotecaBase, :precioEdificar
    attr_accessor :hipotecada, :casilla, :propietario
    
    def initialize(nombre, alquilerBase, factorRevalorizacion, hipotecaBase, precioEdificar)
      @nombre = nombre  #nombre de la propiedad
      @hipotecada = false #indica si la propiedad esta hipotecada o no
      @alquilerBase = alquilerBase  #alquiler base de la propiedad sin edificar
      @factorRevalorizacion = factorRevalorizacion  #Revalorización de la propiedad a lo largo del juego
      @hipotecaBase = hipotecaBase  #valor base de la hipoteca
      @precioEdificar = precioEdificar  #coste edificar casas y hoteles
      @propietario = nil  #Propietario de la casilla
      @casilla = nil  #Casilla en cuestion
    end
    
    def cobrar_alquiler(coste)
      @propietario.modificar_saldo(coste)
    end    

    def propietario_encarcelado
      return @propietario.encarcelado
    end    

    def tengo_propietario
      return !@propietario.nil?
    end
        
    def to_s
      "Propiedad -> #{@nombre}\nHipotecada -> #{@hipotecada}\nAlquiler Base -> #{@alquilerBase}\nFactor Revalorizacion -> #{@factorRevalorizacion}\nHipoteca Base -> #{@hipotecaBase} \nPrecio Edificar -> #{@precioEdificar}\n"
    end
  end
end
