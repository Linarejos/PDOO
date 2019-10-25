# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

#encoding:utf-8

require_relative 'operaciones_juego'
require 'io/console'

module Civitas
  class Vista_textual

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

      opcion = lee_entero(lista.length, "\n"+tab+"Elige una opción: ",
                          tab+"Valor erróneo")
      return opcion
    end
    
    def comprar
    end

    def gestionar
    end

    def get_gestion
    end

    def get_propiedad
    end

    def mostrar_siguiente_operacion(operacion)
    end

    def mostrar_eventos
    end

    def set_civitas_juego(civitas)
        @juegoModel=civitas
        self.actualizarVista
    end

    def actualizar_vista
    end    
  end
end

