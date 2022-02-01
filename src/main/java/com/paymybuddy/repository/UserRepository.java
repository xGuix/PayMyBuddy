package com.paymybuddy.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.paymybuddy.dto.UserDTO;
import com.paymybuddy.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>
{
	Optional<User> findByEmail(String email);
	User save(UserDTO userDto);
}