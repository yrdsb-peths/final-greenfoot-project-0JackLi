import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class TitleScreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TitleScreen extends World
{

    /**
     * Constructor for objects of class TitleScreen.
     * 
     */
    GreenfootSound sound = new GreenfootSound("sounds/homeMusic.mp3");
    SimpleTimer timer = new SimpleTimer();
    public TitleScreen()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(400, 600, 1); 
        sound.playLoop();
        Label label = new Label("Click anywhere to start", 30);
        addObject(label, 200, 500);
    }
    
    public void act()
    {
        if(Greenfoot.mouseClicked(null))
        {
            MyWorld world = new MyWorld();
            sound.stop();
            Greenfoot.setWorld(world);
        }
    }
}
