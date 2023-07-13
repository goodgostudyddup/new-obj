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
}
