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
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

import static com.sun.imageio.plugins.jpeg.JPEG.version;

public class BreadTrip2FunJoyActivity {

    private static final String DOWNLOAD_IMG_CODE = "travels_img";

    TextField et_id = new TextField(50);
    TextField et_topic_id = new TextField(50);
    TextField et_index = new TextField(50);
    JLabel tv_log = new JLabel();
    JLabel tv_process = new JLabel();

    JButton btn_go = new JButton("Go");
    JButton btn_goon = new JButton("Go On");


    DownLoadManager downloader = new DownLoadManager();
    Frame frame = new Frame("面包侣行搬运工");
    private BreadTripBean breadTripBean;
    private String topicId;
    private List<TravelContentBean> contentBeanList = new ArrayList<>();
    private int index = 0;
    private String ids = "";
    private boolean start;

    public static void main(String[] args) {
        BreadTrip2FunJoyActivity breadTrip2FunJoyActivity = new BreadTrip2FunJoyActivity();
        breadTrip2FunJoyActivity.onCreate();
    }


    public void onCreate() {
        initHeader("");
        getToken();


        Panel bottom = new Panel();
        bottom.add(et_id);
        bottom.add(btn_go);
        frame.add(bottom, BorderLayout.SOUTH);

        Panel checkPanel = new Panel();
        checkPanel.add(et_topic_id);
        checkPanel.add(et_index);
        checkPanel.add(tv_log);
        checkPanel.add(tv_process);

        Box topLeft = Box.createVerticalBox();
        topLeft.add(btn_goon);
        topLeft.add(checkPanel);

        Box top = Box.createHorizontalBox();
        top.add(topLeft);
        //top.add(colorList);

        frame.add(top);
        frame.pack();
        frame.setVisible(true);


        et_id.setText("2387991298");
        tv_log.setText("日志:");


        btn_go.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getBreadTripBean(et_id.getText().toString());
            }
        });
        btn_goon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toResume();
            }
        });
    }

    private void initHeader(final String token) {
        HttpManager.getHttpClient().addRequestInterceptor(new HttpRequestInterceptor() {
            @Override
            public void process(HttpRequest request, HttpContext httpContext) throws HttpException, IOException {
                Map<String, String> map = new HashMap<String, String>();
                map.put("response-type", "text/json");
                map.put("request-source", "3");
                map.put("version", version);
                if (!TextUtils.isEmpty(token)) {
                    map.put("request-token", token);
                }

                for (Map.Entry<String, String> entry : map.entrySet()) {
                    request.addHeader(entry.getKey(), entry.getValue());
                }
            }
        });
    }

    public void toResume() {
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
        net.addParam("username", "13771925115");
        net.addParam("password", "changer");
        net.doPost(new NetTask() {
            @Override
            public void doInUI(Response response, Integer transfer) {
                super.doInUI(response, transfer);
                String token = JSONUtil.getString(response.jSONFrom("userInfo"), "token");
                initHeader(token);
            }
        });
    }

    private void addLog(String msg) {
        Date date1 = new Date();
        SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm:ss");
        String str1 = sdf1.format(date1);
        log(msg);
        tv_log.setText(tv_log.getText().toString() + "\n " + str1 + " : " + msg);
//        sv_log.post(new Runnable() {
//            @Override
//            public void run() {
//                sv_log.fullScroll(ScrollView.FOCUS_DOWN);
//            }
//        });
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
        tv_process.setText("总体进度:" + (index) + "/" + contentBeanList.size() + msg);
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
