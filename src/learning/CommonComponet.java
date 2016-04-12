package learning;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Mac on 16/4/12.
 */
public class CommonComponet {

    Frame f = new Frame("测试");
    Button btnOK = new Button("确认");

    CheckboxGroup cbg = new CheckboxGroup();
    Checkbox male = new Checkbox("男", cbg, true);
    Checkbox female = new Checkbox("女", cbg, false);

    Checkbox married = new Checkbox("是否已婚", false);

    Choice colorChooser = new Choice();

    List colorList = new List(6, true);

    TextArea ta = new TextArea(5, 20);

    TextField name = new TextField(50);


    public void init() {
        colorChooser.add("红色");
        colorChooser.add("绿色");
        colorChooser.add("蓝色");

        colorList.add("红色1");
        colorList.add("绿色2");
        colorList.add("蓝色3");
        colorList.add("红色1");
        colorList.add("绿色2");
        colorList.add("蓝色3");
        colorList.add("红色1");
        colorList.add("绿色2");
        colorList.add("蓝色3");

        Panel bottom = new Panel();
        bottom.add(name);
        bottom.add(btnOK);
        f.add(bottom, BorderLayout.SOUTH);

        Panel checkPanel = new Panel();
        checkPanel.add(colorChooser);
        checkPanel.add(male);
        checkPanel.add(female);
        checkPanel.add(married);

        Box topLeft = Box.createVerticalBox();
        topLeft.add(ta);
        topLeft.add(checkPanel);

        Box top = Box.createHorizontalBox();
        top.add(topLeft);
        top.add(colorList);

        f.add(top);
        f.pack();
        f.setVisible(true);

    }

}
