package com.service;
import java.util.List;

import com.bean.AccountBook;

public interface AccountBookService {
    public List<AccountBook> getall() ;

    public List<AccountBook> getUserAll(int uId);

    boolean pushOne(AccountBook accountBook);

    boolean alter(AccountBook accountBook);

    boolean delete(int j_id);

}
