package es.poo.Holamundo;

import java.util.Scanner;
	/**
	 * La clase `JuegoRuletaFortuna` gestiona el flujo del juego.
 	* Permite al jugador elegir el número de partidas, el nivel de dificultad
 	* y coordina la interacción entre la partida y la ruleta.
 	* 
 	* @author Vicente Huesca Climent
 	*/
public class JuegoRuletaFortuna {
    private Jugador jugador;
    private Partida partida;
    private String fraseAdivinar;
    private String fraseEncriptada;
    private Scanner sc;
    private Tirada ruleta;

    /**
     * Constructor de la clase `JuegoRuletaFortuna`.
     * Inicializa el jugador, la partida, la frase a adivinar y la frase encriptada.
     * También inicializa el objeto `Tirada` para la ruleta.
     * 
     * @param jugador El jugador que participa en el juego.
     * @param partida La partida que se va a jugar.
     */
    public JuegoRuletaFortuna(Jugador jugador, Partida partida) {
        this.jugador = jugador;
        this.partida = partida;
        this.fraseAdivinar = "La casa blanca";
        this.fraseEncriptada = fraseAdivinar.replaceAll("[a-zA-Z]", "_");
        this.sc = new Scanner(System.in);
        this.ruleta = new Tirada();
    }

    /**
     * Realiza una tirada de la ruleta y devuelve el resultado.
     * 
     * @return Un valor aleatorio correspondiente a la tirada (10, 20, 30, 40 o 0).
     */
    public int tirarRuleta() {
        return ruleta.tirar();
    }

    /**
     * Muestra el estado actual del juego, incluyendo la puntuación, los intentos restantes
     * y la frase encriptada.
     */
    public void mostrarEstado() {
        System.out.println("Puntuación: " + partida.getPuntuacion());
        System.out.println("Intentos restantes: " + partida.getIntentosRestantes());
        System.out.println("Frase actual: " + fraseEncriptada);
    }

    /**
     * Permite al jugador comprar una vocal si tiene suficientes puntos.
     * La vocal se inserta en las posiciones correspondientes de la frase encriptada.
     */
    public void comprarVocal() {
        if (partida.getPuntuacion() >= 30) {
            System.out.println("Introduce la vocal que deseas comprar:");
            char vocal = sc.nextLine().charAt(0);
            partida.setPuntuacion(partida.getPuntuacion() - 30);
            System.out.println("Vocal comprada: " + vocal);

            // Reemplaza los guiones bajos en la frase encriptada con la vocal
            for (int i = 0; i < fraseAdivinar.length(); i++) {
                if (fraseAdivinar.charAt(i) == vocal) {
                    fraseEncriptada = fraseEncriptada.substring(0, i) + vocal + fraseEncriptada.substring(i + 1);
                }
            }
        } else {
            System.out.println("No tienes suficientes puntos para comprar una vocal.");
        }
    }

    /**
     * Verifica si el jugador ha ganado, es decir, si la frase encriptada coincide con la frase original.
     * 
     * @return `true` si el jugador ha adivinado toda la frase, `false` si no.
     */
    public boolean esGanador() {
        return fraseEncriptada.equals(fraseAdivinar);
    }

    /**
     * Muestra el resultado final del juego.
     * Informa al jugador si ha ganado o si se han acabado los intentos.
     */
    public void mostrarResultado() {
        if (esGanador()) {
            System.out.println("¡Felicidades, has ganado!");
            System.out.println("Tu puntuación final es: " + partida.getPuntuacion());
        } else {
            System.out.println("Se acabaron los intentos. La frase correcta era: " + fraseAdivinar);
            System.out.println("Tu puntuación final es: " + partida.getPuntuacion());
        }
    }
}
