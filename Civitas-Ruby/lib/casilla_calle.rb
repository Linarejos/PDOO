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
  class CasillaCalle < Casilla
    attr_reader :titulo
    public :titulo

    def initialize(titulo)
        super
        @titulo = titulo
    end
    
    @Override
    def recibejugador(iactual, todos)
      if jugadorcorrecto(iactual, todos)
        informe(iactual, todos)
        jugador = todos[iactual]
        
        if @titulo.tienepropietario
          jugador.puedecomprarcasilla
        else
          @titulo.tramitaralquiler(jugador)
        end
      end
    end
    
    @Override
    def to_s
      '\nNombre: ' + @titulo.nombre.to_s +
      '\nTipo Casilla: CALLE'       
    end

      public :titulo
  end
end
