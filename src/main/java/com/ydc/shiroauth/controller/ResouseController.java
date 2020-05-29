package com.ydc.shiroauth.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("menu")
public class ResouseController {

  @GetMapping("add")
  @RequiresPermissions("menu:add")
  public String add() {
    return "add ok";
  }

}
