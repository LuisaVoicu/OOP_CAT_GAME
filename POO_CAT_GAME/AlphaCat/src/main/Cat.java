package main;

import Inputs.KeyboardInputs;
import constants.CatEnum;


public class Cat {
	private static int nx;
	private static int ny;
	private static int catL = 242;
	private static int catH = 230;
	
	protected int animationTick;
	protected int animationIndex = 1;
	protected int animationSpeed = 50;

	public final static int catY = 301;
	public final static int catXR = 1200;
	public  final static int catXL = 0;
	
	
	public static boolean fallingMode = false;
	public static boolean jumpingMode = false;
	
	private int countJumpTime = 0;
	private int jumpTime = 3;
	private CatEnum position =  CatEnum.SIT;
	
	public Cat()
	{
		nx=catXL;
		ny=catY;
	}
	
	protected void updateAnimationTick() {
		

	
		 position = KeyboardInputs.getCatPosition();
			//System.out.println("------------------------- JUMPING MODE " + position);

		 //System.out.println(jumpingMode);
		 animationTick++;
		if(animationTick >= animationSpeed) {
			
			animationTick = 0;			
			if(CheckForObstacles(nx, ny+10, catL/2,catH) == 0){
				
				System.out.println("Falling mode!");
				fallingMode = true;
				animationSpeed = 1;
				if(position == CatEnum.WALK_RIGHT)
				{
					animationIndex = 4;
					
				}
				else
				{
					animationIndex = 8;
				}
				changeNY(8);

				
			}
			else {
				System.out.println("checked!");
				
				fallingMode = false;
				animationSpeed = 50;


				if(position == CatEnum.SIT)
				{
					animationIndex = 0;
				}
				else if(position == CatEnum.WALK_RIGHT)
				{
					if(animationIndex < 6) {
						animationIndex = 6;
					}
					animationIndex++;
					if(animationIndex >= GamePanel.getCatAnimationLength()-1) {
						animationIndex = 6;
					}
				}
				else if(position == CatEnum.WALK_LEFT)
				{
						if(animationIndex >= 6 || animationIndex < 1) {
							animationIndex = 1;
						}
						animationIndex++;
						if(animationIndex >= 6) {
							animationIndex = 1;
						}
				}
				else if(position == CatEnum.JUMP)
				{
					
					System.out.println("JUMPING MODE ==================== "+ jumpTime);
			
					//animationIndex = GamePanel.getCatAnimationLength()-1;
					if(animationIndex >= 6 || animationIndex < 1) {
						animationIndex = 1;
					}
					animationIndex++;
					if(animationIndex >= 6) {
						animationIndex = 1;
					}
						jumpingMode = false;
					
				}
			}
		}
	}
	

	private int CheckForObstacles(int x, int y, int dimX, int dimY )
	{
		System.out.println(x+ " "+(x+dimX)+"--"+y+ " "+ (y+dimY));
		for(int i = Math.min(x,x+dimX); i < Math.min(GamePanel.X, Math.max(x,x+dimX)); i++)
			for(int j = Math.min(y,y+dimY); j < Math.min(GamePanel.Y, Math.max(y,y+dimY)); j++){
					int value = GamePanel.obstacles.getValueFromMap(i,j);
					if(value > 0){
						return value;
					
					}
				}
		
		
		return 0;
	}
	
	private void FeedCat(int i)
	{
		GamePanel.treats.eatenTreats[i]=true;
	}
	
	public void changeNX(int value)
	{
		
		if(this.nx + value + catL > GamePanel.X )
		{
			Treats.countOnce = false;
			this.nx = catXL;
			
			if(GamePanel.level + 1 < GamePanel.nbLevels)
			GamePanel.level++;
		}
		else if(this.nx + value + catL < 0)
		{
			this.nx = catXR;
			
			if(GamePanel.level - 1 >= 0)
				GamePanel.level--;

		}
		else
		{
			int result = CheckForObstacles( nx+value, ny, catL/2, catH) ;
			if(value>0 && result== 0) {
				this.nx += value;
			}
			else if(value<0 &&result == 0) {
				this.nx += value;
			}
			
			else {
				
				if(result >1)
				{
					FeedCat(result-2);
				}
			}

		}
	}
	
	public void changeNY(int value)
	{

		if(this.ny + value + catH > GamePanel.Y  || this.ny +catH < 0)
		{
			ny = catY;
			
		}
		else
		{
			int result = CheckForObstacles( nx+value, ny, catL/2, catH) ;
			
			if(value> 0 && result == 0) {
				this.ny += value;
				}
			else if(value < 0 && result == 0){
				this.ny += value;
			}
			else{
				if(result >1)
				{
					FeedCat(result-2);
				}
			}
	
		}
	}
	
	public static float getNX()
	{
		return nx;
	}
	public static float getNY()
	{
		return ny;
	}
	
	public void setNX(int x)
	{
		nx = x;
	}
	public void setNY(int y)
	{
		ny = y;
	}
	public float getCatLength()
	{
		return catL;
	}
	
	public float getCatHeight()
	{
		return catH;
	}

}
