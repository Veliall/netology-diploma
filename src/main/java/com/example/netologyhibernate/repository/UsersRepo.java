package com.example.netologyhibernate.repository;

import com.example.netologyhibernate.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Igor Khristiuk on 12.12.2021
 */
public interface UsersRepo extends JpaRepository<User,Long> {
    User findByUsername(String username);
}
