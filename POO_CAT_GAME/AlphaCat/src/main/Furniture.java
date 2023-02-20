package main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;



public class Furniture {
	
	
	protected static BufferedImage[] furniture;

	protected void importImage()
	{
		try {
		furniture = new BufferedImage[5];
		furniture[0] = ImageIO.read(new File("Images/bucatarie4 .png"));
		furniture[1] = ImageIO.read(new File("Images/bucatarie3RE.png"));
		furniture[2] = ImageIO.read(new File("Images/bucatarie1.png"));
		furniture[3] = ImageIO.read(new File("Images/wardrobe.png"));
		furniture[4] = ImageIO.read(new File("Images/table.png"));

		}catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	protected void paintFurniture(Graphics g, Obstacles obstacles)
	{
		
		
		switch(GamePanel.level) {
		
		case 1:
			break;
			
		case 2:
			obstacles.MarkOccupancyInMap(350,390,360,150,0);
			obstacles.MarkOccupancyInMap(710,200,140,300,0);
			
			g.drawImage(furniture[0],700,190,null);
			obstacles.MarkOccupancyInMap(700,190,200,350,1);
			
			g.drawImage(furniture[1],570,340,null);
			obstacles.MarkOccupancyInMap(570,340,150,200,1);
			
			g.drawImage(furniture[2],920,200,null);
			obstacles.MarkOccupancyInMap(920,200,150,90,1);
			
			break;
		
		case 3:
			obstacles.MarkOccupancyInMap(700,190,200,350,0);
			obstacles.MarkOccupancyInMap(570,340,150,200,0);
			obstacles.MarkOccupancyInMap(920,200,150,90,0);


			g.drawImage(furniture[4],350,350,null);
			obstacles.MarkOccupancyInMap(350,390,360,150,1);
			
			g.drawImage(furniture[3],710,200,null);
			obstacles.MarkOccupancyInMap(710,200,140,300,1);
			
		
		}
	}
	
	public void eraseFurniture(Obstacles obstacles)
	{
		

			obstacles.MarkOccupancyInMap(700,190,200,350,0);	
			obstacles.MarkOccupancyInMap(570,340,150,200,0);
			obstacles.MarkOccupancyInMap(920,200,150,90,0);

			obstacles.MarkOccupancyInMap(350,390,360,150,0);
			obstacles.MarkOccupancyInMap(710,200,140,300,0);

	}
	

}
