package com.mapper;


import com.bean.User;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.apache.ibatis.annotations.*;
import org.springframework.core.NestedRuntimeException;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;


import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Map;

public interface UserMapper {

    int deleteUser(int u_id);
    @Deprecated
    Map<String,User> login(int u_id);
    @Deprecated
    Integer selectUserById(int u_id);
    @Deprecated
    public List<Map<String, User>> selectUserByAllField(User user);

    /**
     * @needRename
     */
    int addUser(User user);


    @Select("select * from user")
    public List<User> getAll();
    @Select("select * from user where u_id=#{u_id}")
    public User findOneById(@Param("u_id") Integer u_id);
    @Select("select * from user where u_id=#{u_id} and u_pwd=#{u_pwd}")
    public User findOneByIdAndPassword(@Param("u_id") Integer u_id, @Param("u_pwd") String u_pwd);
    @Delete("delete from user where u_id=#{u_id}")
    public Boolean deleteOneById(@Param("u_id") Integer u_id);
    @Insert("insert into user values(#{u_id}, #{u_name}, #{u_pwd})")
    public Boolean pushOne(User user);

}
