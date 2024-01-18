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
    int[] num = new int[4];
    Star star;
    Label label;
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
    ArrayList<Star> starList = new ArrayList<Star>();
    ArrayList<Actor> removeList = new ArrayList<Actor>();
    public int score = 0;
    boolean isStillMoving = true;
    public static boolean stop = false;
    private int clickCount = 0;
    private boolean canSwitch = false;
    boolean isTrue, canMove = true, startAnimation, canContinue = false;
    private SimpleTimer timer = new SimpleTimer();
    private SimpleTimer movingTimer = new SimpleTimer();
    private ArrayList<BackEffect> backEffect = new ArrayList<BackEffect>();
    private ArrayList<ArrowLeft> left = new ArrayList<ArrowLeft>();
    private ArrayList<ArrowRight> right = new ArrayList<ArrowRight>();
    private ArrayList<Actor> animatedVActors = new ArrayList<Actor>();
    private ArrayList<Actor> animatedHActors = new ArrayList<Actor>();
    private ArrayList<Actor> animatedBActors = new ArrayList<Actor>();
    private ArrayList<ArrowUp> up = new ArrayList<ArrowUp>();
    private ArrayList<ArrowDown> down = new ArrayList<ArrowDown>();
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(400, 600, 1);
        label = new Label(score, 30);
        addObject(label, 50, 100);
        initilizeImages();
        initilizeActors();
        createBackground();
        addLine();
        randomBlocks();
        stop = false;
        checkRow(10, 15, true);
        checkColumn(10, 15, true);
    }

    public void act()
    {
        setScore();
        if(!stop)
        {
            if(!isStillMoving)
            {
                checkRow(10, 15, true);
                checkColumn(10, 15, true);
                //checkBelow();
            }
            if(!stop)
            {
                checkBelow();
            }
            if(Greenfoot.mouseClicked(null))
            {
                //System.out.println(Greenfoot.getMouseInfo().getY());
                //removeFromArray(animatedBActors.get(0));
                if(Greenfoot.getMouseInfo().getButton() == 1)
                {
                    checkClick();
                }
                if(Greenfoot.getMouseInfo().getButton() == 3)
                {
                    isTrue = true;
                }
            }
            if(movingTimer.millisElapsed() > 10)
            {
                movingBlockAnimation();
                movingTimer.mark();
            }
        }
        if(startAnimation)
        {
            abilityAnimation();
        }
    }
    
    private void setScore()
    {
        label.setValue(score);
    }

    private void animatedStar(ArrayList<Star> list, int start, int end, int x, int y)
    {
        GreenfootImage image;
        for(int i = start; i < end; i++)
        {
            if(i == start)
            {
                x += 20;
            }
            else if(i == start + 1)
            {
                x -= 5;
                y += 20;               
            }
            else if(i == start + 2)
            {
                x -= 25;
            }
            else if(i == start + 3)
            {
               x -= 10;
               y -= 20;
            }
            else
            {
                x += 20;
                y -= 20;
            }
            addObject(list.get(i), x, y);
            list.get(i).setLocation(x, y);
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
    //15 10
    private void randomBlocks()
    {
        int x = getWidth()/10 - 18;
        int y = getHeight()/15 - 20;
        int count = 2, count2 = 6;
        for(int i = 0; i < 15; i++)
        {
            //count = 0;
            for(int u = 0; u < 10; u++)
            {
                if(blockPosition[i][u] == null)
                {
                    initilizeActors();
                    int rand = Greenfoot.getRandomNumber(blocks.length-1);
                    blockPosition[i][u] = blocks[rand];
                    addObject(blocks[rand], x, y);
                }
                x += 40;
            }
            x = getWidth()/10 - 18;
            y += getHeight()/15;
        }
    }

    private void generateBlocks()
    {

        for(int u = 0; u < 10; u++)
        {
            if(blockPosition[0][u] == null)
            {
                initilizeActors();
                int rand = Greenfoot.getRandomNumber(blocks.length-1);
                blockPosition[0][u] = blocks[rand];
                addObject(blocks[rand], 20 + 40 * u, 0);
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
                    //blockPosition[i - 1][u].setLocation(blockPosition[i - 1][u].getX(), blockPosition[i - 1][u].getY() + 40);
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
                    if(checkSpecial(clickedActors))
                    {
                        removeFromArray(clickedActors);
                        removeObject(clickedActors[0]);
                        removeObject(clickedActors[1]);
                    }
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
            if(checkSpecial(actor))
            {
                return true;
            }
            if(!canSwitch)
            {
                switchElements(actor);
                return false;
            }
            return true;
        }
        return false;
    }

    private boolean checkSpecial(Actor[] actor)
    {
        int count = 0;
        for(int i = 0; i < animatedHActors.size(); i++)
        {
            if(actor[0].equals(animatedHActors.get(i)) || actor[1].equals(animatedHActors.get(i)))
            {
                count++;
            }
        }
        for(int i = 0; i < animatedVActors.size(); i++)
        {
            if(actor[0].equals(animatedVActors.get(i)) || actor[1].equals(animatedVActors.get(i)))
            {
                count++;
            }
        }
        for(int i = 0; i < animatedBActors.size(); i++)
        {
            if(actor[0].equals(animatedBActors.get(i)) || actor[1].equals(animatedBActors.get(i)))
            {
                count++;
            }
        }
        if(count >= 2)
        {
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

                if(i < column - 2 && blockPosition[i][u] != null && blockPosition[i + 1][u] != null 
                && blockPosition[i + 2][u] != null && blockPosition[i][u].getClass().equals(blockPosition[i + 1][u].getClass()) 
                && blockPosition[i + 1][u].getClass().equals(blockPosition[i + 2][u].getClass())) 
                {
                    count += 2;
                    removeList.add(blockPosition[i + 1][u]);
                    removeList.add(blockPosition[i + 2][u]);
                    isBomb = true;
                }
                else if(i > 1 && blockPosition[i][u] != null && blockPosition[i - 1][u] != null && blockPosition[i - 2][u] != null && 
                blockPosition[i][u].getClass().equals(blockPosition[i - 1][u].getClass()) 
                && blockPosition[i - 1][u].getClass().equals(blockPosition[i - 2][u].getClass()))
                {   
                    count += 2;
                    removeList.add(blockPosition[i - 1][u]);
                    removeList.add(blockPosition[i - 2][u]);
                    isBomb = true;
                }
                else if(i > 0 && i < column - 1 && blockPosition[i][u] != null && blockPosition[i - 1][u] != null && blockPosition[i + 1][u] != null
                && blockPosition[i][u].getClass().equals(blockPosition[i - 1][u].getClass()) 
                && blockPosition[i][u].getClass().equals(blockPosition[i + 1][u].getClass()))
                {
                    count += 2;
                    removeList.add(blockPosition[i - 1][u]);
                    removeList.add(blockPosition[i + 1][u]);
                    isBomb = true;
                }
                if(u < row - 1 && blockPosition[i][u] != null && blockPosition[i][u + 1] != null && 
                blockPosition[i][u].getClass().equals(blockPosition[i][u + 1].getClass())) 
                {
                    count++;
                    removeList.add(blockPosition[i][u + 1]);
                }
                else 
                {
                    if(count >= 4 && delete && checkAbilityActors(removeList, true, false))
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
                    if(count >= 4 && delete && checkAbilityActors(removeList, false, true))
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
                                //checkAbility(clickedActors[0], false, false, true);
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

    private boolean checkAbilityActors(ArrayList<Actor> list, boolean horizontal, boolean vertical)
    {
        for(int i = 0; i < animatedHActors.size(); i++)
        {
            for(int u = 0; u < list.size(); u++)
            {
                if(list.get(u).equals(animatedHActors.get(i)))
                {
                    if(vertical && clickedActors[0]!= null)
                    {
                        TrailEffect effect = new TrailEffect(false, true);
                        addObject(effect, clickedActors[0].getX(), clickedActors[0].getY());
                    }
                    return false;
                }
            }
        }

        for(int i = 0; i < animatedVActors.size(); i++)
        {
            for(int u = 0; u < list.size(); u++)
            {
                if(list.get(u).equals(animatedVActors.get(i)))
                {
                    if(horizontal && clickedActors[0]!= null)
                    {
                        TrailEffect effect = new TrailEffect(true, false);
                        addObject(effect, clickedActors[0].getX(), clickedActors[0].getY());
                    }
                    return false;
                }
            }
        }

        for(int i = 0; i < animatedHActors.size(); i++)
        {
            for(int u = 0; u < list.size(); u++)
            {
                if(list.get(u).equals(animatedHActors.get(i)))
                {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean checkIfPossible()
    {
        for(int i = 0; i < blockPosition.length; i++)
        {
            for(int u = 0; u < blockPosition[i].length; u++)
            {
                if(i < blockPosition.length - 1 && u < blockPosition[i].length - 2 && blockPosition[i][u] != null && blockPosition[i + 1][u + 1] != null 
                && blockPosition[i + 1][u + 2] != null && blockPosition[i][u].getClass().equals(blockPosition[i + 1][u + 1].getClass())
                && blockPosition[i][u].getClass().equals(blockPosition[i + 1][u + 2].getClass()))
                {
                    return true;
                }
                else if(i < blockPosition.length - 1 && u > blockPosition[i].length + 2 && blockPosition[i][u] != null && blockPosition[i + 1][u - 1] != null &&
                blockPosition[i + 1][u - 2] != null && blockPosition[i][u].getClass().equals(blockPosition[i + 1][u - 1].getClass()) && 
                blockPosition[i][u].equals(blockPosition[i + 1][u - 2].getClass()))
                {
                    return true;
                }
                else if(i > 0 && u < blockPosition[i].length - 2 && blockPosition[i][u] != null && blockPosition[i - 1][u + 1] != null && 
                blockPosition[i - 1][u + 2] != null && blockPosition[i][u].getClass().equals(blockPosition[i - 1][u + 1].getClass()) && 
                blockPosition[i][u].getClass().equals(blockPosition[i - 1][u + 2].getClass()))
                {
                    return true;
                }
                else if(i > 0 && u > blockPosition[i].length + 2 && blockPosition[i][u] != null && blockPosition[i][u - 1] != null && 
                blockPosition[i][u - 2] != null && blockPosition[i][u].getClass().equals(blockPosition[i][u - 1].getClass()) && 
                blockPosition[i][u].getClass().equals(blockPosition[i][u - 2].getClass()))
                {
                    return true;
                }
                else if(u < blockPosition[i].length - 3 && blockPosition[i][u] != null && blockPosition[i][u + 2] != null && blockPosition[i][u + 3] != null 
                && blockPosition[i][u].getClass().equals(blockPosition[i][u + 2].getClass()) && blockPosition[i][u].equals(blockPosition[i][u + 3].getClass()))
                {
                    return true;
                }
                else if(u > blockPosition[i].length + 3 && blockPosition[i][u] != null && blockPosition[i][u - 2] != null && blockPosition[i][u - 3] != null
                && blockPosition[i][u].getClass().equals(blockPosition[i][u - 2].getClass()) && blockPosition[i][u].equals(blockPosition[i][u - 3].getClass()))
                {
                    return true;
                }
                else if(i < blockPosition.length - 1 && u < blockPosition[i].length - 1 && u > 0 && blockPosition[i][u] != null && blockPosition[i + 1][u - 1] != null
                && blockPosition[i + 1][u + 1] != null && blockPosition[i][u].getClass().equals(blockPosition[i + 1][u - 1].getClass())
                && blockPosition[i][u].getClass().equals(blockPosition[i + 1][u + 1].getClass()))
                {
                    return true;
                }
                else if(i > 0 && u < blockPosition[i].length - 1 && u > 0 && blockPosition[i][u] != null && blockPosition[i - 1][u - 1] != null
                && blockPosition[i - 1][u + 1] != null && blockPosition[i][u].getClass().equals(blockPosition[i - 1][u - 1].getClass()) 
                && blockPosition[i][u].getClass().equals(blockPosition[i - 1][u + 1].getClass()))
                {
                    return true;
                }
                else if(i < blockPosition.length - 2 && u < blockPosition[i].length - 1 && blockPosition[i][u] != null 
                && blockPosition[i + 1][u + 1] != null && blockPosition[i + 2][u + 1] != null && blockPosition[i][u].getClass().equals(blockPosition[i + 1][u + 1].getClass()) 
                && blockPosition[i][u].getClass().equals(blockPosition[i + 2][u + 1].getClass()))
                {
                    return true;
                }
                else if(i < blockPosition.length - 2 && u > 0 && blockPosition[i][u] != null && blockPosition[i + 1][u - 1] != null 
                && blockPosition[i + 2][u - 1] != null && blockPosition[i][u].getClass().equals(blockPosition[i + 1][u - 1].getClass()) 
                && blockPosition[i][u].getClass().equals(blockPosition[i + 2][u - 1].getClass()))
                {
                    return true;
                }
                else if(i > 1 && u < blockPosition[i].length - 1 && blockPosition[i][u] != null && blockPosition[i - 1][u + 1] != null 
                && blockPosition[i - 2][u + 1] != null && blockPosition[i][u].getClass().equals(blockPosition[i - 1][u + 1].getClass()) 
                && blockPosition[i][u].getClass().equals(blockPosition[i - 2][u + 1].getClass()))
                {
                    return true;
                }
                else if(i > 1 && u > 0 && blockPosition[i][u] != null && blockPosition[i - 1][u - 1] != null 
                && blockPosition[i - 2][u - 1] != null && blockPosition[i][u].getClass().equals(blockPosition[i - 1][u - 1].getClass()) 
                && blockPosition[i][u].getClass().equals(blockPosition[i - 2][u - 1].getClass()))
                {
                    return true;
                }
                else if(i < blockPosition.length - 3 && blockPosition[i][u] != null && blockPosition[i + 2][u] != null 
                && blockPosition[i + 3][u] != null && blockPosition[i][u].getClass().equals(blockPosition[i + 2][u].getClass()) 
                && blockPosition[i][u].getClass().equals(blockPosition[i + 3][u].getClass()))
                {
                    return true;
                }
                else if(i > 2 && blockPosition[i][u] != null && blockPosition[i - 2][u] != null 
                && blockPosition[i - 3][u] != null && blockPosition[i][u].getClass().equals(blockPosition[i - 2][u].getClass()) 
                && blockPosition[i][u].getClass().equals(blockPosition[i - 3][u].getClass()))
                {
                    return true;
                }
            }
        }
        return false;
    }

    private void checkAbility(Actor actor, boolean horizontal, boolean vertical, boolean bomb)
    {
        Actor obj = null;
        ArrowLeft leftArrow;
        ArrowRight rightArrow;
        ArrowUp upArrow;
        ArrowDown downArrow;
        ArrayList<Star> currentList = new ArrayList<Star>();
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
        else if(bomb)
        {
            animatedBActors.add(obj);
            BackEffect swirl = new BackEffect();
            backEffect.add(swirl);
            for(int i = 0; i < 5; i++)
            {
                star = new Star(i);
                starList.add(star);
                currentList.add(star);
            }
            addObject(swirl, actor.getX(), actor.getY());
            animatedStar(starList, starList.size() - 5, starList.size(), actor.getX(), actor.getY());
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
            //formed another one
            if(x2[i] - (animatedHActors.get(i).getX() + 15) < 0 || x1[i] - (animatedHActors.get(i).getX() + 15) > 0)
            {
                x1[i] = animatedHActors.get(i).getX() - 18 + num[0];
                x2[i] = animatedHActors.get(i).getX() + 15 + num[1];
            }
            left.get(i).setLocation(x1[i], animatedHActors.get(i).getY() + 5);
            right.get(i).setLocation(x2[i], animatedHActors.get(i).getY() + 5);
        }
        for(int i = 0; i < up.size(); i++)
        {
            y1[i] = up.get(i).getY();
            y2[i] = down.get(i).getY();
            if(y2[i] - (animatedVActors.get(i).getY() + 17) < 0 || y1[i] - (animatedVActors.get(i).getY() - 16) > 0)
            {
                y1[i] = animatedVActors.get(i).getY() - 16 + num[2];
                y2[i] = animatedVActors.get(i).getY() + 17 + num[3];
            }
            up.get(i).setLocation(animatedVActors.get(i).getX() - 1, y1[i]);
            down.get(i).setLocation(animatedVActors.get(i).getX() - 1, y2[i]);
        }
        for(int i = 0; i < backEffect.size(); i++)
        {
            backEffect.get(i).setLocation(animatedBActors.get(i).getX(), animatedBActors.get(i).getY());
            animatedStar(starList, i * 5, i * 5 + 5, animatedBActors.get(i).getX(), animatedBActors.get(i).getY());
        }
        if(timer.millisElapsed() > 125)
        {
            for(int i = 0; i < left.size(); i++)
            {
                if((animatedHActors.get(i).getX() - 18) - left.get(i).getX() < 4 && canMove)
                {
                    num[0] -= 1;
                    num[1] += 1;
                    left.get(i).setLocation(x1[i] - 1, animatedHActors.get(i).getY() + 5);
                    right.get(i).setLocation(x2[i] + 1, animatedHActors.get(i).getY() + 5);
                }
                else if(x2[i] - (animatedHActors.get(i).getX() + 15) != 0)
                {
                    canMove = false;
                    num[0] += 1;
                    num[1] -= 1;
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
                    num[2] -= 1;
                    num[3] += 1;
                    up.get(i).setLocation(animatedVActors.get(i).getX() - 1, y1[i] - 1);
                    down.get(i).setLocation(animatedVActors.get(i).getX() - 1, y2[i] + 1);
                }
                else if(y2[i] - (animatedVActors.get(i).getY() + 17) != 0)
                {
                    canMove = false;
                    num[2] += 1;
                    num[3] -= 1;
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

    public void removeBlock(Actor actor)
    {

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
                        clickedActors[0] = null;
                        clickedActors[1] = null;
                    }
                }
            }
            canSwitch = true;
        }
    }

    public void removeFromArray(Actor[] actor)
    {
        TrailEffect trail, trail2;
        Swirl swirl;
        boolean horizontal = false, vertical = false, bomb = false;
        for(int i = 0; i < blockPosition.length; i++)
        {
            for(int u = 0; u < blockPosition[i].length; u++)
            {
                if(blockPosition[i][u] != null && blockPosition[i][u].equals(actor[0]) 
                || blockPosition[i][u] != null && blockPosition[i][u].equals(actor[1]))
                {
                    blockPosition[i][u] = null;
                    blockToString[i][u] = null;
                    score += 10;
                }
            }
        }
        for(int i = 0; i < animatedHActors.size(); i++)
        {
            if(animatedHActors.get(i).equals(actor[0]) || animatedHActors.get(i).equals(actor[1]))
            {
                horizontal = true;
                removeObject(left.get(i));
                removeObject(right.get(i));
                left.remove(i);
                right.remove(i);
            }
            if(animatedHActors.get(i).equals(actor[0]))
            {
                animatedHActors.remove(actor[0]);
                i--;
            }
            else if(animatedHActors.get(i).equals(actor[1]))
            {
                animatedHActors.remove(actor[1]);
                i--;
            }
        }
        for(int i = 0; i < animatedVActors.size(); i++)
        {
            if(animatedVActors.get(i).equals(actor[0]) || animatedVActors.get(i).equals(actor[1]))
            {
                vertical = true;
                removeObject(up.get(i));
                removeObject(down.get(i));
                up.remove(i);
                down.remove(i);
            }
            if(animatedVActors.get(i).equals(actor[0]))
            {
                animatedVActors.remove(actor[0]);
                i--;
            }
            else if(animatedVActors.get(i).equals(actor[1]))
            {
                animatedVActors.remove(actor[1]);
                i--;
            }
        }
        for(int i = 0; i < animatedBActors.size(); i++)
        {
            if(animatedBActors.get(i).equals(actor[0]) || animatedBActors.get(i).equals(actor[1]))
            {
                bomb = true;
                removeObject(backEffect.get(i));
                backEffect.remove(i);
                for(int u = 0; u < 5; u++)
                {
                    removeObject(starList.get(i * 5));
                    starList.remove(i * 5);
                }
            }
            if(animatedBActors.get(i).equals(actor[0]))
            {
                animatedBActors.remove(actor[0]);
                i--;
            }
            else if(animatedBActors.get(i).equals(actor[1]))
            {
                animatedBActors.remove(actor[1]);
                i--;
            }
        }
        if(vertical && bomb || horizontal && bomb)
        {
            for(int i = 0; i < 3; i++)
            {
                trail = new TrailEffect(true, false);
                trail2 = new  TrailEffect(false, true);
                addObject(trail, actor[0].getX(), (actor[0].getY() - 40) + 40 * i);
                addObject(trail2, (actor[0].getX() - 40) + 40 * i, actor[0].getY());
            }
        }
        else if(vertical && horizontal || vertical || horizontal)
        {
            trail = new TrailEffect(true, false);
            trail2 = new TrailEffect(false, true);
            addObject(trail, actor[0].getX(), actor[0].getY());
            addObject(trail2, actor[0].getX(), actor[0].getY());
        }
        else if(bomb)
        {
            swirl = new Swirl(6, 6);
            addObject(swirl, actor[0].getX(), actor[0].getY());
        }
    }

    public void removeFromArray(Actor actor)
    {
        for(int i = 0; i < blockPosition.length; i++)
        {
            for(int u = 0; u < blockPosition[i].length; u++)
            {
                if(blockPosition[i][u] != null && blockPosition[i][u].equals(actor))
                {
                    blockPosition[i][u] = null;
                    blockToString[i][u] = null;
                    Phase phase = new Phase();
                    addObject(phase, actor.getX(), actor.getY());
                    score += 10;
                }
            }
        }
        for(int i = 0; i < animatedHActors.size(); i++)
        {
            if(animatedHActors.get(i).equals(actor))
            {
                TrailEffect effect = new TrailEffect(true, false);
                animatedHActors.remove(actor);
                removeObject(left.get(i));
                removeObject(right.get(i));
                left.remove(i);
                right.remove(i);
                addObject(effect, actor.getX(), actor.getY());
            }
        }
        for(int i = 0; i < animatedVActors.size(); i++){
            if(animatedVActors.get(i).equals(actor))
            {
                TrailEffect effect = new TrailEffect(false, true);
                animatedVActors.remove(actor);
                removeObject(up.get(i));
                removeObject(down.get(i));
                up.remove(i);
                down.remove(i);
                addObject(effect, actor.getX(), actor.getY());
            }
        }
        for(int i = 0; i < animatedBActors.size(); i++)
        {
            if(animatedBActors.get(i).equals(actor))
            {
                Swirl swirl = new Swirl(3, 3);
                animatedBActors.remove(actor);
                removeObject(backEffect.get(i));
                backEffect.remove(i);
                addObject(swirl, actor.getX(), actor.getY());
            }
        }
        for(int i = 0; i < animatedBActors.size(); i++)
        {
            if(animatedBActors.get(i).equals(actor))
            {
                for(int u = 0; u < 5; u++)
                {
                    removeObject(starList.get(i * 5));
                    starList.remove(i * 5);
                }
            }
            animatedBActors.remove(actor);
        }
    }
    int c = 0;
    private void movingBlockAnimation()
    {
        isStillMoving = false;
        for(int i = 0; i < blockPosition.length; i++)
        {
            for(int u = 0; u < blockPosition[i].length; u++)
            {
                if(blockPosition[i][u] != null && blockPosition[i][u].getY() < 20 + 40 * i)
                {
                    blockPosition[i][u].setLocation(blockPosition[i][u].getX(), blockPosition[i][u].getY() + 20);
                    isStillMoving = true;
                }
            }
        }
        c++;
        if(c >= 2)
        {
            generateBlocks();
            c = 0;
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
