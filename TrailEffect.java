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
    int dx, dy;
    public TrailEffect(boolean horizontal, boolean vertical)
    {
        this.horizontal = horizontal;
        this.vertical = vertical;
        ((MyWorld) getWorld()).stop = true;
    }

    public void act()
    {
        // Add your action code here.
        if(horizontal)
        {
            dx = image.getWidth()/2;
            dy = 0;
        }
        else if(vertical)
        {
            dx = 0;
            dy = image.getHeight()/2;
        }
        removeBlocks();
        if(timer.millisElapsed() > 15)
        {
            if(image.getWidth() < 1200 && horizontal || image.getHeight() < 1200 && vertical)
            {
                if(horizontal)
                {
                    image.scale(image.getWidth() + 40, image.getHeight());
                }
                else if(vertical)
                {
                    image.scale(image.getWidth(), image.getHeight() + 40);
                }
            }
            else
            {
                ((MyWorld) getWorld()).stop = false;
                ((MyWorld) getWorld()).removeObject(this);
            }
            timer.mark();
        }
    }

    /*
     * remove every specific actors that is touched
     */
    private void removeBlocks()
    {
        ((MyWorld) getWorld()).removeFromArray(getOneObjectAtOffset(dx, dy, BlockA.class));
        ((MyWorld) getWorld()).removeFromArray(getOneObjectAtOffset(dx, dy, BlockB.class));
        ((MyWorld) getWorld()).removeFromArray(getOneObjectAtOffset(dx, dy, BlockC.class));
        ((MyWorld) getWorld()).removeFromArray(getOneObjectAtOffset(dx, dy, BlockD.class));
        ((MyWorld) getWorld()).removeFromArray(getOneObjectAtOffset(dx, dy, BlockE.class));
        ((MyWorld) getWorld()).removeFromArray(getOneObjectAtOffset(-(dx), -(dy), BlockA.class));
        ((MyWorld) getWorld()).removeFromArray(getOneObjectAtOffset(-(dx), -(dy), BlockB.class));
        ((MyWorld) getWorld()).removeFromArray(getOneObjectAtOffset(-(dx), -(dy), BlockC.class));
        ((MyWorld) getWorld()).removeFromArray(getOneObjectAtOffset(-(dx), -(dy), BlockD.class));
        ((MyWorld) getWorld()).removeFromArray(getOneObjectAtOffset(-(dx), -(dy), BlockE.class));
        ((MyWorld) getWorld()).removeObject(getOneObjectAtOffset(dx, dy, BlockA.class));
        ((MyWorld) getWorld()).removeObject(getOneObjectAtOffset(dx, dy, BlockB.class));
        ((MyWorld) getWorld()).removeObject(getOneObjectAtOffset(dx, dy, BlockC.class));
        ((MyWorld) getWorld()).removeObject(getOneObjectAtOffset(dx, dy, BlockD.class));
        ((MyWorld) getWorld()).removeObject(getOneObjectAtOffset(dx, dy, BlockE.class));
        ((MyWorld) getWorld()).removeObject(getOneObjectAtOffset(-(dx), -(dy), BlockA.class));
        ((MyWorld) getWorld()).removeObject(getOneObjectAtOffset(-(dx), -(dy), BlockB.class));
        ((MyWorld) getWorld()).removeObject(getOneObjectAtOffset(-(dx), -(dy), BlockC.class));
        ((MyWorld) getWorld()).removeObject(getOneObjectAtOffset(-(dx), -(dy), BlockD.class));
        ((MyWorld) getWorld()).removeObject(getOneObjectAtOffset(-(dx), -(dy), BlockE.class));

    }
}
