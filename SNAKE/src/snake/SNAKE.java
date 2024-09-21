/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

class Const
{
    public static final int Tile=20;
    public static final int Width=Tile*25;
    public static final int Height=Tile*25;
}
class ControlWindow extends JPanel implements ActionListener,KeyListener
{   
    private int FruitX,FruitY;
    private Timer timer=new Timer(80,this);
    private int SnakeX[],SnakeY[];
    private int SnakeLength=4;
    private char SnakeDir='r';
    private boolean gameOver=false;
    private Font font= new Font("Times New Roman",Font.BOLD,40);
    public ControlWindow()
    {
        this.setPreferredSize(new Dimension(Const.Width,Const.Height));
        FruitX= new java.util.Random().nextInt(25)*Const.Tile ;
        FruitY= new java.util.Random().nextInt(25)*Const.Tile ;
        SnakeX=new int[100];
        SnakeY=new int[100];
        this.addKeyListener(this);
        this.setFocusable(true);
        timer.start();
    }
    @Override public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
     
        this.setBackground(Color.black);
        g.setColor(Color.white);
        //Grid
        for(int i=0;i<Const.Width;i+=Const.Tile)
        {
            for(int j=0;j<Const.Height;j+=Const.Tile)
            {
                g.drawRect(i, j, Const.Tile, Const.Tile);
            }
        }
        //Fruit
        g.setColor(Color.white);
        g.fillRect(FruitX, FruitY, Const.Tile, Const.Tile);
        //Snake
        g.setColor(Color.green);
        for(int i=0;i<SnakeLength;i++)
        {
            g.fillRect(SnakeX[i], SnakeY[i], Const.Tile, Const.Tile);
        }
        //Font
        if(gameOver==true)
        {
        g.setColor(Color.YELLOW);
        g.setFont(font);
        g.drawString("Game Over", Const.Width/2-(5*Const.Tile), Const.Height/2);
    }
        g.dispose();
    }
    @Override public void actionPerformed(ActionEvent e)
    {
        if(gameOver==false)
        {
        for(int i=SnakeLength-1;i>0;i--)
        {
            SnakeX[i]=SnakeX[i-1];
            SnakeY[i]=SnakeY[i-1];
            
        }
        if(SnakeDir=='r')
        {
        SnakeX[0]+=Const.Tile;
        }
        if(SnakeDir=='l')
        {
            SnakeX[0]-=Const.Tile;
        }
        if(SnakeDir=='u')
        {
            SnakeY[0]-=Const.Tile;
        }
        if(SnakeDir=='d')
        {
            SnakeY[0]+=Const.Tile;
        }
        if(SnakeX[0]<0)
        {
            SnakeX[0]=Const.Width;
        }
        if(SnakeX[0]>Const.Width)
        {
            SnakeX[0]=0;
        }
        if(SnakeY[0]<0)
        {
            SnakeY[0]=Const.Height;
        }
        if(SnakeY[0]>Const.Height)
        {
            SnakeY[0]=0;
            
        }
        if(SnakeX[0]==FruitX&&SnakeY[0]==FruitY)
        {
            SnakeLength++;
            FruitX= new java.util.Random().nextInt(25)*Const.Tile ;
            FruitY= new java.util.Random().nextInt(25)*Const.Tile ;
        }
        for(int i=SnakeLength-1;i>0;i--)
        {
            if(SnakeX[0]==SnakeX[i]&&SnakeY[0]==SnakeY[i])
            {
                gameOver=true;
            }
        }
        }
        repaint();
    }
    @Override public void keyPressed(KeyEvent e)
    {
        int c=e.getKeyCode();
        if(c==KeyEvent.VK_RIGHT)
        {
            if(SnakeDir!='l')
            {
                SnakeDir='r';
            }
        }
        if(c==KeyEvent.VK_LEFT)
        {
            if(SnakeDir!='r')
            {
                SnakeDir='l';
            }
        }
        if(c==KeyEvent.VK_UP)
        {
            if(SnakeDir!='d')
            {
                SnakeDir='u';
            }
        }
        if(c==KeyEvent.VK_DOWN)
        {
            if(SnakeDir!='u')
            {
                SnakeDir='d';
            }
        }
        if(gameOver==true)
        {
            gameOver=false;
//            SnakeLength=4;
            for(int i=0;i<SnakeLength;i++)
            {
                SnakeX[i] = 0;
                SnakeY[i]=0;
                SnakeDir='r';
            }
            SnakeLength=4;
            
        }
    }
    @Override public void keyTyped(KeyEvent e){}
  
    @Override public void keyReleased(KeyEvent e){}
  
}
public class SNAKE extends JFrame
{
    ControlWindow cw=new ControlWindow();
    public SNAKE()
    {
        this.add(cw);
        this.pack();
        this.setTitle("Snake");
//        this.setSize(Const.Width, Const.Height);
        this.setResizable( false );
        this.setLocationRelativeTo( null );
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        
    }
    public static void main(String[] args) {
        new SNAKE();
    }
    
}
