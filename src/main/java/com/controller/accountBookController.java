package com.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.bean.accountBook;

import java.util.List;

import com.service.accountBookService;
@Controller
@RequestMapping("zb")
public class accountBookController {
    @Autowired
    accountBookService accountBookService;
    @RequestMapping("all")
    @ResponseBody
    public List<accountBook> getall(){
        System.out.println("getall");
        System.out.println(accountBookService.getall());
       return accountBookService.getall();
    }


}
