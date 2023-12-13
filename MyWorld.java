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
    Actor[] blocks;
    BlockA blockA;
    BlockB blockB;
    BlockC blockC;
    BlockD blockD;
    BlockE blockE;
    BlockF blockF;
    BlockG blockG;
    
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(400, 600, 1);
        initilizeImages();
        createBackground();
        addLine();
        initilizeActors();
        addObject(blocks[0], 40, 20);
        //blocks[0].setImage("images/ability1_model/ability1.png");
    }

    public void act()
    {
        
    }
    
    private void initilizeActors()
    {
        blocks = new Actor[]{blockA = new BlockA(), blockB = new BlockB(), blockC = new BlockC(),
                  blockD = new BlockD(), blockE = new BlockE(), blockF = new BlockF()};
    }
    
    private void initilizeImages()
    {
       
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
}
