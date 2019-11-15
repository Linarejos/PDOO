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
    attr_reader :nombre, :titulo
    public :nombre, :titulo
    
    def initialize(nombre, titulo, cantidad, numCasillaCarcel, mazo)
      init()
      @@carcel = numCasillaCarcel
      @importe = cantidad
      @nombre = nombre
      @titulo = titulo
      @mazo = mazo
    end
    
    private       
    def informe(actual, todos)
      jugador = todos[actual]
      evento = "El jugador " + "#{jugador.nombre} ha caido en la casilla  #{to_s}"
      Diario.instance.ocurre_evento(evento)
    end
    
    def recibejugador_calle(actual, todos)  
      if jugadorcorrecto(actual, todos)
        informe(actual, todos)
        jugador = todos[actual]
        
        if !@titulo.tienepropietario
          jugador.puedecomprarcasilla
        else
          @titulo.tramitaralquiler(jugador)
        end
      end
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
      if jugadorcorrecto(actual, todos)
        sorpresa = @mazo.siguiente
        informe(actual, todos)
        sorpresa.aplicarajugador(actual, todos)
      end
    end
    
    def init
      @@carcel = -1;
      @importe = 0;
      @nombre = nil;
      @tipo = nil;
      @tituloPropiedad = nil;
      @sorpresa = nil;
      @mazo = nil;
    end
    
    public 
    def self.new_casilla(nombre)
      @@carcel = -1;
      @importe = 0;
      @nombre = nil;
      @tipo = nil;
      @tituloPropiedad = nil;
      @sorpresa = nil;
      @mazo = nil;
      @tipo = Civitas::TipoCasilla::DESCANSO
      new(nombre, nil, 0, -1, nil)      
    end
    
    def self.new_casillacalle(titulo)
      @@carcel = -1;
      @importe = 0;
      @nombre = nil;
      @tipo = nil;
      @tituloPropiedad = nil;
      @sorpresa = nil;
      @mazo = nil;   
      @tipo = Civitas::TipoCasilla::CALLE
      new(nil, titulo, 0, -1, nil)
    end
    
    def self.new_casillaimpuesto(cantidad, nombre)
      @@carcel = -1;
      @importe = 0;
      @nombre = nil;
      @tipo = nil;
      @tituloPropiedad = nil;
      @sorpresa = nil;
      @mazo = nil;
      @tipo = Civitas::TipoCasilla::IMPUESTO
      new(nombre, nil, cantidad, -1, nil)
    end
    
    def self.new_casillajuez(numcasillacarcel, nombre)
      @@carcel = -1;
      @importe = 0;
      @nombre = nil;
      @tipo = nil;
      @tituloPropiedad = nil;
      @sorpresa = nil;
      @mazo = nil;
      @tipo = Civitas::TipoCasilla::JUEZ
      new(nombre, nil, 0, numcasillacarcel, nil)
    end
    
    def self.new_casillasorpresa(mazo, nombre)
      @@carcel = -1;
      @importe = 0;
      @nombre = nil;
      @tipo = nil;
      @tituloPropiedad = nil;
      @sorpresa = nil;
      @mazo = nil;
      @tipo = Civitas::TipoCasilla::SORPRESA
      new(nombre, nil, 0, -1, mazo)
    end
    
    def jugadorcorrecto(actual, todos)
      actual < todos.lenght
    end
    
    def recibejugador(actual, todos)    
      if @tipo == Civitas::TipoCasilla::CALLE
        recibejugador_calle(actual, todos)
      else if @tipo == Civitas::TipoCasilla::IMPUESTO
        recibejugador_impuesto(actual, todos)
      else if @tipo == Civitas::TipoCasilla::JUEZ
        recibejugador_juez(actual, todos)
      else if @tipo == Civitas::TipoCasilla::SORPRESA
        recibejugador_sorpresa(actual, todos)
      else
        informe(actual, todos)
      end
      end
      end
      end
    end
    
    def to_s
      case @tipo
      when Civitas::TipoCasilla::CALLE
        '\nNombre: ' + @titulo.nombre.to_s +
        '\nTipo: ' + @tipo.to_s
      when Civitas::TipoCasilla::IMPUESTO
        '\nNombre: ' + @nombre.to_s +
        '\nTipo: ' + @tipo.to_s +
        '\nImporte: ' + @importe.to_s
      when Civitas::TipoCasilla::DESCANSO
        '\nNombre: ' + @nombre.to_s +
        '\nTipo: ' + @tipo.to_s
      when Civitas::TipoCasilla::JUEZ
        '\nNombre: ' + @nombre.to_s +
        '\nTipo: ' + @tipo.to_s +
        '\nCarcel: ' + @@carcel.to_s
      when Civitas::TipoCasilla::SORPRESA
        '\nNombre: ' + @nombre.to_s +
        '\nTipo: ' + @tipo.to_s
      end
    end
    
    public :nombre, :titulo
  end
end
