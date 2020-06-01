package com.ydc.shiroauth.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.mapping.FetchType;

import com.ydc.shiroauth.po.User;

@Mapper
public interface UserMapper {
    @Select("select * from user where id=#{id}")
    User get(String id);

    @Select("select * from user where account=#{account}")
    @Results({@Result(column = "user_id", property = "roles",
            many = @Many(select = "com.ydc.shiroauth.dao.RoleMapper.getByUser",
                    fetchType = FetchType.LAZY))})
    User getByAccount(String account);

    @SelectKey(keyProperty = "id", before = true, statement = "select uuid()",
            resultType = String.class)
    @Insert("INSERT INTO `user` (`id`, `name`, `account`, `password`, `sex`) VALUES (#{id},#{name},#{account},#{password},#{sex})")
    void insert(User user);
}
