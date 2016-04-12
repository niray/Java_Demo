package learning;

import java.awt.*;

/**
 * Created by Mac on 16/4/12.
 */
public class RoundCircle extends Canvas {

    final int CIRCLE_RADIUS = 10;
    final int PUBLIC_RADIUS = 100;
    final int SHOW_CYCLE = 500;
    final int ROUND_CYCLE = 6000;
    final double PI = 3.1416926;
    double x = PUBLIC_RADIUS, y = 0.0, angle = 0.0;

    @Override
    public void paint(Graphics g) {
        for (; ; ) {
            x = getSize().getWidth() / 2 + PUBLIC_RADIUS * Math.cos(angle);
            y = getSize().getHeight() / 2 + PUBLIC_RADIUS * Math.sin(angle);


            try {
                Thread.currentThread().sleep(SHOW_CYCLE);
            } catch (InterruptedException e) {
            }
            g.clearRect(0, 0, getSize().width, getSize().height);

            g.drawOval((int) x, (int) y, CIRCLE_RADIUS, CIRCLE_RADIUS);
            angle = angle + SHOW_CYCLE * 2 * PI / ROUND_CYCLE;
        }
    }
}