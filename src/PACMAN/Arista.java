package PACMAN;
 
public class Arista {
    private int codigo;
    private double peso;
    private Nodo origen, destino;
    
    /**
     * Método constructor de la clase Arista. Permite crear un objeto arista
     * @param codigo = código de la arista
     * @param tipo = tipo de arista
     * @param origen = nodo origen
     * @param destino = nodo destino
     */
    public Arista(int codigo, double peso, Nodo origen, Nodo destino) {
        this.codigo = codigo;
        this.origen = origen;
        this.destino = destino;
        this.peso = peso;
    }
    
    /**
     * Método para obtener el codigo de la arista
     * @return  int con el codigo de la arista  
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * Método para obtener el nodo origen de la arista
     * @return  Nodo origen de la arista
     */
    public Nodo getOrigen() {
        return origen;
    }

    /**
     * Método para obtener el nodo destino de la arista
     * @return  Nodo destino de la arista  
     */
    public Nodo getDestino() {
        return destino;
    }
    
    public double getPeso(){
        return peso;
    }
   
}

