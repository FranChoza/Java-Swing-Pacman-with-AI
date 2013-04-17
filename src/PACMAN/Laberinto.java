package PACMAN;

import java.util.ArrayList;
import java.util.List;
import java.lang.Math;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.HashSet;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Laberinto {
    
    private ArrayList<Nodo> nodos;
    
    private ArrayList<Double> funcion_h;
    private ArrayList<Double> funcion_g;
    private ArrayList<Double> funcion_f;
    
    private List<Nodo> frontera;
    private List<Nodo> visitados;
    
    private ArrayList<Nodo> caminotemp;
   
    private ArrayList<Nodo> camino;
    
    DecimalFormatSymbols simbolos;
    DecimalFormat formateador;
    
    int inicio;
    
    int FILAS=13;
    int COLUMNAS=20;
    int PARED = -2;
    
    int contnodos=-1;
    
    int tempnodo;
    double pesoarista=1;
    
    int lab[][]= {
        {-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2},
        {-2,-1,-2,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-2},
        {-2,-1,-2,-1,-1,-1,-1,-1,-1,-1,-2,-2,-2,-2,-1,-2,-2,-2,-1,-2},
        {-2,-1,-2,-1,-1,-1,-1,-2,-1,-1,-1,-1,-1,-1,-1,-2,-1,-1,-1,-2},
        {-2,-1,-1,-1,-2,-1,-1,-2,-1,-1,-1,-2,-2,-2,-2,-2,-1,-2,-2,-2},
        {-2,-2,-2,-2,-2,-1,-1,-2,-1,-1,-1,-1,-1,-1,-2,-1,-1,-1,-1,-2},
        {-2,-1,-1,-1,-1,-1,-1,-2,-1,-1,-1,-2,-2,-1,-2,-1,-2,-2,-1,-2},
        {-2,-1,-1,-1,-1,-1,-1,-2,-1,-1,-1,-1,-2,-1,-1,-1,-2,-1,-1,-2},
        {-2,-1,-1,-1,-1,-1,-1,-2,-1,-1,-2,-1,-2,-1,-2,-1,-2,-1,-2,-2},
        {-2,-1,-1,-1,-1,-1,-1,-2,-1,-1,-1,-1,-2,-1,-2,-1,-2,-1,-1,-1},
        {-2,-1,-1,-1,-1,-1,-1,-1,-1,-1,-2,-2,-2,-2,-2,-1,-2,-2,-2,-2},
        {-2,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-2},
        {-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2}
    };
   
    public Laberinto(){
        nodos = new ArrayList<Nodo>();

        visitados = new ArrayList<Nodo>();
        frontera = new ArrayList<Nodo>();
        
        funcion_h = new ArrayList<Double>();
        funcion_g = new ArrayList<Double>();
        funcion_f = new ArrayList<Double>();
        
        caminotemp = new ArrayList<Nodo>();
        
        simbolos = new DecimalFormatSymbols();
        simbolos.setDecimalSeparator('.');
        formateador = new DecimalFormat("####.#",simbolos);
        
        cargar_grafo();
    }
    
    public void insertar_arista(double peso,int n1, int n2){
        nodos.get(n1).addAdyacente(peso, nodos.get(n2));
        //nodos.get(n2).addAdyacente(peso, nodos.get(n1));
    }
    
    public void insertar_nodo(int codigo,int i,int j){
        nodos.add(codigo, new Nodo(codigo,i,j));
    }

    public void cargar_grafo(){
        for(int i=0; i<FILAS; ++i){
            for(int j=0; j<COLUMNAS; ++j){
                if(lab[i][j]!=PARED){ 
                    if(lab[i][j]==-1){
                        insertar_nodo(++contnodos,i,j);
                        lab[i][j]=contnodos;
                    }
                    tempnodo = lab[i][j];
                    
                    //derecha
                    if(j+1<COLUMNAS && lab[i][j+1]!=PARED){
                        if(lab[i][j+1]==-1){
                            insertar_nodo(++contnodos,i,j);
                            lab[i][j+1]=contnodos;
                        }
                        insertar_arista(pesoarista,tempnodo,lab[i][j+1]);   
                    }
                    //abajo
                    if(i+1<FILAS && lab[i+1][j]!=PARED){
                        if(lab[i+1][j]==-1){
                            insertar_nodo(++contnodos,i,j);
                            lab[i+1][j]=contnodos;
                        }
                        insertar_arista(pesoarista,tempnodo,lab[i+1][j]);
                    }
                    //iaquierda
                    if(j-1>=0 && lab[i][j-1]!=PARED){
                        if(lab[i][j-1]==-1){
                            insertar_nodo(++contnodos,i,j);
                            lab[i][j-1]=contnodos;
                        }
                        insertar_arista(pesoarista,tempnodo,lab[i][j-1]);
                    }
                    //arriba
                    if(i-1>=0 && lab[i-1][j]!=PARED){
                        if(lab[i-1][j]==-1){
                            insertar_nodo(++contnodos,i,j);
                            lab[i-1][j]=contnodos;
                        }
                        insertar_arista(pesoarista,tempnodo,lab[i-1][j]);
                    }
                    funcion_h.add(-1.0);
                    funcion_g.add(-1.0);
                    funcion_f.add(-1.0);
                    //System.out.print(lab[i][j]+"\t");
                }
                //if(lab[i][j]==PARED){
                //    System.out.print("*\t");
                //}
            }
            //System.out.print("\n");
        }
    }
    
    public void mostrar_lab(){
        for(int i=0; i<FILAS; ++i){
            for(int j=0; j<COLUMNAS; ++j){
                if(lab[i][j]!=PARED)
                    System.out.print(lab[i][j]+"\t");
                else
                    System.out.print("*\t");
            }
            System.out.print("\n");
        }
    }
    
    
    
    public double estimar_costo(int x1, int y1, int x2,int y2){
        return Math.sqrt(Math.pow(x2-x1,2) + Math.pow(y2-y1,2));
    }
    
    
    
    //Algoritmo A* para la ruta de ghost a pacman
    public ArrayList<Nodo> a_star_search(int ifant,int jfant, int ipac, int jpac){
        System.out.println("Calculando camino a PACMAN");
        
        clear();
        
        int nodo_f=lab[ifant][jfant];
        int nodo_p=lab[ipac][jpac];

        int nodoelegido=0,nodoi=0;
        
        Nodo nodoady;
        
        double min,valorf;
        
        frontera.add(nodos.get(nodo_f));
        
        funcion_g.set(nodo_f, 0.0);
        funcion_h.set(nodo_f, estimar_costo(ifant,jfant, ipac,jpac));
        funcion_f.set(nodo_f, funcion_g.get(nodo_f)+funcion_h.get(nodo_f));
        nodos.get(nodo_f).valorf=funcion_f.get(nodo_f);
        
        while(!frontera.isEmpty()){
            printValores();

            min=Double.MAX_VALUE;
            for(int i=0;i<frontera.size();++i){
                valorf=funcion_f.get(frontera.get(i).getCodigo());
                if(valorf < min){
                    min = valorf;
                    nodoi=i;
                }
            }
            nodoelegido=frontera.get(nodoi).getCodigo();
            
            frontera.remove(nodos.get(nodoelegido));
            
            if(nodoelegido!=nodo_f)
                caminotemp.add(nodos.get(nodoelegido));           
            
            
            if(nodoelegido == nodo_p){ //Camipo minimo con A* encontrado
//                camino.add(caminotemp.get(0));
//                for(int i=1;i<caminotemp.size();++i){
//                    if(caminotemp.get(i-1).adyacentes.contains(caminotemp.get(i)))
//                        camino.add(caminotemp.get(i));
//                }
                return caminotemp;
            }
                       
            visitados.add(nodos.get(nodoelegido));
            System.out.print("\n"+nodoelegido+" -> ");
            
            for(Arista adyacente : nodos.get(nodoelegido).adyacentes) {
                nodoady = adyacente.getDestino();
                System.out.print(nodoady.getCodigo()+"  ");
                
                if(visitados.contains(nodoady))
                    continue;
                
                if(!frontera.contains(nodoady))
                    frontera.add(nodoady);
                if(frontera.contains(nodoady)){
                    funcion_g.set(nodoady.getCodigo(),funcion_g.get(nodoelegido)+adyacente.getPeso());
                    funcion_h.set(nodoady.getCodigo(), 
                                  estimar_costo(nodoady.geti(),nodoady.getj(), 
                                                nodos.get(nodo_p).geti(),nodos.get(nodo_p).getj()));
                    funcion_f.set(nodoady.getCodigo(),funcion_g.get(nodoady.getCodigo()) + funcion_h.get(nodoady.getCodigo()));
                    nodoady.valorf=funcion_f.get(nodoady.getCodigo());
                }
            }
            //camino.add();
        }
        
        return camino;
    }
    
    public void printValores(){
        System.out.print("\n\n");
        for(int i=0; i<FILAS; ++i){
            for(int j=0; j<COLUMNAS; ++j){
                if(lab[i][j]!=PARED){
                    System.out.print(funcion_g.get(lab[i][j]));
                    System.out.print(","); 
                    System.out.print(formateador.format(funcion_h.get(lab[i][j])));
                    System.out.print(","); 
                    System.out.print(formateador.format(funcion_f.get(lab[i][j])));
                    System.out.print("\t"); 
                }
                else
                    System.out.print("*\t\t");
            }
            System.out.print("\n");
        }
    }
    
    public void clear(){
        for(int i=0;i<nodos.size();++i){
            funcion_h.set(i, -1.0);
            funcion_g.set(i, -1.0);
            funcion_f.set(i, -1.0);
        }
            
    }
}


        
//        Set<Square> adyacentes = elements[0][0].getAdyacentes();
//        for(Square adjacency : adyacentes){
//            adjacency.setParent(elements[0][0]);
//            if(adjacency.isStart() == false)
//                opened.add(adjacency);
//        }
//        
//        while(opened.size() > 0){
//            Square best = findBestPassThrough();
//            opened.remove(best);
//            closed.add(best);
//            if(best.isEnd()) {
//                System.out.println("Found Goal");
//                populateBestList(goal);
//                //draw();
//                return;
//            }else{
//                Set<Square> neighbors = best.getAdjacencies();
//                for(Square neighbor : neighbors) {
//                    if (opened.contains(neighbor)) {
//                        Square tmpSquare = new Square(neighbor.getX(),neighbor.getY(), this);
//                        tmpSquare.setParent(best);
//                        if (tmpSquare.getPassThrough(goal) >= neighbor.getPassThrough(goal))
//                            continue;
//                    }
//                    if (closed.contains(neighbor)) {
//                        Square tmpSquare = new Square(neighbor.getX(),neighbor.getY(), this);
//                        tmpSquare.setParent(best);
//                        if(tmpSquare.getPassThrough(goal) >= neighbor.getPassThrough(goal))
//                            continue;
//                    }
//                    neighbor.setParent(best);
//                    opened.remove(neighbor);
//                    closed.remove(neighbor);
//                    opened.add(0, neighbor);
//                }
//            }
//        }
//        System.out.println("No Path to goal");
    
    

