
package brickbreaker;


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;


public class MapGenerator {
    
    /*this is the class for bricks*/
    
    public int map[][];     //2D array that contains bricks
    
    public int brickWidth;
    public int brickHeight;
    
    public MapGenerator(int row,int column)
    {
        map = new int[row][column];
        
        for(int i = 0;i < map.length;i++)
        {
            
            for(int j = 0;j < map[0].length;j++)
            {
                map[i][j] = 1;      //here 1 represents that brick is present there
            }
        }
        
        brickWidth  = 540/column;
        brickHeight = 150/row;
    }
    
    /**function to display the bricks**/
    
    public void draw(Graphics2D g)      //Graphics2D is an abstract class 
    {
        for(int i = 0;i < map.length;i++)
        {
            for(int j = 0; j < map[0].length; j++)
            {
                if(map[i][j] > 0)
                {
                    /*that means if map status is not 0 then that brick is vsisble*/
                    
                    g.setColor(Color.orange);
                    g.fillRect(j*brickWidth + 80, i*brickHeight + 50, brickWidth, brickHeight);
                    g.setStroke(new BasicStroke(10));    //this makes a stroke of 1 px width between different componets(bricks) so that theay are clearly visisble
                    g.setColor(Color.black);    //color of stroke is kept same as background color
                    g.drawRect(j*brickWidth + 80, i*brickHeight + 50, brickWidth, brickHeight);
                    
                }
            }
        }
    }
    
    //we need to create a method that tells if the ball touches the brick then brick gets invisible
    //this could be done by changing the value of brick to 0
    
    public void setBrickValue(int value,int row , int column)
    {
        map[row][column] = value;
    }
    
    
    
}
