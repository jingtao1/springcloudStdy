package com.test.controller;

import com.test.entity.Book;
import com.test.service.IBookService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
public class BookController {

    @Resource
    IBookService bookService;

    @RequestMapping("/book/{bid}")
    Book findBookById(@PathVariable("bid") int bid, HttpServletRequest request){
        String test = request.getHeader("Tests");
        System.out.println(test);
        return bookService.getBookById(bid);
    }
}
