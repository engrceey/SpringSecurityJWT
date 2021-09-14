package com.zurum.jwtsecurityexample.service;

import com.zurum.jwtsecurityexample.model.ApplicationUser;
import com.zurum.jwtsecurityexample.model.Role;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ApplicationUserService {

    ApplicationUser saveUser(ApplicationUser user);

    Role saveRole(Role role);

    void addRoleToUser(String email, String roleName);

    ApplicationUser getUser(String email);

    List<ApplicationUser> getUsers();
}
