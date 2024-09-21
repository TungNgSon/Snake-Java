/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pong;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

class Ball
{
    public int x,y;
    public int speedX=3;
    public int speedY=3;
    public int diameter;
    public Ball(int x,int y,int diameter)
    {
        this.x=x;
        this.y=y;
        this.diameter=diameter;
    }
       
        
}
class ControlWindow extends JPanel implements ActionListener, KeyListener
{
    Ball ball=new Ball(Const.WIDTH/2, Const.HEIGHT/2, 50);
    Timer timer=new Timer(10,this);
    Player leftp=new Player(Const.WIDTH/2-75,0);
    Player rightp=new Player(Const.WIDTH/2-75,Const.HEIGHT-leftp.height*2-leftp.height/4);
    Font font=new Font("Consolas",Font.BOLD,40);
    public ControlWindow()
    {
        timer.start();
        setFocusable(true);
        this.addKeyListener(this);
        
    }
    @Override
    public void paintComponent(Graphics g)
    {
        System.out.println("PaintComponent duoc goi");
        super.paintComponent(g);
        this.setBackground(Color.black);
        g.setColor(Color.white);
        g.fillOval(ball.x,ball.y,ball.diameter,ball.diameter);
        g.setColor(Color.green);
        g.fillRect(leftp.x, leftp.y, leftp.width, leftp.height);
        g.fillRect(rightp.x, rightp.y, rightp.width, rightp.height);
        g.setFont(font);
        g.drawString("Up: " + leftp.score, 40,40);
        g.drawString("Down: "+rightp.score ,40, Const.HEIGHT-80);
//        if(leftp.score==3)
//        {
//            g.drawString("UP WIN! ",Const.WIDTH/2,Const.HEIGHT/2);
//            
//        }
        
    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
        System.out.println("ActionPerform duoc goi");
        ball.x+=ball.speedX;
        ball.y+=ball.speedY;
        Rectangle rectb=new Rectangle(ball.x,ball.y,ball.diameter,ball.diameter);
        Rectangle rectl=new Rectangle(leftp.x,leftp.y,leftp.width,leftp.height);
        Rectangle rectr=new Rectangle(rightp.x,rightp.y,rightp.width,rightp.height);
        if(rectb.intersects(rectl))
        {
            ball.speedY=Math.abs(ball.speedY);
        }
        if(rectb.intersects(rectr))
        {
            ball.speedY=-Math.abs(ball.speedY);
        }
        if(ball.x>=Const.WIDTH-ball.diameter)
        {
            ball.speedX*=-1;
        }
        if(ball.y>=Const.HEIGHT-ball.diameter-ball.diameter/2)
        {
            ball.speedY*=-1;
            ball.x=Const.WIDTH/2;
            ball.y=Const.HEIGHT/2;
            leftp.score+=1;
        }
        if(ball.x<=0)
        {
            ball.speedX*=-1;
        }
        if(ball.y<=0)
        {
            ball.speedY*=-1;
            ball.x=Const.WIDTH/2;
            ball.y=Const.HEIGHT/2;
            rightp.score+=1;
        }
        repaint();
    }
    @Override 
    public void keyPressed(KeyEvent e)
    {
        System.out.println("DocSuKienBanPhim");
        if (e.getKeyCode() == KeyEvent.VK_A) {
                        leftp.x -= leftp.speedX;
                }
               
                if (e.getKeyCode() == KeyEvent.VK_D) {
                        leftp.x += leftp.speedX;
                }
               
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                        rightp.x -= rightp.speedX;
                }
               
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                        rightp.x += rightp.speedX;
                }
    }
    @Override public void keyTyped(KeyEvent e){}
    
    @Override public void keyReleased(KeyEvent e){}
    
}
class Const
{
    public static final int WIDTH=1000;
    public static final int HEIGHT=500;
}
class Player
{
    public int x,y;
    public int width=150, height=30;
    public int speedX=45;
    public int score=0;
    public Player(int x,int y)
    {
        this.x=x;
        this.y=y;
        
    }
 
}
       
public class PONG extends JFrame 
{

    public static ControlWindow cw=new ControlWindow();
    public PONG()
    {
        this.add(cw);
        this.pack();
        
        this.setTitle("PONG CUA TUNG");
        this.setSize(Const.WIDTH,Const.HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        
        
        
    }
    
    public static void main(String[] args) {
        new PONG();
        
    }
    
}
