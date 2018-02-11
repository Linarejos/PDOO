#Elena Cantero Molina
#encoding: utf-8
#trig.rb

module ModeloQytetet
  class Casilla
    attr_accessor :numero_casas, :numero_hoteles, :titulo, :numero_casilla
    attr_reader :tipo, :coste
  
    def initialize(numero_casilla, coste, tipo) 
      @numero_casilla = numero_casilla
      @coste = coste
      @tipo = tipo
    end
    
    def soy_edificable
      return @tipo.eql?(TipoCasilla::CALLE)
    end    

    def asignar_titulo_propiedad      
    end
    
    def to_s
        "Casilla #{@numero_casilla}\nTipo de Casilla -> #{@tipo}\nCoste -> #{@coste}"
    end
  end
end
