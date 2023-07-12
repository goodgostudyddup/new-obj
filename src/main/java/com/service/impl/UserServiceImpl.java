package com.service.impl;

import com.mapper.UserMapper;
import com.mybatisutil.MybatisUtil;
import com.pojo.User;
import com.service.UserService;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {
    SqlSession sqlSession = MybatisUtil.getSqlSession();
    UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

    @Override
    public List<Map<String, Object>> getlist() {
        return userMapper.getlist();
    }

    /**
     * 根据id删除用户
     *
     * @param u_id
     * @return
     */
    @Override
    public boolean deleteUser(int u_id) {
        System.out.println("impl");
        boolean flag = userMapper.deleteUser(u_id) > 0;
        sqlSession.commit();
        return flag;
    }

    /**
     * 修改用户信息
     * u_id 被修改数据的id
     * uname upwd 修改后的数值
     */
    @Override
    public boolean alterUser(User user) {
        boolean d = deleteUser(user.getU_id());
        boolean a = addUser(user);
        sqlSession.commit();
        if (d == a) return true;
        else return false;
    }

    /**
     * 添加用户
     */
    @Override
    public boolean addUser(User user) {
        boolean flag = userMapper.addUser(user) > 0;
        sqlSession.commit();
        return flag;
    }

    @Override
    public boolean selectUserById(int u_id) {
        return !Objects.isNull(userMapper.selectUserById(u_id));
    }

    @Override
    public List<Map<String, User>> selectUserByAllField(User user) {
        return this.userMapper.selectUserByAllField(user);
    }

    @Override
    public Map<String,Object>  login(int u_id) {
        return userMapper.login(u_id);
    }
}
