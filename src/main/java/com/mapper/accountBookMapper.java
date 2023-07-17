package com.mapper;

import java.util.List;

import com.bean.AccountBook;

public interface AccountBookMapper {
    List<AccountBook> getall();

    List<AccountBook> getUserAll(int u_id);

    int pushOne(AccountBook accountBook);

    int alter(AccountBook accountBook);
    int delete(int j_id);
}
