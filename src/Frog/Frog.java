package Frog;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Frog {
	BufferedImage image;
	int x,y;
	int width,height;
	int size;//the size of frog which is used to do crash checking
	double g;
	double t;
	double v0;
	double speed;
	double s;
	double alpha;
	public Frog() throws Exception{
		image = ImageIO.read(getClass().getResource("frog.png"));
		width = image.getWidth();
		height = image.getHeight();
		x = 132;
		y = 280;
		size = 40;
		
		g = 4;
		v0 = 20;
		t = 0.25;
		speed = v0;
		s = 0;
		alpha = 0;
	}
	public void step(){
		double v0 = speed;
		s = v0*t + g*t*t/2;
		y = y - (int) s;
		double v = v0 - g*t;
		speed = v;
		
		alpha = Math.atan(s/8);
	}
	public void jump(){
		speed = v0;
	}
	public boolean hit(Ground ground){
		boolean hit = y+size/2>ground.y;
		if(hit){
			y = ground.y -size/2;
			alpha = -3.14159265358/2;
		}
		return hit;
	}
	public boolean hit(Column column){
		if(x>column.x-column.width/2-size/2&&x<column.x+column.width/2+size/2){
			if(y>column.y-column.gap/2+size/2&&y<column.y+column.gap/2-size/2){
				return false;
			}
			return true;
			
		}
		return false;
	}

}
