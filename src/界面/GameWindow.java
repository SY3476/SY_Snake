package 界面;

import org.w3c.dom.ls.LSOutput;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

public class GameWindow extends JFrame implements KeyListener, ActionListener {
    private static final int Width=21;
    private static final int Height=31;
    private static final int Speed=200;
    private static final int Blook_Size=20;
    private Point food;
    private String direction;
    private Timer timer;
    private ArrayList<Point> snake;
    private boolean isGameOver;

    public GameWindow() {
        setTitle("贪吃蛇");
        setBackground(Color.black);
        setSize(620, 420);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);

        snake= new ArrayList<>();
        snake.add(new Point(10,16));
        snake.add(new Point(10,15));
        snake.add(new Point(10,14));

        direction="RIGHT";

        isGameOver=false;

        timer = new Timer(Speed,this);
        timer.start();

    }
    private void  generateFood(){
        Random rand = new Random();
        do{
            food = new Point(rand.nextInt(1, 20), rand.nextInt(1, 30));
        }while(snake.contains(food));
    }

    public void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GameWindow());
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}



