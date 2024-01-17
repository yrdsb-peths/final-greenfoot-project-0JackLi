import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Phase here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Phase extends Actor
{
    /**
     * Act - do whatever the Phase wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    SimpleTimer timer;
    GreenfootImage image = getImage();
    int i = 1;
    public Phase()
    {
        timer = new SimpleTimer();
        ((MyWorld) getWorld()).stop = true;
    }
    public void act()
    {
        // Add your action code here.
        if(i < 8)
        {
            ((MyWorld) getWorld()).stop = true;
            if(timer.millisElapsed() > 50)
            {
                setImage("images/effect_removed/phase" + i + ".png");
                i++;
                timer.mark();
            }
        }
        else
        {
            ((MyWorld) getWorld()).removeObject(this);
            ((MyWorld) getWorld()).stop = false;
        }
    }
}
