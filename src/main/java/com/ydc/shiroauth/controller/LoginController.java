package com.ydc.shiroauth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ydc.shiroauth.service.LoginService;

@RestController
public class LoginController {

  @Autowired
  private LoginService service;

  @GetMapping("login")
  public String login(String name, String password) {
    service.login(name, password);
    return "ok";
  }

  @GetMapping("index")
  public String index() {
    return "index";
  }

  @GetMapping("403")
  public String error() {
    return "error";
  }


}
