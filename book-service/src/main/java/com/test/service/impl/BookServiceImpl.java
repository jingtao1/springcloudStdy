package com.test.service.impl;

import com.test.eneity.Book;
import com.test.mapper.BookMapper;
import com.test.service.IBookService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class BookServiceImpl implements IBookService {

    @Resource
    BookMapper bookMapper;

    @Override
    public Book getBookById(int bid) {
        return bookMapper.getBookById(bid);
    }
}
