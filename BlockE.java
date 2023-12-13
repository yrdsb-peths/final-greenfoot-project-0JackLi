import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class BlockE here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BlockE extends Actor
{
    /**
     * Act - do whatever the BlockE wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    GreenfootImage clickedImage = new GreenfootImage("images/block_clicked/click4.png");
    GreenfootImage origImage = new GreenfootImage("images/block5.png");
    public void act()
    {
        // Add your action code here.
        if(Greenfoot.mouseClicked(this))
        {
            if(Greenfoot.getMouseInfo().getButton() == 3)
            {
                setImage(clickedImage);
            }
            else
            {
                setImage(origImage);
            }
        }
    }
}
