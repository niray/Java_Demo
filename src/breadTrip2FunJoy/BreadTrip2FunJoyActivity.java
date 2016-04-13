package breadTrip2FunJoy;


import breadTrip2FunJoy.net.*;
import breadTrip2FunJoy.net.download.DownLoadManager;
import breadTrip2FunJoy.net.download.DownloadTask;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.TextUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

import static com.sun.imageio.plugins.jpeg.JPEG.version;

public class BreadTrip2FunJoyActivity {

    private static final String DOWNLOAD_IMG_CODE = "travels_img";

    DownLoadManager downloader = new DownLoadManager();
    JFrame frame = new JFrame("面包侣行搬运工");
    JTextField et_bread_id = new JTextField(10);
    JTextField et_topic_id = new JTextField(10);
    JTextField et_index = new JTextField(10);
    JButton btn_go = new JButton("开始");
    JButton btn_goon = new JButton("断点续传");
    JTextField tf_status = new JTextField(16);
    JTextField tf_save_url = new JTextField(10);
    JList colorList = new JList();
    JTextField tf_login_acc = new JTextField(10);
    JTextField tf_login_pwd = new JTextField(10);
    JTextField tf_nick = new JTextField(10);
    JTextField tf_lequ_id = new JTextField(10);
    JScrollPane jScrollPane = new JScrollPane(colorList);
    Vector listModel = new Vector();
    private BreadTripBean breadTripBean;
    private String topicId;
    private List<TravelContentBean> contentBeanList = new ArrayList<>();
    private int index = 0;
    private String ids = "";
    private boolean start;
    private JFileChooser fc = new JFileChooser();
    private JTextField tf_token = new JTextField(10);
    private int flag;

    public static void main(String[] args) {
        BreadTrip2FunJoyActivity breadTrip2FunJoyActivity = new BreadTrip2FunJoyActivity();
        breadTrip2FunJoyActivity.onCreate();
    }

