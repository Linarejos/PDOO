# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.
require_relative "diario"
require_relative "mazo_sorpresas"
require_relative "titulo_propiedad"
require_relative "sorpresa"
require_relative "tipo_casilla"
require_relative "tipo_sorpresa"
require_relative "jugador"

module Civitas
  class CasillaSorpresa < Casilla
    
    def initialize(mazo, nombre)
      super(nombre)
      @mazo = mazo
    end
    
    @Override
    def recibejugador(actual, todos)  
      if jugadorcorrecto(actual, todos)
        sorpresa = @mazo.siguiente
        informe(actual, todos)
        sorpresa.aplicarajugador(actual, todos)
      end
    end
    
    @Override
    def to_s
      '\nNombre: ' + super.nombre.to_s +
      '\nTipo Casilla: SORPRESA'
    end
  end
end
