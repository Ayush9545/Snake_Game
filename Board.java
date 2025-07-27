package Java_Projects.Snake_Game;

import java.awt.*;
// import java.util.Timer;
import javax.swing.*;
import javax.swing.Timer;
import java.awt.event.*;

public class Board extends JPanel implements ActionListener {

    private Image mouse;
    private Image body;
    private Image head;
    private Image bonus;

    private final int all_dot = 900;
    private final int dot_size = 10;
    private final int random_position = 10;

    private int mouse_x;
    private int mouse_y;
    private int bonus_x;
    private int bonus_y;

    private final int x[] = new int[all_dot];
    private final int y[] = new int[all_dot];

    private boolean left_d = false;
    private boolean right_d = true;
    private boolean up_d = false;
    private boolean down_d = false;

    private int dots;
    private Timer timer;

    Board() {
        addKeyListener(new TAdapter());

        setBackground(Color.white);
        setFocusable(true);

        image();
        initGame();
    }

    public void image() {
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("Java_Projects/Snake_Game/images/mouse12.png"));
        mouse = i1.getImage();

        ImageIcon i2 = new ImageIcon(ClassLoader.getSystemResource("Java_Projects/Snake_Game/images/body112.png"));
        body = i2.getImage();

        ImageIcon i3 = new ImageIcon(ClassLoader.getSystemResource("Java_Projects/Snake_Game/images/snake12.png"));
        head = i3.getImage();

        ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("Java_Projects/Snake_Game/images/bonus_cube15.png"));
        bonus = i4.getImage();
    }

    public void initGame() {
        dots = 3;

        for (int i = 0; i < dots; i++) {
            x[i] = 50;
            y[i] = 50 - i * dot_size;
        }
        locate_mouse();
        timer = new Timer(140, this);
        timer.start();

        bonus();
        timer = new Timer(300, this);
        timer.start();
    }

    public void locate_mouse() {
        int r = (int) (Math.random() * random_position);
        mouse_x = r * dot_size;

        r = (int) (Math.random() * random_position);
        mouse_y = r * dot_size;
    }

    public void bonus() {
        int r = (int) (Math.random() * random_position);
        bonus_x = r * dot_size;

        r = (int) (Math.random() * random_position);
        bonus_y = r * dot_size;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        g.drawImage(mouse, mouse_x, mouse_y, this);
        g.drawImage(bonus, bonus_x, bonus_y, this);

        for (int i = 0; i < dots; i++) {
            if (i == 0) {
                g.drawImage(head, x[i], y[i], this);
            } else {
                g.drawImage(body, x[i], y[i], this);
            }
        }
        Toolkit.getDefaultToolkit().sync();
    }

    public void move() {
        for (int i = dots; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }

        if (left_d) {
            x[0] = x[0] - dot_size;
        }
        if (right_d) {
            x[0] = x[0] + dot_size;
        }
        if (up_d) {
            y[0] = y[0] - dot_size;
        }
        if (down_d) {
            y[0] = y[0] + dot_size;
        } 
    }

    public void eat(){
        if (x[0]== mouse_x && y[0]==mouse_y) {
            dots++;
            locate_mouse();
        }
    }

    public void actionPerformed(ActionEvent ae) {
        eat();
        move();
        repaint();
    }

    public class TAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();

            if (key == KeyEvent.VK_LEFT && (!right_d)) {
                left_d = true;
                up_d = false;
                down_d = false;
            }
            if (key == KeyEvent.VK_RIGHT && (!left_d)) {
                right_d = true;
                up_d = false;
                down_d = false;
            }
            if (key == KeyEvent.VK_UP && (!down_d)) {
                left_d = false;
                up_d = true;
                right_d = false;
            }
            if (key == KeyEvent.VK_DOWN && (!up_d)) {
                left_d = false ;
                right_d = false;
                down_d = true;
            }
        }

    }
}
