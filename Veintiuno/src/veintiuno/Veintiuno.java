package veintiuno;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 *
 * @author CATHRINA
 */
public class Veintiuno {

    public static void main(String[] args) { //Establecemos los Procesos para el funcionamiento del juego de mesa.

        Scanner scan = new Scanner(System.in); 
        int cantJugadores = 0;
        double dinero = 0;
        Juego juego = new Juego();
        Jugador ganador;
        System.out.println("             Bienvenidos a FullJack 21             ");   //crreamos la visualizacion del inicio del juego de mesa
        System.out.println("==================================================="); 
        System.out.println("Apuesta y Gana.... ");  
        System.out.println("Ganaras si tus puntos y tus cartas se aproximan o suman 21....Perderas si la suma de tus cartas son mayor que 21. ");  
        System.out.println();
        //Iniciamos la solicitud de parámetros de inicio (Cantidad de jugadores y saldo inicial del jugador)
        while (true) {
            try { //aplicamos las esepciones para los datos correspondientes al Nombre de manera correcta.
                System.out.println("Numero de jugadores:");
                cantJugadores = scan.nextInt();
                if (cantJugadores < 2) {
                    System.out.println("Ingrese un número válido.");
                    continue;
                }
                break;
            } catch (Exception e) {
                scan.next();
                System.out.println("Ingrese un número válido.");
            }

        }

        do {
            try {//aplicamos las esepciones para los datos correspondientes a las apuestas de manera correcta.
                System.out.print("Cantidad de dinero apostado: ");
                dinero = scan.nextDouble();
                if (dinero < 0) {
                    System.out.println("Ingrese una cantidad válida.");
                    continue;
                }
                break;
            } catch (Exception e) {
                scan.next();
                System.out.println("Ingrese una cantidad válida.");
            }
        } while (true); //aplicamos un bucle para ejecutar las condiciones continuas ya que su condicion es true

        ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
        for (int i = 0; i < cantJugadores; i++) {
            System.out.print("Nombre del jugador " + (i + 1) + ": "); // Escritura y Lectura del jugador.
            String nombre = scan.next();
            jugadores.add(new Jugador(nombre));
        }
        System.out.println("\nEmpieza el juego");

        juego.setJugadores(jugadores); //asignamos la variable
        juego.iniciarJuego();

        boolean finJuego = false; //

        while (finJuego != true) { //aplicamos un bucle para ejecutar las condiciones de las acciones.

            if (!juego.getJugadorEnTurno().haTerminado()) { // establecemos las acciones a realizar por el jugador
                System.out.println("Jugador en turno: " + juego.getJugadorEnTurno().getNombre());
                System.out.println("Que desea hacer: ");
                System.out.println("[1] digita la tecla 1 y presiona enter para pedir carta ");
                System.out.println("[2] digita la tecla 2 y presiona enter para Plantarse y Terminar partida. ");
                String opcionSeleccionado = scan.next();

                if (opcionSeleccionado.equals("1")) { //establecemos los resultados de las acciones por el jugador
                    Carta carta = juego.getProximaCarta();
                    System.out.println("carta seleccionada " + carta.getNombre());
                    juego.getJugadorEnTurno().getCartas().add(carta);
                    System.out.println("Tu suma de cartas acumulada es: " + juego.getJugadorEnTurno().getValorTotalDeCartas());
                    if(juego.getJugadorEnTurno().getValorTotalDeCartas()>21){
                        System.out.println("Te has pasado de 21. Has perdido");
                        juego.getProximoJugador();
                    }
                }

                ArrayList<Juego> arrayList = new ArrayList<>();  //establecemos un arraylist para que el jugador de por terminada su partida.
                if (opcionSeleccionado.equals("2")) {
                    juego.getJugadorEnTurno().setHaTerminado(true);
                    System.out.println(juego.getJugadorEnTurno().getNombre() + " se planto.");
                    juego.getProximoJugador();
                }
            }else{
                if(juego.hanTerminadoTodosLosJugadores()){
                    finJuego = true;
                }
            }
        }
        ganador = juego.getGanador();
        if(ganador == null){ //establecemos los parametros cuando ningun jugador gana.
            System.out.println("Ninguno Participante gano, tome su dinero");
        }else{
            System.out.println("El ganador es " + ganador.getNombre() + " y su ganancia fue de " + dinero*juego.getJugadores().size());
        }
        
        
    }
}
