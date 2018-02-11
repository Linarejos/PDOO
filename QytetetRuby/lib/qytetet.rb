#Elena Cantero Molina
#encoding: utf-8
#trig.rb

require "singleton"

module ModeloQytetet
  class Qytetet
    include Singleton
    
    attr_accessor :jugadores, :carta_actual, :jugador_actual, :tablero
    attr_accessor :MAX_JUGADORES, :MAX_CARTAS, :MAX_CASILLAS
    attr_accessor :PRECIO_LIBERTAD, :SALDO_SALIDA
    
    @@MAX_JUGADORES = 4
    @@MAX_CARTAS = 10
    @@MAX_CASILLAS = 20
    @@PRECIO_LIBERTAD = 200
    @@SALDO_SALIDA = 1000

    def initialize
      @dado = Dado.instance
      @carta_actual = nil
      @mazo = Array.new
      @jugador_actual = nil
      @jugadores = Array.new
      @tablero = nil
    end
    
    def self.get_saldo_salida
      return @@SALDO_SALIDA
    end
    
    def get_dado
      return @dado.tirar
    end

    def aplicar_sorpresa
      tiene_propietario = false
      
      if @carta_actual.tipo.eql?(TipoSorpresa::PAGARCOBRAR)
        @jugador_actual.modificar_saldo(@carta_actual.valor)
      elsif @carta_actual.tipo.eql?(TipoSorpresa::IRACASILLA)
        es_carcel = @tablero.es_casilla_carcel(@carta_actual.valor)
        if es_carcel
          encarcelar_jugador
        else
          nueva_casilla = @tablero.es_casilla_carcel(@carta_actual.valor)
          tiene_propietario = @jugador_actual.actualizar_posicion(nueva_casilla)
        end
      elsif @carta_actual.tipo.eql?(TipoSorpresa::PORCASAHOTEL)
        @jugador_actual.pagar_cobrar_por_casa_y_hotel(@carta_actual.valor)
      elsif @carta_actual.tipo.eql?(TipoSorpresa::PORJUGADOR)
        for jugador in @jugadores
          if !jugador.eql?(@jugador_actual)
            jugador.modificar_saldo(-@carta_actual.valor)
            @jugador_actual.modificar_saldo(@carta_actual.valor)
          end
        end
      elsif (@carta_actual.tipo.eql?(TipoSorpresa::CONVERTIRME))
        @jugador_actual = @jugador_actual.convertirme(@carta_actual.valor)
      end
      
      if @carta_actual.tipo.eql?(TipoSorpresa::SALIRCARCEL)
        @jugador_actual.carta_libertad = @carta_actual
      else
        @mazo << @carta_actual
      end
      
      return tiene_propietario
    end    

    def cancelar_hipoteca(casilla)
      cancelar_hipoteca = false
      
      if casilla.soy_edificable
        if casilla.esta_hipotecada and @jugador_actual.puedo_pagar_hipoteca(casilla)
          cantidad = casilla.coste
          @jugador_actual.modificar_saldo(-cantidad)
          casilla.titulo.hipotecada = false
          cancelar_hipoteca = true
        end
      end
      
      return cancelar_hipoteca
    end    

    def comprar_titulo_propiedad
      return @jugador_actual.comprar_titulo
    end    

    def edificar_casa(casilla)
      if casilla.soy_edificable
        puts @jugador_actual.factor_especulador
        se_puede_edificar = casilla.se_puede_edificar_casa(@jugador_actual.factor_especulador)
        if (se_puede_edificar and @jugador_actual.puedo_edificar_casa(casilla))
            @jugador_actual.modificar_saldo(-casilla.edificar_casa)
            return true
        end
      end
      
      return false
    end

    def edificar_hotel(casilla)
      if casilla.soy_edificable
        se_puede_edificar = casilla.se_puede_edificar_hotel
        if se_puede_edificar and @jugador_actual.puedo_edificar_hotel(casilla)
            @jugador_actual.modificar_saldo(-casilla.edificar_hotel)
            return true
        end
      end      
      return false
    end

    def hipotecar_propiedad(casilla)
      puedo_hipotecar = false
      cantidad_recibida = 0
      
      if casilla.soy_edificable and !casilla.esta_hipotecada
        puedo_hipotecar = @jugador_actual.puedo_hipotecar(casilla)
        if puedo_hipotecar
          cantidad_recibida = casilla.hipotecar
          @jugador_actual.modificar_saldo(cantidad_recibida)
        end
      end
      
      return puedo_hipotecar
    end    

    def inicializar_juego(nombres)
      inicializar_tablero
      inicializar_cartas_sorpresa
      inicializar_jugadores(nombres)
      salida_jugadores
    end    

    def intentar_salir_carcel(metodo)
      libre = false
      
      if metodo.eql?(MetodoSalirCarcel::TIRANDODADO)
        valor_dado = @dado.tirar
        libre = (valor_dado > 5)
      elsif metodo.eql?(MetodoSalirCarcel::PAGANDOLIBERTAD)
        tengo_saldo = @jugador_actual.pagar_libertad(-Qytetet::PRECIO_LIBERTAD)
        libre = tengo_saldo
      end
      
      if libre
        @jugador_actual.encarcelado = false
      end
      
      return libre
    end    

    def jugar
      valor_dado = @dado.tirar
      casilla_posicion = @jugador_actual.casilla_actual
      nueva_casilla = @tablero.obtener_nueva_casilla(casilla_posicion, valor_dado)
      tiene_propietario = @jugador_actual.actualizar_posicion(nueva_casilla)
      
      if !nueva_casilla.soy_edificable
        if nueva_casilla.tipo.eql?(TipoCasilla::JUEZ)
          encarcelar_jugador
        elsif nueva_casilla.tipo.eql?(TipoCasilla::SORPRESA)
          @carta_actual = @mazo.at(0)
          @mazo.delete_at(0)
        end
      end
      
      return tiene_propietario
    end    

    def obtener_ranking
      r = @jugadores.sort_by(&:obtener_capital)
      .map { |j| [j.nombre, j.obtener_capital] }
      Hash[r]
    end    

    def propiedades_hipotecada_jugador(hipotecadas)
      casillas = Array.new
      mistitulos = @jugador_actual.obtener_propiedades_hipotecadas(hipotecadas)
      for c in @tablero.casillas
        for aux in mistitulos
          if aux.casilla_actual.titulo.eql?(c.titulo)
            casillas << c
          end
        end
      end
      
      return casillas
    end    

    def siguiente_jugador
      pos = 0
      for i in 0..@jugadores.length
        if @jugador_actual == @jugadores.at(i)
          pos = i
        end
      end
      @jugador_actual = @jugadores.at((pos + 1) % @jugadores.length)
      return @jugador_actual
    end    

    def vender_propiedad(casilla)
      puedo_vender = false
      
      if casilla.soy_edificable
        puedo_vender = @jugador_actual.puedo_vender_propiedad(casilla)
        if puedo_vender
          @jugador_actual.vender_propiedad(casilla)
        end
      end
      
      return puedo_vender
    end    

    def encarcelar_jugador
      if !@jugador_actual.tengo_carta_libertad
        casilla_carcel = @tablero.carcel
        @jugador_actual.ir_a_carcel(casilla_carcel)
      else
        carta = @jugador_actual.devolver_carta_libertad
        @mazo << carta
      end
    end    

    def inicializar_cartas_sorpresa 
      @mazo << Sorpresa.new("Sales de fiesta y te encuentras en una esquina dinero, la suerte esta de tu parte", 100, TipoSorpresa::PAGARCOBRAR)
      @mazo << Sorpresa.new("Tu coche se averia en medio de la autovia, debes pagar a la grua y el mecanico.", -500, TipoSorpresa::PAGARCOBRAR)
      @mazo << Sorpresa.new("Has sido pillado copiando durante un examen, debes ir a la carcel", 6, TipoSorpresa::IRACASILLA)
      @mazo << Sorpresa.new("Ha llegado un ataque zombie. Sales corriendo a la casilla 2 que es la unica libre.", 2, TipoSorpresa::IRACASILLA)
      @mazo << Sorpresa.new("Un ovni ha llegado a la Tierra y despues de succionarte el cerebro te dejan en la casilla 15", 15, TipoSorpresa::IRACASILLA)
      @mazo << Sorpresa.new("Has sido elegido alcalde, recibes una indemnizacion por cada propiedad que tengas", 100, TipoSorpresa::PORCASAHOTEL)
      @mazo << Sorpresa.new("Has sufrido fugas de agua en cada una de tus propiedades. Debes pagar por cada una de ellas.", -100, TipoSorpresa::PORCASAHOTEL)
      @mazo << Sorpresa.new("Felicidades. Hoy es el dia de tu no cumpleanios, recibes un regalo de todos.", 200, TipoSorpresa::PORJUGADOR)
      @mazo << Sorpresa.new("Sin querer produciste un apagon en el vecindario. Debes pagar los danos producidos a cada jugador.", -100, TipoSorpresa::PORJUGADOR)
      @mazo << Sorpresa.new("Tienes un admirador secreto que ha pagado tu fianza. Sales de la carcel.", 0, TipoSorpresa::SALIRCARCEL)
      @mazo << Sorpresa.new(" ", 3000, TipoSorpresa::CONVERTIRME)
      @mazo << Sorpresa.new(" ", 5000, TipoSorpresa::CONVERTIRME)
      @mazo.shuffle!
    end    

    def inicializar_jugadores(nombres)
      if(nombres.length < 2 || nombres.length > @@MAX_JUGADORES)
        raise ArgumentError, "Numero incorrecto de jugadores"
      end
      
      @jugadores = Array.new
      
      for jugador in nombres
        @jugadores << Jugador.new(jugador)
      end
    end    

    def inicializar_tablero
      @tablero = Tablero.new
    end    

    def salida_jugadores
      for jugador in @jugadores
        jugador.casilla_actual = @tablero.obtener_casilla_numero(0)
      end
      @jugador_actual = @jugadores.at(rand(@jugadores.size))
    end
    
    private :inicializar_tablero, :salida_jugadores, :inicializar_cartas_sorpresa, :encarcelar_jugador
    
    def to_s
      "QYTETET
        \n->cartaActual #{@carta_actual} \n\n->mazo #{@mazo.to_s} \n\n->jugadores #{@jugadores}
        \n\n->tablero #{@tablero.to_s}"
    end
  end
end
