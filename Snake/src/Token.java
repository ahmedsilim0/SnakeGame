import java.awt.Graphics;
import java.awt.Color;

public class Token {
	private int x, y, score;
	private Snake snake;

	public Token(Snake s) {
		x = (int) (Math.random() * 395); // we want random number between 0 and 394
		y = (int) (Math.random() * 395); // we want random number between 0 and 394
		snake = s;
	}

	public void createNewPos() { // every time that snake hit the token
		x = (int) (Math.random() * 395);
		y = (int) (Math.random() * 395);
	}

	public int getScore() {
		return score;
	}

	public void draw(Graphics g) {
		g.setColor(Color.green);
		g.fillRect(x, y, 6, 6);
	}

	public boolean snakeCollision() {
		int snakeX = snake.getX() + 2;
		int snakeY = snake.getY() + 2;
		if (snakeX > x - 1 && snakeX <= (x + 7)) {
			if (snakeY >= y - 1 && snakeY <= (y + 7)) {
				createNewPos();
				score++;
				snake.setIncreaseOrNot(true);
				return true;
			}
		}
		return false;
	}
}
