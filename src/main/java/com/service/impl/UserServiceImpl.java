package com.service.impl;

import com.mapper.UserMapper;
import com.mybatisutil.MybatisUtil;
import com.bean.User;
import com.service.UserService;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.NestedRuntimeException;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {
    private final SqlSession sqlSession = MybatisUtil.getSqlSession();
    private final UserMapper userMapper = sqlSession.getMapper(UserMapper.class);



    /**
     * 修改用户信息
     * u_id 被修改数据的id
     * uname upwd 修改后的数值
     * @needRename
     */
    @Override
    public boolean alterUser(User user) {
        Boolean d = this.deleteOneById(user.getU_id());
        Boolean a = null;
        try {
            a = this.pushOne(user);
        } catch (Exception e) {
            System.out.println("The primary key already exist.");
            return false;
        }
        sqlSession.commit();
        return d.booleanValue() == a.booleanValue();
    }

    /**
     * @final
     */
    @Override
    public List<User> getAll() {
        return userMapper.getAll();
    }



    /**
     * @final
     */
    @Override
    public User findOneById(Integer u_id) {
        return this.userMapper.findOneById(u_id);
    }

    /**
     * @final
     */
    @Override
    public User findOneByIdAndPassword(Integer u_id, String u_pwd) {
        return this.userMapper.findOneByIdAndPassword(u_id, u_pwd);
    }

    /**
     * 根据id删除用户
     * @final
     */
    @Override
    public Boolean deleteOneById(Integer u_id) {
        Boolean isSuccess = this.userMapper.deleteOneById(u_id);
        sqlSession.commit();
        return isSuccess;
    }

    /**
     * 添加用户
     * @final
     */
    @Override
    public Boolean pushOne(User user) {
        Boolean isSuccess = userMapper.pushOne(user);
        sqlSession.commit();
        return isSuccess;
    }

}
