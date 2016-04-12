package learning;

import okhttp3.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

/**
 * Created by Mac on 16/4/11.
 */
public class MainJFrame extends JFrame implements ActionListener {

    JLabel nameLbl = new JLabel("姓名");
    JTextField nameTf = new JTextField(10);
    JButton btnOk = new JButton("确定");
    JLabel out = new JLabel("       ");

    CheckboxGroup cbg = new CheckboxGroup();
    Checkbox man = new Checkbox("Man", cbg, true);
    Checkbox woman = new Checkbox("Woman", cbg, false);
    OkHttpClient client = new OkHttpClient();

    public MainJFrame() {

        Container container = getContentPane();
        container.setLayout(new FlowLayout());

        // setLayout(new FlowLayout());
        container.add(nameLbl);
        container.add(nameTf);
        container.add(btnOk);
        container.add(out);

        container.add(man);
        container.add(woman);

        btnOk.addActionListener(this);
        setSize(800, 100);
        show();
        String result = null;
        try {
            result = post("http://api.huway.com/uc/login", "username=13771925115&password=changer");
        } catch (IOException e) {
            e.printStackTrace();
        }

        out.setText(result);


        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Human p = new Human();
        p.setName(nameTf.getText());
        out.setText("I am " + p.getName() + " In " + cbg.getSelectedCheckbox().getLabel());
    }

    String post(String url, String json) throws IOException {

        RequestBody formBody = new FormBody.Builder()
                .add("username", "13771925115")
                .add("password", "changer")
                .build();

        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .header("request-source", "3")
                .build();


        Response response = client.newCall(request).execute();

        if (response.isSuccessful()) {
            return response.body().string();
        } else {
            throw new IOException("Unexpected code " + response);
        }
    }
}
