package com.ydc.shiroauth.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ydc.shiroauth.service.PermissionService;

@RestController
@RequestMapping("permission")
public class PermissionController {
    @Autowired
    private PermissionService service;

    @GetMapping("add")
    @RequiresPermissions("add")
    public String add(String code, String name) {
        service.add(code, name);
        return "permission add ok";
    }
}
