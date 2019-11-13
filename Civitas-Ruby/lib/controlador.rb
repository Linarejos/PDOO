# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

require_relative 'vista_textual'
require_relative 'civitas_juego'
require_relative 'operaciones_juego'
require_relative 'operacion_inmobiliaria'
require_relative 'gestiones_inmobiliarias'

module Civitas
  class Controlador
    def initialize(juego, vista)
      @juego = juego
      @vista = vista
    end
    
    def juega     
      @vista.set_civitas_juego(@juego)
      
      while !@juego.finaldeljuego
        @vista.actualizar_vista
        @vista.pausa
        siguiente = @juego.siguientepaso
        @vista.mostrar_siguiente_operacion(siguiente)
        
        if siguiente != Civitas::Operaciones_juego::PASAR_TURNO
          @vista.mostrar_eventos
        end
        
        if !@juego.finaldeljuego
          case siguiente
          when Civitas::Operaciones_juego::COMPRAR
            r = @vista.comprar
            if r == Civitas::Respuestas::SI
              @juego.comprar
              @juego.siguientepasocompletado(siguiente)
            end
          when Civitas::Operaciones_juego::GESTIONAR
            @vista.gestionar
            lista_gestion = [Civitas::GestionesInmobiliarias::CANCELAR_HIPOTECA,
              Civitas::GestionesInmobiliarias::CONSTRUIR_CASA,
              Civitas::GestionesInmobiliarias::CONSTRUIR_HOTEL,
              Civitas::GestionesInmobiliarias::HIPOTECAR,
              Civitas::GestionesInmobiliarias::TERMINAR,
              Civitas::GestionesInmobiliarias::VENDER
            ]
            
            operacion = OperacionInmobiliaria.new(lista_gestion[@vista.iGestion], @vista.iPropiedad)
            
            case opeacion.iGestion
            when Civitas::GestionesInmobiliarias::TERMINAR
              @juego.siguientepasocompletado(siguiente)
            when Civitas::GestionesInmobiliarias::CANCELAR_HIPOTECA
              @juego.cancelarhipoteca(operacion.num_propiedad)
            when Civitas::GestionesInmobiliarias::CONSTRUIR_CASA
              @juego.construircasa(operacion.num_propiedad)
            when Civitas::GestionesInmobiliarias::CONSTRUIR_HOTEL
              @juego.construirhotel(operacion.num_propiedad)
            when Civitas::GestionesInmobiliarias::HIPOTECAR
              @juego.hipotecar(operacion.num_propiedad)
            when Civitas::GestionesInmobiliarias::VENDER
              @juego.vender(operacion.num_propiedad)
            end
            
          when Civitas::Operaciones_juego::SALIR_CARCEL
            if @vista.salir_carcel == Civitas::SalidasCarcel::TIRANDO
              @juego.salircarceltirando
            elsif @vista.salir_carcel == Civitas::SalidasCarcel::PAGANDO
              @juego.salircarcelpagando
            end
            
            @juego.siguientepasocompletado(siguiente)
          end
        else
          #He cambiado la visibilidad del ranking de jugadores
          @juego.ranking
        end
      end
    end
  end
end
