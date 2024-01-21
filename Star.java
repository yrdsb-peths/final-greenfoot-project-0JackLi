import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Star here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Star extends Actor
{
    /**
     * Act - do whatever the Star wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    GreenfootImage image = getImage();
    SimpleTimer timer;
    boolean canStart = false;
    public Star(int i)
    {
        timer = new SimpleTimer();
        image.scale(10, 10);
        if(i % 2 != 0)
        {
            image.setTransparency(0);
        }
    }
    public void act()
    {
        if(timer.millisElapsed() > 100)
        {
            if(image.getTransparency() > 0 && !canStart)
            {
                image.setTransparency(image.getTransparency() - 1);
            }
            else
            {
                canStart = true;
            }
            if(image.getTransparency() < 255 && canStart)
            {
                image.setTransparency(image.getTransparency() + 1);
            }
            else
            {
                canStart = false;
            }
        }
    }
    
    /*
     * sets the transparency of an image
     */
    public int getImageTransparency()
    {
        return image.getTransparency();
    }
}
