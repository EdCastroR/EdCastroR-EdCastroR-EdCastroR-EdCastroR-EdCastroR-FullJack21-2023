package veintiuno;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author CATHRINA
 */

public class Jugador { //atributos de la clase jugador

    private String nombre;
    private List<Carta> cartas;
    private boolean haTerminado;
    

    public Jugador(String nombre) {
        cartas = new ArrayList<>();
        haTerminado = false;
        this.nombre = nombre;
    }

    //Metodos
    public List<Carta> getCartas() { //devuelve las cartas que se repartieron al jugador.
        return cartas;
    }

    public boolean haTerminado() { //devuelve TRUE si el valor total de la carta del jugador es superior a 21.5 o si el jugador ha terminado su turno  
        return getValorTotalDeCartas() > 21 || haTerminado;
    }

    public void setHaTerminado(boolean haTerminado) { //establecemos el metodo Haterminado del valor anterior como parametro.
        this.haTerminado = haTerminado;
    }

    public String getNombre() { // con este metodo se devuelve el nombre del jugador.
        return nombre;
    }

    public double getValorTotalDeCartas() { // devolvemos el valor total de las cartas que se le han repartido al jugador.
        return cartas.stream().mapToDouble(carta -> carta.getValor()).sum();
    }

    public void setCartas(List<Carta> cartas) { //se establece el metodo cartas como parametro.
        this.cartas = cartas;
    }
}
