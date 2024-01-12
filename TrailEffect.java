import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * Write a description of class TrailEffect here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TrailEffect extends Actor
{
    /**
     * Act - do whatever the TrailEffect wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    SimpleTimer timer = new SimpleTimer();
    GreenfootImage image = getImage();
    ArrayList<Actor> removeList = new ArrayList<Actor>();
    public TrailEffect()
    {
        ((MyWorld) getWorld()).stop = true;
    }

    public void act()
    {
        // Add your action code here.
        if(isTouching(BlockA.class))
        {
            removeTouching(BlockA.class);
        }
        else if(isTouching(BlockB.class))
        {
            removeTouching(BlockB.class);
        }
        else if(isTouching(BlockC.class))
        {
            removeTouching(BlockC.class);
        }
        else if(isTouching(BlockD.class))
        {
            removeTouching(BlockD.class);
        }
        else
        {
            removeTouching(BlockE.class);
        }
        if(timer.millisElapsed() > 200)
        {
            if(image.getWidth() < 700)
            {
                image.scale(image.getWidth() + 40, image.getHeight());
            }
            else
            {
                ((MyWorld) getWorld()).stop = false;
                ((MyWorld) getWorld()).removeObject(this);
            }
            timer.mark();
        }
    }
}
