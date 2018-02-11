#Elena Cantero Molina
#encoding: utf-8
#trig.rb

require "singleton"

module ModeloQytetet
  class Dado
    include Singleton
    
    def initialize
      tirar
    end
    
    def tirar
      return rand(6)+1
    end
    
    def to_s
      "Dado"
    end
  end
end
