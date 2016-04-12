package breadTrip2FunJoy.net;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.TextUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * 网络访问
 *
 * @author duohuo-jinghao
 */
public class NetUtil {

    /**
     * 同步网络访问数据
     *
     * @param url    String
     * @param method String
     * @param params Map
     * @return String
     * @throws IOException
     */
    public static String sync(String url, String method, Map<String, Object> params) throws IOException {
        if (TextUtils.isEmpty(url)) return "";
        StringBuffer sb = null;
        InputStream in = syncStream(url, method, params);
        if (in != null) {
            Scanner scanner = new Scanner(in);
            sb = new StringBuffer();
            while (scanner.hasNext()) {
                sb.append(scanner.nextLine());
            }
            in.close();
            scanner.close();
            return new String(sb);
        }
        return null;

    }

    /**
     * 获取同步获取网络流
     *
     * @param url    String
     * @param method String
     * @param params Map
     * @return InputStream
     * @throws IOException
     */
    public static InputStream syncStream(String url, String method, Map<String, Object> params) throws IOException {
        if (TextUtils.isEmpty(url)) return null;

        HttpResponse response;
        if (method.equalsIgnoreCase("POST")) {
            HttpPost httppost = new HttpPost(url);
            List<NameValuePair> formparams = new ArrayList<NameValuePair>();
            for (String key : params.keySet()) {
                if (params.get(key) != null) {
                    formparams.add(new BasicNameValuePair(key, params.get(key).toString()));
                }
            }
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, "UTF-8");
            httppost.setEntity(entity);
            response = HttpManager.execute(httppost);
//            Header[] headers = response.getHeaders("Set-Cookie");
//            System.out.println(headers);
        } else {
            if (!url.contains("?")) {
                url += "?";
            } else {
                if (!url.endsWith("&")) {
                    url += "&";
                }
            }
            url = url + encodeUrl(params);
            HttpGet httpGet = new HttpGet(url);
            response = HttpManager.execute(httpGet);
        }
        HttpEntity rEntity = response.getEntity();
        if (rEntity != null) {
            return rEntity.getContent();
        }
        return null;
    }


    /**
     * 获取get时的url
     *
     * @param params Map
     * @return String
     */
    public static String encodeUrl(Map<String, Object> params) {
        if (params == null || params.size() == 0)
            return "";
        StringBuilder sb = new StringBuilder();
        for (String key : params.keySet()) {
            if (params.get(key) != null) {
                sb.append(key.trim()).append("=").append(URLEncoder.encode((params.get(key).toString()))).append("&");
            }
        }
        return sb.toString();
    }


    /**
     * unicode 转换成 中文
     *
     * @param theString String
     * @return String
     */

    public static String decodeUnicode(String theString) {
        char aChar;
        int len = theString.length();
        StringBuffer outBuffer = new StringBuffer(len);
        for (int x = 0; x < len; ) {
            aChar = theString.charAt(x++);
            if (aChar == '\\') {
                aChar = theString.charAt(x++);
                if (aChar == 'u') {
                    // Read the xxxx
                    int value = 0;
                    for (int i = 0; i < 4; i++) {
                        aChar = theString.charAt(x++);
                        switch (aChar) {
                            case '0':
                            case '1':
                            case '2':
                            case '3':
                            case '4':
                            case '5':
                            case '6':
                            case '7':
                            case '8':
                            case '9':
                                value = (value << 4) + aChar - '0';
                                break;
                            case 'a':
                            case 'b':
                            case 'c':
                            case 'd':
                            case 'e':
                            case 'f':
                                value = (value << 4) + 10 + aChar - 'a';
                                break;
                            case 'A':
                            case 'B':
                            case 'C':
                            case 'D':
                            case 'E':
                            case 'F':
                                value = (value << 4) + 10 + aChar - 'A';
                                break;
                            default:
                                throw new IllegalArgumentException(
                                        "Malformed   \\uxxxx   encoding.");
                        }

                    }
                    outBuffer.append((char) value);
                } else {
                    if (aChar == 't')
                        aChar = '\t';
                    else if (aChar == 'r')
                        aChar = '\r';
                    else if (aChar == 'n')
                        aChar = '\n';
                    else if (aChar == 'f')
                        aChar = '\f';
                    outBuffer.append(aChar);
                }
            } else
                outBuffer.append(aChar);
        }
        return outBuffer.toString();
    }
}