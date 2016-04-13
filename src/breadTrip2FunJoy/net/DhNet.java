package breadTrip2FunJoy.net;


import breadTrip2FunJoy.net.upload.ProgressMultipartEntity;
import com.sun.applet2.preloader.CancelException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.util.EntityUtils;
import org.apache.http.util.TextUtils;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 网络任务处理 默认有的 code <br/>
 * netErrorButCache 网络访问超时,但是使用了缓存 <br/>
 * netCanceled 长传文件时取消了任务<br/>
 * noNetError 没有可用的网络<br/>
 * netError 其他网络故障
 *
 * @author duohuo-jinghao
 */
public class DhNet {

    public static final String METHOD_GET = "GET";
    public static final String METHOD_POST = "POST";
    static ExecutorService executorService;
    // 最后一次访问网络花费的时间
    private static int lastSpeed = 10;
    public int TRANSFER_UPLOADING = -40000;
    Boolean isCanceled = false;
    Future<?> feture;
    NetTask task;
    //对话框内容
    String progressMsg;
    private String url = null;
    private Map<String, Object> params = new HashMap<String, Object>();
    private Map<String, File> files = new HashMap<String, File>();
    private String method = "POST";

    public DhNet() {
        this(null);
    }

    public DhNet(String url) {
        this(url, null);
    }

    public DhNet(String url, Map<String, Object> params) {
        super();
        if (url != null) {
            this.url = url.trim();
        }
        if (params != null) {
            this.params.putAll(params);
        }
    }

    /**
     * 线程池里跑runnable
     *
     * @param runnable
     * @return
     */
    public static Future<?> executeRunalle(Runnable runnable) {
        if (executorService == null) {
            executorService = Executors.newFixedThreadPool(Const.net_pool_size);
        }
        return executorService.submit(runnable);
    }

    /**
     * 获取全部的cookie
     *
     * @return
     */
    public static List<Cookie> getCookies() {
        return HttpManager.getCookieStore().getCookies();
    }

    /**
     * 获取cookie的值
     *
     * @param key
     * @return
     */
    public static String getCookie(String key) {
        List<Cookie> cookies = getCookies();
        for (Iterator<Cookie> iterator = cookies.iterator(); iterator.hasNext(); ) {
            Cookie cookie = iterator.next();
            if (cookie.getName().equals(key)) {
                return cookie.getValue();
            }
        }
        return null;
    }

