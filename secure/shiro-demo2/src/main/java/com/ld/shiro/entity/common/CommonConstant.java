package com.ld.shiro.entity.common;

/**
 * @Author lidai
 * @Date 2020/3/5 11:01
 */
public interface CommonConstant {

    /**
     * 编码
     */
    String UTF_8 = "UTF-8";

    /**
     * 删除状态[0:正常,1:删除]
     */
    int DB_NOT_DELETED = 0;
    int DB_IS_DELETED = 1;

    /**
     * 用户锁定状态
     */
    int DB_ADMIN_NON_LOCKED = 0;
    int DB_ADMIN_LOCKED = 1;

    /**
     * 日志默认状态
     */
    String LOG_NORMAL_TYPE = "1";

    /**
     * 默认为空消息
     */
    String DEFAULT_NULL_MESSAGE = "暂无数据";
    /**
     * 默认成功消息
     */
    String DEFAULT_SUCCESS_MESSAGE = "操作成功";
    /**
     * 默认失败消息
     */
    String DEFAULT_FAILURE_MESSAGE = "操作失败";
    /**
     * 默认未授权消息
     */
    String DEFAULT_UNAUTHORIZED_MESSAGE = "签名认证失败";

    /**
     * 认证模块服务名
     */
    String AUTH_SERVICE_NAME = "bmsys-auth-service";

    /**
     * 字典模块服务名
     */
    String DICT_SERVICE_NAME = "bmsys-dict-service";

    /**
     * 邮箱正则
     */
    String EMAIL_REGEX = "^\\s*?(.+)@(.+?)\\s*$";

    /**
     * 验证信息请求头
     */
    String AUTHENTICATION = "Token";


}
