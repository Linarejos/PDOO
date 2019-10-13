# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

require_relative "diario"

module Civitas
  class Casilla
    attr_reader :nombre, :titulo
    
    def initialize(nombre, titulo, cantidad, numCasillaCarcel, mazo)
      init()
      @@carcel = numCasillaCarcel
      @importe = cantidad
      @nombre = nombre
      @titulo = titulo
      @mazo = mazo
    end
    
    private
    def init
      @@carcel = -1;
      @importe = 0;
      @nombre = nil;
      @tipo = nil;
      @tituloPropiedad = nil;
      @sorpresa = nil;
      @mazo = nil;
    end
    
    def informe(iactual, todos)
      Diario.instance.ocurre_evento("El jugador " + todos[iactual]. + "ha caido en la casilla: \n" + toString)
    end
    
    
    
    public :nombre
  end
end
