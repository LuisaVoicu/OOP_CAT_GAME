package Inputs;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import main.GamePanel;

public class MouseInputs implements MouseListener, MouseMotionListener{

	private GamePanel gamePanel;
	public MouseInputs(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		//System.out.println(e.getX()+ " "+ e.getY()+" "+gamePanel.obstacles.getValueFromMap(e.getX(),e.getY()));
		if(gamePanel.obstacles.getValueFromMap(e.getX(),e.getY()) == 1 && gamePanel.level == 0)
		{
			gamePanel.startIndex=1;
		}
		else
		{
			gamePanel.startIndex=0;

		}
		
		
		if(gamePanel.obstacles.getValueFromMap(e.getX(),e.getY()) == 2 && gamePanel.level == 4)
		{
			gamePanel.cancelIndex=3;
		}
		else
		{
			gamePanel.cancelIndex=2;

		}
		
		
	
		if(gamePanel.obstacles.getValueFromMap(e.getX(),e.getY()) == 3 && gamePanel.level == 4)
		{
			gamePanel.resetIndex=5;
		}
		else
		{
			gamePanel.resetIndex=4;

		}
		
		
		
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println(e.getX()+ " "+ e.getY()+" "+gamePanel.obstacles.getValueFromMap(e.getX(),e.getY()));
		if(gamePanel.obstacles.getValueFromMap(e.getX(),e.getY()) == 1 && 	gamePanel.level == 0){
			gamePanel.level = 1;
		}
		if(gamePanel.obstacles.getValueFromMap(e.getX(),e.getY()) == 3 && 	gamePanel.level == 4){
			gamePanel.level = 1;
			gamePanel.furniture.eraseFurniture(gamePanel.obstacles);
			gamePanel.cat.setNX(gamePanel.cat.catXL);
			gamePanel.cat.setNY(gamePanel.cat.catY);
		}
		else if(gamePanel.obstacles.getValueFromMap(e.getX(),e.getY()) == 2 && 	gamePanel.level == 4){
			gamePanel.level = 0;
		}
		
		
		else if(gamePanel.obstacles.getValueFromMap(e.getX(),e.getY()) == -1 ){
			gamePanel.level = 0;
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
