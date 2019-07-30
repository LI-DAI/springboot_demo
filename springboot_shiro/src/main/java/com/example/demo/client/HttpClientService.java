package com.example.demo.client;

import com.example.demo.entity.Result;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @auther lidai
 * @create 2019/1/11 19:08
 */
@Service
public class HttpClientService {

    private CloseableHttpClient httpClient;

    public HttpClientService(){
        this.httpClient = HttpClients.createDefault();
    }

    public Result doGet(String url, Map<String, Object> map) throws Exception {

        URIBuilder uriBuilder = new URIBuilder(url);
        if(!map.isEmpty()){
            map.forEach((key,value)->uriBuilder.setParameter(key,value.toString()));
        }

        HttpGet httpGet = new HttpGet(uriBuilder.build());
        String token ="eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJ1c2VyIiwiZXhwIjoxNTQ3MjQ5MzYwLCJpYXQiOjE1NDcyMDYxNjAsInVzZXJuYW1lIjoiYWRtaW4ifQ.SbTEU3FI9IsK3VPzTf9Lrr1dXhrhkPQEAOpuhgq2czQc0ETgTXcjZh66G-eHHctktkWNpKjEqGagBuNa34I2xQ";
        httpGet.setHeader("Authorization",token);

        CloseableHttpResponse response = this.httpClient.execute(httpGet);
        // 返回
        return Result.build().success("sucess",EntityUtils.toString(response.getEntity()));
    }


    public Result doPost(String url, String jsonParam) throws Exception {
        // 声明httpPost请求
        HttpPost httpPost = new HttpPost(url);

        // 判断map不为空
//        if (map != null) {
//            // 声明存放参数的List集合
//            List<NameValuePair> params = new ArrayList<NameValuePair>();
//            // 遍历map，设置参数到list中
//            for (Map.Entry<String, Object> entry : map.entrySet()) {
//                params.add(new BasicNameValuePair(entry.getKey(), entry.getValue().toString()));
//            }
//            // 创建form表单对象
//            UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(params, "UTF-8");
//            // 把表单对象设置到httpPost中
//            httpPost.setEntity(formEntity);
//        }
        String token ="eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJ1c2VyIiwiZXhwIjoxNTQ3MjQ5MzYwLCJpYXQiOjE1NDcyMDYxNjAsInVzZXJuYW1lIjoiYWRtaW4ifQ.SbTEU3FI9IsK3VPzTf9Lrr1dXhrhkPQEAOpuhgq2czQc0ETgTXcjZh66G-eHHctktkWNpKjEqGagBuNa34I2xQ";
        httpPost.setHeader("Authorization",token);
        httpPost.addHeader("Content-Type", "application/json");
        httpPost.setEntity(new StringEntity(jsonParam));
        // 使用HttpClient发起请求，返回response
        CloseableHttpResponse response = this.httpClient.execute(httpPost);

        // 返回结果
        return Result.build().success(EntityUtils.toString(response.getEntity()));
    }
}
