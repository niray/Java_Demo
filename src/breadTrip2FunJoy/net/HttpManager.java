package breadTrip2FunJoy.net;


import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.params.ConnPerRouteBean;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.cookie.*;
import org.apache.http.cookie.params.CookieSpecPNames;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.impl.cookie.BrowserCompatSpec;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

public class HttpManager {

    public static final int DEFAULT_SOCKET_TIMEOUT = 45 * 1000;
    public static final int DEFAULT_SOCKET_TIMEOUT_SHORT = 10 * 1000;
    final static HttpParams httpParams = new BasicHttpParams();
    private static final int DEFAULT_MAX_CONNECTIONS = 30;
    private static final int DEFAULT_SOCKET_BUFFER_SIZE = 8192;
    private static DefaultHttpClient sHttpClient;

    static {
        ConnManagerParams.setTimeout(httpParams, 1500);
        ConnManagerParams.setMaxConnectionsPerRoute(httpParams, new ConnPerRouteBean(10));
        ConnManagerParams.setMaxTotalConnections(httpParams, DEFAULT_MAX_CONNECTIONS);
        HttpProtocolParams.setVersion(httpParams, HttpVersion.HTTP_1_1);
        HttpProtocolParams.setContentCharset(httpParams, "UTF-8");
        HttpConnectionParams.setStaleCheckingEnabled(httpParams, false);
        HttpClientParams.setRedirecting(httpParams, false);
        HttpProtocolParams.setUserAgent(httpParams, "Android client");
        HttpConnectionParams.setSoTimeout(httpParams, DEFAULT_SOCKET_TIMEOUT);
        HttpConnectionParams.setConnectionTimeout(httpParams, DEFAULT_SOCKET_TIMEOUT);
        HttpConnectionParams.setTcpNoDelay(httpParams, true);
        HttpConnectionParams.setSocketBufferSize(httpParams, DEFAULT_SOCKET_BUFFER_SIZE);

        SchemeRegistry schemeRegistry = new SchemeRegistry();
        schemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
        try {
            KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
            trustStore.load(null, null);
            SSLSocketFactory sf = new MySSLSocketFactory(trustStore);
            sf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            schemeRegistry.register(new Scheme("https", sf, 443));
        } catch (Exception ex) {
        }

        ClientConnectionManager manager = new ThreadSafeClientConnManager(httpParams, schemeRegistry);
        sHttpClient = new DefaultHttpClient(manager, httpParams);
        BasicCookieStore cookieStore = new BasicCookieStore();
        sHttpClient.setCookieStore(cookieStore);
        CookieSpecFactory csf = new CookieSpecFactory() {
            public CookieSpec newInstance(HttpParams params) {
                return new BrowserCompatSpec() {
                    @Override
                    public void validate(Cookie cookie, CookieOrigin origin) throws MalformedCookieException {

                    }
                };
            }
        };
        sHttpClient.getCookieSpecs().register("oschina", csf);
        sHttpClient.getParams().setParameter(ClientPNames.COOKIE_POLICY, "oschina");
        sHttpClient.getParams().setParameter(CookieSpecPNames.SINGLE_COOKIE_HEADER, true);
    }

    private HttpManager() {
    }

    /**
     * 获取HttpClient
     *
     * @return
     */
    public static DefaultHttpClient getHttpClient() {
        return sHttpClient;
    }

    public static void longTimeOut() {
        HttpConnectionParams.setSoTimeout(httpParams, DEFAULT_SOCKET_TIMEOUT);
        HttpConnectionParams.setConnectionTimeout(httpParams, DEFAULT_SOCKET_TIMEOUT);
        ConnManagerParams.setTimeout(httpParams, DEFAULT_SOCKET_TIMEOUT);
    }

    public static void shortTimeOut() {
        HttpConnectionParams.setSoTimeout(httpParams, DEFAULT_SOCKET_TIMEOUT_SHORT);
        HttpConnectionParams.setConnectionTimeout(httpParams, DEFAULT_SOCKET_TIMEOUT_SHORT);
        ConnManagerParams.setTimeout(httpParams, DEFAULT_SOCKET_TIMEOUT_SHORT);
    }

    public static CookieStore getCookieStore() {
        return sHttpClient.getCookieStore();
    }

    public static HttpResponse execute(HttpGet get) throws IOException {
        return sHttpClient.execute(get);
    }

    public static HttpResponse execute(HttpPost post) throws IOException {
        return sHttpClient.execute(post);
    }

    public static HttpResponse execute(HttpUriRequest post) throws IOException {
        return sHttpClient.execute(post);
    }

    public static HttpResponse execute(HttpHead head) throws IOException {
        return sHttpClient.execute(head);
    }

    public static HttpResponse executeHost(HttpHost host, HttpGet get) throws IOException {
        return sHttpClient.execute(host, get);
    }


    private static class MySSLSocketFactory extends SSLSocketFactory {
        SSLContext sslContext = SSLContext.getInstance("TLS");

        public MySSLSocketFactory(KeyStore truststore)
                throws NoSuchAlgorithmException, KeyManagementException,
                KeyStoreException, UnrecoverableKeyException {
            super(truststore);

            TrustManager tm = new X509TrustManager() {

                public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                }

                public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                }

                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
            };

            sslContext.init(null, new TrustManager[]{tm}, null);
        }

        @Override
        public Socket createSocket(Socket socket, String host, int port, boolean autoClose) throws IOException, UnknownHostException {
            return sslContext.getSocketFactory().createSocket(socket, host, port, autoClose);
        }

        @Override
        public Socket createSocket() throws IOException {
            return sslContext.getSocketFactory().createSocket();
        }
    }
}
