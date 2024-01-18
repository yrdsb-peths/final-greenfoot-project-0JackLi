import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class backEffect here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BackEffect extends Actor
{
    /**
     * Act - do whatever the backEffect wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    SimpleTimer timer;
    public BackEffect()
    {
        timer = new SimpleTimer();
    }
    public void act()
    {
        if(timer.millisElapsed() > 50)
        {
            this.setRotation(this.getRotation() + 13);
            timer.mark();
        }
    }
}
