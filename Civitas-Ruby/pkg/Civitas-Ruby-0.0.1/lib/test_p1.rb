# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

require_relative "dado"
require_relative "tipo_casilla"
require_relative "tipo_sorpresa"
require_relative "operaciones_juego"
require_relative "estados_juego"
require_relative "diario"
require_relative "casilla"
require_relative "tablero"
require_relative "civitas_juego"
require_relative "gestor_estados"
require_relative "mazo_sorpresas"
require_relative "sorpresa"

module Civitas
  class TestP1
        
    def self.main
      @nombres = Array.new
      @nombres << 'Elena'
      @nombres << 'Pepe'
      @nombres << 'Laura'
      @nombres << 'Juan'
      
      @juego = CivitasJuego.new(@nombres)
      
      #puts 'hola'
      puts @juego.infojugadortexto
    end
  end
  
  TestP1.main
end
