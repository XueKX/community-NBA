package org.carry.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.carry.model.User;

/**
 * @Author: CARRY
 * @CreateTime: 2019-06-15 21:17
 * @Description: 访问数据的，进行数据库操作
 */
@Mapper
public interface UserMapper {
    @Insert("insert into user (name,account_id,token,gmt_create,gmt_modified) values (#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified})")
    void inster(User user);

    @Select("select * from user where token =#{token}")
        //类不用写，变量要加@Param
    User findByToken(@Param("token") String token);

    @Select("select * from user where id = #{id}")
    User findById(@Param("id") Long id);
}
