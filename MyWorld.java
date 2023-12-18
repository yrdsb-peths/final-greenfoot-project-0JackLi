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
    String[][] blockToString = new String[15][10];
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
        int count = 0;
        for(int i = 0; i < 15; i++)
        {
            count = 0;
            for(int u = 0; u < 10; u++)
            {
                initilizeActors();
                int rand = Greenfoot.getRandomNumber(blocks.length-1);
                blockPosition[i][u] = blocks[rand];
                blockToString[i][u] = actorsToString(blockPosition[i][u]);
                //System.out.println(blockToString[i][u]);
                addObject(blocks[rand], x, y); 
                x += 40;
            }
            x = getWidth()/10 - 18;
            y += getHeight()/15;
        }
        removeBlocks();
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

    private String actorsToString(Actor actor)
    {
        if(actor.equals(blockA))
        {
            return "fox";
        }
        else if(actor.equals(blockB))
        {
            return "bear";
        }
        else if(actor.equals(blockC))
        {
            return "chicken";
        }
        else if(actor.equals(blockD))
        {
            return "rabbit";
        }
        else if(actor.equals(blockE))
        {
            return "mouse";
        }
        else if(actor.equals(blockF))
        {
            return "cat";
        }
        else
        {
            return "panda";
        }
    }

    private void removeBlocks()
    {
        int count = 0;
        for(int i = 0; i < 15; i++)
        {
            count = 0;
            for(int u = 0; u < 9; u++)
            {
                if(blockToString[i][u].equals(blockToString[i][u + 1]))
                {
                    count++;
                    list.add(blockPosition[i][u]);
                }
                else if(u > 0 && blockToString[i][u].equals(blockToString[i][u-1]))
                {

                    count++;
                    list.add(blockPosition[i][u]);
                }
                else 
                {
                    if(count >= 3)
                    {
                        for(int k = 0; k < list.size(); k++)
                        {
                            removeObject(list.get(k));
                        }
                    }
                    list.clear();
                    count = 0;
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
