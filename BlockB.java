import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class BlockB here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BlockB extends Actor
{
    /**
     * Act - do whatever the BlockB wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    GreenfootImage clickedImage = new GreenfootImage("images/block_clicked/click2.png");
    GreenfootImage origImage;
    public BlockB()
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
