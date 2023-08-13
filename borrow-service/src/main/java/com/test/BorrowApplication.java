package com.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients //启动FeignClients远程调用
@EnableHystrix //启用Hystrix 服务熔断
public class BorrowApplication {

    public static void main(String[] args) {
        SpringApplication.run(BorrowApplication.class,args);
    }

}
