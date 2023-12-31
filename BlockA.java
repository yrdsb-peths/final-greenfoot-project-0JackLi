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
    public boolean verticalAbility, horizontalAbility, bombAbility;
    private ArrowLeft left = new ArrowLeft();
    private ArrowRight right = new ArrowRight();
    private ArrowUp up = new ArrowUp();
    private ArrowDown down = new ArrowDown();
    private SimpleTimer timer = new SimpleTimer();
    boolean canMove = true;
    public BlockA()
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
        setAbilityImage();
        //checkAbility();
    }
    
    private void setAbilityImage()
    {
        if(verticalAbility || horizontalAbility)
        {
            setImage("images/ability1_model/ability1.png");
            origImage = getImage();
        }
        else if(bombAbility)
        {
            setImage("images/ability2_model/ability1.png");
            origImage = getImage();
        }
    }
     /*if(timer.millisElapsed() > 125)
            {
                if((getX() - 19) - x1 > 3)
                {
                    x1 = getX() - 19;
                }
                if(x2 - (getX() + 14) > 3)
                {
                    x2 = getX() + 14;
                }
                left.setLocation(x1 - 1, left.getY());
                right.setLocation(x2 + 1, left.getY());
                timer.mark();
            }
            */
}
