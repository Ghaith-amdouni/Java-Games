import java.awt.*;

public class Bullet {
    private double x, y, dx, dy;
    private final int speed = 10;
    private final int size = 6;
    private int life = 100;

    public Bullet(int startX, int startY, Point target) {
        x = startX;
        y = startY;
        double angle = Math.atan2(target.y - y, target.x - x);
        dx = Math.cos(angle) * speed;
        dy = Math.sin(angle) * speed;
    }

    public void update() {
        x += dx;
        y += dy;
        life--;
    }

    public void draw(Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillOval((int) x - size / 2, (int) y - size / 2, size, size);
    }

    public boolean isAlive() {
        return life > 0;
    }

    public Rectangle getBounds() {
        return new Rectangle((int) x - size / 2, (int) y - size / 2, size, size);
    }
}
