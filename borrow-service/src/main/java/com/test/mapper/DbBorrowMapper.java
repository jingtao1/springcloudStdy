package com.test.mapper;

import com.test.entity.DbBorrow;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DbBorrowMapper {


    int insert(DbBorrow record);

    int insertSelective(DbBorrow record);

    @Select("select * from db_borrow where uid = #{uid}")
    List<DbBorrow> getBorrowsByUid(int uid);

    @Select("select * from db_borrow where bid = #{bid}")
    List<DbBorrow> getBorrowsByBid(int bid);

    @Select("select * from db_borrow where bid = #{bid} and uid = #{uid}")
    DbBorrow getBorrow(@Param("uid") int uid, @Param("bid") int bid);


}