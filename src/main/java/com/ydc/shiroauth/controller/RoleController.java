package com.ydc.shiroauth.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ydc.shiroauth.po.Role;
import com.ydc.shiroauth.service.RoleService;

@RestController
@RequestMapping("role")
public class RoleController {
    @Autowired
    private RoleService service;

    @GetMapping("add")
    @RequiresPermissions("add")
    public String add(String code, String name) {
        Role r = new Role();
        r.setName(name);
        r.setCode(code);
        service.add(r);
        return "ok";
    }

    @GetMapping("addPermission")
    @RequiresPermissions("add")
    public String addPermission(String roleId,String[] permissionIds){

        return "ok";
    }

}
