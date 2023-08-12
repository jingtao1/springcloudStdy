package com.test.controller;

import com.test.entity.UserBorrowDetail;
import com.test.service.DbBorrowService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class DbBorrowController {

    @Resource
    private DbBorrowService dbBorrowService;


    @RequestMapping("/borrow/{uid}")
    UserBorrowDetail findUserBorrows(@PathVariable("uid") int uid){
        return dbBorrowService.getUserBorrowDetail(uid);
    }


}
