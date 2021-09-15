package com.zurum.jwtsecurityexample.service.implementation;

import com.zurum.jwtsecurityexample.model.ApplicationUser;
import com.zurum.jwtsecurityexample.model.Role;
import com.zurum.jwtsecurityexample.repository.ApplicationUserRepository;
import com.zurum.jwtsecurityexample.repository.RoleRepository;
import com.zurum.jwtsecurityexample.service.ApplicationUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
@Slf4j
public class ApplicationUserServiceImpl implements ApplicationUserService, UserDetailsService {

    private final ApplicationUserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public ApplicationUser saveUser(ApplicationUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
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

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        ApplicationUser user = userRepository.findByEmail(email);

        if(user == null) {
            throw new UsernameNotFoundException("User does not exist");
        }

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });

        return new org.springframework.security.core.userdetails.User(user.getName(),
                    user.getPassword(),
                authorities);


    }
}
