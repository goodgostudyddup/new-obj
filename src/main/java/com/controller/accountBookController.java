package com.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.bean.AccountBook;

import java.util.List;
import com.service.AccountBookService;

@CrossOrigin
@Controller
@RequestMapping("zb")
public class AccountBookController {
    @Autowired
    AccountBookService accountBookService;

    /**
     * 接口名:delete
     * 请求方式:get
     * 需要参数:j_id
     * 返回类型:boolean
     * 用途:根据j_id删除一条记录
     */
    @RequestMapping("delete")
    @ResponseBody
    public boolean delete(int j_id){
        return accountBookService.delete(j_id);
    }


    /**
     * 接口名:alter
     * 请求方式:get
     * 需要参数:accountbook(u_id,j_id, szqk, money, lx, bz,date)
     * 返回类型:Boolean
     * 用途:根据j_id u_id修改记录
     */
    @RequestMapping("alter")
    @ResponseBody
    public boolean alter(AccountBook accountBook){
        return accountBookService.alter(accountBook);
    }


    /**
     * 接口名:getUserAll
     * 请求方式:get
     * 需要参数:u_id
     * 返回类型 list
     * 用途:获取该用户的所有账本记录
     */
    @RequestMapping("getUserAll")
    @ResponseBody
    public List<AccountBook> getUserAll(int u_id){
        return accountBookService.getUserAll(u_id);
    }


    /**
     * 接口名:pushOne
     * 请求方式:get
     * 需要参数:accountbook(u_id, szqk, money, lx, bz)
     * 返回类型:Boolean
     * 用途:增加一条记录
     */
    @RequestMapping("pushOne")
    @ResponseBody
    public boolean push (AccountBook accountBook){
        System.out.println(accountBook.toString());
        return accountBookService.pushOne(accountBook);
    }




    /**
     * 接口名:all
     * 请求方式:get
     * 需要参数:无
     * 返回类型 list<>
     * 用途:获取账本所有记录
     * @return
     */
    @RequestMapping("all")
    @ResponseBody
    public List<AccountBook> getall(){
       return accountBookService.getall();
    }



}
