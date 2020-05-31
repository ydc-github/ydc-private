package com.ydc.shiroauth.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ydc.shiroauth.po.User;
import com.ydc.shiroauth.service.UserService;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Random;

@Slf4j
@RestController
@RequestMapping("user")
public class UserController {

  @Autowired
  private UserService service;

  @GetMapping("add")
  @RequiresPermissions("user:add")
  public void add() {
    int r = new Random().nextInt(100);
    User u = new User();
    u.setAccount("test"+r);
    u.setPassword("123456");
    u.setName("test"+r);
    u.setSex(1);
    service.add(u);
  }

  @GetMapping("addRoles")
  @RequiresPermissions("add")
  public String addRoles(String userId,String[] roleIds){
    service.userSetRoles(userId, Arrays.asList(roleIds));
    return "ok";
  }

  @GetMapping("get")
  public User get() {
    log.debug("user get ");
    User u = new User();
    u.setName("test");
    return u;
  }

}
