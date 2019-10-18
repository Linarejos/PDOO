# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

require_relative "titulo_propiedad"
require_relative "sorpresa"
require_relative "diario"

module Civitas
  class Jugador
    include Comparable
    def initialize(nombre)
      @@CasasMax = 4
      @@CasasPorHotel = 4
      @@HotelesMax = 4
      @@PasoPorSalida = 1000
      @@PrecioLibertad = 200
      @@SaldoInicial = 7500
      @encarcelado = false
      @nombre = nombre
      @numCasillaActual = -1
      @puedeComprar = true
      @saldo = @@SaldoInicial
      @propiedades = Array.new
      @salvoconducto = nil
    end
    
    def self.new_jugador(otro)
      @nombre = otro.nombre;
      @numCasillaActual = otro.numCasillaActual;
      @encarcelado = otro.encarcelado;
      @puedeComprar = otro.puedeComprar;
      @saldo = otro.saldo;
      @propiedades = otro.propiedades;
      @salvoconducto = otro.salvoconducto;
    end
    
    attr_reader :nombre, :puedeComprar, :numCasillaActual, :encarcelado, :propiedades            
    
    attr_accessor :saldo
    
    protected :nombre, :propiedades, :saldo
            
    public :saldo=
    
    #private :CasasMax, :HotelesMax, :PasoPorSalida, :PasoPorSalida
    
    def cancelarhipoteca(ip)      
    end
    
    def cantidadcasashoteles
      total = 0
      for p in @propiedades
        total += p.cantidadcasashoteles
      end
      total
    end
    
    def <=>(otro)
      @saldo <=> otro.saldo
    end
    
    def comprar(titulo)      
    end
    
    def construircasa(ip)      
    end
    
    def construirhotel(ip)      
    end
    
    def enbancarrota
      @saldo < 0
    end
    
    def encarcelar(numcasillacarcel)
      if debeserencarcelado
        moveracasilla(numcasillacarcel)
        @encarcelado = true
        Diario.instance.ocurre_evento('Jugador encarcelado')
      end
      @encarcelado
    end
       
    def hipotecar(ip)      
    end
            
    def moveracasilla(numcasilla)
      if isencarcelado
        false
      else
        @numCasillaActual = numcasilla
        @puedeComprar = false
        Diario.instance.ocurre_evento('Jugador cambiado de casilla')
        true
      end
    end
    
    def obtenersalvoconducto(sorpresa)
      if isencarcelado
        false
      else
        @salvoconducto = sorpresa
        true
      end
    end
    
    def paga(cantidad)
      modificarsaldo(cantidad*-1)
    end
    
    def pagaalquiler(cantidad)
      if isencarcelado
        false
      else
        paga(cantidad)
      end
    end
    
    def pagaimpuesto(cantidad)
      if isencarcelado
        false
      else
        paga(cantidad)
      end
    end
    
    def pasaporsalida
      modificarsaldo(@@PasoPorSalida)
      Diario.instance.ocurre_evento('Paso por salida')
      true
    end
    
    def puedecomprarcasilla
      if isencarcelado
        @puedeComprar = false
      else
        @puedeComprar = true
      end
      @puedeComprar
    end
    
    def modificarsaldo(cantidad)
      @saldo = cantidad
      Diario.instance.ocurre_evento('Modificando saldo del jugador actual')
      true
    end
    
    def recibe(cantidad)
      if isencarcelado
        false
      else
        modificarsaldo(cantidad)
      end
    end
    
    def salicarcelpagando
      if isencarcelado and puedesalircarcelpagando
        paga(@@PrecioLibertad)
        @encarcelado = false
        Diario.instance.ocurre_evento('Salir de carcel pagando')
        true
      else
        false
      end
    end
    
    def salircarceltirando
      if Dado.instance.salgodelacarcel
        @encarcelado = false
        Diario.instance.ocurre_evento('Salir de carcel tirando')
        true
      else
        false
      end
    end
    
    def tienealgoquegestionar
      @propiedades != nil
    end
    
    def tienesalvoconducto
      @salvoconducto != nil
    end
    
    def to_s
      'Nombre: ' + @nombre.to_s +
      '      Propiedades: ' + @propiedades.length.to_s + 
      '      Encarcelado: ' + @encarcelado.to_s +
      '      Casilla Actual: ' + @numCasillaActual.to_s +
      '      Puede Comprar: ' + @puedeComprar.to_s + 
      '      Saldo: ' + @saldo.to_s +
      '      Salvoconducto: ' + @salvoconducto.to_s
    end
    
    def vender(ip)
      if isencarcelado
        false
      else
        if existelapropiedad(ip)
          if @propiedades[ip].vender(self)
            @propiedades.delete_at(ip)
            Diario.instance.ocurre_evento('Propiedad vendida')
            true
          else
            false
          end
        else
          false
        end
      end
    end
    
    protected
    def debeserencarcelado
      if !isencarcelado
        if tienesalvoconducto
          perdersalvoconducto
          Diario.instance.ocurre_evento('Jugador se libra de la carcel')          
          false
        else
          true
        end
      else
        false
      end
    end
    
    private
    def existelapropiedad(ip)
      i = 0
      loop do
        if @propiedades[i] == @propiedades[ip]
          true
        end
      end
      false
    end
    
    def perdersalvoconducto
      @salvoconducto.usada
      @salvoconducto = nil
    end
    
    def puedesalircarcelpagando
      @saldo >= @@PrecioLibertad
    end
    
    def puedoedificarcasa(propiedad)
      @saldo > propiedad.precioEdificar and propiedad.numCasas < @@CasasMax
    end
    
    def puedoedificarhotel(propiedad)
      @saldo > propiedad.precioEdificar and propiedad.numCasas == @@CasasMax and
        propiedad.numHoteles < @@HotelesMax
    end
    
    def puedogastar(precio)
      if isencarcelado
        false
      else
        @saldo >= precio
      end
    end
  end
end
