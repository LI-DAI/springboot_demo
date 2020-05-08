package com.ld.shiro.shiro.filter;

import com.ld.shiro.entity.common.R;
import com.ld.shiro.entity.common.ResultCode;
import com.ld.shiro.utils.WebUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author ld
 * @Date 2020/4/28 12:46
 */
public class CustomPermissionFilter extends AccessControlFilter {

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        Subject subject = getSubject(request, response);
        String[] perms = (String[]) mappedValue;
        boolean isPermitted = true;
        if (perms != null && perms.length > 0) {
            if (perms.length == 1) {
                if (!subject.isPermitted(perms[0])) {
                    isPermitted = false;
                }
            } else {
                if (!subject.isPermittedAll(perms)) {
                    isPermitted = false;
                }
            }
        }
        return isPermitted;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) {
        WebUtils.out(R.fail(ResultCode.UN_AUTHORIZED), (HttpServletResponse) response);
        return false;
    }
}
