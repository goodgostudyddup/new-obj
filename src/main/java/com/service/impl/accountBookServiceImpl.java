package com.service.impl;

import com.bean.AccountBook;
import com.mapper.AccountBookMapper;
import com.mybatisutil.MybatisUtil;
import com.service.AccountBookService;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountBookServiceImpl implements AccountBookService {
    private final SqlSession sqlSession = MybatisUtil.getSqlSession();
    private final AccountBookMapper accountBookMapper = sqlSession.getMapper(AccountBookMapper.class);


    @Override
    public List<AccountBook> getall() {
        return accountBookMapper.getall();
    }

    @Override
    public List<AccountBook> getUserAll(int u_id) {
        return accountBookMapper.getUserAll(u_id);
        }

    @Override
    public boolean pushOne(AccountBook accountBook) {
        boolean flag= accountBookMapper.pushOne(accountBook)>0;
        sqlSession.commit();
        return flag;
    }


    @Override
    public boolean alter(AccountBook accountBook) {
        boolean flag= accountBookMapper.alter(accountBook) >0;
        sqlSession.commit();
        return flag;
    }

    @Override
    public boolean delete(int j_id) {
        boolean flag= accountBookMapper.delete(j_id) >0;
        sqlSession.commit();
        return flag;
    }
}
