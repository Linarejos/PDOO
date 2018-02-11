#Elena Cantero Molina
#encoding: utf-8
#trig.rb

module ModeloQytetet
  class Sorpresa
    attr_reader :texto, :valor, :tipo
    
    def initialize(texto, valor, tipo)
      @texto = texto
      @valor = valor
      @tipo = tipo
    end
    
    def to_s
      "Texto: #{@texto} \n Valor: #{@valor} \n Tipo: #{@tipo}"
    end
  end
end
