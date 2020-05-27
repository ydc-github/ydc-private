package com.ydc.shiroauth.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ydc.shiroauth.po.User;
import com.ydc.shiroauth.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("user")
public class UserController {

  @Autowired
  private UserService service;

  @GetMapping("add")
  @RequiresPermissions("add")
  public void add() {
    log.debug("user add controller");
    User u = new User();
    u.setName("test");
    service.add(u);
  }

  @GetMapping("get")
  public User get() {
    log.debug("user get ");
    User u = new User();
    u.setName("test");
    return u;
  }

}
