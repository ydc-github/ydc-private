package com.ydc.shiroauth.dao;

import com.ydc.shiroauth.po.Permission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PermissionMapper {
    @Select("SELECT p.* FROM permission p LEFT JOIN role_permission rp on p.id=rp.role_id WHERE role_id=#{roleId}")
    List<Permission> getByRole(String roleId);
}
