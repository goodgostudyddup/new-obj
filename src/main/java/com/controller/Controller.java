package com.controller;

import com.bean.User;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.NestedRuntimeException;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class Controller {

    @Autowired
    private UserService userService;



    /**
     * 接口名:selectAll
     * 请求方式 :post
     * 需要参数:无
     * 返回类型 List<Map<String, Object>>
     * 用途:返回数据库user表中全部数据
     * 示例:http://localhost:8080/selectAll
     * @final
     */
    @GetMapping("/findAll")
    public List<User> findAll() {
        System.out.println("test");
        return userService.getAll();
    }
    /**
     * 接口名 :selectUserById
     * 请求方式:get
     * 需要参数:u_id
     * 返回类型:boolean
     * 用途:根据id查找用户
     * @final
     */
    @GetMapping(value = "findOneById", params = "u_id")
    public User findOneById(@RequestParam Integer u_id){
        System.out.println("select:"+u_id);
        return userService.findOneById(u_id);
    }

    /**
     * 接口名:login
     * 请求方式: post
     * 需要参数:u_id u_pwd
     * 返回类型 Boolean
     * 用途:登录
     * @final
     */
    @PostMapping(value = {"/findOneByIdAndPassword", "/login"})
    public User findOneByIdAndPassword(@RequestBody User user) {
        return this.userService.findOneByIdAndPassword(user.getU_id(), user.getU_pwd());
    }

    /**
     * 接口名 :deleteUser
     * 请求方式:get
     * 需要参数:u_id
     * 用途:根据ID删除用户
     * 返回类型:boolean
     * 示例:http://localhost:8080/deleteUser?u_id=???
     * @final
     */
    @DeleteMapping("/deleteOneById")
    public Boolean deleteOneById(Integer u_id) {
        System.out.println("delete +" + u_id);
        return this.userService.deleteOneById(u_id);
    }

    /**
     *接口名:addUser
     * 请求方式:get
     * 返回类型:Boolean
     * 参数:  pojo.User
     * 用途:添加一条记录
     * 示例:http://localhost:8080/addUser?u_id=?&u_name=?&u_pwd=?
     * @final
     */
    @PostMapping(value = {"/pushOne", "/register"})
    public Boolean pushOne(@RequestBody User user) {
        System.out.println("addUser  +" + user.toString());
        try {
            return userService.pushOne(user);
        } catch (Exception e) {
            System.out.println("The primary key already exist.");
            return false;
        }
    }

    /**
     * 接口名:alterUser
     * 请求方式 get
     * 返回类型 boolean
     * 参数:pojo.User
     * 用途:修改记录数据
     * @param user u_id 需要修改的数据的id
     *             u_name 修改后的name
     *             u_pwd 修改后的pwd
     * @needRename
     */
    @PostMapping("/alterUser")
    public Boolean alterUser(@RequestBody User user) {
        System.out.println(user.toString());
        return userService.alterUser(user);
    }
}