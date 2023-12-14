import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class BlockF here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BlockF extends Actor
{
    /**
     * Act - do whatever the BlockF wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    GreenfootImage clickedImage = new GreenfootImage("images/block_clicked/click6.png");
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
                if(origImage != null){
                    setImage(origImage);
                }
            }
        }
    }
}
