#Elena Cantero Molina
#encoding: utf-8
#trig.rb

module InterfazTextualQytetet
  class VistaTextualQytetet
    def seleccion_menu(menu)
      # Hasta que se hace una seleccion valida
      begin
        valido = true
        menu.each do |m| # se muestran las opciones del menu
          mostrar("#{m[0]}  :  #{m[1]}")
        end
        mostrar("\nElige un numero de opcion: ")
        captura = gets.chomp
        # metodo para comprobar la eleccion correcta
        valido = comprobar_opcion(captura, 0, menu.length - 1)
        unless valido
          mostrar("\n\nERROR!!! \n\n Seleccion erronea. Intentalo de nuevo.\n\n")
        end
      end until valido
      Integer(captura)
    end

    # metodo para comprobar si la opcion introducida
    # es correcta, usado por seleccion_menu
    def comprobar_opcion(captura, min, max)
      valido = true
      begin
        opcion = Integer(captura)
        if opcion < min || opcion > max # No es un entero entre los validos
          valido = false
          mostrar('el numero debe estar entre min y max')
        end
      rescue StandardError # No se ha introducido un entero
        valido = false
        mostrar('debes introducir un numero')
      end
      valido
    end
    
    def menu_gestion_inmobiliaria
      mostrar("Elige la gestion inmobiliaria que deseas hacer\n\n")
      menu_gi = [[0, 'Siguiente Jugador'], [1, 'Edificar casa'],
        [2, 'Edificar Hotel'], [3, 'Vender propiedad'],
        [4, 'Hipotecar Propiedad'], [5, 'Cancelar Hipoteca'],
        [6, 'Mostrar Propiedades']]
      salida = seleccion_menu(menu_gi)
      mostrar('has elegido')
      mostrar(salida)
      return salida
    end

    def elegir_comprar_propiedad
      mostrar("\nDesea comprar la propiedad?")
      menu_sc = [[1, 'Si'], [0,'No']]
      salida = seleccion_menu(menu_sc)
      mostrar('has elegido')
      mostrar(salida)
      if salida == 1
        return true
      else
        return false
      end
    end

    def menu_salir_carcel
      mostrar('Elige el metodo para salir de la carcel')
      menu_sc = [[0, 'Tirando el dado'], [1, 'Pagando mi libertad']]
      salida = seleccion_menu(menu_sc)
      mostrar('has elegido')
      mostrar(salida)
      return salida
    end

    # numero y nombre de propiedades
    def menu_elegir_propiedad(lista_propiedades)
      menu_ep = Array.new
      numero_opcion = 0
      lista_propiedades.each do |prop|
        # opcion de menu, numero y nombre de propiedad
        menu_ep << [numero_opcion, prop]
        numero_opcion += 1
      end
      # Metodo para controlar la eleccion correcta en el main
      seleccion_menu(menu_ep)
    end

    def obtener_nombre_jugadores
      nombres = Array.new
      valido = true
      begin
        mostrar('Escribe el numero de jugadores: (de 2 a 4):')
        lectura = gets.chomp # lectura de teclado
        # metodo para comprobar la eleccion correcta
        valido = comprobar_opcion(lectura, 2, 4)
      end until valido

      # pide nombre de jugadores y los mete en un array
      (1..Integer(lectura)).each do |i|
        mostrar('Jugador:  ' + i.to_s)
        nombre = gets.chomp
        nombres << nombre
      end
      nombres
    end
    
    # metodo para mostrar el string que recibe como argumento
    def mostrar(texto)
      puts texto
    end
    
    private :comprobar_opcion, :seleccion_menu
  end
end
