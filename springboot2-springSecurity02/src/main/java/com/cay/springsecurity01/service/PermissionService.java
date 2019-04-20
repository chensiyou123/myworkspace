package com.cay.springsecurity01.service;
import java.util.Map;
import org.springframework.security.access.ConfigAttribute;

import java.util.Collection;

public interface PermissionService {
     Map<String, Collection<ConfigAttribute>> getPermissionMap();
}
