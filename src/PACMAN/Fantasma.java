package PACMAN;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.ImageIcon;

public class Fantasma implements Runnable {

    int x,y,xDirection, yDirection;
    Image agente;
    
    Laberinto L;
    
    boolean resting = false;

    Pacman objetivo;
    
    int pos;
    
    int k=1;
    
    ArrayList<Nodo> camino = new ArrayList<Nodo>();
    
    public Fantasma(Pacman P,int x, int y){
        objetivo = P;
        this.x = x;
        this.y = y;
        L = new Laberinto();
        
        agente = new ImageIcon("C:/Users/RGAP/Desktop/PACMAN/recursos/ghost.gif").getImage();
    }
    
    public void draw(Graphics g){
        g.drawImage(agente, x, y, null);
    }
    
    public void setXDirection(int dir){
        xDirection = dir;
    }
    
    public void setYDirection(int dir){
        yDirection = dir;
    }
    
    public void move(){
        if(k==1){
            pos=0;
            camino=L.a_star_search(y/50,x/50,objetivo.y/50,objetivo.x/50);
            k=0;
        }
        y=camino.get(pos).geti()*50;
        x=camino.get(pos).getj()*50;
        pos++;
//        x += xDirection;
//        y += yDirection;
        
        if(xDirection == 50)
            agente = new ImageIcon("C:/Users/RGAP/Desktop/PACMAN/recursos/ghost.gif").getImage();
        if(xDirection == -50)
            agente = new ImageIcon("C:/Users/RGAP/Desktop/PACMAN/recursos/ghost.gif").getImage();
        if(yDirection == 50)
            agente = new ImageIcon("C:/Users/RGAP/Desktop/PACMAN/recursos/ghost.gif").getImage();
        if(yDirection == -50)
            agente = new ImageIcon("C:/Users/RGAP/Desktop/PACMAN/recursos/ghost.gif").getImage();
    }
    
    @Override
    public void run(){
        try{
            while(true){
                move();
                Thread.sleep(1000);
            }
        }catch(Exception ex){
            System.err.println(ex.getMessage());
        }
    }
}