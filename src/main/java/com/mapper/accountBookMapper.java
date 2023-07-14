package com.mapper;

import java.util.List;

import com.bean.accountBook;

public interface accountBookMapper {
    List<accountBook> getall();

    List<accountBook> getUserAll(int u_id);

    int pushOne(accountBook accountBook);

    int alter(accountBook accountBook);
    int delete(int j_id);
}
