package com.ydc.shiroauth.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

import com.ydc.shiroauth.dao.UserMapper;
import com.ydc.shiroauth.po.Role;
import com.ydc.shiroauth.po.User;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class LoginService {
    @Resource
    private UserMapper userMapper;

    public User get(String loginAccount) {
        User u = userMapper.getByAccount(loginAccount);
        List<Role> roles = u.getRoles();
        for (Role r : roles) {
            r.setPermissions(r.getPermissions());
        }
        u.setRoles(roles);
        return u;
    }

    public void login(String name, String password) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken uToken = new UsernamePasswordToken(name, password);
        // 实现记住我
        uToken.setRememberMe(true);
        try {
            // 进行验证，报错返回首页，不报错到达成功页面。
            subject.login(uToken);
            log.info("user {} login success", name);
            // return "success";
        } catch (UnknownAccountException e) {
            throw new RuntimeException("用户不存在");
            // m.addObject("result", "用户不存在");
            // m.setViewName("login");
            // return m;
            // return "faild";
        } catch (IncorrectCredentialsException e) {
            throw new RuntimeException("密码错误");
            // m.addObject("result", "密码错误");
            // m.setViewName("login");
            // return m;
            // return "faild";
        }
    }

    public User getLoginUser() {
        String name = (String) SecurityUtils.getSubject().getPrincipal();
        return userMapper.getByAccount(name);
    }

}
