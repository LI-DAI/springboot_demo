package com.example.demo.entity;

import com.example.demo.enums.ResultStatus;

/**
 * @author lidai
 * @date 2018/10/30 15:45
 * 统一响应对象
 */
public class Result {

    /**
     * 请求状态码
     */
    private int code;
    /**
     * 请求提示信息
     */
    private String msg;
    /**
     * 请求响应内容
     */
    private Object result;

    /**
     * 私有构造方法，即禁止通过 {@code new Result()} 方式构造一个该类型的对象
     * 可通过 {@link Result#build()} 方法获取一个该类型的对象
     *
     * @see Result#build()
     */
    private Result() {}

    /**
     * 构造Result对象
     * @return Result对象
     */
    public static Result build() {
        return new Result();
    }

    /**
     * 接口请求成功
     * @return {@link Result}
     */
    public Result success() {
        return this.success(null);
    }

    public Result success(Object result) {
        return this.success(ResultStatus.REQUEST_SUCCESS.message(), result);
    }

    public Result success(String msg, Object result) {
        this.code = ResultStatus.REQUEST_SUCCESS.code();
        this.msg = msg;
        this.result = result;
        return this;
    }

    /**
     * 所有成功的响应可以通过这个方法返回
     * @param code      请求失败状态码，具体参考{@link ResultStatus}
     * @param msg       请求失败提示信息
     * @param result    请求失败返回给前端的信息，一般情况下这个值为null或其他空对象
     * @return          {@link Result}
     */
    public Result success(int code, String msg, Object result) {
        this.code = code;
        this.msg = msg;
        this.result = result;
        return this;
    }

    /**
     * 接口请求失败
     * @return {@link Result}
     */
    public Result fail() {
        return fail(null);
    }

    public Result fail(Object result) {
        return this.fail(ResultStatus.REQUEST_FAILED.message(), result);
    }

    public Result fail(String msg, Object result) {
        this.code = ResultStatus.REQUEST_FAILED.code();
        this.msg = msg;
        this.result = result;
        return this;
    }

    /**
     * 所有失败的响应可以通过这个方法返回
     * @param code      请求失败状态码，具体参考{@link ResultStatus}
     * @param msg       请求失败提示信息
     * @param result    请求失败返回给前端的信息，一般情况下这个值为null或其他空对象
     * @return          {@link Result}
     */
    public Result fail(int code, String msg, Object result) {
        this.code = code;
        this.msg = msg;
        this.result = result;
        return this;
    }

    /**
     * 用户未登录
     * @return  {@link Result}
     */
    public Result unauthenticated() {
        return unauthenticated(null);
    }

    public Result unauthenticated(Object result) {
        return unauthenticated(ResultStatus.UNAUTHENTICATED.message(), result);
    }

    public Result unauthenticated(String msg, Object result) {
        this.code = ResultStatus.UNAUTHENTICATED.code();
        this.msg = msg;
        this.result = result;
        return this;
    }

    /**
     * 用户权限不足
     * @return  {@link Result}
     */
    public Result unauthorized() {
        return unauthorized(null);
    }

    public Result unauthorized(Object result) {
        return unauthorized(ResultStatus.UNAUTHORIZED.message(), result);
    }

    public Result unauthorized(String msg, Object result) {
        this.code = ResultStatus.UNAUTHORIZED.code();
        this.msg = msg;
        this.result = result;
        return this;
    }

    /**
     * 用户不存在
     * @return  {@link Result}
     */
    public Result userNotExist() {
        return userNotExist(null);
    }

    public Result userNotExist(Object result) {
        return userNotExist(ResultStatus.USER_NOT_EXIST.message(), result);
    }

    public Result userNotExist(String msg, Object result) {
        this.code = ResultStatus.USER_NOT_EXIST.code();
        this.msg = msg;
        this.result = result;
        return this;
    }

    /**
     * 用户登录失败
     * @return  {@link Result}
     */
    public Result loginFailed() {
        return loginFailed(null);
    }

    public Result loginFailed(Object result) {
        return loginFailed(ResultStatus.LOGIN_FAILED.message(), result);
    }

    public Result loginFailed(String msg, Object result) {
        this.code = ResultStatus.LOGIN_FAILED.code();
        this.msg = msg;
        this.result = result;
        return this;
    }

    /**
     * 无效的Token
     * @return  {@link Result}
     */
    public Result invalidToken() {
        return invalidToken(null);
    }

    public Result invalidToken(Object result) {
        return invalidToken(ResultStatus.INVALID_TOKEN.message(), result);
    }

    public Result invalidToken(String msg, Object result) {
        this.code = ResultStatus.INVALID_TOKEN.code();
        this.msg = msg;
        this.result = result;
        return this;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
