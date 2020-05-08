package com.ld.shiro.entity.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.servlet.http.HttpServletResponse;

/**
 * @Author ld
 * @Date 2020/3/5 10:57
 */
@Getter
@AllArgsConstructor
public enum ResultCode implements IResultCode {


    /**
     * 操作成功
     */
    SUCCESS(HttpServletResponse.SC_OK, "操作成功"),

    /**
     * 业务异常
     */
    FAILURE(HttpServletResponse.SC_BAD_REQUEST, "业务异常"),

    /**
     * 无访问权限
     */
    UN_AUTHORIZED(HttpServletResponse.SC_UNAUTHORIZED, "无访问权限"),

    /**
     * 404 没找到请求
     */
    NOT_FOUND(HttpServletResponse.SC_NOT_FOUND, "404 没找到请求"),

    /**
     * 消息不能读取
     */
    MSG_NOT_READABLE(HttpServletResponse.SC_BAD_REQUEST, "消息不能读取"),

    /**
     * 不支持当前请求方法
     */
    METHOD_NOT_SUPPORTED(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "不支持当前请求方法"),

    /**
     * 不支持当前媒体类型
     */
    MEDIA_TYPE_NOT_SUPPORTED(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE, "不支持当前媒体类型"),

    /**
     * 请求被拒绝
     */
    REQ_REJECT(HttpServletResponse.SC_FORBIDDEN, "请求被拒绝"),

    /**
     * 服务器异常
     */
    INTERNAL_SERVER_ERROR(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "服务器异常"),

    /**
     * 缺少必要的请求参数
     */
    PARAM_MISS(HttpServletResponse.SC_BAD_REQUEST, "缺少必要的请求参数"),

    /**
     * 请求参数类型错误
     */
    PARAM_TYPE_ERROR(HttpServletResponse.SC_BAD_REQUEST, "请求参数类型错误"),

    /**
     * 请求参数绑定错误
     */
    PARAM_BIND_ERROR(HttpServletResponse.SC_BAD_REQUEST, "请求参数绑定错误"),

    /**
     * 参数校验失败
     */
    PARAM_VALID_ERROR(HttpServletResponse.SC_BAD_REQUEST, "参数校验失败"),

    /**
     * 未登录
     */
    UN_AUTHENTICATION(-100, "未登录"),

    /**
     * 无效Token
     */
    EXPIRE_TOKEN(-200, "无效的Token"),

    /**
     * 账户失效
     */
    ACCOUNT_EXPIRE(-300, "账户已失效，请重新登陆"),

    /**
     * 账号锁定
     */
    ACCOUNT_LOCKED(-400, "账户已被锁定，无法登陆");

    /**
     * code编码
     */
    final int code;
    /**
     * 中文信息描述
     */
    final String message;

}
