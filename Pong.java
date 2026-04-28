import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Pong extends JPanel implements ActionListener, KeyListener {

    private int ballX = 390, ballY = 290;
    private int ballXDelta = 4, ballYDelta = 3;

    private int paddle1Y = 250, paddle2Y = 250;
    private final int paddleHeight = 80, paddleWidth = 12;

    private int score1 = 0, score2 = 0;

    private Timer timer;
    private boolean gameRunning = true;

    private boolean wPressed, sPressed, upPressed, downPressed;
    private final int PADDLE_SPEED = 8;

    public Pong() {
        setPreferredSize(new Dimension(800, 600));
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(this);

        timer = new Timer(10, this); // stabiler
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.WHITE);

        // Mittellinie
        for (int i = 0; i < getHeight(); i += 20) {
            g.fillRect(getWidth() / 2 - 5, i, 10, 10);
        }

        // Paddles
        g.fillRect(30, paddle1Y, paddleWidth, paddleHeight);
        g.fillRect(getWidth() - 30 - paddleWidth, paddle2Y, paddleWidth, paddleHeight);

        // Ball
        g.fillOval(ballX, ballY, 16, 16);

        // Score
        g.setFont(new Font("Arial", Font.BOLD, 48));
        g.setColor(Color.CYAN);
        g.drawString("" + score1, getWidth()/2 - 60, 60);
        g.drawString("" + score2, getWidth()/2 + 30, 60);

        if (!gameRunning) {
            g.setColor(Color.YELLOW);
            g.setFont(new Font("Arial", Font.BOLD, 36));
            String text = score1 >= 10 ? "Spieler 1 gewinnt!" : "Spieler 2 gewinnt!";
            g.drawString(text, 200, 300);
            g.drawString("Drücke R zum Neustart", 180, 350);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!gameRunning) return;

        // Bewegung
        if (wPressed && paddle1Y > 0) paddle1Y -= PADDLE_SPEED;
        if (sPressed && paddle1Y < getHeight() - paddleHeight) paddle1Y += PADDLE_SPEED;

        if (upPressed && paddle2Y > 0) paddle2Y -= PADDLE_SPEED;
        if (downPressed && paddle2Y < getHeight() - paddleHeight) paddle2Y += PADDLE_SPEED;

        // Ball
        ballX += ballXDelta;
        ballY += ballYDelta;

        // Oben/Unten
        if (ballY <= 0 || ballY >= getHeight() - 16) {
            ballYDelta *= -1;
        }

        // Paddle 1
        if (ballX <= 42 &&
            ballY + 16 >= paddle1Y &&
            ballY <= paddle1Y + paddleHeight) {

            ballXDelta = Math.abs(ballXDelta);

            // Winkel abhängig vom Trefferpunkt
            int hitPos = (ballY + 8) - (paddle1Y + paddleHeight / 2);
            ballYDelta = hitPos / 10;
        }

        // Paddle 2
        if (ballX + 16 >= getWidth() - 42 &&
            ballY + 16 >= paddle2Y &&
            ballY <= paddle2Y + paddleHeight) {

            ballXDelta = -Math.abs(ballXDelta);

            int hitPos = (ballY + 8) - (paddle2Y + paddleHeight / 2);
            ballYDelta = hitPos / 10;
        }

        // Punkte
        if (ballX < 0) {
            score2++;
            resetBall();
        }

        if (ballX > getWidth()) {
            score1++;
            resetBall();
        }

        if (score1 >= 10 || score2 >= 10) {
            gameRunning = false;
        }

        repaint();
    }

    private void resetBall() {
        ballX = getWidth()/2 - 8;
        ballY = getHeight()/2 - 8;

        ballXDelta = (Math.random() > 0.5 ? 4 : -4);
        ballYDelta = (int)(Math.random() * 5 - 2);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_W) wPressed = true;
        if (key == KeyEvent.VK_S) sPressed = true;
        if (key == KeyEvent.VK_UP) upPressed = true;
        if (key == KeyEvent.VK_DOWN) downPressed = true;

        if (key == KeyEvent.VK_R) {
            score1 = 0;
            score2 = 0;
            paddle1Y = 250;
            paddle2Y = 250;
            gameRunning = true;
            resetBall();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_W) wPressed = false;
        if (key == KeyEvent.VK_S) sPressed = false;
        if (key == KeyEvent.VK_UP) upPressed = false;
        if (key == KeyEvent.VK_DOWN) downPressed = false;
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    public static void main(String[] args) {
        JFrame frame = new JFrame("Pong");

        Pong pong = new Pong();
        frame.add(pong);

        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        pong.requestFocusInWindow(); // WICHTIG
    }
}