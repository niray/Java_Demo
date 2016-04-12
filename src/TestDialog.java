import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

/**
 * Created by Mac on 16/4/12.
 */
public class TestDialog implements ActionListener {
    Frame f = new Frame("测试");



    FileDialog d1 = new FileDialog(f, "选择需要打开的文件", FileDialog.LOAD);
    FileDialog d2 = new FileDialog(f, "选择保存文件的路径", FileDialog.SAVE);

    Button b1 = new Button("打开文件");
    Button b2 = new Button("保存文件");

    public void init (){

        MenuBar mb = new MenuBar();

        Menu m1 = new Menu("文件");
        Menu m2 = new Menu("文件2");
        MenuItem mi1 = new MenuItem("新建");
        MenuItem mi2 = new MenuItem("保存");
        MenuItem mi3 = new MenuItem("退出 Ctrl+X",new MenuShortcut(KeyEvent.VK_X));
        MenuItem mi4 = new MenuItem("退出 Ctrl+X",new MenuShortcut(KeyEvent.VK_X));

        CheckboxMenuItem cmi = new CheckboxMenuItem("自动换行");

        m1.add(mi1);
        m1.add(mi2);
        m1.addSeparator();
        m1.add(cmi);
        m1.addSeparator();
        m1.add(mi3);
        m2.add(mi4);

        m1.add(m2);
        mb.add(m1);
        f.setMenuBar(mb);

        mi1.addActionListener(this);
        mi2.addActionListener(this);
        mi3.addActionListener(this);


        d1.setBounds(20, 30, 300, 400);
        d2.setBounds(20, 30, 300, 400);

        b1.addActionListener(e -> {
            d1.setVisible(true);
            System.out.println(d1.getDirectory()+d1.getFile());
        });

        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                d2.setVisible(true);
                System.out.println(d2.getDirectory()+d2.getFile());
            }
        });

        f.add(b1);
        f.add(b2, BorderLayout.SOUTH);

        f.pack();
        f.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        System.out.println(cmd);
    }
}
