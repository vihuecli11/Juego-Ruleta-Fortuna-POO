package es.poo.Holamundo;

import java.util.Random;

public class Tirada {
    // Valores posibles de la tirada de la ruleta
    private static final int[] VALORES = {10, 20, 30, 40, 0};

    private Random random = new Random();

    /**
     * Método para simular la tirada de la ruleta.
     * Selecciona un valor aleatorio entre los posibles valores: 10, 20, 30, 40 o 0.
     * 
     * @return El valor obtenido de la tirada, uno de los valores en el array `VALORES`.
     */
    public int tirar() {
        int indice = random.nextInt(VALORES.length); 
        return VALORES[indice];
    }
}
