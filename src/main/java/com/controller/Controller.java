package com.controller;

import com.pojo.User;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@org.springframework.stereotype.Controller
public class Controller {
    @Autowired
    UserService userService;

    /**
     * 接口名:selectAll
     * 请求方式 :post
     * 需要参数:无
     * 返回类型 List<Map<String, Object>>
     * 用途:返回数据库user表中全部数据
     *示例:http://localhost:8080/selectAll
     */
    @CrossOrigin
    @RequestMapping(value = "selectAll", method = RequestMethod.POST)
    @ResponseBody
    public List<Map<String, Object>> test() {
        System.out.println("test");
        return userService.getlist();
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
    @RequestMapping(value = "addUser")
    @ResponseBody
    public boolean addUser(User user) {
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
    @RequestMapping(value = "alterUser")
    @ResponseBody
    public boolean alterUser(User user) {
        System.out.println(user.toString());
        return userService.alterUser(user);
    }
}
