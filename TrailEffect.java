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
    private boolean horizontal, vertical;
    public TrailEffect(boolean horizontal, boolean vertical)
    {
        this.horizontal = horizontal;
        this.vertical = vertical;
        ((MyWorld) getWorld()).stop = true;
    }
    public void act()
    {
        // Add your action code here.
        if(timer.millisElapsed() > 25)
        {
            if(image.getWidth() < 1000 && horizontal || image.getHeight() < 1000 && vertical)
            {
                if(horizontal)
                {
                    image.scale(image.getWidth() + 20, image.getHeight());
                }
                else if(vertical)
                {
                    image.scale(image.getWidth(), image.getHeight() + 30);
                }
                removeBlocks();
            }
            else
            {
                ((MyWorld) getWorld()).stop = false;
                ((MyWorld) getWorld()).removeObject(this);
            }
            timer.mark();
        }
    }
    
    private void removeBlocks()
    {
        ((MyWorld) getWorld()).removeFromArray(getOneObjectAtOffset(image.getWidth()/2 - 10, image.getHeight() - 5, BlockA.class));
        ((MyWorld) getWorld()).removeFromArray(getOneObjectAtOffset(image.getWidth()/2 - 10, image.getHeight() - 5, BlockB.class));
        ((MyWorld) getWorld()).removeFromArray(getOneObjectAtOffset(image.getWidth()/2 - 10, image.getHeight() - 5, BlockC.class));
        ((MyWorld) getWorld()).removeFromArray(getOneObjectAtOffset(image.getWidth()/2 - 10, image.getHeight() - 5, BlockD.class));
        ((MyWorld) getWorld()).removeFromArray(getOneObjectAtOffset(image.getWidth()/2 - 10, image.getHeight() - 5, BlockE.class));
        ((MyWorld) getWorld()).removeObject(getOneObjectAtOffset(image.getWidth()/2 - 15, image.getHeight() - 10, BlockA.class));
        ((MyWorld) getWorld()).removeObject(getOneObjectAtOffset(image.getWidth()/2 - 15, image.getHeight() - 10, BlockB.class));
        ((MyWorld) getWorld()).removeObject(getOneObjectAtOffset(image.getWidth()/2 - 15, image.getHeight() - 10, BlockC.class));
        ((MyWorld) getWorld()).removeObject(getOneObjectAtOffset(image.getWidth()/2 - 15, image.getHeight() - 10, BlockD.class));
        ((MyWorld) getWorld()).removeObject(getOneObjectAtOffset(image.getWidth()/2 - 15, image.getHeight() - 10, BlockE.class));
    }
}
