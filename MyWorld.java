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
        super(400, 600, 1);        
        createBackground();
        addLine();
        addCha();
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
        for(int i = 0; i < 16; i++)
        {
            Line line = new Line();
            GreenfootImage image = line.getImage();
            image.scale(900, 100);
            addObject(line, getWidth()/2, getHeight()/15 * i);
        }

        for(int i = 0; i < 11; i++)
        {
            Line2 line2 = new Line2();
            GreenfootImage image = line2.getImage();
            image.scale(100, 900);
            addObject(line2, getWidth()/10 * i, getHeight()/2);
        }
    }

    
    private void addCha()
    {
        BlockA ch0;
        BlockB ch1;
        BlockC ch2;
        BlockD ch3;
        BlockE ch4;
        BlockF ch5;
        BlockG ch6;
        Actor[] actor = { ch0 = new BlockA(),
                ch1 = new BlockB(),
                ch2 = new BlockC(),
                ch3 = new BlockD(), 
                ch4 = new BlockE(),
                ch5 = new BlockF(),
                ch6 = new BlockG()};
        for(int i = 0; i < actor.length; i++)
        {
            addObject(actor[i], 40 * i + 22, 60);
        }
    }

}
