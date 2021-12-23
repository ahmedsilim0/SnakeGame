import java.util.ArrayList;
import java.util.List;
import java.awt.Graphics;
import java.awt.Color;

public class Snake {

	private List<Point> snakePoints;
	private int xDir, yDir;
	private boolean isMoving, increaseOrNot;
	private final int STARTSIZE = 20, STARTX = 150, STARTY = 150;

	Snake() {
		snakePoints = new ArrayList<Point>();
		xDir = 0;
		yDir = 0;
		isMoving = false;
		increaseOrNot = false;
		snakePoints.add(new Point(STARTX, STARTY));
		for (int i = 1; i < STARTSIZE; ++i) {
			snakePoints.add(new Point(STARTX - i * 4, STARTY));
		}
	}

	public void draw(Graphics g) {
		g.setColor(Color.white);
		for (Point p : snakePoints) {
			g.fillRect(p.getX(), p.getY(), 4, 4);
		}
	}

	public boolean isIncreaseOrNot() {
		return increaseOrNot;
	}

	public void setIncreaseOrNot(boolean increaseOrNot) {
		this.increaseOrNot = increaseOrNot;
	}

	public void move() {
		if (isMoving) {
			Point oldStartPos = snakePoints.get(0);
			Point oldLastPos = snakePoints.get(snakePoints.size() - 1);
			Point newStartPos = new Point(oldStartPos.getX() + xDir * 4, oldStartPos.getY() + yDir * 4);
			for (int i = snakePoints.size() - 1; i >= 1; --i) {
				snakePoints.set(i, snakePoints.get(i - 1));
			}
			if(increaseOrNot) {
				snakePoints.add(oldLastPos);
				increaseOrNot=false;
			}
			snakePoints.set(0, newStartPos);
			// Point newLastPos = new Point(oldLastPos.getX() + xDir * 4, oldLastPos.getY()
			// + yDir * 4);
		}
	}

	public boolean snakeCollision() {
		int x = this.getX();
		int y = this.getY();
		for (int i = 1; i < snakePoints.size(); ++i) {
			if (snakePoints.get(i).getX() == x && snakePoints.get(i).getY() == y) {
				return true;
			}
		}
		return false;
	}

	public boolean isMoving() {
		return isMoving;
	}

	public void setMoving(boolean isMoving) {
		this.isMoving = isMoving;
	}

	public int getXDir() {
		return xDir;
	}

	public void setXDir(int xDir) {
		this.xDir = xDir;
	}

	public int getYDir() {
		return yDir;
	}

	public void setYDir(int yDir) {
		this.yDir = yDir;
	}

	// get position of head of snake
	public int getX() {
		return snakePoints.get(0).getX();
	}

	public int getY() {
		return snakePoints.get(0).getY();
	}

}
