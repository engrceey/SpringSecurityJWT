package com.zurum.jwtsecurityexample.api;

import com.zurum.jwtsecurityexample.dto.RoleToUserForm;
import com.zurum.jwtsecurityexample.model.ApplicationUser;
import com.zurum.jwtsecurityexample.model.Role;
import com.zurum.jwtsecurityexample.service.ApplicationUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final ApplicationUserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<ApplicationUser>>getUsers() {
        return ResponseEntity.ok().body(userService.getUsers());
    }


    @PostMapping("/user/save")
    public ResponseEntity<ApplicationUser>saveUser(@RequestBody ApplicationUser user) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveUser(user));
    }

    @PostMapping("/role/save")
    public ResponseEntity<Role>saveRole(@RequestBody Role role) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/role/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveRole(role));
    }

    @PostMapping("/role/addRole")
    public ResponseEntity<?> addRoleTouUser(@RequestBody RoleToUserForm form) {

        userService.addRoleToUser(form.getEmail(),form.getRoleName());
        return ResponseEntity.ok().build();
    }

}
