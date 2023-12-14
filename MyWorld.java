import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{

    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    Actor[] blocks;
    Actor[][] blockPosition = new Actor[15][10];
    BlockA blockA;
    BlockB blockB;
    BlockC blockC;
    BlockD blockD;
    BlockE blockE;
    BlockF blockF;
    BlockG blockG;
    Frame frame, lastFrame;
    ArrayList<Actor> list = new ArrayList<Actor>();
    private int clickCount = 0;
    
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(400, 600, 1);
        initilizeImages();
        createBackground();
        addLine();
        randomBlocks();
        removeBlocks(blockPosition);
    }

    public void act()
    {
        if(Greenfoot.mouseClicked(null))
        {
            if(Greenfoot.getMouseInfo().getButton() == 1)
            {
                checkClick();
            }
        }
    }
    
    private void initilizeActors()
    {
        blocks = new Actor[]{blockA = new BlockA(), blockB = new BlockB(), blockC = new BlockC(),
                  blockD = new BlockD(), blockE = new BlockE(), blockF = new BlockF()};
    }
    
    private void initilizeImages()
    {
       
    }
    
    private void randomBlocks()
    {
        int x = getWidth()/10 - 18;
        int y = getHeight()/15 - 20;
        for(int i = 0; i < 15; i++)
        {
            for(int u = 0; u < 10; u++)
            {
                initilizeActors();
                int rand = Greenfoot.getRandomNumber(blocks.length-1);
                blockPosition[i][u] = blocks[rand];
                if(blockPosition[i][u].equals(blockA))
                {
                    System.out.println("A");
                }
                addObject(blocks[rand], x, y); 
                x += 40;
            }
            x = getWidth()/10 - 18;
            y += getHeight()/15;
        }
    }
    
    private void checkClick()
    {
        int x = 0, y = 0;
        for(int i = 0; i < 10; i++)
        {
            if(Greenfoot.getMouseInfo().getX() >= getWidth()/10 * i && Greenfoot.getMouseInfo().getX() < getWidth()/10 * (i+1))
            {
                x = getWidth()/10 * i;
            }
        }
        
        for(int i = 0; i < 15; i++)
        {
            if(Greenfoot.getMouseInfo().getY() >= getHeight()/15 *i && Greenfoot.getMouseInfo().getY() < getHeight()/15 * (i + 1))
            {
                y = getHeight()/15 * i;
            }
        }
        if(clickCount >= 2)
        {
            removeObject(frame);
            removeObject(lastFrame);
        }
        else{
            lastFrame = frame;
            frame = new Frame();
            addObject(frame, x + 20, y + 20);
        }
        clickCount = (clickCount + 1) % 3;
    }
    
    private void removeBlocks(Actor[][] actors)
    {
        int count = 0;
        for(int i = 0; i < 15; i++)
        {
            for(int u = 0; u < 9; u++)
            {
               if(actors[i][u].equals(blockA))
               {
                   System.out.println("S");
                   count++;
                   list.add(actors[u][i]);
               }
               else
               {
                   count = 0;
                   list.clear();
               }
               if(count >= 3)
               {
                   for(Actor k : list)
                   {
                       removeObject(k);
                   }
               }
            }
        }
    }
    private void createBackground()
    {
        Background background = new Background();
        GreenfootImage image = background.getImage();
        image.scale(getWidth(), getHeight());
        setBackground(image);
    }
    
    private void addLine()
    {
        for(int i = 0; i < 16; i++)
        {
            Line line = new Line();
            GreenfootImage image = line.getImage();
            image.scale(900, 100);
            addObject(line, getWidth()/2, getHeight()/15 * i);
        }

        for(int i = 0; i < 11; i++)
        {
            Line2 line2 = new Line2();
            GreenfootImage image = line2.getImage();
            image.scale(100, 900);
            addObject(line2, getWidth()/10 * i, getHeight()/2);
        }
    }
}
