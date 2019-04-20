package com.cay.springsecurity01.repository;

import com.cay.springsecurity01.bean.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
}
