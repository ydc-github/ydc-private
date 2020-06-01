package com.ydc.shiroauth.service;

import com.ydc.shiroauth.dao.RoleMapper;
import com.ydc.shiroauth.po.Role;
import com.ydc.shiroauth.po.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.beans.Transient;
import java.util.List;

@Service
public class RoleService {
    @Resource
    private RoleMapper mapper;

    @Transactional
    public void add(Role role){
        mapper.insert(role);
    }


}
