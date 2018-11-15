package com.example.demo.enums;

/**
 * @author lidai
 * @date 2018/10/30 15:45
 * 统一响应状态
 */
public enum ResultStatus {
    /**
     * 2XX 权限相关code
     */
    UNAUTHORIZED    (-200, "用户权限不足"),
    /**
     * 1XX 用户登录相关
     */
    USER_NOT_EXIST  (-103, "用户不存在"),
    INVALID_TOKEN   (-102, "无效的Token"),
    LOGIN_FAILED    (-101, "用户登录失败"),
    UNAUTHENTICATED (-100, "用户未登录"),
    /**
     * 通用接口调用
     */
    REQUEST_FAILED  (0,  "接口请求失败"),
    REQUEST_SUCCESS (1,  "接口请求成功");

    /**
     * 响应状态码
     */
    private final int code;
    /**
     * 响应状态信息
     */
    private final String message;

    ResultStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int code() {
        return code;
    }

    public String message() {
        return message;
    }

    public static void main(String[] args) {
        System.out.println(ResultStatus.LOGIN_FAILED.code());
    }

}
