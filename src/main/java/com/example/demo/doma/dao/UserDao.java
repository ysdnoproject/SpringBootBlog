package com.example.demo.doma.dao;

import com.example.demo.doma.entity.User;
import org.seasar.doma.Dao;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.boot.ConfigAutowireable;

import java.util.List;
import java.util.Optional;

@ConfigAutowireable
@Dao
public interface UserDao extends BaseDao<User> {
    @Select
    List<User> selectAll();

    @Select
    Optional<User> selectByUsername(String username);

    @Insert
    int insert(User user);

}
