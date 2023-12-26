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
    GreenfootImage clickedImage = new GreenfootImage("images/block_clicked/click5.png");
    GreenfootImage origImage;
    public boolean verticalAbility, horizontalAbility, bombAbility;
    public BlockE()
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
        checkAbility();
    }
    private void checkAbility()
    {
        if(verticalAbility || horizontalAbility)
        {
            setImage("images/ability1_model/ability5.png");
            origImage = getImage();
        }
        else if(bombAbility)
        {
            setImage("images/ability2_model/ability5.png");
            origImage = getImage();
        }
    }
}
