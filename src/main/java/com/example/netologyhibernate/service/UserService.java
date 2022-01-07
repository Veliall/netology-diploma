package com.example.netologyhibernate.service;

import com.example.netologyhibernate.entity.Role;
import com.example.netologyhibernate.entity.User;
import com.example.netologyhibernate.repository.UsersRepo;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

/**
 * @author Igor Khristiuk on 12.12.2021
 */

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
    private final UsersRepo usersRepo;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = usersRepo.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return user;
    }

    public boolean saveUser(User user) {
        User userFromDB = usersRepo.findByUsername(user.getUsername());

        if (userFromDB != null) {
            return false;
        }

        user.setRoles(Collections.singleton(new Role(1L, "ROLE_USER")));
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        usersRepo.save(user);
        return true;
    }
}
