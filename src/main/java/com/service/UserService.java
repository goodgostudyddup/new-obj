package com.service;

import com.pojo.User;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserService {
    List<Map<String, Object>> getlist();
    boolean deleteUser(int u_id);

    boolean alterUser(User user);

    boolean addUser(User user);

    boolean selectUserById(int u_id);

    public List<Map<String, User>> selectUserByAllField(User user);

    Map<String,Object>  login(int u_id);
}
