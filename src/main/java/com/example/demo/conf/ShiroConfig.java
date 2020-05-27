package com.example.demo.conf;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class ShiroConfig {

  /**
   * ShiroFilterFactoryBean 处理拦截资源文件问题。
   * 注意：单独一个ShiroFilterFactoryBean配置是或报错的，因为在初始化ShiroFilterFactoryBean的时候需要注入：SecurityManager Filter
   * Chain定义说明 1、一个URL可以配置多个Filter，使用逗号分隔 2、当设置多个过滤器时，全部验证通过，才视为通过 3、部分过滤器可指定参数，如perms，roles
   */
  @Bean
  public ShiroFilterFactoryBean shirFilter(
      @Qualifier("securityManager") DefaultWebSecurityManager securityManager) {
    ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
    // 必须设置 SecurityManager
    shiroFilterFactoryBean.setSecurityManager(securityManager);
    // 常用的过滤器：
    // anon： 无需认证（登录）可以访问
    // authc： 必须认证才可以访问
    // user： 如果使用rememberMe的功能可以直接访问
    // perms： 该资源必须得到角色权限才可以访问
    // role： 该资源必须得到角色权限才可以访问
    // 访问的是后端url地址为 /login的接口
    shiroFilterFactoryBean.setLoginUrl("/login");
    // 登录成功后要跳转的链接
    shiroFilterFactoryBean.setSuccessUrl("/index");
    // 未授权界面
    shiroFilterFactoryBean.setUnauthorizedUrl("/403");

    // 拦截器.
    Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
    // 配置不会被拦截的链接 顺序判断
    filterChainDefinitionMap.put("/static/**", "anon");
    filterChainDefinitionMap.put("/login", "anon");

    // 配置退出过滤器,其中的具体的退出代码Shiro已经替我们实现了
    filterChainDefinitionMap.put("/logout", "logout");

    // 配置某个url需要某个权限码
    filterChainDefinitionMap.put("/*/add", "perms[add]");

    // 过滤链定义，从上向下顺序执行，一般将 /**放在最为下边
    // <!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
    filterChainDefinitionMap.put("/", "user");
    filterChainDefinitionMap.put("/**", "authc");

    shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
    log.debug("Shiro拦截器工厂类注入成功");
    return shiroFilterFactoryBean;
  }

  @Bean(name = "securityManager")
  public DefaultWebSecurityManager getDefaultWebSecurityManager(
      @Qualifier("userRealm") TestRealm userRealm,
      @Qualifier("cacheManager") CacheManager cacheManager) {
    DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
    // 关联Realm
    defaultWebSecurityManager.setRealm(userRealm);
    defaultWebSecurityManager.setCacheManager(cacheManager);
    return defaultWebSecurityManager;
  }

  @Bean("cacheManager")
  public CacheManager cacheManager() {
    return new MemoryConstrainedCacheManager();
  }

  @Bean(name = "userRealm")
  TestRealm testRealm() {
    return new TestRealm();
  }

}
