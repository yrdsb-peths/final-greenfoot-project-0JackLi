import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class EndScreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EndScreen extends World
{

    /**
     * Constructor for objects of class EndScreen.
     * 
     */
    GreenfootSound sound = new GreenfootSound("sounds/gameOver.mp3");
    public EndScreen(int score)
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(400, 600, 1); 
        sound.playLoop();
        Label label = new Label("GameOver", 60);
        Label scoreLabel = new Label("Final Score: " + score, 40);
        addObject(label, 200, 200);
        addObject(scoreLabel, 190, 400);
    }
}
