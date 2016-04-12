import java.applet.Applet;
import java.awt.*;

/**
 * Created by Mac on 16/4/12.
 */
public class Keyboard extends Applet {


    String text = "";

    public void paint(Graphics graphics) {
        graphics.drawString(text, 20, 20);
    }

    public boolean keyDown(Event event, int x) {
        text = "key down";
        repaint();
        return true;
    }

    public boolean keyUp(Event event, int x) {

        text = "";
        repaint();
        return  true;
    }
}
