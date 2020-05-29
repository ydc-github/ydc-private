package com.ydc.shiroauth.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ydc.shiroauth.po.Permission;
import com.ydc.shiroauth.po.Role;
import com.ydc.shiroauth.po.User;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserService {

  public User get(String loginAccount) {
    // 暂时写死
    if (!"test".equals(loginAccount)) {
      return null;
    }
    User u = new User();
    u.setPassword("123");
    List<Role> roles = new ArrayList<>();
    Role r = new Role();
    r.setName("用戶管理");
    r.setCode("sys");
    List<Permission> permissions = new ArrayList<>();
    Permission p = new Permission();
    p.setCode("add");
    permissions.add(p);
    r.setPermissions(permissions);
    roles.add(r);
    u.setRoles(roles);
    return u;
  }

  public void add(User u) {
    log.debug("add user:u={}", u.getName());
  }

}
