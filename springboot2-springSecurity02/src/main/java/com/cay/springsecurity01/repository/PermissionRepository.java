package com.cay.springsecurity01.repository;

import com.cay.springsecurity01.bean.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission,Long> {
}