    /**
     * 清空cookie
     */
    public static void clearCookies() {
        HttpManager.getCookieStore().clear();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 清空
     */
    public void clear() {
        params.clear();
        files.clear();
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }


    public DhNet fixURl(String tag, Object value) {
        if (value != null) {
            this.url = this.url.replace("<" + tag + ">", value.toString());
        }
        return this;
    }

    /**
     * 添加参数
     *
     * @param key
     * @param value
     * @return
     */
    public DhNet addParam(String key, Object value) {
        if (TextUtils.isEmpty(key))// || value == null || TextUtils.isEmpty(value.toString())
            return this;

        this.params.put(key.trim(), value);
        return this;
    }

    /**
     * 添加参数
     *
     * @param params
     * @return
     */
    public DhNet addParams(Map<String, Object> params) {
        this.params.putAll(params);
        return this;
    }

    /**
     * 设置方法 GET POST
     *
     * @param mehtod
     * @return
     */
    public DhNet setMethod(String mehtod) {
        this.method = mehtod;
        return this;
    }

    /**
     * get方法访问
     *
     * @param task
     * @return
     */
    public DhNet doGet(NetTask task) {
        this.method = METHOD_GET;
        excuse(task);
        return this;
    }

    /**
     * get方法访问
     *
     * @param task
     * @return
     */
    public DhNet doGet(boolean dialog, NetTask task) {
        this.method = METHOD_GET;
        return dialog ? executeInDialog(task) : excuse(task);
    }

    /**
     * get方法访问
     *
     * @param task
     * @return
     */
    public DhNet doGet(boolean dialog, String msg, NetTask task) {
        this.method = METHOD_GET;
        if (!TextUtils.isEmpty(msg)) {
            setProgressMsg(msg);
        }
        return dialog ? executeInDialog(task) : excuse(task);
    }

    /**
     * get方法访问 ,同时打开对话框
     *
     * @param task
     * @return
     */
    public DhNet doGetInDialog(NetTask task) {
        this.method = METHOD_GET;
        executeInDialog(task);
        return this;
    }

    public DhNet doGetInDialog(String msg, NetTask task) {
        if (!TextUtils.isEmpty(msg)) {
            setProgressMsg(msg);
        }
        this.method = METHOD_GET;
        executeInDialog(task);
        return this;
    }

    /**
     * post方法访问
     *
     * @param task
     * @return
     */
    public DhNet doPost(NetTask task) {
        this.method = METHOD_POST;
        excuse(task);
        return this;
    }

    /**
     * post方法访问 ,同时打开对话框
     *
     * @param task
     * @return
     */
    public DhNet doPostInDialog(NetTask task) {
        this.method = METHOD_POST;
        executeInDialog(task);
        return this;
    }

    /**
     * post方法访问 ,同时打开对话框
     *
     * @param task
     * @return
     */
    public DhNet doPostInDialog(String msg, NetTask task) {
        if (!TextUtils.isEmpty(msg)) {
            setProgressMsg(msg);
        }
        this.method = METHOD_POST;
        executeInDialog(task);
        return this;
    }

    /**
     * 执行网络访问
     *
     * @param task
     * @return
     */
    public DhNet excuse(NetTask task) {
        this.task = task;

        boolean isCacheOk = false;
        //添加全局参数
        task.onStart();

        final boolean isCacheOkf = isCacheOk;// 是否使用了缓存
        this.feture = executeRunalle(new Runnable() {
            public void run() {
                boolean hasNet = true;
                // 有网直接访问
                if (hasNet) {
                    // 网络状态良好
                    if (lastSpeed < HttpManager.DEFAULT_SOCKET_TIMEOUT) {
                        HttpManager.longTimeOut();
                    } else {
                        // 网络不好
                        HttpManager.shortTimeOut();
                    }
                    String url = DhNet.this.url;
                    Map<String, Object> params = DhNet.this.params;
                    try {
                        long begin = System.currentTimeMillis();
                        String result = NetUtil.sync(url, DhNet.this.method, params);
                        long end = System.currentTimeMillis();
                        lastSpeed = (int) ((end - begin) / 1000);

                        System.out.println("DhNet  " + DhNet.this.url + "  method: " + method + "\n params: " + params + "\n result: " + NetUtil.decodeUnicode(result) + "\n");

                        Response response = new Response(result);
                        response.isCache(false);

                        DhNet.this.task.transfer(response, NetTask.TRANSFER_CODE);
                        try {
                            DhNet.this.task.doInBackground(response);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        if (!isCanceled) {
                            // 当没有使用缓存或者缓存策略是网后更新
                            if (!isCacheOkf) {
                                DhNet.this.task.transfer(response, NetTask.TRANSFER_DOUI);
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        onNetError(e, isCacheOkf);
                    }
                } else {
                    onNoNet(isCacheOkf);
                }
            }
        });
        return this;
    }

    /**
     * 在没有网的时候的处理
     */
    private void onNoNet(Boolean hasUserCache) {

        String errorJson = "{'" + Const.response_success + "':false,'" + Const.response_msg + "':'没有可用的网络','" + Const.response_code + "':'noNetError'}";
        Response response = new Response(errorJson);
        DhNet.this.task.transfer(response, NetTask.TRANSFER_DOERROR);
    }

    /**
     * 处理网路异常
     *
     * @param e
     */
    private void onNetError(Exception e, Boolean hasUserCache) {
        lastSpeed = HttpManager.DEFAULT_SOCKET_TIMEOUT + 1;
        // 网络访问出错
        if (e instanceof UnknownHostException) {
            System.out.println("DhNet  " + "域名不对可能是没有配置网络权限");
        }
        boolean isFromCache = false;
        // 同时提示网络问题
        String errorjson;
        if (isFromCache) {
            errorjson = "{'success':false,'msg':'当前网络信号不好,使用缓存数据','code':'netErrorButCache'}";
        } else {
            errorjson = "{'success':false,'msg':'当前网络信号不好','code':'netError'}";
        }
        Response response = new Response(errorjson);
        response.addBundle("e", e);
        DhNet.this.task.transfer(response, NetTask.TRANSFER_DOERROR);
    }

    /**
     * 执行同时打开对话框
     *
     * @param task
     * @return
     */
    public DhNet executeInDialog(NetTask task) {
        excuse(task);
        return this;
    }

    /**
     * 取消访问 如果访问没有开始就永远不会启动访问
     * 如果访问已经启动 如果isInterrupt 为 true 则访问会被打断 , 否则 会线程继续运行 取消时必定会调用 task
     * 的onCancel方法
     *
     * @return
     */
    public Boolean cancel(Boolean isInterrupt) {
        this.isCanceled = true;
        if (feture != null) {
            feture.cancel(isInterrupt);
        }
        if (task != null) {
            task.onCancelled();
        }
        return true;
    }

    /**
     * 当网络访问没启动或被取消都返回 false
     *
     * @return
     */
    public Boolean isCanceled() {
        if (isCanceled != null) {
            return isCanceled;
        }
        return false;
    }

    public DhNet addFile(String name, File file) {
        files.put(name, file);
        return this;
    }

    public void upload(NetTask task) {
        this.task = task;
        this.feture = executeRunalle(new Runnable() {
            public void run() {
                String url = DhNet.this.url;
                HttpPost httpPost = new HttpPost(url);
                long fileLen = 0;
                ProgressMultipartEntity mulentity = new ProgressMultipartEntity();
                for (String key : files.keySet()) {
                    File file = files.get(key);
                    if (file == null) continue;
                    fileLen += file.length();
                    FileBody filebody = new FileBody(file);
                    mulentity.addPart(key, filebody);
                }
                final long total = fileLen;
                mulentity.setProgressListener(new ProgressMultipartEntity.ProgressListener() {
                    public void transferred(long num) {
                        Response response = new Response("{success:true}");
                        response.addBundle("uploading", true);
                        response.addBundle("length", num);
                        response.addBundle("total", total);
                        DhNet.this.task.transfer(response, TRANSFER_UPLOADING);
                    }

                    public boolean isCanceled() {
                        return DhNet.this.isCanceled();
                    }
                });
                try {
                    if (params != null) {
                        for (String key : params.keySet()) {
                            if (params.get(key) != null) {
                                StringBody body = new StringBody(params.get(key).toString(), Charset.forName("UTF-8"));
                                mulentity.addPart(key, body);
                            }
                        }
                    }
                } catch (UnsupportedEncodingException e1) {
                    e1.printStackTrace();
                }
                httpPost.setEntity(mulentity);
                HttpResponse response;
                try {
                    response = HttpManager.execute(httpPost);
                    if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                        HttpEntity rentity = response.getEntity();
                        String result = EntityUtils.toString(rentity);
                        Response myresponse = new Response(result);
                        myresponse.addBundle("uploading", false);
                        myresponse.addBundle("proccess", 100);
                        DhNet.this.task.transfer(myresponse, NetTask.TRANSFER_DOUI);

                        System.out.println("DhNet  " + DhNet.this.url + "  method: " + method + "\n params: " + params + "\n result: " + NetUtil.decodeUnicode(result));

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    if (e.getCause() instanceof UnknownHostException) {
                        System.out.println("域名不对可能是没有配置网络权限");
                    }
                    if (e instanceof CancelException) {
                        String errorjson = "{'success':false,'msg':'上传任务已被取消','code':'netCanceled'}";
                        Response myresponse = new Response(errorjson);
                        myresponse.addBundle("e", e);
                        DhNet.this.task.transfer(myresponse, NetTask.TRANSFER_DOERROR);
                    } else {
                        String errorjson = "{'success':false,'msg':'上传失败','code':'netError'}";
                        Response myresponse = new Response(errorjson);
                        myresponse.addBundle("e", e);
                        DhNet.this.task.transfer(myresponse, NetTask.TRANSFER_DOERROR);
                    }
                }
            }
        });
    }

    /**
     * 文件上传, 支持大文件的上传 和文件的上传进度更新 task inui response 的bundle参数 uploading true
     * 上传中,false 上传完毕 ; process 上传进度 0-100 cancel 方法可以取消上传
     *
     * @param name
     * @param file
     * @param task
     */
    public void upload(final String name, final File file, NetTask task) {
        if (file == null) return;
        System.out.println("DhNet  " + name + ":" + file.getAbsolutePath() + "  isExists:" + file.exists());
        addFile(name, file);
        upload(task);
    }

    public void setProgressMsg(String progressMsg) {
        this.progressMsg = progressMsg;
    }
}
