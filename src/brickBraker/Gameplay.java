package brickBraker;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Gameplay extends JPanel implements KeyListener, ActionListener {

	
	private boolean play = false;
	private int score = 0;
	
	private int totalBricks = 21;
	
	private Timer timer;
	private int delay = 8;
	
	private int playerX = 310;
	
	private int ballposX = 120;
	private int ballposY = 350;
	private int ballXdir = getRandomDirection() * getRandomSpeed();
	private int ballYdir = getRandomDirection() * getRandomSpeed();
	private MapGenerator map;
	
	
	
	public int getRandomDirection() {
		int rand = (int)(Math.random() * 2);
		if(rand ==1)
			return 1;
		else 
			return -1;
	}
	
	public int getRandomSpeed() {
		int rand = (int)(Math.random() * 2);
		if(rand ==1)
			return 1;
		else 
			return 2;
	}
	
	
	public Gameplay() {
		map = new MapGenerator(3, 7);
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(delay, this);
		timer.start();
		
	}
	
	public void paint(Graphics g)
	{
		g.setColor(Color.black);
		g.fillRect(1, 1, 692, 592);
		
		map.draw((Graphics2D)g);
		
		g.setColor(Color.yellow);
		g.fillRect(0,  0,  3,  592);
		g.fillRect(0,  0,  692,  3);
		g.fillRect(691,  0,  3,  592);
		
		g.setColor(Color.white);
		g.setFont(new Font("serif", Font.BOLD, 25));
		g.drawString(""+score, 590, 30);
		
		
		g.setColor(Color.green);
		g.fillRect(playerX, 550, 100, 8);
		
		g.setColor(Color.yellow);
		g.fillOval(ballposX, ballposY, 20, 20);
		
		if(totalBricks <=0)
		{
			play = false;
			ballXdir = 0;
			ballYdir = 0;
			g.setColor(Color.red);
			g.setFont(new Font("serif", Font.BOLD, 30));
			g.drawString("YOU WON!! ", 210, 300);
			
			g.setFont(new Font("serif", Font.BOLD, 20));
			g.drawString("Press Enter to Restart", 230, 350);
		}
		
		
		if(ballposY>570)
		{
			play = false;
			ballXdir = 0;
			ballYdir = 0;
			g.setColor(Color.red);
			g.setFont(new Font("serif", Font.BOLD, 30));
			g.drawString("GAME OVER! Score: "+score, 190, 300);
			
			g.setFont(new Font("serif", Font.BOLD, 20));
			g.drawString("Press Enter to Restart", 230, 350);
			
		}
		g.dispose();
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		timer.start();
		
		
		if(play) {
			if(new Rectangle(ballposX, ballposY, 20, 20).intersects(new Rectangle(playerX, 550, 100, 8))) {
				ballYdir = - ballYdir;
			}
			
			 for(int i = 0; i<map.getRows(); i++)
			{
				for(int j=0;j<map.getCol();j++)
				{
					if(map.getBrickValue(i, j)==1)
					{
						int brickX = j * map.getBrickWidth() + 80;
						int brickY = i * map.getBrickHeight() + 50;
						int brickWidth = map.getBrickWidth();
						int brickHeight = map.getBrickHeight();
						
						Rectangle rect = new Rectangle(brickX, brickY, brickWidth, brickHeight);
						Rectangle ballRect = new Rectangle(ballposX, ballposY, 20, 20);
						Rectangle brickRect = rect;
						
						if(rect.intersects(ballRect))
						{
							map.setBrickValue(0,  i,  j);
							totalBricks--;
							score+=5;
							
							if(ballposX + 19 <= brickRect.x || ballposX + 1 >= brickRect.x + brickRect.width)
							{
								ballXdir = -ballXdir;
							}
							else
							{
								ballYdir = -ballYdir;
							}
						}
							
					}
				}
			}
					
				ballposX += ballXdir;
				ballposY += ballYdir;
				
				
				if(ballposX < 0) {
					ballXdir = -ballXdir;
				}
				if(ballposY < 0) {
					ballYdir = -ballYdir;
				}
				if(ballposX > 670) {
					ballXdir = -ballXdir;
				}
			
			repaint();
		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_RIGHT)
		{
			if(playerX>=600)
			playerX=600;
			else
				moveRight();
		}
		else if(e.getKeyCode()==KeyEvent.VK_LEFT) {
			if(playerX < 10)
				playerX=10;
				else
					moveLeft();
		}
		if(e.getKeyCode()==KeyEvent.VK_ENTER)
		{
			if(!play)
			{
				play = true;
				playerX = 310;
				
				ballposX = 120;
				ballposY = 350;
				ballXdir = getRandomDirection() * getRandomSpeed();
				ballYdir = getRandomDirection() * getRandomSpeed();
				map = new MapGenerator(3, 7);
				score = 0;
				totalBricks = 21;
				
				repaint();
			}
		}
		
	}

	
	public void moveRight() {
		play = true;
		playerX+=20;
	}
	
	public void moveLeft() {
		play = true;
		playerX-=20;
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
	
		
	}
	
}
