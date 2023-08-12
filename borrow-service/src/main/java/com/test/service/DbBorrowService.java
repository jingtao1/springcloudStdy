package com.test.service;

import com.test.entity.DbBorrow;
import com.test.entity.UserBorrowDetail;

public interface DbBorrowService{


    int insert(DbBorrow record);

    int insertSelective(DbBorrow record);

    UserBorrowDetail getUserBorrowDetail(int uid);

}
