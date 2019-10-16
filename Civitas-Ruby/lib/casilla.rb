# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

require_relative "diario"
require_relative "mazo_sorpresas"
require_relative "titulo_propiedad"
require_relative "tipo_casilla"
require_relative "tipo_sorpresa"

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
      Diario.instance.ocurre_evento('El jugador ' + todos[iactual].nombre + 'ha caido en la casilla: \n' + toString)
    end
    
    def recibejugador_calle(actual, todos)      
    end
    
    def recibejugador_impuesto(actual, todos)
      if jugadorcorrecto(actual, todos)
        informe(actual, todos)
        todos[actual].pagaimpuesto(@importe)
      end
    end
    
    def recibejugador_juez(actual, todos)
      if jugadorcorrecto(actual, todos)
        informe(actual, todos)
        todos[actual].encarcelar(@@carcel)
      end
    end
    
    def recibejugador_sorpresa(actual, todos)      
    end
    
    public 
    def self.new_casilla(nombre)
      init
      new(nombre, nil, 0, -1, nil)      
    end
    
    def self.new_casillacalle(titulo)
      init   
      @tipo = Civitas::TipoCasilla::CALLE
      new(nil, titulo, 0, -1, nil)
    end
    
    def self.new_casillaimpuesto(cantidad, nombre)
      init
      @tipo = Civitas::TipoCasilla::IMPUESTO
      new(nombre, nil, cantidad, -1, nil)
    end
    
    def self.new_casillajuez(numcasillacarcel, nombre)
      init
      @tipo = Civitas::TipoCasilla::JUEZ
      new(nombre, nil, 0, numcasillacarcel, nil)
    end
    
    def self.new_casillasorpresa(mazo, nombre)
      init
      @tipo = Civitas::TipoCasilla::SORPRESA
      new(nombre, nil, 0, -1, mazo)
    end
    
    def jugadorcorrecto(actual, todos)
      actual < todos.lenght
    end
    
    def recibejugador(actual, todos)      
    end
    
    def to_s
      case @tipo
      when Civitas::TipoCasilla::CALLE
        '\nNombre: ' + @nombre +
        '\nTipo: ' + @tipo +
        '\nTitulo Propiedad: ' + @titulo.nombre
      when Civitas::TipoCasilla::IMPUESTO
        '\nNombre: ' + @nombre +
        '\nTipo: ' + @tipo +
        '\nImporte: ' + @importe
      when Civitas::TipoCasilla::DESCANSO
        '\nNombre: ' + @nombre +
        '\nTipo: ' + @tipo
      when Civitas::TipoCasilla::JUEZ
        '\nNombre: ' + @nombre +
        '\nTipo: ' + @tipo +
        '\nCarcel: ' + @@carcel
      when Civitas::TipoCasilla::SORPRESA
        '\nNombre: ' + @nombre +
        '\nTipo: ' + @tipo
      end
    end
    
    public :nombre, :titulo
  end
end
