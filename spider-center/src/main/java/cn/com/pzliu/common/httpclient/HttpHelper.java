package cn.com.pzliu.common.httpclient;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ProtocolVersion;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Naive
 * @date 2022-03-05 15:31
 */
@Component
public class HttpHelper {

    private static CloseableHttpClient httpClient;


    private static CloseableHttpClient getHttpclient() {
        try {
            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
                @Override
                public boolean isTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
                    return true;
                }
            }).build();
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext);
            return HttpClients.custom().setSSLSocketFactory(sslsf).build();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
        return HttpClients.createDefault();
    }

    public static HttpEntity get(String url) {
        CloseableHttpResponse response = null;
        httpClient = getHttpclient();
        try {
            HttpGet httpGet = new HttpGet(url);
            httpGet.setHeader("cookie", "existmag=mag; 4fJN_2132_saltkey=u28J97Hx; 4fJN_2132_lastvisit=1655905270; 4fJN_2132_sid=ADVlbi; 4fJN_2132_forum_lastvisit=D_2_1656748861; 4fJN_2132_visitedfid=2D36; 4fJN_2132_lastact=1656748926%09forum.php%09viewthread; PHPSESSID=g06080c3c6kh28ci0ha8tft9g4");
            httpGet.setHeader("Accept-Encoding","gzip, deflate, br");
            httpGet.setHeader("Accept-Language","zh-CN,zh;q=0.9,en;q=0.8,en-GB;q=0.7,en-US;q=0.6");
            httpGet.setHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7");
            httpGet.setHeader("Host","yz.chsi.com.cn");
            response = httpClient.execute(httpGet);
            System.out.println(response);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            graceCloseResponse(response);
        }

        return null;
    }

    public static String post(String url, Map<String,String> form) throws IOException {
        CloseableHttpResponse response = null;
        httpClient = getHttpclient();
        String result;
        try{
            HttpPost httpPost = new HttpPost(url);
            List<NameValuePair> paramList = new ArrayList<>();
            form.forEach((key,value)->{
                paramList.add(new BasicNameValuePair(key,value));

            });
            UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(paramList, HTTP.UTF_8);
            httpPost.setEntity(formEntity);
            httpPost.setHeader("cookie", "existmag=mag; 4fJN_2132_saltkey=u28J97Hx; 4fJN_2132_lastvisit=1655905270; 4fJN_2132_sid=ADVlbi; 4fJN_2132_forum_lastvisit=D_2_1656748861; 4fJN_2132_visitedfid=2D36; 4fJN_2132_lastact=1656748926%09forum.php%09viewthread; PHPSESSID=g06080c3c6kh28ci0ha8tft9g4");
            httpPost.setHeader("Accept-Encoding","gzip, deflate, br");
            httpPost.setHeader("Accept-Language","zh-CN,zh;q=0.9,en;q=0.8,en-GB;q=0.7,en-US;q=0.6");
            httpPost.setHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7");
            httpPost.setHeader("Host","yz.chsi.com.cn");
            response = httpClient.execute(httpPost);
            if (response.getStatusLine().getStatusCode() == 200){
                result = EntityUtils.toString(response.getEntity());
            }else {
                throw new ConnectException("网站访问失败，返回值："+response.getStatusLine().getStatusCode());
            }
        }finally {
            graceCloseResponse(response);
        }


        return result;
    }


    private static void graceCloseResponse(CloseableHttpResponse response) {

        if (response != null) {
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}

