package com.test.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.test.entity.UserBorrowDetail;
import com.test.service.DbBorrowService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;

@RestController
public class DbBorrowController {

    @Resource
    private DbBorrowService dbBorrowService;

//    @HystrixCommand(fallbackMethod = "getErrorMethod")
//  @HystrixCommand 通过此注解配置当前调用服务失败后返回方法，如下所示，默认返回一个空对象
    //此时就相当于服务降级，不会直接对调用服务失败抛出错误，而是取一个自定义折中方案
    //但是需要注意的是，如果当大量请求多次调用该接口时，会先去检查该接口服务是否无误，如果有问题则先进行降级，如果次数过多，会导致跳过服务降级，直接进行服务熔断，返回当前getErrorMethod调用结果
    //间隔一段时间，继续进行检查判断服务是否运行，如果正常则直接退出熔断状态执行方法，如果出现问题则继续熔断状态
    @RequestMapping("/borrow/{uid}")
    UserBorrowDetail findUserBorrows(@PathVariable("uid") int uid){
        return dbBorrowService.getUserBorrowDetail(uid);
    }

    //注意参数和返回值要与接口方法返回一致
//    UserBorrowDetail getErrorMethod(int uid){
//        return new UserBorrowDetail(null, Collections.emptyList());
//    }
}
