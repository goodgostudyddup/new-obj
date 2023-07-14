package com.service.impl;

import com.bean.accountBook;
import com.mybatisutil.MybatisUtil;
import com.service.accountBookService;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;
import com.mapper.accountBookMapper;

import java.util.List;

@Service
public class accountBookServiceImpl implements accountBookService {
    private final SqlSession sqlSession = MybatisUtil.getSqlSession();
    private final accountBookMapper accountBookMapper = sqlSession.getMapper(accountBookMapper.class);


    @Override
    public List<accountBook> getall() {
        return accountBookMapper.getall();
    }

    @Override
    public List<accountBook> getUserAll(int u_id) {
        return accountBookMapper.getUserAll(u_id);
        }

    @Override
    public boolean pushOne(accountBook accountBook) {
        boolean flag= accountBookMapper.pushOne(accountBook)>0;
        sqlSession.commit();
        return flag;
    }


    @Override
    public boolean alter(accountBook accountBook) {
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
