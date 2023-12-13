import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class BlockA here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BlockA extends Actor
{
    /**
     * Act - do whatever the BlockA wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    GreenfootImage clickedImage = new GreenfootImage("images/block_clicked/click1.png");
    GreenfootImage origImage;
    public void act()
    {
        // Add your action code here.
        if(Greenfoot.mouseClicked(this))
        {
            if(Greenfoot.getMouseInfo().getButton() == 3)
            {
                if(origImage == null)
                {
                    origImage = getImage();
                }
                setImage(clickedImage);
            }
            else
            {
                setImage(origImage);
            }
        }
    }
}
