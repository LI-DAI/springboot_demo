package com.ld.shiro.shiro;

import com.ld.shiro.entity.SecurityProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author ld
 * @Date 2020/4/29 12:13
 */
@Slf4j
@Service
public class PermissionService {

    @Autowired
    private SecurityProperties properties;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 加载权限
     *
     * @return
     */
//    public Map<String, String> loadFilterChainDefinitionMap() {
//        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
//        List<Map<String, Object>> ms = getAllMenus();
//        List<String> anonUrls = properties.getUrl();
//        for (String anon : anonUrls) {
//            filterChainDefinitionMap.put(anon, "anon");
//        }
//        ms.stream().filter(m -> m.get("url") != null && !m.get("url").equals("#") && m.get("perms") != null)
//                .filter(m -> anonUrls.contains(m.get("url")))
//                .forEach(m -> filterChainDefinitionMap.put(String.valueOf(m.get("url")), "jtf,cpf[" + m.get("perms") + "]"));
//        filterChainDefinitionMap.put("/**", "jtf");
//        return filterChainDefinitionMap;
//    }

    /**
     * 更新权限
     */
//    public synchronized void updatePermission() {
//        AbstractShiroFilter shiroFilter = null;
//        try {
//            shiroFilter = (AbstractShiroFilter) shiroFilterFactoryBean.getObject();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        PathMatchingFilterChainResolver filterChainResolver = (PathMatchingFilterChainResolver) shiroFilter.getFilterChainResolver();
//        DefaultFilterChainManager manager = (DefaultFilterChainManager) filterChainResolver.getFilterChainManager();
//        // 清空拦截管理器中的存储
//        manager.getFilterChains().clear();
//        // 清空拦截工厂中的存储,如果不清空这里,还会把之前的带进去
//        shiroFilterFactoryBean.getFilterChainDefinitionMap().clear();
//        // 动态查询数据库中所有权限
//        shiroFilterFactoryBean.setFilterChainDefinitionMap(loadFilterChainDefinitionMap());
//        // 重新构建生成拦截
//        Map<String, String> chains = shiroFilterFactoryBean.getFilterChainDefinitionMap();
//        for (Map.Entry<String, String> entry : chains.entrySet()) {
//            manager.createChain(entry.getKey(), entry.getValue());
//        }
//        log.info("权限添加完成");
//    }

    /**
     * 启动完成后执行
     *
     * @param args
     * @throws Exception
     */
//    @Override
//    public void run(String... args) throws Exception {
//        updatePermission();
//    }


    public List<Map<String, Object>> getAllMenus() {
        return jdbcTemplate.queryForList("SELECT * FROM sys_menu ORDER BY menu_id ASC");
    }

    public List<Map<String, Object>> getRolesByUserId(String userId) {
        String sql = "SELECT r.* FROM sys_role r " +
                "LEFT JOIN sys_user_role ur ON r.role_id=ur.role_id " +
                "WHERE ur.user_id='" + userId + "' AND r.delete_flag='N'";
        return jdbcTemplate.queryForList(sql);
    }

    public List<Map<String, Object>> getMenusByUserId(String userId) {
        String sql = "SELECT DISTINCT m.* FROM sys_menu m" +
                "          LEFT JOIN sys_role_menu  rm ON m.menu_id=rm.menu_id" +
                "          LEFT JOIN sys_role r on rm.role_id = r.role_id" +
                "          LEFT JOIN sys_user_role  ur ON r.role_id= ur.role_id" +
                "        WHERE ur.user_id='" + userId + "'AND r.delete_flag='N'";
        return jdbcTemplate.queryForList(sql);
    }
}
