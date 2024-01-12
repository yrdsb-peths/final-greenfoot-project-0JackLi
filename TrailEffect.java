import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

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
    public TrailEffect()
    {
        //((MyWorld) getWorld()).stop = true;
    }
    public void act()
    {
        // Add your action code here.
        if(timer.millisElapsed() > 5)
        {
            if(image.getWidth() < 700)
            {
                //image.scale(image.getWidth() + 40, image.getHeight());
            }
            else
            {
                //((MyWorld) getWorld()).stop = false;
                //((MyWorld) getWorld()).removeObject(this);
            }
            timer.mark();
        }
    }
}
