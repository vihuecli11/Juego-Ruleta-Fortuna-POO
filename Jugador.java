package es.poo.Holamundo;

import java.util.Scanner;

/**
 * La clase `Jugador` representa a un jugador que participa en el juego.
 * Al crear un jugador, se le solicita su alias, contraseña, edad y fecha de nacimiento.
 * Esta clase también gestiona el inicio de una partida y su interacción con el juego.
 * 
 * @author Vicente Huesca Climent
 */
public class Jugador {
    private String alias;
    private String contraseña;
    private int edad;
    private String cumpleaños;
    
    /**
     * Constructor de la clase `Jugador`. Solicita los datos al usuario y los almacena.
     * Pide el alias, contraseña, edad y fecha de nacimiento del jugador.
     */
    public Jugador() {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("¿Cuál es tu alias?");
        this.alias = sc.nextLine();
        
        System.out.println("Introduce una contraseña (mínimo 6 caracteres):");
        this.contraseña = sc.nextLine();
        while (contraseña.length() < 6) {
            System.out.println("La contraseña debe tener mínimo 6 caracteres");
            this.contraseña = sc.nextLine();
        } 
        
        System.out.println("¿Cuál es tu edad?");
        this.edad = sc.nextInt();
        sc.nextLine();
        
        do {
            System.out.println("Introduce tu fecha de nacimiento (Formato AAAA-MM-DD):");
            this.cumpleaños = sc.nextLine();
        } while (!esFechaValida(this.cumpleaños));
        
        System.out.println("Hola " + alias + ", tu edad es: " + edad + " y naciste el " + cumpleaños);
        
        iniciarPartida();
    }

    /**
     * Valida si la fecha de nacimiento introducida es válida (formato AAAA-MM-DD).
     * 
     * @param fecha La fecha de nacimiento en formato cadena de texto (AAAA-MM-DD).
     * @return `true` si la fecha es válida, `false` si no lo es.
     */
    private boolean esFechaValida(String fecha) {
        String[] partes = fecha.split("-");
        int anio = Integer.parseInt(partes[0]);
        int mes = Integer.parseInt(partes[1]);
        int dia = Integer.parseInt(partes[2]);
        
        return anio <= 2025 && mes <= 12 && dia <= 31;
    }

    /**
     * Inicia una nueva partida para el jugador.
     * Llama al constructor de `Partida` y ejecuta `iniciarJuego()`.
     */
    private void iniciarPartida() {
        Partida partida = new Partida(this);
        partida.iniciarJuego();
    }
    
    /**
     * Método principal para ejecutar el programa.
     * Crea un objeto `Jugador` que inicializa al jugador y comienza la partida.
     * 
     * @param args Argumentos de línea de comandos (no utilizado en este caso).
     */
    public static void main(String[] args) {
        Jugador jugador = new Jugador(); 
    }
    
    /**
     * Obtiene el alias del jugador.
     * 
     * @return El alias del jugador.
     */
    public String getAlias() {
        return alias;
    }

    /**
     * Obtiene la edad del jugador.
     * 
     * @return La edad del jugador.
     */
    public int getEdad() {
        return edad;
    }
}
