package com.ydc.shiroauth.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ydc.shiroauth.dao.RoleMapper;
import com.ydc.shiroauth.po.Role;

@Service
public class RoleService {
    @Resource
    private RoleMapper mapper;

    @Transactional
    public void add(Role role) {
        mapper.insert(role);
    }


}
