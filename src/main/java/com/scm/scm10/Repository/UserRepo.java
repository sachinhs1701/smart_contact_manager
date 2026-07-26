package com.scm.scm10.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.scm.scm10.entity.User;

public interface UserRepo extends JpaRepository<User, String>{

	Optional<User> findByEmail(String email);
}
