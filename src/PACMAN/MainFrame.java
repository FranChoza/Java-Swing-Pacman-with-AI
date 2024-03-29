package PACMAN;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MainFrame extends JFrame {
    
    static PacmanPanel P = new PacmanPanel();

    public MainFrame(){
        setTitle("PACMAN");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setBackground(Color.BLACK);
        
        JMenuBar menuBar = new JMenuBar();
        JMenu menu;
        JMenuItem menuItem;

        menu = new JMenu("Inicio");
        menuBar.add(menu);

        menuItem = new JMenuItem("Iniciar Juego");
        menu.add(menuItem);
        
        menuItem = new JMenuItem("Posicion de Fantasma");
        menu.add(menuItem);
        
        menu = new JMenu("Ayuda");
        menuBar.add(menu);
        
        menuItem = new JMenuItem("Acerca de");
        menu.add(menuItem);

        setJMenuBar(menuBar);
        
        //KEY LISTENER
        addKeyListener(new AL());
    }
    
    public class AL extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e){
            P.keyPressed(e);
        }
        @Override
        public void keyReleased(KeyEvent e){
            P.keyReleased(e);
        }
    }
    
    public static void main(String[] args) {
        //Frame
        MainFrame frame = new MainFrame();
        frame.setVisible(true);
        frame.add(P);
    } 
}
