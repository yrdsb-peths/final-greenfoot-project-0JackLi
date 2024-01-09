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
    Actor animatedActor;
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
    boolean isTrue, canMove = true, startAnimation;
    private SimpleTimer timer = new SimpleTimer();
    private SimpleTimer movingTimer = new SimpleTimer();
    private ArrayList<ArrowLeft> left = new ArrayList<ArrowLeft>();
    private ArrayList<ArrowRight> right = new ArrayList<ArrowRight>();
    private ArrayList<Actor> animatedVActors = new ArrayList<Actor>();
    private ArrayList<Actor> animatedHActors = new ArrayList<Actor>();
    private ArrayList<ArrowUp> up = new ArrayList<ArrowUp>();
    private ArrayList<ArrowDown> down = new ArrayList<ArrowDown>();

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
        checkRow(10, 15, true);
        checkColumn(10, 15, true);
        checkBelow();
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
        if(startAnimation)
        {
            abilityAnimation();
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
                if(blockPosition[i][u] == null)
                {
                    initilizeActors();
                    int rand = Greenfoot.getRandomNumber(blocks.length-1);
                    blockPosition[i][u] = blocks[rand];
                    //blockToString[i][u] = actorsToString(blockPosition[i][u]);
                    addObject(blocks[rand], x, y); 
                }
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
        //randomBlocks();
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
        boolean isBomb = false;
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
                    if(i < column - 2 && blockPosition[i + 1][u] != null && blockPosition[i + 2][u] != null && 
                    blockPosition[i][u].getClass().equals(blockPosition[i + 1][u].getClass()) 
                    && blockPosition[i + 1][u].getClass().equals(blockPosition[i + 2][u].getClass())) 
                    {
                        count += 2;
                        removeList.add(blockPosition[i + 1][u]);
                        removeList.add(blockPosition[i + 2][u]);
                        isBomb = true;
                    }
                    else if(i > 1 && blockPosition[i - 1][u] != null && blockPosition[i - 2][u] != null && 
                    blockPosition[i][u].getClass().equals(blockPosition[i - 1][u].getClass()) 
                    && blockPosition[i - 1][u].getClass().equals(blockPosition[i - 2][u].getClass()))
                    {
                        count += 2;
                        removeList.add(blockPosition[i - 1][u]);
                        removeList.add(blockPosition[i - 2][u]);
                        isBomb = true;
                    }
                }
                else 
                {
                    if(count >= 4 && delete)
                    {
                        if(clickedActors[0] != null && clickedActors[0].getClass().equals(removeList.get(0).getClass()))
                        {
                            removeList.remove(clickedActors[0]);
                            if(count == 4 && !isBomb)
                            {
                                checkAbility(clickedActors[0], true, false, false);
                            }
                            else if(count > 4 && isBomb)
                            {
                                checkAbility(clickedActors[0], false, false, true);
                            }
                        }
                        else if(clickedActors[1] != null && clickedActors[1].getClass().equals(removeList.get(0).getClass()))
                        {
                            removeList.remove(clickedActors[1]);
                            if(count == 4 && isBomb)
                            {
                                checkAbility(clickedActors[1], true, false, false);
                            }
                            else if(count > 4 && isBomb)
                            {
                                checkAbility(clickedActors[1], false, false, true);
                            }
                        }
                        else if(count == 4 && !isBomb)
                        {
                            checkAbility(removeList.get(0), true, false, false);
                            removeList.remove(removeList.get(0));
                        }
                        else if(count > 4 && isBomb)
                        {
                            checkAbility(removeList.get(0), false, false, true);
                            removeList.remove(removeList.get(0));
                        }
                    }
                    if(isBomb && count < 5)
                    {
                        removeList.clear();
                    }
                    removeBlocks(count, 3, removeList, delete);
                    removeList.clear();
                    count = 1;
                    isBomb = false;
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
                                //checkAbility(clickedActors[1], false, false, true);
                            }
                        }
                        else if(count == 4)
                        {
                            checkAbility(removeList.get(0), false, true, false);
                            removeList.remove(removeList.get(0));
                        }
                        else if(count > 4)
                        {
                            
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

    private void checkAbility(Actor actor, boolean horizontal, boolean vertical, boolean bomb)
    {
        Actor obj = null;
        ArrowLeft leftArrow;
        ArrowRight rightArrow;
        ArrowUp upArrow;
        ArrowDown downArrow;
        if(actor.getClass().equals(BlockA.class))
        {
            BlockA a = new BlockA();
            a.horizontalAbility = horizontal;
            a.verticalAbility = vertical;
            a.bombAbility = bomb;
            obj = a;
        }
        else if(actor.getClass().equals(BlockB.class))
        {
            BlockB b = new BlockB();
            b.horizontalAbility = horizontal;
            b.verticalAbility = vertical;
            b.bombAbility = bomb;
            obj = b;
        }
        else if(actor.getClass().equals(BlockC.class))
        {
            BlockC c = new BlockC();
            c.horizontalAbility = horizontal;
            c.verticalAbility = vertical;
            c.bombAbility = bomb;
            obj = c;
        }
        else if(actor.getClass().equals(BlockD.class))
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
        if(horizontal)
        {
            leftArrow = new ArrowLeft();
            rightArrow = new ArrowRight();
            left.add(leftArrow);
            right.add(rightArrow);
            addObject(leftArrow, actor.getX() - 18, actor.getY() + 5);
            addObject(rightArrow, actor.getX() + 15, actor.getY() + 5);
            animatedHActors.add(obj);
        }
        else if(vertical)
        {
            upArrow = new ArrowUp();
            downArrow = new ArrowDown();
            up.add(upArrow);
            down.add(downArrow);
            addObject(upArrow, actor.getX() - 1, actor.getY() - 16);
            addObject(downArrow, actor.getX() - 1, actor.getY() + 17);
            animatedVActors.add(obj);
        }
        startAnimation = true;
        addObject(obj, actor.getX(), actor.getY());
        checkEquals(blockPosition, actor, obj);
        removeObject(actor);
    }

    private void abilityAnimation()
    {
        int[] x1 = new int[animatedHActors.size()];
        int[] x2 = new int[animatedHActors.size()];
        int[] y1 = new int[animatedVActors.size()];
        int[] y2 = new int[animatedVActors.size()];

        for(int i = 0; i < left.size(); i++){
            x1[i] = left.get(i).getX();
            x2[i] = right.get(i).getX();
        }
        for(int i = 0; i < up.size(); i++)
        {
            y1[i] = up.get(i).getY();
            y2[i] = down.get(i).getY();
            if(y2[i] - (animatedVActors.get(i).getY() + 17) < 0)
            {
                up.get(i).setLocation(animatedVActors.get(i).getX() - 1, animatedVActors.get(i).getY() - 18);
                down.get(i).setLocation(animatedVActors.get(i).getX() - 1, animatedVActors.get(i).getY() + 17);
            }
        }
        if(timer.millisElapsed() > 125)
        {
            for(int i = 0; i < left.size(); i++)
            {
                if((animatedHActors.get(i).getX() - 18) - left.get(i).getX() < 4 && canMove)
                {
                    left.get(i).setLocation(x1[i] - 1, animatedHActors.get(i).getY() + 5);
                    right.get(i).setLocation(x2[i] + 1, animatedHActors.get(i).getY() + 5);
                }
                else if(x2[i] - (animatedHActors.get(i).getX() + 15) != 0)
                {
                    canMove = false;                   
                    left.get(i).setLocation(x1[i] + 1, animatedHActors.get(i).getY() + 5);
                    right.get(i).setLocation(x2[i] - 1, animatedHActors.get(i).getY() + 5);
                }
                else
                {
                    canMove = true;
                    //left.get(i).setLocation(x1[i] - 1, animatedHActors.get(i).getY() + 5);
                    //right.get(i).setLocation(x2[i] + 1, animatedHActors.get(i).getY() + 5);
                }
            }
            for(int i = 0; i < up.size(); i++)
            {
                if((animatedVActors.get(i).getY() - 16) - up.get(i).getY() < 4 && canMove)
                {
                    up.get(i).setLocation(animatedVActors.get(i).getX() - 1, y1[i] - 1);
                    down.get(i).setLocation(animatedVActors.get(i).getX() - 1, y2[i] + 1);
                }
                else if(y2[i] - (animatedVActors.get(i).getY() + 17) != 0)
                {
                    canMove = false;
                    up.get(i).setLocation(animatedVActors.get(i).getX() - 1, y1[i] + 1);
                    down.get(i).setLocation(animatedVActors.get(i).getX() - 1, y2[i] - 1);
                }
                else
                {
                    canMove = true;
                    //up.get(i).setLocation(animatedVActors.get(i).getX() - 2, y1[i] + 1);
                    //down.get(i).setLocation(animatedVActors.get(i).getX() - 2, y2[i] - 1);
                }
            }
            timer.mark();
        }
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
                    if(list.get(i) != null)
                    {
                        removeFromArray(list.get(i));
                        //frame = new Frame();
                        //addObject(frame, list.get(i).getX(), list.get(i).getY());
                        removeObject(list.get(i));
                    }
                }
            }
            if(delete)
            {
                clickedActors[0] = null;
                clickedActors[1] = null;
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
        for(int i = 0; i < animatedHActors.size(); i++)
        {
            if(animatedHActors.get(i).equals(actor))
            {
                animatedHActors.remove(actor);
                removeObject(left.get(i));
                removeObject(right.get(i));
                left.remove(i);
                right.remove(i);
            }
        }
        for(int i = 0; i < animatedVActors.size(); i++){
            if(animatedVActors.get(i).equals(actor))
            {
                animatedVActors.remove(actor);
                removeObject(up.get(i));
                removeObject(down.get(i));
                up.remove(i);
                down.remove(i);
            }
        }
    }
    
    private void movingBlockAnimation(Actor actor1, int endX, int endY)
    {
        int x = actor1.getX();
        int y = actor1.getY();
        if(actor1.getX() < endX)
        {
            actor1.setLocation(x + 1, y);
        }
        else if(actor1.getX() > endX)
        {
            actor1.setLocation(x - 1, y);
        }
        else if(actor1.getY() < endY)
        {
            actor1.setLocation(x, y + 1);
        }
        else 
        {
            actor1.setLocation(x, y - 1);
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
