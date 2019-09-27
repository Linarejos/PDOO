# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

require_relative "dado"
require_relative "tipo_casilla"
require_relative "tipo_sorpresa"
require_relative "operaciones_juego"
require_relative "estados_juego"
require_relative "mazo_sorpresas"
require_relative "sorpresa"
require_relative "diario"
require_relative "tablero"
require_relative "casilla"

module Civitas
  class TestP1
    
    @@dado = Dado.instance
    #@tirada = 0
        
    def self.main
      #for i in 0..100 do
       #@tirada = @@dado.quienempieza(4)
        #puts @tirada
        #puts " "
      #end
      
      #@@dado.setdebug(false)
      #for i in 0..10 do
        #puts @@dado.tirar
      #end
      
      #puts @@dado.salgodelacarcel
      #puts @@dado.ultimoResultado
      
      #puts Civitas::TipoCasilla::CALLE
      
      #mazo = MazoSorpresas.new
      #casilla1 = Sorpresa.new("casa")
      #casilla2 = Sorpresa.new("hotel")
      #mazo.almazo(casilla1)
      #mazo.almazo(casilla2)
      #siguiente = mazo.siguiente
      #mazo.inhabilitarcartaespecial(siguiente)
      #mazo.habilitarcartaespecial(siguiente)
      #puts "fin"
      
      #@diario = Diario.instance
      #puts @diario.eventos_pendientes
      #puts @diario.leer_evento
      
      #tablero = Tablero.new(5)
      #casilla = Casilla.new("casa")
      #tablero.aniadecasilla(casilla)
      #tablero.aniadecasilla(Casilla.new("hotel"))
      #tablero.aniadecasilla(Casilla.new("juez"))
      #tablero.aniadecasilla(Casilla.new("moto"))
      #tablero.aniadecasilla(Casilla.new("avion"))
      
      #for i in 0..4 do
        #puts tablero.getcasilla(i).nombre
      #end
    end
  end
  
  TestP1.main
end
