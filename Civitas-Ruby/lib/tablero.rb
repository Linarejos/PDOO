# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.
# encoding:utf-8

module Civitas
  class Tablero
    def initialize(numCasillaCarcel)
      midiario = Diario.instance
      midado = Dado.instance
      
      if(numCasillaCarcel >= 1)
        @numCasillaCarcel = numCasillaCarcel
      end
      
      @casillas = Array.new
      salida = Casilla.new("Salida")
      @casillas << salida #¿Introduce salida en el array?
      
      @porSalida = 0
      @tieneJuez = false
    end
    
    private
    
    def correcto
      @casillas.length > @numCasillaCarcel && @tieneJuez
    end
    
    def correcto(numCasilla)
      correcto && numCasilla < @casillas.length
    end
    
    public
    
    attr_reader :numCasillaCarcel
    
    def getporsalida
      actual = @porSalida
      
      if @porSalida > 0
        @porSalida = @posSalida - 1
      end
      
      return actual
    end
    
    def aniadecasilla(casilla)
      carcel = new Casilla("Carcel")
      
      if @casillas.length == @numCasillaCarcel
        @casillas << carcel
      end
      
      @casillas << casilla
      
      if @casillas.length.eq?(@numCasillaCarcel)
        @casillas << carcel
      end
    end
    
    def aniadejuez
      juez = new Casilla("Juez")
      
      if !@tieneJuez
        @casillas << juez
        @tieneJuez = true
      end
    end
    
    def getcasilla(numCasilla)
      if numCasilla >= 0 && numCasilla < @casillas.length
        return @casillas[numCasilla]
      else 
        return null
      end
    end
    
    def nuevaposicion(actual, tirada)
      if !correcto
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
