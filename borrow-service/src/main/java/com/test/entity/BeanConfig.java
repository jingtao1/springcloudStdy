package com.test.entity;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
//自定义配置 负载均衡策略，需要添加注解LoadBalancerClient
//当有多个配置项的时候，可以采用@LoadBalancerClients 注解进行对应指定
@LoadBalancerClient(value = "userservice",      //指定为 userservice 服务，只要是调用此服务都会使用我们指定的策略
        configuration = LoadBalancerConfig.class)   //指定我们刚刚定义好的配置类
public class BeanConfig {


    @Bean
    @LoadBalanced
   /* 由于加了@LoadBalanced注解，使用RestTemplateCustomizer对所有标注了@LoadBalanced的RestTemplate Bean
    添加了一个LoadBalancerInterceptor拦截器。利用RestTemplate的拦截器，spring可以对restTemplate bean进行定制，
    加入loadbalance拦截器进行ip:port的替换，也就是将请求的地址中的服务逻辑名转为具体的服务地址。
     */
    RestTemplate template(){
        return new RestTemplate();
    }
}