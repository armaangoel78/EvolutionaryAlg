import java.awt.Rectangle;

public class Player {
	private int x;
	private int y;
	private final int WIDTH = 50;
	private final int HEIGHT = 50;
	
	public Player() {
		
	}
	
	public Player(int x, int y) {
		this.x = x;
		this.y = y;
	}

	
	public Rectangle getRect() {
		return new Rectangle(x, y, WIDTH, HEIGHT);
	}
	
	public void updateRect(int adjX, int adjY) {
		x += adjX;
		y += adjY;
	}
	
}
