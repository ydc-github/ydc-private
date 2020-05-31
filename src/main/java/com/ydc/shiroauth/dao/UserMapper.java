package com.ydc.shiroauth.dao;

import com.ydc.shiroauth.po.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

@Mapper
public interface UserMapper {
    @Select("select * from user where id=#{id}")
    User get(String id);

    @Select("select * from user where account=#{account}")
    @Results({
            @Result(column = "user_id", property = "roles", many = @Many(select = "com.ydc.shiroauth.dao.RoleMapper.getByUser", fetchType = FetchType.LAZY)),
    })
    User getByAccount(String account);

    @SelectKey(keyProperty = "id", before = true, statement = "select uuid()", resultType = String.class)
    @Insert("INSERT INTO `user` (`id`, `name`, `account`, `password`, `sex`) VALUES (#{id},#{name},#{account},#{password},#{sex})")
    void insert(User user);
}
