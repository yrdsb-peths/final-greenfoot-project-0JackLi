import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
import greenfoot.Actor;

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
    Frame frame = new Frame();
    //int count = -1;
    ArrayList<Actor> removeList = new ArrayList<Actor>();
    private int clickCount = 0;
    private boolean canSwitch = false;
    boolean isTrue;

    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(400, 600, 1);
        initilizeImages();
        createBackground();
        addLine();
        randomBlocks();
        checkRow(10, 15, true);
        checkColumn(10, 15, true);
    }

    public void act()
    {
        checkBelow();
        checkRow(10, 15, true);
        checkColumn(10, 15, true);

        if(Greenfoot.mouseClicked(null))
        {
            if(Greenfoot.getMouseInfo().getButton() == 1)
            {
                checkClick();
            }
            if(Greenfoot.getMouseInfo().getButton() == 3)
            {
                isTrue = true;
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
                //blockToString[i][u] = actorsToString(blockPosition[i][u]);
                addObject(blocks[rand], x, y); 
                x += 40;
            }
            x = getWidth()/10 - 18;
            y += getHeight()/15;
        }
    }

    private void convertToString(Actor[][] actor)
    {
        for(int i = 0; i < actor.length; i++)
        {
            for(int u = 0; u < actor[i].length; u++)
            {

            }
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
        //convertToString(blockPosition);
    }

    private void checkClick()
    {
        int x = 0, y = 0;
        canSwitch = false;
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
            if(isClickedBlock(clickedActors[0]) && isClickedBlock(clickedActors[1]))
            {
                if(canMove(clickedActors))
                {
                    moveBlocks(clickedActors);
                }
            }
            removeObject(frame);
            clickCount = 0;
        }
        else
        {
            clickedActors[0] = Greenfoot.getMouseInfo().getActor();
        }
    }

    private boolean isClickedBlock(Actor actor)
    {
        for(int i = 0; i < blocks.length; i++)
        {
            if(actor.getClass().equals(blocks[i].getClass()))
            {
                return true;
            }
        }
        return false;
    }

    private void moveBlocks(Actor[] actor)
    {
        int x = actor[0].getX();
        int y = actor[0].getY();
        actor[0].setLocation(actor[1].getX(), actor[1].getY());
        actor[1].setLocation(x, y);
        //switchElements(actor);
    }

    private boolean canMove(Actor[] actor)
    {
        if(actor[0].getX() + 40 == actor[1].getX() && actor[0].getY() == actor[1].getY() || actor[0].getX() - 40 == actor[1].getX()
        && actor[0].getY() == actor[1].getY() || actor[0].getX() == actor[1].getX() && actor[0].getY() + 40 == actor[1].getY() ||
        actor[0].getX() == actor[1].getX() && actor[0].getY() - 40 == actor[1].getY())
        {   
            switchElements(actor); 
            checkRow(10, 15, false);
            checkColumn(10, 15, false);
            if(!canSwitch)
            {
                switchElements(actor);
                return false;
            }
            return true;
        }
        return false;
    }

    private void switchElements(Actor[] actor)
    {
        int[] column = new int[2];
        int[] row = new int[2];
        for(int i = 0; i < blockPosition.length; i++)
        {
            for(int u = 0; u < blockPosition[i].length; u++)
            {
                if(actor[0].equals(blockPosition[i][u]))
                {
                    column[0] = i;
                    row[0] = u;
                }
                else if(actor[1].equals(blockPosition[i][u]))
                {
                    column[1] = i;
                    row[1] = u;
                }
            }
        }
        Actor lastActor = blockPosition[column[0]][row[0]];
        blockPosition[column[0]][row[0]] = blockPosition[column[1]][row[1]];
        blockPosition[column[1]][row[1]] = lastActor;
        //convertToString(blockPosition);
    }

    private String actorsToString(Actor actor)
    {
        if(actor != null)
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
        return null;
    }

    private void checkRow(int row, int column, boolean delete)
    {
        int count = 1;
        for(int i = 0; i < column; i++)
        {
            count = 1;
            for(int u = 0; u < row; u++)
            {
                if(blockPosition[i][u] != null && count == 1)
                {
                    removeList.add(blockPosition[i][u]);
                }
                if(u < row - 1 && blockPosition[i][u] != null && blockPosition[i][u + 1] != null && 
                blockPosition[i][u].getClass().equals(blockPosition[i][u + 1].getClass())) 
                {
                    count++;
                    removeList.add(blockPosition[i][u + 1]);
                }
                else 
                {
                    if(count >= 4 && delete)
                    {
                        if(clickedActors[0] != null && clickedActors[0].getClass().equals(removeList.get(0).getClass()))
                        {
                            removeList.remove(clickedActors[0]);
                            if(count == 4)
                            {
                                checkAbility(clickedActors[0], true, false, false);
                            }
                            else if(count > 4)
                            {
                                checkAbility(clickedActors[0], false, false, true);
                            }
                        }
                        else if(clickedActors[1] != null && clickedActors[1].getClass().equals(removeList.get(0).getClass()))
                        {
                            removeList.remove(clickedActors[1]);
                            if(count == 4)
                            {
                                checkAbility(clickedActors[1], true, false, false);
                            }
                            else if(count > 4)
                            {
                                checkAbility(clickedActors[1], false, false, true);
                            }
                        }
                    }
                    removeBlocks(count, 3, removeList, delete);
                    removeList.clear();
                    count = 1;
                }
            }
            removeBlocks(count, 3, removeList, delete);
        }
    }

    private void checkColumn(int row, int column, boolean delete)
    {
        int count = 1;
        for(int i = 0; i < row; i++)
        {
            count = 1;
            for(int u = 0; u < column; u++)
            {
                if(blockPosition[u][i] != null && count == 1)
                {
                    removeList.add(blockPosition[u][i]);
                }
                if(u < column - 1 && blockPosition[u][i] != null && blockPosition[u + 1][i] != null && 
                blockPosition[u][i].getClass().equals(blockPosition[u + 1][i].getClass()))
                {
                    count++;
                    removeList.add(blockPosition[u + 1][i]);
                }
                else 
                {
                    if(count >= 4 && delete)
                    {
                        if(clickedActors[0] != null && clickedActors[0].getClass().equals(removeList.get(0).getClass()))
                        {
                            removeList.remove(clickedActors[0]);
                            if(count == 4)
                            {
                                checkAbility(clickedActors[0], false, true, false);
                            }
                            else if(count > 4)
                            {
                                checkAbility(clickedActors[0], false, false, true);
                            }
                        }
                        else if(clickedActors[1] != null && clickedActors[1].getClass().equals(removeList.get(0).getClass()))
                        {
                            removeList.remove(clickedActors[1]);
                            if(count == 4)
                            {
                                checkAbility(clickedActors[1], false, true, false);
                            }
                            else if(count > 4)
                            {
                                checkAbility(clickedActors[1], false, false, true);
                            }
                        }
                    }
                    removeBlocks(count, 3, removeList, delete);
                    removeList.clear();
                    count = 1;
                }
            }
            removeBlocks(count, 3, removeList, delete);
        }
    }

    private void checkAbility(Actor actor, boolean horizontal,boolean vertical, boolean bomb)
    {
        Actor obj = null;
        if(actor.getClass().equals(blockA.getClass()))
        {
            BlockA a = new BlockA();
            a.horizontalAbility = horizontal;
            a.verticalAbility = vertical;
            a.bombAbility = bomb;
            obj = a;
        }
        else if(actor.getClass().equals(blockB.getClass()))
        {
            BlockB b = new BlockB();
            b.horizontalAbility = horizontal;
            b.verticalAbility = vertical;
            b.bombAbility = bomb;
            obj = b;
        }
        else if(actor.getClass().equals(blockC.getClass()))
        {
            BlockC c = new BlockC();
            c.horizontalAbility = horizontal;
            c.verticalAbility = vertical;
            c.bombAbility = bomb;
            obj = c;
        }
        else if(actor.getClass().equals(blockD.getClass()))
        {
            BlockD d = new BlockD();
            d.horizontalAbility = horizontal;
            d.verticalAbility = vertical;
            d.bombAbility = bomb;
            obj = d;
        }
        else
        {
            BlockE e = new BlockE();
            e.horizontalAbility = horizontal;
            e.verticalAbility = vertical;
            e.bombAbility = bomb;
            obj = e;
        }
        addObject(obj, actor.getX(), actor.getY());
        checkEquals(blockPosition, actor, obj);
        removeObject(actor);
    }

    private void checkEquals(Actor[][] arr, Actor actor, Actor newActor)
    {
        for(int i = 0; i < arr.length; i++)
        {
            for(int u = 0; u < arr[i].length; u++)
            {
                if(arr[i][u] != null && arr[i][u].equals(actor))
                {
                    arr[i][u] = newActor;
                }
            }
        }
    }

    private void removeBlocks(int num, int limit, ArrayList<Actor> list, boolean delete)
    {
        if(num >= limit)
        {
            for(int i = 0; i < list.size(); i++)
            {
                if(delete)
                {
                    removeFromArray(list.get(i));
                    removeObject(list.get(i));
                }
            }
            canSwitch = true;
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
                    blockToString[i][u] = null;
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
