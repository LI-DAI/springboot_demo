package com.alibaba.cloudapi.sdk.model;

import com.alibaba.cloudapi.sdk.constant.HttpConstant;
import com.alibaba.cloudapi.sdk.constant.SdkConstant;
import com.alibaba.fastjson.JSONObject;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class ApiHttpMessage {
    protected byte[] body;
    /*
    只有在websocket应答中可以读取bodyStr
     */
    protected String bodyStr;
    protected Map<String, List<String>> headers = new HashMap<String, List<String>>();

    public String getBodyStr() {
        return bodyStr;
    }

    public byte[] getBody() {
        return body;
    }
    public void setBody(byte[] body) {
        this.body = body;
    }
    public Map<String, List<String>> getHeaders() {
        return headers;
    }
    public void setHeaders(Map<String, List<String>> headers) {
        this.headers = headers;
    }

    public void addHeader(String name , String value){
        name = name.trim().toLowerCase();
        if(headers.containsKey(name)){
            headers.get(name).add(value);
        }
        else{
            List<String> values = new ArrayList<String>();
            values.add(value == null ? "" : value.trim());
            headers.put(name , values);
        }
    }

    public String getFirstHeaderValue(String name){
        if(headers.containsKey(name) && headers.get(name).size() > 0){
            return headers.get(name).get(0);
        }

        return null;
    }

    public void parse(JSONObject message){
        JSONObject headers = message.getJSONObject("header");
        for(Map.Entry<String, Object> header : headers.entrySet()){
            if(header.getValue() instanceof String){
                addHeader(header.getKey() , (String)header.getValue());
            }
            else if(header.getValue() instanceof List){
                List<String> values = (List<String>)header.getValue();
                for(String value : values){
                    addHeader(header.getKey() , value);
                }
            }
        }

        String contentType = headers.getString(HttpConstant.CLOUDAPI_HTTP_HEADER_CONTENT_TYPE);
        Charset charset = SdkConstant.CLOUDAPI_ENCODING;
        if(null  != contentType){
            try{
                contentType = contentType.toLowerCase();
                String[] charsetStr = contentType.split(";");
                for(int i = 0 ; i < charsetStr.length ; i++){
                    if(charsetStr[i].contains("charset")){
                        charset = Charset.forName(charsetStr[i].substring(charsetStr[i].indexOf("=")));
                    }
                }
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }

        bodyStr = message.getString("body");
        body = bodyStr.getBytes(charset);
    }
}
