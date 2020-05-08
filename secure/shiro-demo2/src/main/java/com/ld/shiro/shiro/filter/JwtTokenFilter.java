package com.ld.shiro.shiro.filter;

import com.ld.shiro.entity.SecurityProperties;
import com.ld.shiro.entity.common.CommonConstant;
import com.ld.shiro.entity.common.R;
import com.ld.shiro.entity.common.ResultCode;
import com.ld.shiro.shiro.JwtToken;
import com.ld.shiro.utils.JwtUtil;
import com.ld.shiro.utils.WebUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @Author ld
 * @Date 2020/4/27 14:26
 */
public class JwtTokenFilter extends AccessControlFilter {

    private SecurityProperties properties;

    private StringRedisTemplate redisTemplate;

    public JwtTokenFilter(SecurityProperties properties, StringRedisTemplate redisTemplate) {
        this.properties = properties;
        this.redisTemplate = redisTemplate;
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) {
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String onlineToken = request.getHeader(CommonConstant.AUTHENTICATION);
        if (!StringUtils.hasText(onlineToken)) {
            //无token，则未登陆
            WebUtils.out(R.fail(ResultCode.UN_AUTHENTICATION), response);
            return false;
        }
        String jwtToken = onlineToken.substring(properties.getOnlineKey().length());
        try {
            Map<String, Object> userMap = JwtUtil.parseJwtToken(jwtToken);
        } catch (Exception e) {
            e.printStackTrace();
            //解析token异常
            WebUtils.out(R.fail(ResultCode.EXPIRE_TOKEN), response);
            return false;
        }
        String redisToken = redisTemplate.opsForValue().get(onlineToken);
        if (!StringUtils.hasText(redisToken)) {
            //jwt正常，redis中找不到，则被踢掉了
            WebUtils.out(R.fail(ResultCode.ACCOUNT_EXPIRE), response);
            return false;
        }
        JwtToken token = new JwtToken(jwtToken);
        SecurityUtils.getSubject().login(token);
        return true;
    }
}
