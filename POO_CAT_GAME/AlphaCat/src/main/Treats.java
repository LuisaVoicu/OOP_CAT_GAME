package main;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;



public class Treats {
	
	
	
	private static BufferedImage[] treats;
	protected static boolean[] eatenTreats;
	private int[] height;
	private static int width = 80;
	public int treatsIndex;
	public static boolean countOnce;
	public static int index = -1;
	
	
	protected void importImage()
	{
		try {
			
			treats = new BufferedImage[5];
			eatenTreats = new boolean[5];
			height = new int[5];
			
			treats[0] = ImageIO.read(new File("Images/catfood2.png"));
			treats[1] = ImageIO.read(new File("Images/pizza.png"));
			treats[2] = ImageIO.read(new File("Images/chicken.png"));
			treats[3] = ImageIO.read(new File("Images/hamburger.png"));
			treats[4] = ImageIO.read(new File("Images/steak.png"));

			height[0] = 57;
			height[1] = 63;
			height[2] = 79;
			height[3] = 80;
			height[4] = 45;
			
			for(int i = 0 ; i  < eatenTreats.length; i++)
				eatenTreats[i] = false;

			
		}catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	protected void paintTreats(Graphics g, Obstacles obstacles)
	{

		switch(GamePanel.level) {
		
		case 1:
	
			createTreat(g,obstacles,0,700,450);
			break;
			
		case 2:
			createTreat(g,obstacles,2,1000,130);
			createTreat(g,obstacles,4,800,160);
			break;
			
		case 3:
			createTreat(g,obstacles,1,530,330);
			createTreat(g,obstacles,3,850,160);

			break;
			
		}
		
	}
	
	private void createTreat(Graphics g, Obstacles obstacles, int index, int x, int y)
	{
		if(eatenTreats[index] == false)
		{
		g.drawImage(treats[index],x,y, null);
		obstacles.MarkOccupancyInMap(x,y,width,height[index],index+2);
		}
		else
		{
			if(countOnce == false)
			{
			GamePanel.foodLevel+=5;
			countOnce = true;
			}
			obstacles.MarkOccupancyInMap(x,y,width,height[index],0);

		}
	}
	
	protected void initEatenTreats()
	{
		for(int i = 0 ; i  < eatenTreats.length; i++)
			eatenTreats[i] = false;
	}
	

}
