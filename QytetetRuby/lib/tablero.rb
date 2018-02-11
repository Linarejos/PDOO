#Elena Cantero Molina
#encoding: utf-8
#trig.rb

module ModeloQytetet
  class Tablero
    attr_reader :carcel, :casillas
    
    def initialize
      @casillas = Array.new
      @casillas << Casilla.new(0, 0, TipoCasilla::SALIDA)
      @casillas << Calle.new(1, 300, TituloPropiedad.new("Calle Tranvia", 50, 0.10, 250, 150))
      @casillas << Casilla.new(2, -200, TipoCasilla::IMPUESTO)
      @casillas << Calle.new(3, 400, TituloPropiedad.new("Calle Vengala", 55, -0.16, 280, 300))
      @casillas << Calle.new(4, 450, TituloPropiedad.new("Calle Tren", 60, 0.18, 300, 450))
      @casillas << Casilla.new(5, 0, TipoCasilla::CARCEL)
      @casillas << Calle.new(6, 500, TituloPropiedad.new("Calle Final", 60, 0.20, 350, 500))
      @casillas << Casilla.new(7, 0, TipoCasilla::SORPRESA)
      @casillas << Calle.new(8, 550, TituloPropiedad.new("Calle Reinado", 65, -0.15, 400, 600))
      @casillas << Calle.new(9, 650, TituloPropiedad.new("Calle Europa", 70, 0.17, 450, 650))
      @casillas << Casilla.new(10, 0, TipoCasilla::PARKING)
      @casillas << Calle.new(11, 700, TituloPropiedad.new("Calle Periodistas", 70, 0.14, 500, 700))
      @casillas << Calle.new(12, 700, TituloPropiedad.new("Calle Cabezas", 75, 0.16, 550, 750))
      @casillas << Casilla.new(13, 0, TipoCasilla::SORPRESA)
      @casillas << Calle.new(14, 750, TituloPropiedad.new("Calle Molina", 80, -0.20, 600, 800))
      @casillas << Casilla.new(15, 0, TipoCasilla::JUEZ)
      @casillas << Calle.new(16, 800, TituloPropiedad.new("Calle America", 90, 0.18, 650, 850))
      @casillas << Calle.new(17, 900, TituloPropiedad.new("Calle Humilladero", 95, 0.12, 700, 900))
      @casillas << Casilla.new(18, 0, TipoCasilla::SORPRESA)
      @casillas << Calle.new(19, 950, TituloPropiedad.new("Calle Princesa", 100, -0.19, 750, 950))
      @carcel = @casillas[5] 
    end
    
    def es_casilla_carcel(numero_casilla)
      return numero_casilla.eql?(@carcel.numero_casilla)
    end    

    #precondicion -> numeroCasilla > 0 && numeroCasilla < 20
    def obtener_casilla_numero(numero_casilla)
      @casillas.at(numero_casilla)
    end    

    def obtener_nueva_casilla(casilla, desplazamiento)
      numero_casilla = casilla.numero_casilla
      numero_casilla = (numero_casilla + desplazamiento) % @casillas.size
      return @casillas.at(numero_casilla)
    end
    
    def to_s
      "Tablero: \nCasillas:\n#{@casillas}\nCarcel= #{@carcel}"
    end
  end
end
