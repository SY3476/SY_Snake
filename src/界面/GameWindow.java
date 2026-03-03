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
    private static final int Width=31;
    private static final int Height=21;
    private static int Speed;
    private static final int Blook_Size=20;
    private Point food;
    private String direction;
    private Timer timer;
    private ArrayList<Point> snake;
    private boolean isGameOver;
    private int Score;
    private int Count;

    public GameWindow() {
        setTitle("贪吃蛇");
        getContentPane().setBackground(Color.BLACK);
        setSize(Width * Blook_Size, Height * Blook_Size);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);

        snake= new ArrayList<>();
        snake.add(new Point(10,16));
        snake.add(new Point(10,15));
        snake.add(new Point(10,14));

        generateFood();

        direction="R";

        Score=0;
        Count=0;
        Speed=200;

        isGameOver=false;

        this.addKeyListener(this);
        this.setFocusable(true);


        timer = new Timer(Speed,this);
        timer.setDelay(Speed);
        timer.start();


    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GameWindow());
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (isGameOver) {
            g.setColor(Color.red);
            g.setFont(new Font("黑体",Font.BOLD,35));
            g.drawString("你把自己创思了",Blook_Size*Width/2,Blook_Size*Height/2);
        }//进行游戏结束处理
        if(Count==5) {
            g.setColor(Color.white);
            g.setFont(new Font("黑体",Font.BOLD,35));
            g.drawString("恭喜通过，地图已无空位",Blook_Size*Width,Blook_Size*Height);
        }//胜利结束
        g.setColor(Color.black);
        g.fillRect(0,0,Width,Height);

        g.setColor(Color.green);
        for(int i=1;i<Height;i++) {
            g.setFont(new Font( "黑体",Font.BOLD,10));
            g.drawString("#",Blook_Size,i*Blook_Size);
            g.drawString("#",30*Blook_Size,i*Blook_Size);
        }
        for(int i=1;i<Width;i++) {
            g.setFont(new Font( "黑体",Font.BOLD,10));
            g.drawString("#",i*Blook_Size,Blook_Size);
            g.drawString("#",i*Blook_Size,20*Blook_Size);
        }


        g.setColor(Color.yellow);
        Point head = snake.getFirst();
        g.fillRect(head.x*Blook_Size,head.y*Blook_Size,Blook_Size,Blook_Size);//化蛇

        g.setColor(Color.white);
        snake.stream()
                .skip(1)
                .forEach((e)->{g.fillRect(e.x*Blook_Size,e.y*Blook_Size,Blook_Size,Blook_Size);});//化蛇

        g.setColor(Color.red);
        g.fillRect(food.x*Blook_Size,food.y*Blook_Size,Blook_Size,Blook_Size);
    }

    private void  generateFood(){
        Count=0;
        Random rand = new Random();
        do{
            food = new Point(rand.nextInt(1, 30), rand.nextInt(1, 20));
            if(snake.contains(food)){
                Count++;
            }
            if (Count==5){
                break;
            }
        }while(snake.contains(food));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(isGameOver){
            return;
        }//结束判断

        Point newhead = new Point(snake.getFirst().x, snake.getFirst().y);
        if (direction.equals("R")) {
            newhead.x=snake.getFirst().x+1;
        }else  if (direction.equals("L")) {
            newhead.x=snake.getFirst().x-1;
        }else  if (direction.equals("U")) {
            newhead.y=snake.getFirst().y-1;
        }else  if (direction.equals("D")) {
            newhead.y=snake.getFirst().y+1;
        }//运动

        if((newhead.x==0 || newhead.y==0 || newhead.y==21 ||newhead.x==31)||snake.contains(newhead)){
            isGameOver=true;
            timer.stop();
            return;
        }//碰撞检测

        snake.addFirst(newhead);
        if(newhead.equals(food)) {
            if(Speed>=100) {
                Speed -= 10;
                timer.setDelay(Speed);
            }
            Score+=10;
            generateFood();
        }else {
            snake.removeLast();
        }//干饭检测

        repaint();
}

    @Override
    public void keyTyped(KeyEvent e) {
//不使用
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key=e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_UP:
                if(!direction.equals("D")) {
                direction="U";
                }
                break;
            case KeyEvent.VK_DOWN:
                if(!direction.equals("U")) {
                    direction="D";
                }
                break;
            case KeyEvent.VK_LEFT:
                if(!direction.equals("R")) {
                    direction="L";
                }
                break;
            case KeyEvent.VK_RIGHT:
                if(!direction.equals("L")) {
                    direction="R";
                }
                break;
            case KeyEvent.VK_ENTER:
                if(isGameOver){
                    restartGame();
                    break;
                }
        }

    }
    private void restartGame(){
        if(isGameOver){
            isGameOver=false;
            direction="R";
            Speed=200;
            Score=0;
            Count=0;
            snake.clear();
            snake.add(new Point(10,16));
            snake.add(new Point(10,15));
            snake.add(new Point(10,14));
            timer.restart();
        }
    }


    @Override
    public void keyReleased(KeyEvent e) {
//不使用
    }
}



