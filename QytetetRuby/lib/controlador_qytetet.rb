#Elena Cantero Molina
#encoding: utf-8
#trig.rb

require_relative "qytetet"
require_relative "jugador"
require_relative "tablero"
require_relative "casilla"
require_relative "tipo_sorpresa"
require_relative "sorpresa"
require_relative "titulo_propiedad"
require_relative "tipo_casilla"
require_relative "metodo_salir_carcel"
require_relative "dado"
require_relative "visual_textual_qytetet"
require_relative "especulador"
require_relative "calle"

module InterfazTextualQytetet  
  class ControladorQytetet    
    include ModeloQytetet
    
    def initialize
      @vista = VistaTextualQytetet.new
    end
    
    def pausa
      @vista.mostrar("Pulsa una tecla para continuar...")
      gets
    end
    
    def inicializacion_juego
      @juego = Qytetet.instance      
      nombres = Array.new
      nombres = @vista.obtener_nombre_jugadores      
      @juego.inicializar_juego(nombres)
      @jugador = @juego.jugador_actual
      @casilla = @juego.jugador_actual.casilla_actual
      
      @vista.mostrar("---------------- JUEGO -------------")
      @vista.mostrar(@juego.to_s)
      puts "-----------------------------------------------------------------"
      pausa
    end
    
    def desarrollo_juego
      loop do
        estado_actual
        if @jugador.encarcelado
          if(!intentar_salir_carcel)
            @vista.mostrar('Sigues en la carcel, pasas turno.')
          end
          pausa
        else
          pausa
          @vista.mostrar("Tirando dado...\n\n")
          pausa
          estado = @juego.jugar
          @casilla = @jugador.casilla_actual          
          @vista.mostrar("\nValor del dado: #{@juego.get_dado}\n")
          @vista.mostrar("Has caido en la casilla numero: #{@casilla.numero_casilla}\nTipo: #{@casilla.tipo}\nCoste: #{@casilla.coste}\n")
          pausa          
          
          if !@jugador.encarcelado
            case @casilla.tipo
            when ModeloQytetet::TipoCasilla::SORPRESA
              @vista.mostrar("#{@juego.carta_actual}\n\nAplicando Sorpresa...\n\n")
              @juego.aplicar_sorpresa
              @jugador = @juego.jugador_actual #Si el jugador se ha convertido en especulador
              estado_actual
              if @juego.carta_actual == ModeloQytetet::TipoSorpresa::IRACASILLA
                @casilla = @jugador.casilla_actual
                @vista.mostrar "Nueva casilla: #{@casilla}"
              end
            when ModeloQytetet::TipoCasilla::CALLE              
              @vista.mostrar("Has caido en la calle:\n#{@casilla}")
              if @casilla.tengo_propietario
                @vista.mostrar("\nPagando alquiler... \n\n")
                pausa
              else                
                elegido = @vista.elegir_comprar_propiedad 
                comprado = @juego.comprar_titulo_propiedad if elegido
                if comprado
                  @vista.mostrar("Has comprado el Titulo de propiedad: #{@casilla.titulo.nombre}") 
                else
                  @vista.mostrar("\nNo puedes comprar la propiedad")
                end
              end
              estado_actual
            end
          else
            @vista.mostrar("Has caido en la casilla del juez,"\
                "vas directamente a la carcel.")
          end
        end
        if @jugador.tengo_propiedades && !@jugador.encarcelado
          gestion_inmobiliaria
        else
          @vista.mostrar("No tienes propiedades\n\n")
        end
        pasa_turno
      end
    end
    
    def gestion_inmobiliaria
      loop do
        opcion = @vista.menu_gestion_inmobiliaria
        
        if (@jugador.tengo_propiedades or opcion.eql?(0))
          no_hipotecadas = @juego.propiedades_hipotecada_jugador(false)
          hipotecadas = @juego.propiedades_hipotecada_jugador(true)          
          case opcion
          when 1
            if !no_hipotecadas.empty?
              mod_casilla = elegir_propiedad(no_hipotecadas)
              if !mod_casilla.nil?
                if @juego.edificar_casa(mod_casilla)
                  @vista.mostrar("\nHas edificado una casa en #{mod_casilla.titulo.nombre}\n")
                else
                  @vista.mostrar("\nNo has podido edificar la casa\n")
                end
              end
            else
              @vista.mostrar("\nTodas tus propiedades estan hipotecadas\n")
              pausa
            end
          when 2
            if !no_hipotecadas.empty?
              mod_casilla = elegir_propiedad(no_hipotecadas)
              if !mod_casilla.nil?
                if @juego.edificar_hotel(mod_casilla)
                  @vista.mostrar("\nHas edificado un hotel en #{mod_casilla.titulo.nombre}\n")
                else
                  @vista.mostrar("\nNo has podido edificar el hotel\n")
                end
              end
            else
              @vista.mostrar("\nTodas tus propiedades estan hipotecadas\n")
              pausa
            end
          when 3
            if !no_hipotecadas.empty?
              mod_casilla = elegir_propiedad(no_hipotecadas)
              if !mod_casilla.nil?
                if @juego.vender_propiedad(mod_casilla)
                  @vista.mostrar("\nHas vendido la propiedad #{mod_casilla.titulo.nombre}\n")
                else
                  @vista.mostrar("\nNo has podido vender la propiedad\n")
                end
              end
            else
              @vista.mostrar("\nTodas tus propiedades estan hipotecadas\n")
              pausa
            end          
          when 4
            if !no_hipotecadas.empty?
              mod_casilla = elegir_propiedad(no_hipotecadas)
              if !mod_casilla.nil?
                if @juego.hipotecar_propiedad(mod_casilla)
                  @vista.mostrar("\nHas hipotecado la propiedad #{mod_casilla.titulo.nombre}\n")
                else
                  @vista.mostrar("\nNo has podido hipotecar la propiedad\n")
                end
              end
            else
              @vista.mostrar("\nTodas tus propiedades estan hipotecadas\n")
              pausa
            end
          when 5
            if !hipotecadas.empty?
              mod_casilla = elegir_propiedad(hipotecadas)
              if !mod_casilla.nil?
                if @juego.cancelar_hipoteca(mod_casilla)
                  @vista.mostrar("\nHas cancelado la hipoteca de #{mod_casilla.titulo.nombre}\n")
                else
                  @vista.mostrar("\nNo has podido cancelar la hipoteca\n")
                end
              end
            else
              @vista.mostrar("\nTodas tus propiedades estan hipotecadas\n")
              pausa
            end
          when 6
            puts no_hipotecadas
          end
        else
          @vista.mostrar("\nNo te quedan propiedades\n")
          pausa
        end
        break if opcion == 0
      end
    end
    
    def pasa_turno
      @vista.mostrar("Turno siguiente jugador... \n\n")
      @juego.siguiente_jugador
      @jugador = @juego.jugador_actual
      @casilla = @jugador.casilla_actual
    end
    
    def comprobar_cambio_saldo(saldo_anterior)
      if (saldo_anterior != @jugador.saldo)
        if (@casilla.soy_edificable && @casilla.tengo_propietario)
          @vista.mostrar("El jugador #{@jugador.nombre} ha caido en la casilla de #{@casilla.titulo.propietario.nombre} y le tiene que pagar")

        elsif (@casilla.tipo == TipoCasilla::IMPUESTO)
          @vista.mostrar("El jugador #{@jugador.nombre} ha caido en una casilla de impuesto")

        elsif (@casilla.tipo == TipoCasilla::SALIDA)
          @vista.mostrar("El jugador #{@jugador.nombre} ha pasado por la salida y su saldo se ha visto modificado")
        elsif (@casilla.tipo == TipoCasilla::SORPRESA)
          @vista.mostrar("El jugador #{@jugador.nombre} ha caido en una casilla sorpresa y su saldo se ha visto modificado")
        end
        pausa

        @vista.mostrar("El saldo del jugador #{@jugador.nombre} se ha visto afectado.")
        @vista.mostrar("Saldo Anterior: #{saldo_anterior} -> Saldo Actual: #{@jugador.saldo}")
        @vista.mostrar("Ha habido una diferencia de #{@jugador.saldo - saldo_anterior}")
        pausa
      end
    end
    
    def bancarrota
      return (@jugador.saldo < 0)
    end
    
    def game_over
      pos = 1
      @vista.mostrar("Game Over! Jugador #{@jugador.nombre} en bancarrota")
      @vista.mostrar("Ranking segun el capital")
      puts @juego.obtener_ranking
    end
    
    def estado_actual
      @vista.mostrar("#{@jugador.to_s}\n")
      if bancarrota
        game_over
      end
    end
    
    def intentar_salir_carcel
      libre = false
      eleccion = @vista.menu_salir_carcel
      
      if eleccion.eql?(0)
        libre = @juego.intentar_salir_carcel(MetodoSalirCarcel::TIRANDODADO)
        @vista.mostrar("Valor Dado: #{@juego.get_dado}")
      else
        libre = @juego.intentar_salir_carcel(MetodoSalirCarcel::PAGANDOLIBERTAD)
      end
      
      if libre
        @vista.mostrar("Has salido de la carcel")
      else
        @vista.mostrar("Sigues en la carcel. Sorry")
      end
      
      pausa
    end
    
    def elegir_propiedad(propiedades)
      @vista.mostrar("\tCasilla\tTitulo")
      lista_propiedades = Array.new
      for casilla in propiedades
        lista_propiedades << "\t #{casilla.numero_casilla} \t #{casilla.titulo.nombre}"
      end
      seleccion = @vista.menu_elegir_propiedad(lista_propiedades)
      return propiedades.at(seleccion)
    end
  
    def self.main
      controlador = ControladorQytetet.new
      controlador.inicializacion_juego
      controlador.desarrollo_juego
    end
    
    private :intentar_salir_carcel, :estado_actual, :game_over, :bancarrota, :pasa_turno, :gestion_inmobiliaria
  
    ControladorQytetet.main
  end
  
end
  
    
