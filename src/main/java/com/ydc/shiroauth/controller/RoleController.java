package com.ydc.shiroauth.controller;

import com.ydc.shiroauth.po.Role;
import com.ydc.shiroauth.service.RoleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequestMapping("role")
public class RoleController {
    @Autowired
    private RoleService service;

    @GetMapping("add")
    @RequiresPermissions("add")
    public String add(String code,String name){
        String userId="971461d2-a344-11ea-889c-34298f99a1af";
        Role r = new Role();
        r.setName(name);
        r.setCode(code);
        service.add(userId,r);
        return "ok";
    }

}