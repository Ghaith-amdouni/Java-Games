import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Player {
    private int x, y, speed = 4, size = 30;
    private int health = 100;
    private boolean up, down, left, right;
    private Point mousePos = new Point(0, 0);

    public Player(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void update() {
        if (up) y -= speed;
        if (down) y += speed;
        if (left) x -= speed;
        if (right) x += speed;
    }

    public void draw(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillOval(x - size / 2, y - size / 2, size, size);
        g.setColor(Color.WHITE);
        g.drawLine(x, y, mousePos.x, mousePos.y);
    }

    public void setMousePosition(Point p) {
        this.mousePos = p;
    }

    public void shoot(Point target, ArrayList<Bullet> bullets) {
        bullets.add(new Bullet(x, y, target));
    }

    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_Z-> up = true;
            case KeyEvent.VK_S -> down = true;
            case KeyEvent.VK_Q -> left = true;
            case KeyEvent.VK_D -> right = true;
        }
    }

    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_Z-> up = false;
            case KeyEvent.VK_S -> down = false;
            case KeyEvent.VK_Q-> left = false;
            case KeyEvent.VK_D -> right = false;
        }
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public int getSize() { return size; }
    public int getHealth() { return health; }

    public void takeDamage() {
        health -= 1;
    }
}
