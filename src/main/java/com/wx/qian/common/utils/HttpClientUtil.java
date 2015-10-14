package com.wx.qian.common.utils;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.SimpleHttpConnectionManager;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *
 * @author <a href="mailto:qwx890206@gmail.com">wxqian</a>
 * @version v1.0, 2015/10/14
 */
public class HttpClientUtil {
    private HttpClientUtil() {
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpClientUtil.class);

    /**
     * 功能描述:使用http协议发起get请求 <br>
     * 〈功能详细描述〉
     *
     * @param url
     * @return
     */
    public static String httpGet(String url) {
        HttpClient client = new HttpClient();

        // 使用 GET 方法 ，如果服务器需要通过 HTTPS 连接，那只需要将下面 URL 中的 http 换成 https
        HttpMethod method = new GetMethod(url);

        try {
            int responseCode = client.executeMethod(method);

            LOGGER.info("http responseCode:{}", responseCode);
            // 打印服务器返回的状态
            LOGGER.info("http response StatusLine:{}", method.getStatusLine());
            // 打印返回的信息
            String responseBodyAsString = method.getResponseBodyAsString();

            LOGGER.info("http responseBodyAsString:{}", responseBodyAsString);

            return responseBodyAsString;

        } catch (IOException e) {
            throw new RuntimeException("HTTP GET请求发生异常", e);
        } finally {
            // 释放连接
            method.releaseConnection();
            ((SimpleHttpConnectionManager) client.getHttpConnectionManager()).shutdown();
        }
    }

    /**
     * 功能描述:使用http协议发起 post请求 <br>
     * 〈功能详细描述〉
     *
     * @param url
     * @param data
     * @return
     */
    public static String httpPost(String url, Map<String, String> data) {
        HttpClient client = new HttpClient();

        // 使用POST方法
        PostMethod method = new PostMethod(url);

        method.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
        method.setRequestBody(getNameValue(data));

        try {
            int responseCode = client.executeMethod(method);

            LOGGER.info("http responseCode:{}", responseCode);

            // 打印服务器返回的状态
            LOGGER.info("http response StatusLine:{}", method.getStatusLine());
            // 打印返回的信息
            String responseBodyAsString = method.getResponseBodyAsString();

            LOGGER.info("http responseBodyAsString:{}", responseBodyAsString);

            return responseBodyAsString;
        } catch (Exception e) {
            throw new RuntimeException("HTTP POST请求发生异常", e);
        } finally {
            // 释放连接
            method.releaseConnection();
            ((SimpleHttpConnectionManager) client.getHttpConnectionManager()).shutdown();
        }
    }

    public static String httpPost(String url, String data) {
        HttpClient client = new HttpClient();

        // 使用POST方法
        PostMethod method = new PostMethod(url);

        method.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
        RequestEntity requestEntity;
        try {
            requestEntity = new StringRequestEntity(data, "text/xml", "utf-8");
            method.setRequestEntity(requestEntity);
        } catch (UnsupportedEncodingException e1) {
            LOGGER.error("post请求参数封装异常", e1);
            throw new RuntimeException("post请求参数封装异常", e1);
        }

        try {
            int responseCode = client.executeMethod(method);

            LOGGER.info("http responseCode:{}", responseCode);

            // 打印服务器返回的状态
            LOGGER.info("http response StatusLine:{}", method.getStatusLine());
            // 打印返回的信息
//            String responseBodyAsString = method.getResponseBodyAsString();
            byte[] bytes = method.getResponseBody();
            String responseBodyAsString = new String(bytes, Charset.defaultCharset());

            LOGGER.info("http responseBodyAsString:{}", responseBodyAsString);

            return responseBodyAsString;
        } catch (Exception e) {
            throw new RuntimeException("HTTP POST请求发生异常", e);
        } finally {
            // 释放连接
            method.releaseConnection();
            ((SimpleHttpConnectionManager) client.getHttpConnectionManager()).shutdown();
        }
    }

    /**
     * 功能描述: post xml请求<br>
     * 〈功能详细描述〉
     *
     * @param url
     * @param data
     * @param encoding
     * @return
     */
    public static String httpPost(String url, String data, String encoding) {
        HttpClient client = new HttpClient();

        // 使用POST方法
        PostMethod method = new PostMethod(url);

        RequestEntity requestEntity;
        try {
            requestEntity = new StringRequestEntity(data, "text/xml", encoding);
            method.setRequestEntity(requestEntity);
        } catch (UnsupportedEncodingException e1) {
            LOGGER.error("post请求参数封装异常", e1);
            throw new RuntimeException("post请求参数封装异常", e1);
        }

        try {
            int responseCode = client.executeMethod(method);

            LOGGER.info("http responseCode:{}", responseCode);

            // 打印服务器返回的状态
            LOGGER.info("http response StatusLine:{}", method.getStatusLine());
            // 打印返回的信息
            String responseBodyAsString = method.getResponseBodyAsString();

            LOGGER.info("http responseBodyAsString:{}", responseBodyAsString);

            return responseBodyAsString;
        } catch (Exception e) {
            throw new RuntimeException("HTTP POST请求发生异常", e);
        } finally {
            // 释放连接
            method.releaseConnection();
            ((SimpleHttpConnectionManager) client.getHttpConnectionManager()).shutdown();
        }
    }

    private static NameValuePair[] getNameValue(Map<String, String> param) {

        if (param == null || param.size() == 0) {
            throw new RuntimeException("请求参数不能为空");
        }

        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

        NameValuePair pair;

        final int size = nameValuePairs.size();

        // 迭代MAP
        for (Map.Entry<String, String> entry : param.entrySet()) {

            String key = entry.getKey();
            String value = entry.getValue();

            pair = new NameValuePair();
            pair.setName(key);
            pair.setValue(value);
            nameValuePairs.add(pair);
        }

        return nameValuePairs.toArray(new NameValuePair[size]);
    }
}
