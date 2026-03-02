package 界面;

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

        direction="R";

        isGameOver=false;

        timer = new Timer(Speed,this);
        timer.start();


    }
    public void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GameWindow());
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (isGameOver) {
            g.setColor(Color.red);
            g.
        }//进行游戏结束处理，暂时搁置
        g.setColor(Color.yellow);
        Point head = snake.getFirst();
        g.fillRect(head.x*Blook_Size,head.y*Blook_Size,Blook_Size,Blook_Size);

        g.setColor(Color.white);
        snake.stream()
                .skip(1)
                .forEach((e)->{g.fillRect(e.x*Blook_Size,e.y*Blook_Size,Blook_Size,Blook_Size);});

        g.setColor(Color.red);
        g.fillRect(food.x*Blook_Size,food.y*Blook_Size,Blook_Size,Blook_Size);
    }

    private void  generateFood(){
        Random rand = new Random();
        do{
            food = new Point(rand.nextInt(1, 20), rand.nextInt(1, 30));
        }while(snake.contains(food));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(isGameOver){
            return;
        }//结束判断

        Point newhead = snake.getFirst();
        if (direction.equals("R")) {
            newhead.x=snake.getFirst().x+1;
            newhead.y=snake.getFirst().y;
        }else  if (direction.equals("L")) {
            newhead.x=snake.getFirst().x-1;
            newhead.y=snake.getFirst().y;
        }else  if (direction.equals("U")) {
            newhead.x=snake.getFirst().x;
            newhead.y=snake.getFirst().y-1;
        }else  if (direction.equals("D")) {
            newhead.x=snake.getFirst().x;
            newhead.y=snake.getFirst().y+1;
        }//运动

        if((newhead.x==0 || newhead.y==0 || newhead.y==31 ||newhead.x==21)||snake.contains(newhead)){
            isGameOver=true;
            timer.stop();
            return;
        }//碰撞检测

        snake.add(0,newhead);
        if(newhead.equals(food)) {
            generateFood();
        }else {
            snake.remove(snake.size() - 1);
        }//干饭检测

        repaint();
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



