import java.awt.*;

public class Zombie {
    private int x, y, size = 30, speed = 1;

    public Zombie(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void update(Player player) {
        double angle = Math.atan2(player.getY() - y, player.getX() - x);
        x += (int) (Math.cos(angle) * speed);
        y += (int) (Math.sin(angle) * speed);
    }

    public void draw(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillOval(x - size / 2, y - size / 2, size, size);
    }

    public boolean collidesWith(Player player) {
        int dist = (int) Math.hypot(player.getX() - x, player.getY() - y);
        return dist < (size + player.getSize()) / 2;
    }

    public boolean collidesWith(Bullet b) {
        Rectangle r = new Rectangle(x - size / 2, y - size / 2, size, size);
        return r.intersects(b.getBounds());
    }
}
