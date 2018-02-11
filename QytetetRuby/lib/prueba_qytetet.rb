#Elena Cantero Molina
#encoding: utf-8
#trig.rb

require_relative "sorpresa"
require_relative "tipo_sorpresa"
require_relative "tablero"
require_relative "qytetet"

module ModeloQytetet
  class PruebaQytetet
    @@mazo = Array.new
    @tablero = Tablero.new
    
    #def self.inicializar_sorpresas
      #@@mazo << Sorpresa.new("Sales de fiesta y te encuentras en una esquina dinero, la suerte esta de tu parte", 100, TipoSorpresa::PAGARCOBRAR)
      #@@mazo << Sorpresa.new("Tu coche se avería en medio de la autovía, debes pagar a la grúa y el mecánico.", 500, TipoSorpresa::PAGARCOBRAR)
      #@@mazo << Sorpresa.new("Has sido pillado copiando durante un examen, ¡debes ir a la carcel!", 5 ,TipoSorpresa::IRACASILLA)
      #@@mazo << Sorpresa.new("¡Ha llegado un ataque zombie! Sales corriendo a la casilla 2 que es la única libre.", 6, TipoSorpresa::IRACASILLA)
      #@@mazo << Sorpresa.new("Un ovni ha llegado a la Tierra y después de succionarte el cerebro te dejan en la casilla 15", 15, TipoSorpresa::IRACASILLA)
      #@@mazo << Sorpresa.new("Has sido elegido alcalde, recibes una indemnización por cada propiedad que tengas", 100, TipoSorpresa::PORCASAHOTEL)
      #@@mazo << Sorpresa.new("Has sufrido fugas de agua en cada una de tus propiedades. Debes pagar por cada una de ellas.", 100, TipoSorpresa::PORCASAHOTEL)
      #@@mazo << Sorpresa.new("¡Felicidades! Hoy es el día de tu no cumpleaños, recibes un regalo de todos.", 200, TipoSorpresa::PORJUGADOR)
      #@@mazo << Sorpresa.new("Sin querer produciste un apagón en el vecindario. Debes pagar los daños producidos a cada jugador.", 100, TipoSorpresa::PORJUGADOR)
      #@@mazo << Sorpresa.new("Tienes un admirador secreto que ha pagado tu fianza. Sales de la cárcel.", 0, TipoSorpresa::SALIRCARCEL)
    #end
    
    def self.mayorque0
      @mayor = Array.new
        
        @@mazo.each do |sorpresa|
            if sorpresa::valor > 0
              @mayor << sorpresa
            end
        end
    end
    
    def self.sorpresairacasilla
        @ircasilla = Array.new
        
        @@mazo.each do |sorpresa|
            if sorpresa::tipo == TipoSorpresa::IRACASILLA
                @ircasilla << sorpresa
            end
        end
    end
    
    def self.tiposorpresaindicado (tipo)
        @tiposorpresa =Array.new
        
        @@mazo.each do |sorpresa|
            if sorpresa::tipo == tipo
                @tiposorpresa << sorpresa
            end
        end
    end
    
    def self.main
      #inicializar_sorpresas
      #puts @@mazo.to_s
      #puts mayorque0
      #puts sorpresairacasilla
      #puts tiposorpresaindicado(TipoSorpresa::SALIRCARCEL)
      #puts @tablero.to_s
      mc = Qytetet.instance
      puts mc.to_s
    end
  end
  PruebaQytetet.main
end
