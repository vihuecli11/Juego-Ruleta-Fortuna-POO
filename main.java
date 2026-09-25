package es.poo.Holamundo;

public class main {

    public static void main(String[] args) {
      
        Jugador jugador = new Jugador();  

        Partida partida = new Partida(jugador);

        partida.jugarPartida();
    }
}