    public void onCreate() {

        Box box_left = Box.createVerticalBox();
        //面包Id相关UIPanel
        JPanel breadIdPanel = new JPanel();
        JLabel tv_bread_id = new JLabel("面包ID:");
        breadIdPanel.add(tv_bread_id);
        breadIdPanel.add(et_bread_id);
        breadIdPanel.add(btn_go);
        box_left.add(breadIdPanel);

        //乐去Id相关UIPanel
        JPanel topicIdPanel = new JPanel();
        JLabel tv_lequ_id = new JLabel("乐去ID:");
        tf_lequ_id.setText("19");
        JButton btn_open = new JButton("查看");
        topicIdPanel.add(tv_lequ_id);
        topicIdPanel.add(tf_lequ_id);
        topicIdPanel.add(btn_open);
        btn_open.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //判断当前系统是否支持Java AWT Desktop扩展
                if (Desktop.isDesktopSupported()) {
                    try {
                        //创建一个URI实例,注意不是URL

                        //http://www.huway.com/travels/topicId
                        java.net.URI uri = java.net.URI.create("http://www.huway.com/travels/" + tf_lequ_id.getText());
                        //获取当前系统桌面扩展
                        Desktop dp = Desktop.getDesktop();
                        //判断系统桌面是否支持要执行的功能
                        if (dp.isSupported(Desktop.Action.BROWSE)) {
                            //获取系统默认浏览器打开链接
                            dp.browse(uri);
                        }
                    } catch (NullPointerException | IOException ex) {
                        //此为uri为空时抛出异常
                        ex.printStackTrace();
                    }
                }
            }
        });
        box_left.add(topicIdPanel);

        //保存地址相关UIPanel
        JPanel saveUrlPanel = new JPanel();
        JLabel tv_save_url = new JLabel("缓存地址:");
        JButton btn_save = new JButton("选择地址");

        btn_save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);//只能选择目录
                String path = null;
                File f = null;
                try {
                    flag = fc.showOpenDialog(null);
                } catch (HeadlessException head) {
                    System.out.println("Open File Dialog ERROR!");
                }
                if (flag == JFileChooser.APPROVE_OPTION) {
                    //获得该文件
                    f = fc.getSelectedFile();
                    path = f.getPath();
                    tf_save_url.setText(path);
                    PropUtil.writeProperties("path", tf_save_url.getText());

                }
            }
        });

        saveUrlPanel.add(tv_save_url);
        saveUrlPanel.add(tf_save_url);
        saveUrlPanel.add(btn_save);
        box_left.add(saveUrlPanel);


        //登陆及Token 相关
        JPanel tokenPanel = new JPanel();
        JLabel tv_token = new JLabel("Token:");
        tokenPanel.add(tv_token);
        tokenPanel.add(tf_token);
        box_left.add(tokenPanel);

        JPanel nickPanel = new JPanel();
        JLabel tv_nick = new JLabel("昵称:");

        nickPanel.add(tv_nick);
        nickPanel.add(tf_nick);
        box_left.add(nickPanel);


        JPanel loginPanel = new JPanel();
        JLabel tv_acc = new JLabel("账号:");
        loginPanel.add(tv_acc);
        loginPanel.add(tf_login_acc);
        box_left.add(loginPanel);


        JPanel pwdPanel = new JPanel();
        JLabel tv_pwd = new JLabel("密码:");
        pwdPanel.add(tv_pwd);
        pwdPanel.add(tf_login_pwd);
        box_left.add(pwdPanel);


        JButton btn_login = new JButton("登陆");
        btn_login.addActionListener(e -> getToken());
        box_left.add(btn_login);

        Box top = Box.createHorizontalBox();
        top.add(box_left);

        Box box_right = Box.createVerticalBox();

        JLabel tv_log = new JLabel("日志:");
        box_right.add(tv_log);

        colorList.setSize(500, 550);
        jScrollPane.setSize(100, 100);

        //  box_right.add(colorList);
        box_right.add(jScrollPane);

        JPanel resumePanel = new JPanel();
        JLabel tv_status = new JLabel("总体进度:");

        resumePanel.add(tv_status);
        resumePanel.add(tf_status);
        resumePanel.add(btn_goon);
        box_right.add(resumePanel);


        frame.setSize(800, 600);

        frame.add(box_left, BorderLayout.WEST);
        frame.add(box_right, BorderLayout.EAST);
        frame.pack();
        frame.setVisible(true);


        et_bread_id.setText("2387991298");


        //  tf_login_acc.setText("huway");
        // tf_login_pwd.setText("72@huway");

        tf_login_acc.setText(PropUtil.readValue("acc"));
        tf_login_pwd.setText(PropUtil.readValue("pwd"));
        tf_nick.setText(PropUtil.readValue("nick"));
        tf_token.setText(PropUtil.readValue("token"));
        tf_save_url.setText(PropUtil.readValue("path"));

        initHeader();

        btn_go.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getBreadTripBean(et_bread_id.getText().toString());
            }
        });

        btn_goon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toResume();
            }
        });

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    private void initHeader() {
        HttpManager.getHttpClient().addRequestInterceptor(new HttpRequestInterceptor() {
            @Override
            public void process(HttpRequest request, HttpContext httpContext) throws HttpException, IOException {
                Map<String, String> map = new HashMap<String, String>();
                map.put("response-type", "text/json");
                map.put("request-source", "3");
                map.put("version", version);
                if (!TextUtils.isEmpty(tf_token.getText())) {
                    map.put("request-token", tf_token.getText());
                }

                for (Map.Entry<String, String> entry : map.entrySet()) {
                    request.addHeader(entry.getKey(), entry.getValue());
                }
            }
        });
    }

    public void toResume() {
        if (index == 0) return;
        toUploadImg(contentBeanList.get(index).getImages(), false);
    }

    private void getBreadTripBean(String id) {
        if (!start) {
            start = true;
        } else {
            return;
        }

        if (breadTripBean != null) return;
        addLog("正在拉取Id为" + id + "的游记");
        DhNet net = new DhNet("http://api.breadtrip.com/trips/" + id + "/waypoints/");
        net.doGetInDialog(new NetTask() {

            @Override
            public void doInUI(Response response, Integer transfer) {
                if (response.isSuccess()) {
                    breadTripBean = response.model(BreadTripBean.class);
                    toUploadImg(breadTripBean.getCover_image(), true);
                }
            }

            @Override
            public void onFailure(Response response) {
                super.onFailure(response);
                addLog("错误" + response.getMsg());
            }
        });
    }

    private void getToken() {
        DhNet net = new DhNet(API.User.login);
        net.addParam("username", tf_login_acc.getText());
        net.addParam("password", tf_login_pwd.getText());
        net.doPost(new NetTask() {
            @Override
            public void doInUI(Response response, Integer transfer) {
                super.doInUI(response, transfer);
                String token = JSONUtil.getString(response.jSONFrom("userInfo"), "token");
                String nickName = JSONUtil.getString(response.jSONFrom("userInfo"), "nickname");

                PropUtil.writeProperties("acc", tf_login_acc.getText());
                PropUtil.writeProperties("pwd", tf_login_pwd.getText());
                PropUtil.writeProperties("nick", nickName);
                PropUtil.writeProperties("token", token);

                tf_nick.setText(nickName);
                tf_token.setText(token);

                addLog(nickName + " 登陆成功");
                initHeader();
            }
        });
    }

    private void addLog(String msg) {
        Date date1 = new Date();
        SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm:ss");
        String str1 = sdf1.format(date1);
        log(msg);
        listModel.addElement(str1 + " : " + msg);
        colorList.setListData(listModel);

        Point p = colorList.indexToLocation(listModel.size() - 1);
        JScrollBar jScrollBar = jScrollPane.getVerticalScrollBar();//获得垂直滚动条
        jScrollBar.setValue(p.y);//设置垂直滚动条位置

    }

    public void onCreateEvent(String title, String district, String imgId) {
        DhNet net = new DhNet(API.Travels.createTravelsHeader);
        net.addParam("title", title);
        net.addParam("district", district);
        net.addParam("cover_img", imgId);
        net.addParam("is_prefect", 0);
        net.doPost(new NetTask() {
            @Override
            public void doInUI(Response response, Integer transfer) {
                if (response.isSuccess()) {
                    TravelsHeaderBean headerBean = response.modelFromData(TravelsHeaderBean.class);
                    topicId = headerBean.getId();
                    et_topic_id.setText(topicId);
                    addLog("创建游记头成功" + topicId);

                    for (BreadTripBean.DaysEntity daysEntity : breadTripBean.getDays()) {
                        for (BreadTripBean.DaysEntity.WaypointsEntity waypointsEntity : daysEntity.getWaypoints()) {
                            String content = waypointsEntity.getText();
                            int date = waypointsEntity.getDate_added();
                            String imgUrl = waypointsEntity.getPhoto_w640();
                            int w = 960, h = 540;
                            if (waypointsEntity.getPhoto_info() != null) {
                                w = waypointsEntity.getPhoto_info().getW();
                                h = waypointsEntity.getPhoto_info().getH();
                            }

                            TravelContentBean travelContentBean = new TravelContentBean();
                            travelContentBean.setTravels_id(topicId);
                            travelContentBean.setContent(content);
                            travelContentBean.setDate(date);
                            travelContentBean.setImages(imgUrl);
                            travelContentBean.setImage_width(w);
                            travelContentBean.setImage_height(h);
                            contentBeanList.add(travelContentBean);
                        }
                    }
                    index = 0;
                    toUploadImg(contentBeanList.get(index).getImages(), false);
                } else {
                    showToast(response.getMsg());
                }
            }

            @Override
            public void onFailure(Response response) {
                super.onFailure(response);
                addLog("错误" + response.getMsg());
            }
        });
    }

    public void showToast(String msg) {
        addLog("错误:" + msg);
    }

    private void uploadContent(String topicId, final String content, long date, String imgId, int w, int h) {
        if (content.length() > 35) {
            addLog("上传 " + content.substring(0, 30).replace("\\s", "").trim());
        } else {
            addLog("上传 " + content.replace("\\s", "").trim());
        }

        DhNet net = new DhNet(API.Travels.addTopicItem);
        net.addParam("travels_id", topicId);
        net.addParam("content", content);
        net.addParam("date", date);
        if (!TextUtils.isEmpty(imgId)) {
            net.addParam("images", imgId);
            net.addParam("image_width", w);
            net.addParam("image_height", h);
        }
        net.addParam("image_width", w);
        net.addParam("image_height", h);
        net.doPost(new NetTask() {
            @Override
            public void doInUI(Response response, Integer transfer) {
                if (response.isSuccess()) {
                    TravelContentBean contentBean = response.modelFromData(TravelContentBean.class);
                    ids += contentBean.getId() + ",";
                }
            }

            @Override
            public void onFailure(Response response) {
                super.onFailure(response);
                addLog("错误" + response.getMsg());
            }
        });
    }

    private void postIds() {
        DhNet idsNet = new DhNet(API.Travels.travelsContentSort);
//        travels_id<string> 游记id
        idsNet.addParam("travels_id", topicId);
//        item_sort<string> 游记内容排序
        idsNet.addParam("item_sort", ids.substring(0, ids.length() - 1));
        idsNet.doPost(new NetTask() {
            @Override
            public void doInUI(Response response, Integer transfer) {
                if (response.isSuccess()) {
                    addLog("排序成功");
                    releaseTravels();
                } else {
                    showToast(response.getMsg());
                }
            }

            @Override
            public void onFailure(Response response) {
                super.onFailure(response);
                addLog("错误" + response.getMsg());
            }
        });
    }

    /**
     * 获取项目缓存文件
     *
     * @return
     */
    private File getCacheDir() {
        File file = new File(getDir().getAbsolutePath() + "/imageCache");
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }

    /***
     * 获取项目文件夹
     *
     * @return
     */
    private File getDir() {
        File dir = new File("/Users/huway_iosdev2/Desktop/");
        dir.mkdirs();
        return dir;
    }

    private void releaseTravels() {
        DhNet idsNet = new DhNet(API.Travels.release);
        idsNet.addParam("travels_ids", topicId);        //travels_id<string> 游记id
        idsNet.doPost(new NetTask() {
            @Override
            public void doInUI(Response response, Integer transfer) {
                if (response.isSuccess()) {
                    addLog("搬运成功");
                    //http://www.huway.com/travels/topicId
                    tf_lequ_id.setText(topicId);
                    start = false;
                } else {
                    showToast(response.getMsg());
                }
            }

            @Override
            public void onFailure(Response response) {
                super.onFailure(response);
                addLog("错误" + response.getMsg());
            }
        });
    }

    public void toUploadImg(final String url, final boolean isCover) {
        if (TextUtils.isEmpty(url)) {
            nextTask("");
        } else {
            downloadImgCode(url, isCover);
        }
    }

    private void setLog(String msg) {
        tf_status.setText((index) + "/" + contentBeanList.size() + msg);
    }

    private void uploadPic(final File imgFile, final boolean isCover) {
        DhNet net = new DhNet(API.Global.uploadPic);
        net.upload("file", imgFile, new NetTask() {
            boolean uploadOk = false;

            @Override
            public void doInUI(Response response, Integer transfer) {
                try {
                    if (response.getBundle("length") != null && response.getBundle("total") != null) {
                        long uploaded = response.getBundle("length");
                        long total = response.getBundle("total");
                        float percent = ((float) uploaded / total);
                        int p = (int) (percent * 100);
                        if (percent < 1) {
                            setLog("       图片上传:" + p + "% ");
                        } else {
                            setLog("       图片上传:100% ");
                        }
                    }
                    if (response.isSuccess() && !uploadOk) {
                        if (response.JSON().has("info")) {
                            uploadOk = true;
                            String imgId = response.JSON().getJSONObject("info").getString("id");
//                            String url = response.JSON().getJSONObject("info").getString("url");
                            if (isCover) {
                                String title = breadTripBean.getName();
                                String locate = breadTripBean.getCity();
                                addLog("题目:" + title + "   地点:" + locate + ids);
                                if (TextUtils.isEmpty(locate) && breadTripBean.getCities().size() > 0) {
                                    locate = breadTripBean.getCities().get(0);
                                }
                                onCreateEvent(title, locate, imgId);
                            } else {
                                nextTask(imgId);
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    addLog("错误" + e.getMessage());

                }
            }

            @Override
            public void onFailure(Response response) {
                super.onFailure(response);
                addLog("错误" + response.getMsg());
            }
        });
    }

    private void nextTask(String imgId) {
        if (index < contentBeanList.size() - 1) {
            TravelContentBean bean = contentBeanList.get(index);
            uploadContent(topicId, bean.getContent(), bean.getDate(), imgId, bean.getImage_width(), bean.getImage_height());
            index++;
            et_index.setText(String.valueOf(index));
            toUploadImg(contentBeanList.get(index).getImages(), false);
        } else {
            postIds();
        }
    }

    private void log(String string) {
        System.out.println(string);
    }

    public File getCachePicFile() {
        return new File(getCacheDir(), System.currentTimeMillis() + ".jpg");
    }

    private void downloadImgCode(String imgUrl, final boolean isCover) {

        log("下载:" + imgUrl);
        final File pic = getCachePicFile();
        //启动下载
        downloader.download(DOWNLOAD_IMG_CODE, imgUrl, null, pic.getAbsolutePath());//第三个参数附加参数
        //注册下载监听3
        downloader.registerCallBack(DOWNLOAD_IMG_CODE, new DownLoadManager.DownLoadCallBack() {
            @Override
            public void onPresent(DownloadTask task, float present) {
                setLog("       图片下载:" + (int) (present) + "%");
            }

            @Override
            public void onEnd(DownloadTask task) {
                downloader.unregisterCallBack(DOWNLOAD_IMG_CODE);
                uploadPic(pic, isCover);
            }

            @Override
            public void onStop(DownloadTask task) {
                addLog("下载失败!");
                downloader.unregisterCallBack(DOWNLOAD_IMG_CODE);
            }
        });
    }
}
