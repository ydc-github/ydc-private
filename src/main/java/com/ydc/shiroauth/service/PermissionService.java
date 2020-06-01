package com.ydc.shiroauth.service;

import com.ydc.shiroauth.dao.PermissionMapper;
import com.ydc.shiroauth.po.Permission;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class PermissionService {
    @Resource
    private PermissionMapper mapper;

    @Transactional
    public void add(String code,String name){
        Permission p = new Permission();
        p.setCode(code);
        p.setName(name);
        mapper.insert(p);
    }
}
