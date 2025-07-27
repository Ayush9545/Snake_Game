package Java_Projects.Snake_Game;

import javax.swing.*;
public class Snake extends JFrame{

    Snake(){
        super("Snake Game");
        add(new Board());
        pack();
        setSize(300, 300);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    public static void main(String[] args) {
        new Snake();
    }
    
}
