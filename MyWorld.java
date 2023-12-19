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
    Actor[] clickedActors = new Actor[2];
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
    Line line;
    Line2 line2;
    Frame frame = new Frame();;
    ArrayList<Actor> removeList = new ArrayList<Actor>();
    private int clickCount = 0;

    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(400, 600, 1);
        initilizeImages();
        createBackground();
        addLine();
        randomBlocks();
        checkRow(10, 15);
        checkColumn(10, 15);
    }

    public void act()
    {
        checkBelow();
        if(Greenfoot.mouseClicked(null))
        {
            System.out.println(Greenfoot.getMouseInfo().getActor());
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
                addObject(blocks[rand], x, y); 
                x += 40;
            }
            x = getWidth()/10 - 18;
            y += getHeight()/15;
        }
    }
    
    private void checkBelow()
    {
        for(int i = 0; i < blockPosition.length; i++)
        {
            for(int u = 0; u < blockPosition[i].length; u++)
            {
                if(blockPosition[i][u] == null && i > 0 && blockPosition[i-1][u] != null)
                {
                    blockPosition[i - 1][u].setLocation(blockPosition[i - 1][u].getX(), blockPosition[i - 1][u].getY() + 40);
                    blockPosition[i][u] = blockPosition[i - 1][u];
                    blockPosition[i - 1][u] = null;
                }
            }
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
        frame.setLocation(x + 20, y + 20);
        addObject(frame, x + 20, y + 20);
        clickCount++;
        if(clickCount >= 2)
        {
            clickedActors[1] = Greenfoot.getMouseInfo().getActor();
            if(canMove(clickedActors))
            {
                removeObject(frame);
                clickCount = 0;
            }
            else
            {
                clickCount = 1;
            }
        }
        clickedActors[0] = Greenfoot.getMouseInfo().getActor();
    }

    private boolean canMove(Actor[] actor)
    {
        if(actor[0].getX() + 40 == actor[1].getX() && actor[0].getY() == actor[1].getY() || actor[0].getX() - 40 == actor[1].getX()
          && actor[0].getY() == actor[1].getY() || actor[0].getX() == actor[1].getX() && actor[0].getY() + 40 == actor[1].getY() ||
          actor[0].getX() == actor[1].getX() && actor[0].getY() - 40 == actor[1].getY())
        {
            return true;
        }
        return false;
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

    private void checkRow(int row, int column)
    {
        int count = 0;
        for(int i = 0; i < column; i++)
        {
            count = 0;
            for(int u = 0; u < row; u++)
            {
                if(u < row - 1 && blockToString[i][u].equals(blockToString[i][u + 1]) || u > 0 && blockToString[i][u].equals(blockToString[i][u-1]))
                {
                    count++;
                    removeList.add(blockPosition[i][u]);
                }
                else 
                {
                    removeBlocks(count, 3, removeList);
                    removeList.clear();
                    count = 0;
                }
            }
            removeBlocks(count, 3, removeList);
        }
    }
    
    private void checkColumn(int row, int column)
    {
        int count = 0;
        for(int i = 0; i < row; i++)
        {
            count = 0;
            for(int u = 0; u < column; u++)
            {
                if(u < column - 1 && blockToString[u][i].equals(blockToString[u + 1][i]) || u > 0 && blockToString[u][i].equals(blockToString[u - 1][i]))
                {
                    count++;
                    removeList.add(blockPosition[u][i]);
                }
                else 
                {
                    removeBlocks(count, 3, removeList);
                    removeList.clear();
                    count = 0;
                }
            }
            removeBlocks(count, 3, removeList);
        }
    }
    
    private void removeBlocks(int num, int limit, ArrayList<Actor> list)
    {
        if(num >= limit)
        {
            for(int i = 0; i < list.size(); i++)
            {
                removeFromArray(list.get(i));
                removeObject(list.get(i));
            }
        }
    }
    
    private void removeFromArray(Actor actor)
    {
        for(int i = 0; i < blockPosition.length; i++)
        {
            for(int u = 0; u < blockPosition[i].length; u++)
            {
                if(blockPosition[i][u] != null && blockPosition[i][u].equals(actor))
                {
                    blockPosition[i][u] = null;
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
            line = new Line();
            GreenfootImage image = line.getImage();
            image.scale(900, 100);
            addObject(line, getWidth()/2, getHeight()/15 * i);
        }

        for(int i = 0; i < 11; i++)
        {
            line2 = new Line2();
            GreenfootImage image = line2.getImage();
            image.scale(100, 900);
            addObject(line2, getWidth()/10 * i, getHeight()/2);
        }
    }
}
