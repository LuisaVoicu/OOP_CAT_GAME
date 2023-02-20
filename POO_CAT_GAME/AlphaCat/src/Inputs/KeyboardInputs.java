package Inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import main.Cat;
import main.GamePanel;
import constants.CatEnum;
public class KeyboardInputs implements KeyListener{

	private static CatEnum position = CatEnum.SIT;
	
	private GamePanel gamePanel;
	
	public KeyboardInputs(GamePanel gamePanel){
		this.gamePanel = gamePanel;
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		// TODO Auto-generated method stub
		if(Cat.fallingMode == false && Cat.jumpingMode==false) {
			System.out.println("hei");
		switch(e.getKeyCode()) {
		case KeyEvent.VK_SPACE:

			Cat.jumpingMode = true;
			position = CatEnum.JUMP;
			gamePanel.cat.changeNY(-250);
			gamePanel.cat.changeNX(+100);
			break;
		
		
		case KeyEvent.VK_A:
			position = CatEnum.WALK_LEFT;
			gamePanel.cat.changeNX(-5);
			break;
			
			
		case KeyEvent.VK_D:
			position = CatEnum.WALK_RIGHT;
			gamePanel.cat.changeNX(+5);
			break;
			
		case KeyEvent.VK_S:
			position = CatEnum.SIT;
			gamePanel.cat.changeNY(0);
			break;
			
	
		 
		}
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

		if(Cat.jumpingMode == false) {
			System.out.println("hellooooooooooo??????????????????");
			position = CatEnum.SIT;
		}
		else
			position = CatEnum.JUMP;

		
		
	}
	
	public static CatEnum getCatPosition()
	{
		return position;
	}

}
