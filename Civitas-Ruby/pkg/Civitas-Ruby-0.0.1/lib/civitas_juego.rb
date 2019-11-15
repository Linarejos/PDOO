# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

require_relative "tablero"
require_relative "operaciones_juego"
require_relative "mazo_sorpresas"
require_relative "dado"
require_relative "gestor_estados"
require_relative "estados_juego"
require_relative "jugador"

module Civitas
  class CivitasJuego
    def initialize(nombres)
      @jugadores = Array.new
      for n in nombres
        @jugadores << Jugador.new(n)
      end
      
      @gestorestados = Gestor_estados.new
      @estado =  @gestorestados.estado_inicial
      
      @indiceJugadorActual = Dado.instance.tirar
      
      @mazo = MazoSorpresas.new
      inicializartablero(@mazo)
      inicializarmazosorpresas(@tablero)
    end
    
    def cancelarhipoteca(ip)
      getjugadoractual.cancelarhipoteca(ip)
    end
    
    def comprar      
      res = false
      jugador_actual = getjugadoractual
      num_casilla_actual = jugador_actual.numCasillaActual
      casilla = @tablero.getcasilla(num_casilla_actual)
      if casilla.nombre != 'Salida'
        titulo = casilla.titulo
        res = jugador_actual.comprar(titulo)
      end
      res
    end
    
    def construircasa(ip)
      getjugadoractual.construircasa(ip)
    end
    
    def construirhotel(ip)
      getjugadoractual.construirhotel(ip)
    end
    
    def finaldeljuego
      for j in @jugadores
        if j.enbancarrota
          true
        end
      end
      false
    end
    
    def getcasillaactual      
      @tablero.getcasilla(getjugadoractual.numCasillaActual)
    end
    
    def getjugadoractual
      @jugadores.at(@indiceJugadorActual)
    end
    
    def hipotecar(ip)
      getjugadoractual.hipotecar(ip)
    end
    
    def infojugadortexto
      getjugadoractual.to_s
    end
    
    def salircarcelpagando
      getjugadoractual.salircarcelpagando(ip)
    end
    
    def salircarceltirando
      getjugadoractual.salircarceltirando(ip)
    end
    
    def siguientepaso    
      jugador_actual = getjugadoractual
      operacion = @gestorestados.operaciones_permitidas(jugador_actual, @estado)
      
      if operacion == Civitas::Operaciones_juego::PASAR_TURNO
        pasarturno
        siguientepasocompletado(operacion)
      else if operacion == Civitas::Operaciones_juego::AVANZAR
        avanzajugador
        siguientepasocompletado(operacion)
      end
      end
      
      operacion
    end
    
    def siguientepasocompletado(operacion)
      @estado = @gestorestados.siguiente_estado(getjugadoractual, @estado, operacion)
    end
    
    def vender(ip)
      getjugadoractual.vender(ip)
    end
    
    private
    def avanzajugador
      jugador_actual = getjugadoractual  #1.1
      posicion_actual = jugador_actual.numCasillaActual #1.2
      tirada = Dado.instance.tirar  #1.3
      posicion_nueva = @tablero.nuevaposicion(posicion_actual, tirada)  #1.4
      casilla = @tablero.getcasilla(posicion_nueva)   #1.5
      contabilizarpasosporsalida(jugador_actual)  #1.6
      jugador_actual.moveracasilla(posicion_nueva) #1.7
      casilla.recibejugador(@indiceJugadorActual, @jugadores) #1.8
      contabilizarpasosporsalida(jugador_actual)  #1.9
    end
    
    def contabilizarpasosporsalida(jugadoractual)
      if @tablero.porSalida > 0
        jugadoractual.pasaporsalida
      end
    end
    
    def inicializarmazosorpresas(tablero)
      @mazo.almazo(Sorpresa.new_sorpresa_ircarcel(Civitas::TipoSorpresa::IRCARCEL, tablero))
      @mazo.almazo(Sorpresa.new_sorpresa_ircarcel(Civitas::TipoSorpresa::IRCARCEL, tablero))
      @mazo.almazo(Sorpresa.new_sorpresa_casilla(Civitas::TipoSorpresa::IRCASILLA, tablero, 4, 'Ve a la casilla 4'))
      @mazo.almazo(Sorpresa.new_sorpresa_casilla(Civitas::TipoSorpresa::IRCASILLA, tablero, 12, 'Ve a la casilla 12'))
      @mazo.almazo(Sorpresa.new_sorpresas(Civitas::TipoSorpresa::PAGARCOBRAR, -500, 'Paga a otro jugador'))
      @mazo.almazo(Sorpresa.new_sorpresas(Civitas::TipoSorpresa::PAGARCOBRAR, 500, 'Recibes dinero *-*'))
      @mazo.almazo(Sorpresa.new_sorpresas(Civitas::TipoSorpresa::PORCASAHOTEL, 100, 'Pagas por cada casa y hotel'))
      @mazo.almazo(Sorpresa.new_sorpresas(Civitas::TipoSorpresa::PORJUGADOR, 50, 'Recibes dinero de los demas'))
      @mazo.almazo(Sorpresa.new_sorpresas(Civitas::TipoSorpresa::PORJUGADOR, -50, 'Dale dinero a los demas'))
      @mazo.almazo(Sorpresa.new_sorpresa_evitarcarcel(Civitas::TipoSorpresa::SALIRCARCEL, @mazo))
      @mazo.almazo(Sorpresa.new_sorpresa_evitarcarcel(Civitas::TipoSorpresa::SALIRCARCEL, @mazo))
      @mazo.almazo(Sorpresa.new_sorpresa_evitarcarcel(Civitas::TipoSorpresa::SALIRCARCEL, @mazo))                          
    end
    
    def inicializartablero(mazo)
      @tablero = Tablero.new(5)
      
      @tablero.aniadecasilla(Casilla.new_casilla('Salida'))
      @tablero.aniadecasilla(Casilla.new_casillacalle(TituloPropiedad.new('Calle Recogidas',
      50, 20, 100, 100, 50)))
      @tablero.aniadecasilla(Casilla.new_casillacalle(TituloPropiedad.new('Calle Tranvia',
      55, 20, 120, 150, 60)))
      @tablero.aniadecasilla(Casilla.new_casillacalle(TituloPropiedad.new('Calle Aeropuerto',
      60, 20, 140, 200, 70)))
      @tablero.aniadecasilla(Casilla.new_casillacalle(TituloPropiedad.new('Calle Sana',
      65, 20, 160, 250, 80)))
      @tablero.aniadecasilla(Casilla.new_casillacalle(TituloPropiedad.new('Calle Ole',
      80, 20, 200, 300, 90)))
      @tablero.aniadecasilla(Casilla.new_casillacalle(TituloPropiedad.new('Calle Mesa',
      85, 20, 220, 350, 100)))
      @tablero.aniadecasilla(Casilla.new_casillacalle(TituloPropiedad.new('Calle Trompeta',
      90, 20, 240, 400, 110)))
      @tablero.aniadecasilla(Casilla.new_casillacalle(TituloPropiedad.new('Calle Maracas',
      95, 20, 260, 450, 120)))
      @tablero.aniadecasilla(Casilla.new_casillaimpuesto(400, 'Impuesto'))
      @tablero.aniadecasilla(Casilla.new_casillacalle(TituloPropiedad.new('Calle Solida',
      110, 20, 300, 500, 130)))
      @tablero.aniadecasilla(Casilla.new_casillacalle(TituloPropiedad.new('Calle Coche',
      115, 20, 320, 550, 140)))
      @tablero.aniadecasilla(Casilla.new_casillacalle(TituloPropiedad.new('Calle Pienso',
      120, 20, 340, 600, 150)))
      @tablero.aniadecasilla(Casilla.new_casillacalle(TituloPropiedad.new('Calle Tren',
      125, 20, 360, 650, 160)))
      @tablero.aniadejuez
      @tablero.aniadecasilla(Casilla.new_casillacalle(TituloPropiedad.new('Calle Mal',
      140, 20, 400, 700, 170)))
      @tablero.aniadecasilla(Casilla.new_casillacalle(TituloPropiedad.new('Calle Guitarra',
      145, 20, 420, 750, 180)))
      @tablero.aniadecasilla(Casilla.new_casillacalle(TituloPropiedad.new('Calle Hora',
      155, 20, 440, 800, 190)))
      @tablero.aniadecasilla(Casilla.new_casillacalle(TituloPropiedad.new('Calle Diamante',
      170, 20, 460, 850, 200))) 
    end
    
    def pasarturno
      @indiceJugadorActual = (@indiceJugadorActual+1)%@jugadores.length
    end
    
    public
    def ranking
      @jugadores.sort
    end
  end
end
