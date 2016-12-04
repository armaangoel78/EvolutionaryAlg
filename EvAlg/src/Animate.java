import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Animate extends Applet implements Runnable{

	private Thread thread;
	private final int WIDTH = 800, HEIGHT = 600;
	private Player p1 = new Player(50, 50);
	private Player p2 = new Player(50, 50);
	private final int FPS = 60;
	
	public void init() {
		this.resize(WIDTH, HEIGHT);
		
		thread = new Thread(this);
		thread.start();
	}
	
	public void paint(Graphics g) {
		
		//background
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		//player1
		g.setColor(Color.cyan);
		g.fillRect(p1.getRect().x, p1.getRect().y, p1.getRect().width, p1.getRect().height); 
		
		//player2
		g.setColor(Color.green);
		g.fillRect(p2.getRect().x, p2.getRect().y, p2.getRect().width, p2.getRect().height); 
	}
	
	public void update(Graphics g) { 
		paint(g);
	}

	
	@Override
	public void run() {
		long startTime = System.currentTimeMillis();
		while (true) {
			if (System.currentTimeMillis() - startTime >= FPS/10) { //Gameloop
				
				repaint();
				p1.updateRect(1, 1);
				
				startTime = System.currentTimeMillis();
			}
		}
		
	}

}
