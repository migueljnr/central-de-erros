package com.projetofinal.repository;

import com.projetofinal.data.User;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {

    Optional<User> findByUsername(@Param("userName") String userName);
    Optional<User> findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
    User save(@Param("user") User user);
}
