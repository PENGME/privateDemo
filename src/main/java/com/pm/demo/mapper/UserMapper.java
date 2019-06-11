package com.pm.demo.mapper;

import com.pm.demo.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {

    @Select("select id,uname,pwd from user_0610 where uname=#{uname} and pwd=#{pwd}")
    User findByLogin(String uname,String pwd);
}
