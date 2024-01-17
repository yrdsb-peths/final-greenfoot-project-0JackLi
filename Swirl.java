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
    Class<?> cls;
    SimpleTimer timer = new SimpleTimer();
    int horizontal = 0, vertical = 0;
    public Swirl(int horizontal, int vertical)
    {
        image.scale(30, 30);
        this.horizontal = horizontal;
        this.vertical = vertical;
    }
    
    public void act()
    {
        // Add your action code here.
        removeOctagonShape();
        ((MyWorld) getWorld()).removeObject(this);
    }
    
    private void removeOctagonShape()
    {
        for(int i = 0; i < vertical; i++)
        {
            for(int u = 0; u < horizontal; u++)
            {
                for(int k = 0; k < 5; k++)
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
                        ((MyWorld) getWorld()).removeFromArray(getOneObjectAtOffset(40 * u , 40 * i, cls));
                        ((MyWorld) getWorld()).removeFromArray(getOneObjectAtOffset(-(40 * u), 40 * i, cls));
                        ((MyWorld) getWorld()).removeFromArray(getOneObjectAtOffset(-(40 * u) , -(40 * i), cls));
                        ((MyWorld) getWorld()).removeFromArray(getOneObjectAtOffset(40 * u, -(40 * i), cls));
                        ((MyWorld) getWorld()).removeObject(getOneObjectAtOffset(40 * u , 40 * i, cls));
                        ((MyWorld) getWorld()).removeObject(getOneObjectAtOffset(-(40 * u), 40 * i, cls));
                        ((MyWorld) getWorld()).removeObject(getOneObjectAtOffset(-(40 * u) , -(40 * i), cls));
                        ((MyWorld) getWorld()).removeObject(getOneObjectAtOffset(40 * u, -(40 * i), cls));
                    }
                    else if(u >= 1)
                    {
                        ((MyWorld) getWorld()).removeFromArray(getOneObjectAtOffset(40 * (u - 1), 40 * i, cls));
                        ((MyWorld) getWorld()).removeFromArray(getOneObjectAtOffset(-40 * (u - 1), -(40 * i), cls));
                        ((MyWorld) getWorld()).removeFromArray(getOneObjectAtOffset(-40 * (u - 1), 40 * i, cls));
                        ((MyWorld) getWorld()).removeFromArray(getOneObjectAtOffset(40 * (u - 1), -(40 * i), cls));
                        ((MyWorld) getWorld()).removeObject(getOneObjectAtOffset(40 * (u - 1), 40 * i, cls));
                        ((MyWorld) getWorld()).removeObject(getOneObjectAtOffset(-40 * (u - 1), -(40 * i), cls));
                        ((MyWorld) getWorld()).removeObject(getOneObjectAtOffset(-40 * (u - 1), 40 * i, cls));
                        ((MyWorld) getWorld()).removeObject(getOneObjectAtOffset(40 * (u - 1), -(40 * i), cls));
                    }
                }
            }
        }
    }
}
