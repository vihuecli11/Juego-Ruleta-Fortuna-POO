package es.poo.Holamundo;

import java.util.Scanner;
import java.util.Random;

	/**
	 * La clase `Partida` gestiona todos los aspectos de la partida del jugador,
	 * incluyendo la elección del nivel de dificultad, la frase a adivinar,
	 * los intentos restantes, y la puntuación.
	 * 
	 * @author Vicente Huesca Climent
	 */
public class Partida {
    private Jugador jugador;
    private int numeroPartidas;
    private int puntuacion;
    private int intentosRestantes;
    private String fraseAdivinar;
    private String fraseEncriptada;
    private String nivel;
    private String[] frasesFaciles = {"la casa blanca", "el sol brilla", "el gato negro"};
    private String[] frasesMedias = {"el arbol se balancea", "el viento sopla fuerte", "las nubes cubren el cielo"};
    private String[] frasesDificiles = {"la programacion es la clave del futuro", "el algoritmo optimiza el rendimiento", "la computacion cuantica revolucionara el mundo"};

    /**
     * Constructor de la clase `Partida`.
     * Inicializa la partida con los datos del jugador y asigna valores predeterminados
     * para el número de partidas, los intentos restantes, el nivel y la frase a adivinar.
     * 
     * @param jugador El jugador que participa en la partida.
     */
    public Partida(Jugador jugador) {
        this.jugador = jugador;
        this.puntuacion = 0;
        this.numeroPartidas = 3;
        this.intentosRestantes = 10;
        this.nivel = "Novato";
        this.fraseAdivinar = "La casa blanca";
        this.fraseEncriptada = fraseAdivinar.replaceAll("[a-zA-Z]", "_");
    }

    /**
     * Verifica si el jugador tiene la edad suficiente para participar.
     * 
     * @return `true` si el jugador tiene 10 años o más, `false` en caso contrario.
     */
    public boolean comprobarEdad() {
        return jugador.getEdad() >= 10;
    }

    /**
     * Solicita al jugador que elija el número de partidas a jugar.
     */
    public void elegirNumeroPartidas() {
        Scanner sc = new Scanner(System.in);
        System.out.println("¿Cuántas partidas quieres jugar?");
        this.numeroPartidas = sc.nextInt();
    }

    /**
     * Solicita al jugador que elija el nivel de dificultad.
     * Dependiendo de la elección, se asigna la cantidad de intentos restantes
     * y se elige una frase aleatoria para adivinar.
     */
    public void elegirNivel() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Elige el nivel de dificultad:");
        System.out.println("1. Novato (10 intentos)");
        System.out.println("2. Medio (8 intentos)");
        System.out.println("3. Experto (5 intentos)");
        String entrada = sc.nextLine();

        int eleccion = entrada.isEmpty() ? 1 : Integer.parseInt(entrada);
        
