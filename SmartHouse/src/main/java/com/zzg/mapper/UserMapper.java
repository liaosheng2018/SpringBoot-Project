package com.zzg.mapper;

import com.zzg.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {
    @Insert("insert into house_user(nickname,password,sex,birthday) values (#{nickname},#{password},#{sex},#{birthday})")
    void addUser(User user);

    @Select("select * from house_user")
    List<User> findUsers();

}