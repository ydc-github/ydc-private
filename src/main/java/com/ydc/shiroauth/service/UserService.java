package com.ydc.shiroauth.service;

import java.util.ArrayList;
import java.util.List;

import com.ydc.shiroauth.dao.RoleMapper;
import com.ydc.shiroauth.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ydc.shiroauth.po.Permission;
import com.ydc.shiroauth.po.Role;
import com.ydc.shiroauth.po.User;

import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Slf4j
@Service
public class UserService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private RoleMapper roleMapper;

    public User getByAccount(String loginAccount) {
        User u = userMapper.getByAccount(loginAccount);
        List<Role> roles = u.getRoles();
        for (Role r : roles) {
            r.setPermissions(r.getPermissions());
        }
        u.setRoles(roles);
        return u;
    }

    public void add(User u) {
        log.debug("add user:u={}", u.getName());
        userMapper.insert(u);
    }

    @Transactional
    public void userSetRoles(String userId, List<String> roleIds) {
        for (String role : roleIds) {
            roleMapper.insertUserRole(role, userId);
        }
    }
}
