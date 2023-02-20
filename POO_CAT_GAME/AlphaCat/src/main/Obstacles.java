package main;

public class Obstacles {
	
	
	private int obstacleMap[][];
	private float Y,X;
	
	public Obstacles(float x, float y)
	{
		X = x;
		Y = y;
		obstacleMap = new int[(int)X][(int) Y];
		
	
	}
	
	private boolean InMap(int x, int y)
	{
		if ( x < 0 || x > X || y < 0 || y > Y){
			return false;
		}
		return true;
	}
	public void MarkOccupancyInMap(int x, int y, int dimX, int dimY, int value)
	{
		if(!InMap(x,y) || !InMap(x+dimX, y+dimY)) {
			System.out.println("nu se poateeeee");
			return;
		}
		for(int i = x ; i < x + dimX; i++){
			for(int j = y ; j  < y + dimY ; j++)
				obstacleMap[i][j] = value;
		}
		
	}
	
	public int getValueFromMap(int x, int y)
	{
		if(!InMap(x,y))
		{
			
			return 0;
		}
		return obstacleMap[x][y];
	}

}
