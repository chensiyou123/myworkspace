package com.cay.springsecurity01.repository;

import com.cay.springsecurity01.bean.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoRepository  extends JpaRepository<UserInfo,Long> {
     UserInfo findByUsername(String username);
}
