
package brickbreaker;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import javax.swing.Timer;


public class Gameplay extends JPanel implements KeyListener,ActionListener {
    
 //This class will be a panel in our gameplay and we need to include this class in JFrame
    
    //ActionListener and KeyListeners are interfaces 
    
    private boolean play = false;   //play is initialised with false because when our application starts it should not start playing itself
    
    private int score = 0;
    private int totalbricks = 32;   //3 rows and 7 columns
    private Timer timer;    //refernce to Timer class is used for setting the speed of ball
    
    private int delay = 5;  //delay given to timer
    
    private int playerX = 300; //starting position of slider
    
    //starting position of ball
    private int ballposX = 120;
    private int ballposY = 350;
    
    private int ballXdir = -1;  //in the animation of ball it moves x by -1 and y by -2;(left and up respectively)
    private int ballYdir = -2;
    
    private MapGenerator map;
    //constructor for Gameplay class
    
    public Gameplay()
    {
        map = new MapGenerator(4,8);
        addKeyListener(this);   //addKeyLIstener method is used to identify the type of kry pressed by user
        setFocusable(true);
        setFocusTraversalKeysEnabled(true);
        timer = new Timer(delay,this);  //timer object allotted memory for the given object
        timer.start();      //until and unless you dont start timer game will not start
    }
    
    
    //NOW the method paint() to create the graphical objects
    
    public void paint(Graphics g)
    {
        //background
        g.setColor(Color.black);
        g.fillRect(1,1,700,600);        //g.fillRect(Xpos(),Ypos(),Width,height)
        
        
        
        //borders
        
        /*g.setColor(Color.yellow);
        g.fillRect(0,0,3,592);
        g.fillRect(0,0,692,3);
        g.fillRect(691,0,3,592);*/
        
        
        
        //paddle
        
        g.setColor(Color.cyan);
        g.fillRect(playerX,550,100,10);  //here variable is kept instead of a constant because position of paddle changes in the game
        
        //map generator
        map.draw((Graphics2D) g);
        
        //ball
        g.setColor(Color.red);
        g.fillOval(ballposX,ballposY,20,20);
        
        //display score
        g.setColor(Color.green);
        g.setFont(new Font("serif",Font.BOLD,30));
        g.drawString("SCORE :" + score,400,30);
        
        
        //check if game is over
        if(ballposY > 570)
        {
            play = false;
            ballXdir = 0;
            ballYdir = 0;
            g.setColor(Color.red);
            g.setFont(new Font("serif",Font.BOLD,30));
            g.drawString("GAME OVER!!!",250,300);
            
            //request to restart  the game
            g.setFont(new Font("serif",Font.BOLD,30));
            g.drawString("press space to play again",220,500);
          
        }
        
        
        //condition after winning
        
        if(totalbricks <= 0)
        {
            play = false;
            ballXdir = 0;
            ballYdir = 0;
            g.setColor(Color.red);
            g.setFont(new Font("serif",Font.BOLD,30));
            g.drawString("YOU WON!!!",250,300);
            
            g.drawString("your score is:" + score,250,400);
            
            //request to restart  the game
            g.setFont(new Font("serif",Font.BOLD,30));
            g.drawString("press space to play again",220,500);
        }
        
        g.dispose();
    }
    
   
    

    @Override
    public void keyTyped(KeyEvent ke) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if(e.getKeyCode()== KeyEvent.VK_RIGHT)
        {
            if(playerX >= 600)
            {
                playerX = 600;
            }
            
            else
            {
                moveright();
            }
        }
        
        
        if(e.getKeyCode()== KeyEvent.VK_LEFT)
        {
            if(playerX <= 10)
            {
                playerX = 10;
            }
            
            else
            {
                moveleft();
            }
        }
        
        
        if(e.getKeyCode() == KeyEvent.VK_SPACE)
        {
            if(!play)
            {
                play = true;
                ballposX = 120;
                ballposY = 350;
                ballXdir = -1;
                ballYdir = -2;
                playerX = 310;
                score = 0;
                totalbricks = 32;
                map = new MapGenerator(4,8);
                
               // repaint();
            }
        }
            
    
    }
    
    
    
    
    public void moveright()
    {
        play = true;
        playerX += 20; //if you press right the paddle moves right by 20 px      
    }
    
    public void moveleft()
    {
        play = true;
        playerX -= 20; //if you press left the paddle moves left by 20 px      
    }
    
    

    @Override
    public void keyReleased(KeyEvent ke) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
       timer.start();
       repaint();   //repaint method draws the figure completely again so as to recreate the image when the action is preformed
        
       
       
       //given below is code for animation of moving ball
       
       if(play)
       {
           
           ballposX += ballXdir;
           ballposY += ballYdir;
           
           if(ballposX < 0)
           {
               ballXdir = -ballXdir;    //rebound from left edge
           }
           
           
           if(ballposY < 0)
           {
               ballYdir = -ballYdir;    //rebound from top edge
           }
           
           
           if(ballposX > 670)
           {
               ballXdir = -ballXdir;    //rebound from right edge
           }
           
           //the fourth condition is to check whether there is a collision between ball and the paddle
           
           //since the ball is oval in shape so we need to add a rectangle around the ball so as to 
           //easily check the condition for rebound from paddle as then both will be rectangle (ball and paddle)
           
           if(new Rectangle(ballposX,ballposY,20,20).intersects(new Rectangle(playerX,550,100,10)))
           {
               ballYdir = -ballYdir;
           }
           
           //here we need to check whether there is any contact between ball and the brick
           
           A: for(int i = 0;i < map.map.length;i++)     //map.map.length represents the row length of 2d array "map" of "map" object of MapGenerator class
           {
               for(int j = 0;j < map.map[0].length ;j++)
               {
                   //checking for untouched brick
                   if(map.map[i][j] != 0)       //if any brick value is not 0 then it is untouched and thus it is visible
                   {
                       int brickX = j*map.brickWidth + 80;
                       int brickY = i*map.brickHeight + 50;
                       int brickWidth = map.brickWidth;
                       int brickHeight = map.brickHeight;
                       
                       //now as in case of paddle and ball we created a recatangle around paddle and ball
                       //similary here also in order to detect contact between brick and ball we need to create a recatangle around brick and ball
                       
                       Rectangle brickRect = new Rectangle(brickX,brickY,brickWidth,brickHeight);
                       Rectangle ballRect = new Rectangle(ballposX,ballposY,20,20);
                       
                       if(ballRect.intersects(brickRect))
                       {
                           map.setBrickValue(0,i,j);
                           totalbricks--;
                           score += 10;
                           
                           if(ballposX + 19 <= brickRect.x || ballposX + 1 >= brickRect.x + brickRect.width)
                           {
                               ballXdir = -ballXdir;
                           }
                           else 
                           {
                               ballYdir = -ballYdir;
                           }
                           break A;     //here break label is applied because if this happens then we want contro, to go out of the outer for loop
                       }
                       
                       
                   }
                       
               }
           }
           
           
           //repaint();
       }
    }
}
    
    
 
