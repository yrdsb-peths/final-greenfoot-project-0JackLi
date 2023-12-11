import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{

    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1);
        createBackground();
        addLine();
    }
    
    private void createBackground()
    {
        Background background = new Background();
        GreenfootImage image = background.getImage();
        image.scale(getWidth(), getHeight());
        setBackground(image);
    }
    
    private void addLine()
    {
        for(int i = 0; i < 9; i++)
        {
            Line line = new Line();
            GreenfootImage image = line.getImage();
            image.scale(900, 300);
            addObject(line, getWidth()/2, getHeight()/8 * i);
        }
    }
}
