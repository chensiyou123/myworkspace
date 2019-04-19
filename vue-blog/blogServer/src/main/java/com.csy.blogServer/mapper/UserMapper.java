package com.csy.blogServer.mapper;

import com.csy.blogServer.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

@Mapper
public interface UserMapper {
    User loadUserByUsername(Map map);
}
