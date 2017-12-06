package Frog;

import java.awt.image.BufferedImage;
import java.util.Random;

import javax.imageio.ImageIO;

public class birthcake {
	BufferedImage image;
	int x,y;
	int width,height;
	private int speed=2;
	Random random = new Random();
	public birthcake(int n) throws Exception{
		image = ImageIO.read(getClass().getResource("birthcake.png"));
		width = image.getWidth();
		height = image.getHeight();
		
		y = 200;
		x = random.nextInt(FrogGame.WIDTH-width);
	}
	public void drop(){
		y+=speed;
	}
	

}
