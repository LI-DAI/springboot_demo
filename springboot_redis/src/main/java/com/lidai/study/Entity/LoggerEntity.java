/*
 * Copyright (C), 2013-2018, 天津大海云科技有限公司
 */
package com.lidai.study.Entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author lidai
 * @date 2018/10/22 11:10
 */
public class LoggerEntity implements Serializable {

    private String id;
    private String clientIp;
    private String uri;
    private String type;
    private String method;
    private String paramData;
    private String sessionId;
    private Date time;
    private Date returnTime;
    private String returnData;
    private String httpStatusCode;
    private int timeConsuming;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getParamData() {
        return paramData;
    }

    public void setParamData(String paramData) {
        this.paramData = paramData;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Date getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(Date returnTime) {
        this.returnTime = returnTime;
    }

    public String getReturnData() {
        return returnData;
    }

    public void setReturnData(String returnData) {
        this.returnData = returnData;
    }

    public String getHttpStatusCode() {
        return httpStatusCode;
    }

    public void setHttpStatusCode(String httpStatusCode) {
        this.httpStatusCode = httpStatusCode;
    }

    public int getTimeConsuming() {
        return timeConsuming;
    }

    public void setTimeConsuming(int timeConsuming) {
        this.timeConsuming = timeConsuming;
    }

    @Override
    public String toString() {
        return "LoggerEntity{" +
                "id='" + id + '\'' +
                ", clientIp='" + clientIp + '\'' +
                ", uri='" + uri + '\'' +
                ", type='" + type + '\'' +
                ", method='" + method + '\'' +
                ", paramData='" + paramData + '\'' +
                ", sessionId='" + sessionId + '\'' +
                ", time=" + time +
                ", returnTime=" + returnTime +
                ", returnData='" + returnData + '\'' +
                ", httpStatusCode='" + httpStatusCode + '\'' +
                ", timeConsuming=" + timeConsuming +
                '}';
    }
}