# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

#encoding:utf-8

require 'io/console'

require_relative 'operaciones_juego'
require_relative 'civitas_juego'
require_relative 'respuestas'
require_relative 'operacion_inmobiliaria'
require_relative 'diario'
require_relative 'salidas_carcel'

module Civitas
  class Vista_textual
    attr_reader :iGestion, :iPropiedad
    
    def initialize
      @iPropiedad = -1
      @iGestion = -1
    end
    
    def mostrar_estado(estado)
      puts estado
    end
    
    def pausa
      print "Pulsa una tecla"
      STDIN.getch
      print "\n"
    end

    def lee_entero(max,msg1,msg2)
      ok = false
      begin
        print msg1
        cadena = gets.chomp
        begin
          if (cadena =~ /\A\d+\Z/)
            numero = cadena.to_i
            ok = true
          else
            raise IOError
          end
        rescue IOError
          puts msg2
        end
        if (ok)
          if (numero >= max)
            ok = false
          end
        end
      end while (!ok)

      return numero
    end
    
    def menu(titulo,lista)
      tab = "  "
      puts titulo
      index = 0
      lista.each { |l|
        puts tab+index.to_s+"-"+l
        index += 1
      }

      opcion = lee_entero(lista.length, tab+'Elige una opcion: ', tab+'Valor erroneo')
      return opcion
    end
    
    def comprar
      opciones = Array.new
      opciones = ['Si', 'No']
      opcion = menu('Desea comprar la calle', opciones)
      
      lista_respuestas = [Civitas::Respuestas::SI, Civitas::Respuestas::NO]
      
      lista_respuestas[opcion]
    end

    def gestionar      
      propiedades = Array.new
      
      for t in @juegoModel.getjugadoractual.propiedades
        propiedades << t.nombre
      end
            
      opciones = Array.new
      opciones = ['Vender', 'Hipotecar', 'Cancelar Hipoteca', 'Construir Casa',
        'Construir Hotel', 'Terminar']
      @iGestion = menu('Que gestion inmobiliaria desea hacer', opciones)
      
      if !propiedades.empty?
        @iPropiedad = menu("Que propiedad desea gestionar", propiedades)
      else
        puts 'No tienes propiedades'
      end
      
    end

    def get_gestion
      @iGestion
    end

    def get_propiedad
      @iPropiedad
    end

    def mostrar_siguiente_operacion(operacion)
      puts operacion.to_s
    end

    def mostrar_eventos
      while Diario.instance.eventos_pendientes
        puts Diario.instance.leer_evento
      end
    end
    
    def salir_carcel
      opciones = Array.new
      opciones = ['Pagando', 'Tirando el dado']
      opcion = menu('Elige la forma para intentar salir de la carcel', 
               opciones)
             
      lista_salidas = [Civitas::SalidasCarcel::PAGANDO, Civitas::SalidasCarcel::TIRANDO]
      
      lista_salidas[opcion]
    end

    def set_civitas_juego(civitas)
        @juegoModel=civitas
        self.actualizar_vista
    end

    def actualizar_vista
      jugador_actual = @juegoModel.getjugadoractual
      casilla = @juegoModel.getcasillaactual      
      
      #puts jugador_actual.to_s
      #puts casilla.to_s  No muestra la casilla    
    end    
  end
end

