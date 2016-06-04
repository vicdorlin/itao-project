package com.itao.httpclient;

import com.itao.util.CommonUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * HttpClient功能测试类
 *
 * @author vicdor
 * @create 2016-06-04 16:43
 */
public class HttpClientTest {
    @Test
    public void testDoget() {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse httpResponse = null;
        try {
            //创建一个HttpClient对象
            httpClient = HttpClients.createDefault();
            //创建一个GET请求
            HttpGet httpGet = new HttpGet("http://www.sogou.com/web?query=貂蝉");
            //执行请求
            httpResponse = httpClient.execute(httpGet);
            //取响应结果
            int statusCode = httpResponse.getStatusLine().getStatusCode();
            System.out.println("=== statusCode === " + statusCode);
            HttpEntity httpEntity = httpResponse.getEntity();
            String entityContent = EntityUtils.toString(httpEntity,"UTF-8");
            System.out.println("=== entityContent === " + entityContent);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (CommonUtils.exist(httpClient)) httpClient.close();
                if (CommonUtils.exist(httpResponse)) httpResponse.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void testDogetByUriBuilder() {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse httpResponse = null;
        try {
            //创建一个HttpClient对象
            httpClient = HttpClients.createDefault();
            //创建一个URI对象
            URIBuilder uriBuilder = new URIBuilder("http://www.sogou.com/web");
            uriBuilder = uriBuilder.addParameter("query","貂蝉");
            //创建一个GET请求
            HttpGet httpGet = new HttpGet(uriBuilder.build());
            //执行请求
            httpResponse = httpClient.execute(httpGet);
            //取响应结果
            int statusCode = httpResponse.getStatusLine().getStatusCode();
            System.out.println("=== statusCode === " + statusCode);
            HttpEntity httpEntity = httpResponse.getEntity();
            String entityContent = EntityUtils.toString(httpEntity,"UTF-8");
            System.out.println("=== entityContent === " + entityContent);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } finally {
            try {
                if (CommonUtils.exist(httpClient)) httpClient.close();
                if (CommonUtils.exist(httpResponse)) httpResponse.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
