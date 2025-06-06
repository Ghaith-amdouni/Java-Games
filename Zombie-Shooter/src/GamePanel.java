// GamePanel.java
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener {
    public static final int WIDTH = 800, HEIGHT = 600;

    private Timer timer;
    private Player player;
    private ArrayList<Zombie> zombies;
    private ArrayList<Bullet> bullets;
    private Random random = new Random();
    private int score = 0;

    public GamePanel() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.DARK_GRAY);
        setFocusable(true);
        addKeyListener(new KeyHandler());
        addMouseListener(new MouseHandler());
        addMouseMotionListener(new MouseMotionHandler());

        player = new Player(WIDTH / 2, HEIGHT / 2);
        zombies = new ArrayList<>();
        bullets = new ArrayList<>();
    }

    public void startGame() {
        timer = new Timer(16, this);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        player.update();

        if (random.nextInt(100) < 2) {
            zombies.add(new Zombie(random.nextInt(WIDTH), random.nextInt(HEIGHT)));
        }

        for (Zombie z : zombies) {
            z.update(player);
        }

        Iterator<Bullet> it = bullets.iterator();
        while (it.hasNext()) {
            Bullet b = it.next();
            b.update();
            if (!b.isAlive()) it.remove();
        }

        checkCollisions();
        repaint();
    }

    private void checkCollisions() {
        Iterator<Zombie> zit = zombies.iterator();
        while (zit.hasNext()) {
            Zombie z = zit.next();
            if (z.collidesWith(player)) {
                player.takeDamage();
            }
            for (Bullet b : bullets) {
                if (z.collidesWith(b)) {
                    zit.remove();
                    score++;
                    break;
                }
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        player.draw(g);

        for (Zombie z : zombies) {
            z.draw(g);
        }

        for (Bullet b : bullets) {
            b.draw(g);
        }

        g.setColor(Color.WHITE);
        g.drawString("Health: " + player.getHealth(), 10, 20);
        g.drawString("Score: " + score, 10, 40);
    }

    private class KeyHandler extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            player.keyPressed(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            player.keyReleased(e);
        }
    }

    private class MouseHandler extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            player.shoot(e.getPoint(), bullets);
        }
    }

    private class MouseMotionHandler extends MouseMotionAdapter {
        @Override
        public void mouseMoved(MouseEvent e) {
            player.setMousePosition(e.getPoint());
        }
    }
}
