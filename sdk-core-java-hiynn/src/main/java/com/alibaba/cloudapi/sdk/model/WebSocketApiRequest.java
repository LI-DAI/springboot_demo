package com.alibaba.cloudapi.sdk.model;

import java.util.List;
import java.util.Map;

/**
 * Created by fred on 2017/7/31.
 */
public class WebSocketApiRequest {
    String method;
    String host;
    String path;
    Map<String , String> querys;
    Map<String, List<String>> headers;
    int isBase64 = 0;
    String body;



    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Map<String, List<String>> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, List<String>> headers) {
        this.headers = headers;
    }

    public Map<String, String> getQuerys() {
        return querys;
    }

    public void setQuerys(Map<String, String> querys) {
        this.querys = querys;
    }

    public int getIsBase64() {
        return isBase64;
    }

    public void setIsBase64(int isBase64) {
        this.isBase64 = isBase64;
    }
}
