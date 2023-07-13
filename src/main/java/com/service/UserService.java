package com.service;


import com.bean.User;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Map;

public interface UserService {

    /**
     * @needRename
     */
    public boolean alterUser(User user);


    public List<User> getAll();
    public User findOneById(Integer u_id);
    public User findOneByIdAndPassword(Integer u_id, String u_pwd);
    public Boolean deleteOneById(Integer u_id);
    public Boolean pushOne(User user);
}
