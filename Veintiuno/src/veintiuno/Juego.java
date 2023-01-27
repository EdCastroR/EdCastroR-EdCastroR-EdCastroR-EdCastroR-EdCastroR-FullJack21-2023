
package veintiuno;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

/**
 *
 * @author CATHRINA
 */

public class Juego{ // atributos de la clase juego.

    private List<Carta> cartas;
    private List<Jugador> jugadores;
    private int jugadorEnTurno;
    
    //getter y setters
   
    public Juego() {

        jugadores = new ArrayList<>();

    }

    public List<Carta> getCartas() {
        return cartas;
    }

    public void iniciarJuego() { //Metodos de la clase para iniciar el juego

        String[] tiposCartas = {"DIAMANTE", "CORAZON", "PIKAS", "TREVOL"}; 

        cartas = new ArrayList<>();

        //establecemos los valores de las cartas
        for (int i = 0; i < tiposCartas.length; i++) {
            String nombreCarta = tiposCartas[i];

            for (int k = 1; k <= 13; k++) {

                if (k == 1) {
                   
                   cartas.add( new Carta("AS "+nombreCarta, 1));
                    
                    
                }
                
                 if (k == 11) {
                   
                   cartas.add( new Carta("J "+nombreCarta, 10));
                      
                }
                 if (k == 12) {
                   
                   cartas.add( new Carta("Q "+nombreCarta, 10));
                      
                }
                 if (k == 13) {
                   
                   cartas.add( new Carta("K "+nombreCarta, 10));
                      
                }
                
                 if (k > 1 && k < 11) {
                    cartas.add( new Carta(k+" "+nombreCarta, k));

                }
                
            }
        }


      

        jugadorEnTurno = 0;

    }

    public void setJugadores(List<Jugador> jugadores) { //llamamos a la lista de jugadores.
        this.jugadores = jugadores;
    }

    
    public Carta getProximaCarta() {
        if (cartas.size() > 0) {
            //valores para tomar una carta de manera aleatoria
            Carta carta = cartas.get(ThreadLocalRandom.current().nextInt(0, cartas.size()));
            cartas.remove(carta);
            return carta;
        }
        return null;
    }

    public Jugador getJugadorEnTurno() {
        return jugadores.get(jugadorEnTurno);
    }

    public Jugador getProximoJugador() {
        if (jugadorEnTurno < jugadores.size() - 1) {
            jugadorEnTurno++;
        } else {
            jugadorEnTurno = 0;
        }

        return jugadores.get(jugadorEnTurno);
    }

    public Jugador getGanador() {
        
        System.out.println("***************************");        
        System.out.println("***************************");
        
        List<Jugador> lista = jugadores.stream().filter(jugador -> jugador.getValorTotalDeCartas() <= 21).collect(Collectors.toList());

        if (!lista.isEmpty()) {
            Jugador ganador = lista.get(0);

            for (int i = 1; i < lista.size(); i++) {
                //se crea la instancia para buscar el que este mas cerca de 21.5 y en caso que tengan los mismos puntos ganara el que tenga menos cartas
                if (lista.get(i).getValorTotalDeCartas() > ganador.getValorTotalDeCartas()) {
                    ganador = lista.get(i);
                } else {
                    if (lista.get(i).getValorTotalDeCartas() == ganador.getValorTotalDeCartas() && lista.get(i).getCartas().size() < ganador.getCartas().size()) {
                        ganador = lista.get(i);
                    }
                }

            }
            return ganador;
        }

        return null;

    }

    public boolean hanTerminadoTodosLosJugadores() {
        return jugadores.stream().filter(jugador -> jugador.haTerminado()).count() == jugadores.size();
    }

    public boolean haTerminadoJugador() {
        return jugadores.get(jugadorEnTurno).haTerminado();
    }

    public List<Jugador> getJugadores() {
        return jugadores;
    }


}
 