import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class BlockD here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BlockD extends Actor
{
    /**
     * Act - do whatever the BlockD wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    GreenfootImage clickedImage = new GreenfootImage("images/block_clicked/click4.png");
    GreenfootImage origImage;
    public BlockD()
    {
        origImage = getImage();
    }
    public void act()
    {
        // Add your action code here.
        if(Greenfoot.mouseClicked(this) && Greenfoot.getMouseInfo().getButton() >= 3)
        {
            setImage(clickedImage);
        }
        else if(Greenfoot.mouseClicked(null))
        {
            setImage(origImage);
        }
    }
}
