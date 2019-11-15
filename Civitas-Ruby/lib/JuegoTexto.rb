# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

require_relative 'civitas_juego'
require_relative 'dado'
require_relative 'controlador'
require_relative 'vista_textual'

module Civitas
  class JuegoTexto
        
    def self.main
      mivista = Vista_textual.new
      
      @nombres = Array.new
      @nombres << 'Elena'
      @nombres << 'Pepe'
      @nombres << 'Laura'
      @nombres << 'Juan'      
      
      @juego = CivitasJuego.new(@nombres)
      @dado = Dado.instance      
      @dado.setdebug(true)
      
      @con = Controlador.new(@juego, mivista)
      @con.juega
    end
  end
  
  JuegoTexto.main
end