        switch(eleccion) {
            case 1:
                nivel = "Novato";
                intentosRestantes = 10;
                fraseAdivinar = frasesFaciles[new Random().nextInt(frasesFaciles.length)];
                break;
            case 2:
                nivel = "Medio";
                intentosRestantes = 8;
                fraseAdivinar = frasesMedias[new Random().nextInt(frasesMedias.length)];
                break;
            case 3:
                nivel = "Experto";
                intentosRestantes = 5;
                fraseAdivinar = frasesDificiles[new Random().nextInt(frasesDificiles.length)];
                break;
            default:
                System.out.println("Opción inválida, se seleccionará el nivel Novato por defecto.");
                nivel = "Novato";
                intentosRestantes = 10;
                fraseAdivinar = frasesFaciles[new Random().nextInt(frasesFaciles.length)];
                break;
        }
        fraseEncriptada = fraseAdivinar.replaceAll("[a-zA-Z]", "_");
        System.out.println("Nivel elegido: " + nivel);
        System.out.println("Frase a adivinar: " + fraseEncriptada);
    }

    /**
     * Inicia el juego, permite al jugador elegir el número de partidas y el nivel de dificultad.
     * Luego, comienza el ciclo de las partidas y muestra el resultado final.
     */
    public void iniciarJuego() {
        if (comprobarEdad()) {
            elegirNumeroPartidas();
            elegirNivel();
            System.out.println("¡Bienvenido al juego, " + jugador.getAlias() + "!");
            System.out.println("Número de partidas a jugar: " + numeroPartidas);
            System.out.println("Comenzando el juego...");

            for (int i = 0; i < numeroPartidas; i++) {
                System.out.println("Iniciando la partida " + (i + 1) + "...");
                jugarPartida();
                System.out.println("Fin de la partida " + (i + 1) + ". Puntuación acumulada: " + puntuacion);
                fraseEncriptada = fraseAdivinar.replaceAll("[a-zA-Z]", "_");
                intentosRestantes = 10;
            }

            System.out.println("Juego finalizado. Tu puntuación total es: " + puntuacion);
        } else {
            System.out.println("Lo siento, no puedes jugar. Debes tener al menos 10 años.");
        }
    }

    /**
     * Cuenta el número total de aciertos en la frase encriptada.
     * Recorre la frase y cuenta las posiciones donde la letra no es un guion bajo.
     * 
     * @return El número total de aciertos en la frase.
     */
    public int numeroAciertos() {
        int aciertos = 0;
        for (int i = 0; i < fraseAdivinar.length(); i++) {
            if (fraseEncriptada.charAt(i) != '_') {
                aciertos++;
            }
        }
        return aciertos;
    }

    /**
     * Realiza el ciclo de una partida. El jugador realiza intentos para adivinar la frase.
     * En cada intento, se tira la ruleta, se elige una consonante, se comprueba si es correcta,
     * y se actualiza la frase encriptada.
     */
    public void jugarPartida() {
        Scanner sc = new Scanner(System.in);
        intentosRestantes = intentosRestantes > 0 ? intentosRestantes : 10;

        while (intentosRestantes > 0 && !fraseEncriptada.equals(fraseAdivinar)) {
            System.out.println("\nFrase actual: " + fraseEncriptada);
            System.out.println("Intentos restantes: " + intentosRestantes);
            System.out.println("Puntuación: " + puntuacion);

            int tirada = new Tirada().tirar();
            if (tirada == 0) {
                System.out.println("¡Bancarrota! Has perdido toda tu puntuación.");
                puntuacion = 0;
            } else {
                System.out.println("Puntaje obtenido en la tirada: " + tirada);
            }

            System.out.println("Introduce una consonante:");
            String entrada = sc.nextLine().trim();
            if (entrada.isEmpty()) {
                System.out.println("No has introducido ninguna letra, intenta de nuevo.");
                continue;
            }
            char consonante = Character.toLowerCase(entrada.charAt(0));

            int aciertosEstaJugada = descubrirConsonante(consonante);
            if (aciertosEstaJugada > 0) {
                puntuacion += tirada * aciertosEstaJugada;
                System.out.println("¡La consonante está en la frase! Has acertado " + aciertosEstaJugada + " letra(s).");
            } else {
                System.out.println("La consonante no está en la frase.");
                intentosRestantes--;
            }

            if (!fraseEncriptada.equals(fraseAdivinar)) {
                System.out.println("¿Deseas comprar una vocal por 30 puntos? (Sí/No)");
                String respuesta = sc.nextLine().trim();
                if (respuesta.equalsIgnoreCase("si") && puntuacion >= 30) {
                    System.out.println("Introduce la vocal que deseas comprar:");
                    String vocalEntrada = sc.nextLine().trim();
                    if (!vocalEntrada.isEmpty()) {
                        char vocal = Character.toLowerCase(vocalEntrada.charAt(0));
                        if (esVocal(vocal)) {
                            puntuacion -= 30;
                            int aciertosVocal = descubrirConsonante(vocal);
                            if (aciertosVocal > 0) {
                                System.out.println("Has revelado " + aciertosVocal + " vocal(es).");
                            } else {
                                System.out.println("Esa vocal no está en la frase.");
                            }
                        } else {
                            System.out.println("Eso no es una vocal.");
                        }
                    }
                }
            }

            if (fraseEncriptada.equals(fraseAdivinar)) {
                System.out.println("\n🎉 ¡Has adivinado la frase! 🎉");
                System.out.println("Frase completa: " + fraseAdivinar);
                break;
            }
        }

        if (intentosRestantes == 0 && !fraseEncriptada.equals(fraseAdivinar)) {
            System.out.println("\n❌ Se acabaron los intentos.");
            System.out.println("La frase correcta era: " + fraseAdivinar);
        }
    }

    /**
     * Descubre las consonantes que coinciden en la frase y actualiza la frase encriptada.
     * 
     * @param consonante La consonante que el jugador ha intentado adivinar.
     * @return El número de coincidencias de la consonante en la frase.
     */
    public int descubrirConsonante(char consonante) {
        consonante = Character.toLowerCase(consonante);
        int coincidencias = 0;

        for (int i = 0; i < fraseAdivinar.length(); i++) {
            char original = Character.toLowerCase(fraseAdivinar.charAt(i));
            if (original == consonante && fraseEncriptada.charAt(i) == '_') {
                fraseEncriptada = fraseEncriptada.substring(0, i) 
                        + fraseAdivinar.charAt(i) 
                        + fraseEncriptada.substring(i + 1);
                coincidencias++;
            }
        }
        return coincidencias;
    }

    /**
     * Verifica si el carácter proporcionado es una vocal.
     * 
     * @param c El carácter a verificar.
     * @return `true` si el carácter es una vocal, `false` si no lo es.
     */
    public boolean esVocal(char c) {
        return "aeiouáéíóú".indexOf(Character.toLowerCase(c)) != -1;
    }
    
    /**
     * Obtiene la puntuación actual de la partida.
     * 
     * @return La puntuación actual de la partida.
     */
    public int getPuntuacion() {
        return puntuacion;
    }

    /**
     * Establece la puntuación de la partida.
     * 
     * @param puntuacion La nueva puntuación que se asignará a la partida.
     */
    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    /**
     * Obtiene el número de intentos restantes en la partida.
     * 
     * @return El número de intentos restantes.
     */
    public int getIntentosRestantes() {
        return intentosRestantes;
    }

    /**
     * Establece el número de intentos restantes en la partida.
     * 
     * @param intentosRestantes El nuevo número de intentos restantes.
     */
    public void setIntentosRestantes(int intentosRestantes) {
        this.intentosRestantes = intentosRestantes;
    }
}
