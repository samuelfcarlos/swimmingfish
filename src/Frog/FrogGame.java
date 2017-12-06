package Frog;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class FrogGame extends JPanel{
	Frog frog;
	Column column1,column2;
	Ground ground;
	BufferedImage background;
	//birthcake birthcake[];
	
	//boolean gameOver;
	
	int state;
	public static final int START=0;
	public static final int RUNNING =1;
	public static final int GAME_OVER=2;
	
	BufferedImage gameOverImage;
	BufferedImage startImage;
	
	int score;
	
	public FrogGame() throws Exception{
		state=START;
		//gameOver = false;
		startImage=ImageIO.read(getClass().getResource("start.png"));
		gameOverImage = ImageIO.read(getClass().getResource("gameover.png"));
		frog = new Frog();
		column1 = new Column(1);
		column2 = new Column(2);
		ground = new Ground();
		background = ImageIO.read(getClass().getResource("4.png"));
		//birthcake =new birthcake[10];
	}

	public static void main(String[] args) throws Exception {
		JFrame frame = new JFrame();
		FrogGame game = new FrogGame();
		frame.add(game);
		frame.setSize(440, 670);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		game.action();

	}
	public void action() throws Exception{
		MouseListener l = new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				//frog.jump();
				try{
					switch(state){
						case GAME_OVER:
							column1 = new Column(1);
							column2 = new Column(2);
							frog = new Frog();
							score = 0;
							state = START;
							break;
						case START:
							state = RUNNING;
						case RUNNING:
							frog.jump();
					}
				}catch(Exception ex){
					ex.printStackTrace();
				}
			}
		};
		addMouseListener(l);
		while(true){
			/*if(!gameOver){
				ground.step();
			column1.step();
			column2.step();
			frog.step();
			}
			if(frog.hit(ground)||frog.hit(column1)||frog.hit(column2)){
				gameOver = true;
			}
			if(frog.x==column1.x||frog.x==column2.x){
				score++;
			}*/
			switch(state){
					case START:
						ground.step();
						break;
					case RUNNING:
						column1.step();
						column2.step();
						frog.step();
						ground.step();
						/*for(int i =0;i<birthcake.length;i++){
							birthcake b =birthcake[i];
							b.drop();
						}
						*/
						if(frog.x==column1.x||frog.x==column2.x){
							score++;
						}
						if(frog.hit(ground)||frog.hit(column1)||frog.hit(column2)){
							state = GAME_OVER;
						}
						break;
			}
			repaint();
			Thread.sleep(1000/30);
		}
	}

	public void paint(Graphics g){
		g.drawImage(background,0,0,null);
		g.drawImage(column1.image, column1.x-column1.width/2,column1.y-column1.height/2, null);
		g.drawImage(column2.image, column2.x-column2.width/2,column2.y-column2.height/2, null);
		g.drawImage(ground.image, ground.x, ground.y, null);
		g.drawImage(frog.image,frog.x-frog.width/2,frog.y-frog.height/2,null);
		//g.drawImage(birthcake[1].image, birthcake[1].x-birthcake[1].width/2, birthcake[1].y-birthcake[1].height/2, null);
		Graphics2D g2 = (Graphics2D) g;
		g2.rotate(-frog.alpha,frog.x,frog.y);
		g2.rotate(frog.alpha, frog.x, frog.y);
		
		Font f = new Font(Font.SANS_SERIF,Font.BOLD,20);
		g.setFont(f);
		g.setColor(Color.WHITE);
		g.drawString("score:"+score, 20 , 50 );
		g.setColor(Color.ORANGE);
		g.drawString("邱小瑜生日快乐哦", 20, 30);
		
		
		//if(gameOver){
		//	g.drawImage(gameOverImage, 0, 0, null);
		//}
		switch(state){
		case GAME_OVER:
			 g.drawImage(gameOverImage, 0, 0, null);
			 break;
		case START:
			 g.drawImage(startImage, 0, 0, null);
			 break;
		
		}
	}

}
