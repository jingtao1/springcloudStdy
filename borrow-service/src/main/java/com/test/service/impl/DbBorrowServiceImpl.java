package com.test.service.impl;

import com.test.entity.Book;
import com.test.entity.DbBorrow;
import com.test.entity.User;
import com.test.entity.UserBorrowDetail;
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


    @Override
    public UserBorrowDetail getUserBorrowDetail(int uid) {
        List<DbBorrow> borrowsByUid = dbBorrowMapper.getBorrowsByUid(uid);
        //RestTemplate支持多种方式的远程调用
        RestTemplate template = new RestTemplate();
        //这里通过调用getForObject来请求其他服务，并将结果自动进行封装
        //获取User信息
        User user = template.getForObject("http://localhost:8301/user/"+uid, User.class);
        //获取每一本书的详细信息
        List<Book> bookList = borrowsByUid
                .stream()
                .map(b -> template.getForObject("http://localhost:8101/book/"+b.getBid(), Book.class))
                .collect(Collectors.toList());
        return new UserBorrowDetail(user, bookList);
    }
}
