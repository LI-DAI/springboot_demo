package com.ld.shiro.utils;

import com.alibaba.fastjson.JSONObject;
import com.ld.shiro.entity.common.R;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author ld
 * @Date 2020/3/11 13:52
 */
public class WebUtils {

    /**
     * 输出
     *
     * @param r
     * @param response
     */
    public static void out(R r, HttpServletResponse response) {
        response.setContentType("application/json;charset=UTF-8");
        try (PrintWriter writer = response.getWriter()) {
            writer.println(JSONObject.toJSONString(r));
//            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
