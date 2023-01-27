package veintiuno;

/**
 *
 * @author CATHRINA
 */

public class Carta {
    private String nombre;
    private double valor;

    public Carta(String nombre, double valor) { 
        //se realiza la variable de encapsulamiento, la variable valor es privada, que solo puede ser accedida desde dentro de la clase.
               this.nombre = nombre;
               this.valor = valor;
    }

    public String getNombre() {// proporcionamos el metodo getNombre con el fin de acceder al valor de esta variable.
        return nombre;
    }
        
    public double getValor() { // proporcionamos el metodo getValor con el fin de acceder al valor de esta variable.
        return valor;
    }
    
}
