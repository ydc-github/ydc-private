package com.ydc.shiroauth.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.mapping.FetchType;

import com.ydc.shiroauth.po.Role;

@Mapper
public interface RoleMapper {
    @Select("SELECT r.* FROM role LEFT JOIN user_role ur ON r.id=ur.role_id"
            + "WHERE ur.user_id=#{userId}")
    @Results({@Result(column = "role_id", property = "permissions",
            many = @Many(select = "com.ydc.shiroauth.dao.PermissionMapper.getByRole",
                    fetchType = FetchType.LAZY))})
    List<Role> getByUser(String userId);

    @SelectKey(keyProperty = "id", before = true, statement = "select uuid()",
            resultType = String.class)
    @Insert("INSERT INTO `role` (`id`, `name`, `code`) VALUES (#{id}, #{name}, #{code})")
    void insert(Role role);

    @SelectKey(keyProperty = "id", before = true, statement = "select uuid()",
            resultType = String.class)
    @Insert("INSERT INTO `user_role` (`id`, `user_id`, `role_id`) VALUES (#{id}, #{userId}, #{roleId})")
    void insertUserRole(@Param("roleId") String roleId, @Param("userId") String userId);
}
