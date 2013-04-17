package PACMAN;

import java.util.HashSet;
import java.util.Set;

public class Nodo {
    
    private int codigo;    
    
    public double valorf;
    
    private int i;
    private int j;
    
    private int contaristas;
    
    public Set<Arista> adyacentes = new HashSet<Arista>();
    
    public Nodo(int codigo,int i, int j) {
        this.codigo = codigo;
        this.i=i;
        this.j=j;
        contaristas=0;
    }
    
    public int getCodigo(){
        return codigo;
    }
    
    public void addAdyacente(double peso, Nodo nodoady) {
        adyacentes.add(new Arista(contaristas++,peso,this,nodoady));
    }
    
    public int geti(){
        return i;
    }
    
    public int getj(){
        return j;
    }

}
