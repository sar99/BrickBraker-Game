package brickBraker;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class MapGenerator {
	private int map[][];
	private int brickWidth;
	private int brickHeight;
	
	
	public MapGenerator(int row, int col) {
		map = new int[row][col];
		for(int i = 0;i < map.length;i++)
		{
			for(int j = 0;j < map[0].length;j++)
			{
				map[i][j]=1;
			}
		}
		
		brickWidth = 540/col;
		brickHeight = 150/row;
	}
	
	public void draw(Graphics2D g)
	{
		for(int i = 0;i < map.length;i++)
		{
			for(int j = 0;j < map[0].length;j++)
			{
				if(map[i][j]==1)
				{
					g.setColor(Color.white);
					g.fillRect(j*brickWidth + 80, i*brickHeight + 50, brickWidth-3, brickHeight-3);
				}
			}
		}
	}
	
	
	public void setBrickValue(int value, int row, int col)
	{
		map[row][col]=value;
	}
	
	public int getBrickValue(int row, int col)
	{
		return map[row][col];
	}
	public int getBrickWidth()
	{
		return brickWidth;
	}
	public int getBrickHeight()
	{
		return brickHeight;
	}
	public int getRows()
	{
		return map.length;
	}
	public int getCol()
	{
		return map[0].length;
	}
	
}
