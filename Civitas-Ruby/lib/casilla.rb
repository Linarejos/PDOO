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
  class Casilla
    attr_reader :nombre
    public :nombre
    
    def initialize(nombre)
      @nombre = nombre
    end
           
    def informe(actual, todos)
      jugador = todos[actual]
      evento = "El jugador " + "#{jugador.nombre} ha caido en la casilla  #{to_s}"
      Diario.instance.ocurre_evento(evento)
    end
    
    def recibejugador(actual, todos)  
        informe(actual, todos)      
    end
        
    public 
    
    def jugadorcorrecto(actual, todos)
      actual < todos.lenght
    end
    
    def to_s
        '\nNombre: ' + @nombre.to_s +
        '\nTipo Casilla: DESCANSO'      
    end
    
    public :nombre, :titulo
  end
end
