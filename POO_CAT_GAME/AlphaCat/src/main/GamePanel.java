package main;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import Inputs.KeyboardInputs;
import Inputs.MouseInputs;


public class GamePanel extends JPanel{
	
	
	private BufferedImage food;
	private BufferedImage image;
	private BufferedImage image2;
	private BufferedImage image3;
	private BufferedImage image4;
	private BufferedImage restartButton;


	
	private BufferedImage[] buttons;
	private static BufferedImage[] catsAnimation;
	private static BufferedImage[] backgrounds;
	public int startIndex;
	public int cancelIndex=2;
	public int resetIndex=4;
	
	
	protected final static  float X = 1400;
	protected final static float Y = 550;//467;




	public static int level;
	public static int nbLevels = 5;
	
	protected static  int foodLevel;

	
	Image imageResized; 


	public static Obstacles  obstacles;
	public Cat cat;
	public Furniture furniture;
	protected static Treats treats;
	private MouseInputs mouseInputs;
	
	public GamePanel() {
		
		cat = new Cat();
		furniture = new Furniture();
		treats = new Treats();
		
		setPanelSize();
	
		importImage();
		loadAnimations();
		
		mouseInputs = new MouseInputs(this);
		addKeyListener(new KeyboardInputs(this));
		addMouseListener(mouseInputs);
		addMouseMotionListener(mouseInputs);
		
	}
	
	private void loadAnimations() {
		catsAnimation = new BufferedImage[12];
		catsAnimation[0]=image2.getSubimage(0, 0, 149, 236);
		for(int i = 1 ; i < 6; i++){
			catsAnimation[i] = image.getSubimage((i-1)*229+2, 0, 230, 242);
		}
		for(int i = 6 ; i < catsAnimation.length - 1; i++){
			catsAnimation[i] = image3.getSubimage((i-6)*229+2, 0, 230, 242);
		}
		catsAnimation[catsAnimation.length-1] = image4.getSubimage(0,0,242,214);
		
		
	}

	private void importImage() {
		//BufferedImage bufferedImage;
		try {
			
			buttons = new BufferedImage[7];
			BufferedImage auxiliar;
			food = ImageIO.read(new File("Images/catfood.png"));
			buttons[0] = ImageIO.read(new File("Images/startbutton.png"));
			buttons[1] = ImageIO.read(new File("Images/startbuttonPressed.png"));
			buttons[2] = ImageIO.read(new File("Images/cancelbutton.png"));
			buttons[3] = ImageIO.read(new File("Images/cancelButtonColor.png"));
			buttons[4] = ImageIO.read(new File("Images/restartButtonFinal.png"));
			buttons[5] = ImageIO.read(new File("Images/restartButtonFinalColor.png"));


			restartButton = ImageIO.read(new File("Images/restartButton.png"));
			image = ImageIO.read(new File("Images/cats_left.png"));
			image2 = ImageIO.read(new File("Images/sitting_cat2.png"));
			image3 = ImageIO.read(new File("Images/cats_right.png"));
			image4 = ImageIO.read(new File("Images/jumping_cat.png"));
			
			
			
			backgrounds = new BufferedImage[5];
			backgrounds[0] = ImageIO.read(new File("Images/startBackground.png"));
			backgrounds[1] = ImageIO.read(new File("Images/livingroom.png"));
			backgrounds[2] = ImageIO.read(new File("Images/background_l2.png"));
			backgrounds[3] = ImageIO.read(new File("Images/background_l3.png"));
			backgrounds[4] = ImageIO.read(new File("Images/startBackground.png"));

			//imageBackground = ImageIO.read(new File("Images/livingroom.png"));
			
			//imageResized = image.getScaledInstance(50, 50, Image.SCALE_DEFAULT);

			
			
			furniture.importImage();
			treats.importImage();

			
			
		}catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void setPanelSize() {
		Dimension size = new Dimension((int)X,(int)Y);
		setMinimumSize(size);
		setPreferredSize(size);
		setMaximumSize(size);
	    obstacles = new Obstacles(X,Y);
	    obstacles.MarkOccupancyInMap(0, 0, (int)X, (int)Y, 0);
	    
	    obstacles.MarkOccupancyInMap(0,(int)Y-10,(int)X,10,1);  

	}


	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		
		Font myFont = new Font ("Courier New", 1, 30);
		g.setFont (myFont);
		cat.updateAnimationTick();
		
		g.drawImage(backgrounds[level],0,0,null);
		
		
		
		if(level == 0) {
			
			obstacles.MarkOccupancyInMap((int)X/2 - 63, (int)Y/2,150,63,0);		
			obstacles.MarkOccupancyInMap((int)X/2 - 63, (int)Y/2+80,150,63,0);	

			obstacles.MarkOccupancyInMap((int)X/2 - 63, (int)Y/2,150,63,1);		
			g.drawImage(buttons[startIndex],(int)X/2 - 63, (int)Y/2,null);
			
			furniture.eraseFurniture(obstacles);
			cat.setNX(cat.catXL);
			cat.setNY(cat.catY);

		}
		else if(level == 4) {
			furniture.eraseFurniture(obstacles);

			//g.drawImage(cancelbutton,(int)X/2 - 63, (int)Y/2,null);
			g.drawImage(buttons[cancelIndex],(int)X/2 - 63, (int)Y/2,null);
			obstacles.MarkOccupancyInMap((int)X/2 - 63, (int)Y/2,150,63,2);	
			
			g.drawImage(buttons[resetIndex],(int)X/2 - 63, (int)Y/2+80,null);
			obstacles.MarkOccupancyInMap((int)X/2 - 63, (int)Y/2+80,150,63,3);	
			
			cat.setNX(cat.catXL);
			cat.setNY(cat.catY);
			treats.initEatenTreats();
			foodLevel = 0;

			
			
		}
		else {
			
			obstacles.MarkOccupancyInMap((int)X/2 - 63, (int)Y/2,150,63,0);		
			obstacles.MarkOccupancyInMap((int)X/2 - 63, (int)Y/2+80,150,63,0);		
			obstacles.MarkOccupancyInMap((int)X/2 - 63, (int)Y/2,150,63,0);		
			
			g.drawImage(food, 10, 35,null);
			String res = Integer.toString(foodLevel);
			g.drawString(res,70,70);
			res = "Level "+ Integer.toString(level);
			g.drawString(res,10, 25);
			g.drawImage(restartButton, 10, 90,null);
			obstacles.MarkOccupancyInMap(10,90,50,50,-1);
			
			furniture.paintFurniture(g,obstacles);
			//treats.initEatenTreats();
			treats.paintTreats(g, obstacles);
			
	
			g.drawImage(catsAnimation[cat.animationIndex], (int)cat.getNX(), (int)cat.getNY(),null);
		}
		
	}




	public static int getCatAnimationLength(){
	return catsAnimation.length;
}

}
