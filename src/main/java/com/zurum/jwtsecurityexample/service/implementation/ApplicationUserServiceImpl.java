package com.zurum.jwtsecurityexample.service.implementation;

import com.zurum.jwtsecurityexample.model.ApplicationUser;
import com.zurum.jwtsecurityexample.model.Role;
import com.zurum.jwtsecurityexample.repository.ApplicationUserRepository;
import com.zurum.jwtsecurityexample.repository.RoleRepository;
import com.zurum.jwtsecurityexample.service.ApplicationUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
@Slf4j
public class ApplicationUserServiceImpl implements ApplicationUserService {

    private final ApplicationUserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public ApplicationUserServiceImpl(ApplicationUserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public ApplicationUser saveUser(ApplicationUser user) {
        return userRepository.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String email, String roleName) {
        ApplicationUser user = userRepository.findByEmail(email);
        log.info("User {}", user);
        Role role = roleRepository.findByName(roleName);
        user.getRoles().add(role);
    }

    @Override
    public ApplicationUser getUser(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<ApplicationUser> getUsers() {
        return userRepository.findAll();
    }
}
