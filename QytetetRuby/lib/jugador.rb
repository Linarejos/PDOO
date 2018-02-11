#Elena Cantero Molina
#encoding: utf-8
#trig.rb

module ModeloQytetet
  class Jugador
    attr_accessor :casilla_actual, :encarcelado, :carta_libertad, :saldo, :nombre, :propiedades   
    
    def initialize(nombre)
      @encarcelado = false
      @nombre = nombre
      @saldo = 7500
      @carta_libertad = nil
      @casilla_actual = nil
      @propiedades = Array.new
      @factor_especulador = 1
    end
    
    def copy_constructor(other)
      @encarcelado = other.encarcelado
      @nombre = other.nombre
      @saldo = other.saldo
      @casilla_actual = other.casilla_actual
      @propiedades = other.propiedades
      @carta_libertad = other.carta_libertad
      @factor_especulador = other.factor_especulador
    end
    
    def factor_especulador
      return @factor_especulador
    end
    
    def tengo_propiedades
      return !@propiedades.empty?
    end

    def actualizar_posicion(casilla)
      if casilla.numero_casilla < @casilla_actual.numero_casilla
        modificar_saldo(Qytetet.get_saldo_salida)
      end
      
      tengo_propietario = false
      @casilla_actual = casilla
      
      if casilla.soy_edificable
        tengo_propietario = casilla.tengo_propietario
        if tengo_propietario
          encarcelado = casilla.propietario_encarcelado
          if !encarcelado
            if !casilla.esta_hipotecada
              modificar_saldo(-casilla.cobrar_alquiler)
            end            
          end
        end
      elsif casilla.tipo.eql?(TipoCasilla::IMPUESTO)
        pagar_impuestos(casilla.coste)
        return true
      end
      
      return tengo_propietario
    end    

    def comprar_titulo
      puedo_comprar = false
      
      if @casilla_actual.soy_edificable and !@casilla_actual.tengo_propietario
        coste_compra = @casilla_actual.coste
        if coste_compra <= @saldo
          @propiedades << @casilla_actual.asignar_propietario(self)
          modificar_saldo(-coste_compra)
          puedo_comprar = true
        end
      end
      
      return puedo_comprar
    end    

    def devolver_carta_libertad
      aux = @carta_libertad
      @carta_libertad = nil
      return aux
    end    

    def ir_a_carcel(casilla)
      @casilla_actual = casilla
      @encarcelado = true
    end    

    def modificar_saldo(cantidad)
      @saldo += cantidad
    end    

    def obtener_capital
      suma_propiedades = 0
      for tit in @propiedades
        if tit.hipotecada.eql?(true)
          suma_propiedades -= tit.hipotecaBase
        else
          suma_propiedades += tit.casilla.coste + ((tit.casilla.numero_casas + tit.casilla.numero_hoteles)*tit.precioEdificar)
        end
      end
      return suma_propiedades
    end
    
    def obtener_propiedades_hipotecadas(hipotecadas)
      propi = Array.new
      
      for prop in @propiedades
        if prop.casilla_actual.titulo.hipotecada.eql?(hipotecadas)
          propi << prop
        end
      end
      
      return propi
    end    

    def pagar_cobrar_por_casa_y_hotel(cantidad)
      numero_total = cuantas_casas_hoteles_tengo
      modificar_saldo(numero_total*cantidad)
    end    

    def pagar_libertad(cantidad)
      tengo_saldo = tengo_saldo(cantidad)
      
      if tengo_saldo
        modificar_saldo(-cantidad)
      end
      
      return tengo_saldo
    end    

    def puedo_edificar_casa(casilla)
      es_mia = es_de_mi_propiedad(casilla)
      tengo_saldo = true
      
      if es_mia
        coste_edificar_casa = casilla.titulo.precioEdificar
        tengo_saldo = tengo_saldo(coste_edificar_casa)
      end
      
      return (es_mia and tengo_saldo)
    end

    def puedo_edificar_hotel(casilla)
      es_mia = es_de_mi_propiedad(casilla)
      tengo_saldo = true
      
      if es_mia
        coste_edificar_hotel = casilla.titulo.precioEdificar
        tengo_saldo = tengo_saldo(coste_edificar_hotel)
      end
      
      return (es_mia and tengo_saldo)
    end    

    def puedo_hipotecar(casilla)
      return es_de_mi_propiedad(casilla)
    end    

    def puedo_pagar_hipoteca(casilla)
      return (casilla.titulo.hipotecaBase < @saldo)
    end    

    def puedo_vender_propiedad(casilla)
      puts casilla
      return (es_de_mi_propiedad(casilla) and !casilla.titulo.hipotecada)
    end    

    def tengo_carta_libertad
      return !@carta_libertad.nil?
    end    

    def vender_propiedad(casilla)
      precio_venta = casilla.vender_titulo
      modificar_saldo(precio_venta)
      eliminar_de_mis_propiedades(casilla)
    end    

    def cuantas_casas_hoteles_tengo
      total = 0
      for prop in @propiedades
        total += prop.casilla.num_casas + prop.casilla.num_hoteles
      end
      return total
    end    

    def eliminar_de_mis_propiedades(casilla)
      for tit in @propiedades
        if tit.casilla_actual.titulo.eql?(casilla.titulo)
          @propiedades.delete(tit)
        end
      end
    end    

    def es_de_mi_propiedad(casilla)
      for prop in @propiedades
        if  prop.casilla_actual.titulo.eql?(casilla.titulo)
          return true
        end
      end
      return false
    end    

    def tengo_saldo(cantidad)
      return (@saldo >= cantidad)
    end
    
    def convertirme(fianza)
      especulador = Especulador.new(self, fianza)      
      return especulador
    end
    
    def pagar_impuestos(cantidad)
      modificar_saldo(-cantidad)
    end
    
    def to_s
      return "Jugador #{@nombre}\nEncarcelado -> #{@encarcelado}\nSaldo -> #{@saldo}\nPropiedades -> \n #{@propiedades}\nCarta Libertad -> #{@carta_libertad}\nCasilla Actual -> #{@casilla_actual}\n"
    end
    
    private :cuantas_casas_hoteles_tengo, :eliminar_de_mis_propiedades, :es_de_mi_propiedad, :tengo_saldo
    protected :pagar_impuestos, :copy_constructor
  end
end
