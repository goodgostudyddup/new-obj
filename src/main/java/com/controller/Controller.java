package com.controller;

import com.pojo.User;
import com.service.UserService;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@CrossOrigin
@org.springframework.stereotype.Controller
public class Controller {
    @Autowired
    UserService userService;

    /**
     * 接口名:login
     * 请求方式: post
     * 需要参数:u_id u_pwd
     * 返回类型 Boolean
     * 用途:登录
     */
    @RequestMapping(value = "login",method = RequestMethod.POST)
    @ResponseBody
    public boolean login(User user){
        System.out.println("login  " +user.toString());
        Map<String,Object> map = new HashMap<>();
        map=userService.login(user.getU_id());
        return map.get("u_pwd").equals(user.getU_pwd())==true;

    }


    /**
     * 接口名:selectAll
     * 请求方式 :post
     * 需要参数:无
     * 返回类型 List<Map<String, Object>>
     * 用途:返回数据库user表中全部数据
     *示例:http://localhost:8080/selectAll
     */

    @RequestMapping(value = "selectAll", method = RequestMethod.POST)
    @ResponseBody
    public List<Map<String, Object>> test() {
        System.out.println("test");
        return userService.getlist();
    }
    /**
     * 接口名 :selectUserById
     * 请求方式:get
     * 需要参数:u_id
     * 返回类型:boolean
     * 用途:根据id查找用户
     */
    @GetMapping(value = "selectUserById", params = "u_id")
    @ResponseBody
    public Boolean selectUserById(@RequestParam int u_id){
        System.out.println("select:"+u_id);
        return userService.selectUserById(u_id);
    }

    @PostMapping("/selectUserByAllField")
    @ResponseBody
    public List<Map<String, User>> findUserByAllField(@RequestBody User user) {
        return this.userService.selectUserByAllField(user);
    }

    /**
     * 接口名 :deleteUser
     * 请求方式:get
     * 需要参数:u_id
     * 用途:根据ID删除用户
     * 返回类型:boolean
     * 示例:http://localhost:8080/deleteUser?u_id=???
     */
    @RequestMapping(value = "deleteUser")
    @ResponseBody
    public boolean deleteUser(int u_id) {
        System.out.println("delete +" + u_id);
        return userService.deleteUser(u_id);
    }

    /**
     *接口名:addUser
     * 请求方式:get
     * 返回类型:Boolean
     * 参数:  pojo.User
     * 用途:添加一条记录
     * 示例:http://localhost:8080/addUser?u_id=?&u_name=?&u_pwd=?
     */
    @PostMapping("/addUser")
    @ResponseBody
    public boolean addUser(@RequestBody User user) {
        System.out.println("addUser  +" + user.toString());
        return userService.addUser(user);
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
     */
    @PostMapping("/alterUser")
    @ResponseBody
    public boolean alterUser(@RequestBody User user) {
        System.out.println(user.toString());
        return userService.alterUser(user);
    }
}