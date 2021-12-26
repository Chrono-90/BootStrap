package com.bootstrap.repository;

import com.bootstrap.model.User;
import org.springframework.data.jpa.repository.JpaRepository;



public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByEmail(String mail);
}
