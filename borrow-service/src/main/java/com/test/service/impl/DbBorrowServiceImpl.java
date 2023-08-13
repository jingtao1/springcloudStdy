package com.test.service.impl;

import com.test.entity.Book;
import com.test.entity.DbBorrow;
import com.test.entity.User;
import com.test.entity.UserBorrowDetail;
import com.test.service.client.BookClient;
import com.test.service.client.UserClinent;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

import com.test.mapper.DbBorrowMapper;
import com.test.service.DbBorrowService;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DbBorrowServiceImpl implements DbBorrowService{

    @Resource
    private DbBorrowMapper dbBorrowMapper;

    @Override
    public int insert(DbBorrow record) {
        return dbBorrowMapper.insert(record);
    }

    @Override
    public int insertSelective(DbBorrow record) {
        return dbBorrowMapper.insertSelective(record);
    }


//    @Resource
//    private RestTemplate restTemplate;

    @Resource
    UserClinent userClinent;

    @Resource
    BookClient bookClient;

    @Override
    public UserBorrowDetail getUserBorrowDetail(int uid) {
        List<DbBorrow> borrowsByUid = dbBorrowMapper.getBorrowsByUid(uid);
        //RestTemplate支持多种方式的远程调用
        //这里通过调用getForObject来请求其他服务，并将结果自动进行封装
        //获取User信息
        //将user服务和book服务注册到Eureka注册中心中，将restTemplate组件化，开启负载均衡功能，使得可以通过当前服务名 直接替换 ip地址与端口的调用
//        User user = restTemplate.getForObject("http://userservice/user/"+uid, User.class);
        User userById = userClinent.findUserById(uid);
        //获取每一本书的详细信息
        List<Book> bookList = borrowsByUid
                .stream()
//                .map(b -> restTemplate.getForObject("http://bookservice/book/"+b.getBid(), Book.class))
                .map(b -> bookClient.findBookById(b.getBid()))
                .collect(Collectors.toList());
        return new UserBorrowDetail(userById, bookList);
    }
}
