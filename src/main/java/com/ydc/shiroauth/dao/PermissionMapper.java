package com.ydc.shiroauth.dao;

import com.ydc.shiroauth.po.Permission;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;

import java.util.List;

@Mapper
public interface PermissionMapper {
    @Select("SELECT p.* FROM permission p LEFT JOIN role_permission rp on p.id=rp.role_id WHERE role_id=#{roleId}")
    List<Permission> getByRole(String roleId);

    @SelectKey(keyProperty = "id", before = true, statement = "select uuid()", resultType = String.class)
    @Insert("INSERT INTO `permission`(`id`, `name`, `code`) VALUES (#{id},#{name},#{code})")
    void insert(Permission p);
}
