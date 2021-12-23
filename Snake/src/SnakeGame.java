import java.applet.Applet;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Image;

import javax.swing.event.MenuKeyEvent;
import javax.swing.event.MenuKeyListener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SnakeGame extends Applet implements Runnable, KeyListener {

	Graphics gfx;
	Image img;
	Thread thread;
	Snake snake;
	boolean gameOver;
	Token token;

	public void init() {
		this.resize(400, 400);
		gameOver = false;
		img = createImage(400, 400);
		gfx = img.getGraphics();
		this.addKeyListener(this);
		snake = new Snake();
		token = new Token(snake);
		thread = new Thread(this);
		thread.start();
	}

	public void paint(Graphics g) {
		gfx.setColor(Color.black);
		gfx.fillRect(0, 0, 400, 400);
		if (!gameOver) {
			snake.draw(gfx);
			token.draw(gfx);
		} else {
			gfx.setColor(Color.red);
			gfx.drawString("Game Over", 180, 150);
			gfx.drawString("your score " + token.getScore(), 180, 170);
		}

		g.drawImage(img, 0, 0, null);
	}

	public void update(Graphics g) {
		paint(g);
	}

	public void repaint(Graphics g) {
		update(g);
	}

	public void run() {
		for (;;) {
			try {
				if (!gameOver) {
					snake.move();
					this.checkGameOver();
					token.snakeCollision();
				}

				this.repaint();
				if (token.getScore() < 5) {
					Thread.sleep(40);
				} else if (token.getScore() > 5 && token.getScore() < 10) {
					Thread.sleep(30);
				} else if (token.getScore() > 10 && token.getScore() < 12) {
					Thread.sleep(20);
				} else {
					Thread.sleep(10);
				}

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public void checkGameOver() {
		if (snake.getX() < 0 || snake.getX() > 396) {
			gameOver = true;
		}
		if (snake.getY() < 0 || snake.getY() > 396) {
			gameOver = true;
		}
		if (snake.snakeCollision()) {
			gameOver = true;
		}
	}

	public void keyPressed(KeyEvent e) {
		if (!snake.isMoving()) {
			if (e.getKeyCode() == MenuKeyEvent.VK_UP || e.getKeyCode() == MenuKeyEvent.VK_DOWN
					|| e.getKeyCode() == MenuKeyEvent.VK_RIGHT) {
				snake.setMoving(true);
			}
		}
		if (e.getKeyCode() == MenuKeyEvent.VK_UP) {
			if (snake.getYDir() != 1) {
				snake.setYDir(-1);
				snake.setXDir(0);
			}
		}
		if (e.getKeyCode() == MenuKeyEvent.VK_DOWN) {
			if (snake.getYDir() != -1) {
				snake.setYDir(1);
				snake.setXDir(0);
			}
		}
		if (e.getKeyCode() == MenuKeyEvent.VK_LEFT) {
			if (snake.getXDir() != 1) {
				snake.setXDir(-1);
				snake.setYDir(0);
			}
		}
		if (e.getKeyCode() == MenuKeyEvent.VK_RIGHT) {
			if (snake.getXDir() != -1) {
				snake.setXDir(1);
				snake.setYDir(0);
			}
		}

	}

	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
