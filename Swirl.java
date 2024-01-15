import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Swirl here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Swirl extends Actor
{
    /**
     * Act - do whatever the Swirl wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    GreenfootImage image = getImage();
    Class cls;
    SimpleTimer timer = new SimpleTimer();
    public Swirl()
    {
        
    }
    
    public void act()
    {
        // Add your action code here.
        if(timer.millisElapsed() > 125)
        {
            
        }
    }
    
    private void removeOctagonShape()
    {
        for(int i = 0; i < 3; i++)
        {
            for(int u = 0; u < 3; u++)
            {
                for(int k = 0; k < 5; i++)
                {
                    if(k == 0)
                    {
                        cls = BlockA.class;
                    }
                    else if(k == 1)
                    {
                        cls = BlockB.class;
                    }
                    else if(k == 2)
                    {
                        cls = BlockC.class;
                    }
                    else if(k == 3)
                    {
                        cls = BlockD.class;
                    }
                    else
                    {
                        cls = BlockE.class;
                    }
                    if(i < 2)
                    {
                        ((MyWorld) getWorld()).removeObject(getOneObjectAtOffset(40 * u , 40 * i, cls));
                        ((MyWorld) getWorld()).removeObject(getOneObjectAtOffset(-(40 * u), 40 * i, cls));
                        ((MyWorld) getWorld()).removeObject(getOneObjectAtOffset(-(40 * u) , -(40 * i), cls));
                        ((MyWorld) getWorld()).removeObject(getOneObjectAtOffset(40 * u, -(40 * i), cls));
                    }
                    else
                    {
                        ((MyWorld) getWorld()).removeObject(getOneObjectAtOffset(40, 40 * i, cls));
                        ((MyWorld) getWorld()).removeObject(getOneObjectAtOffset(-40, -(40 * i), cls));
                        ((MyWorld) getWorld()).removeObject(getOneObjectAtOffset(-40, 40 * i, cls));
                        ((MyWorld) getWorld()).removeObject(getOneObjectAtOffset(40, -(40 * i), cls));
                    }
                }
            }
        }
    }
}
