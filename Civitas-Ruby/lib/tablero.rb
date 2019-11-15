# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.
# encoding:utf-8

require_relative "casilla"

module Civitas
  class Tablero
    def initialize(numCasillaCarcel)
      
      if(numCasillaCarcel >= 1)
        @numCasillaCarcel = numCasillaCarcel
      end
      
      @casillas = Array.new
      salida = Casilla.new_casilla('Salida')
      @casillas << salida 
      
      @porSalida = 0
      @tieneJuez = false
    end
    
    private
    
    def correcto_sin
      @casillas.length > @numCasillaCarcel && @tieneJuez
    end
    
    def correcto(numCasilla)
      correcto_sin && numCasilla < @casillas.length
    end
    
    public
    
    attr_reader :numCasillaCarcel, :porSalida
    
    def getporsalida
      actual = @porSalida
      
      if @porSalida > 0
        @porSalida = @posSalida - 1
      end
      
      return actual
    end
    
    def aniadecasilla(casilla)
            
      if @casillas.length == @numCasillaCarcel
        carcel = Casilla.new_casilla("carcel")
        @casillas << carcel
      end
      
      @casillas << casilla
      
      if @casillas.length == @numCasillaCarcel
        carcel = Casilla.new_casilla("carcel")
        @casillas << carcel
      end
    end
    
    def aniadejuez
      @casillas.each do |casilla|
        if casilla.nombre == 'juez'
          @tieneJuez = true
        end
      end
      
      if !@tieneJuez
        juez = Casilla.new_casillajuez(5, 'juez')
        @casillas << juez
        @tieneJuez = true
      end
    end
    
    def getcasilla(numCasilla)
      if numCasilla >= 0 && numCasilla < @casillas.length
        return @casillas[numCasilla]
      else 
        return nil
      end
    end
    
    def nuevaposicion(actual, tirada)
      if !correcto_sin
        return -1
      end
      
      nueva_posicion = (actual + tirada)%@casillas.length
      
      if nueva_posicion != (actual + tirada)
        @porSalida += 1
      end
      
      return nueva_posicion
    end
    
    def calculartirada(origen, destino)
      tirada = destino - origen
      
      if tirada < 0
        tirada += @casillas.length
      end
      
      return tirada
    end
  end
end
