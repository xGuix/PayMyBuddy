package com.paymybuddy.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.paymybuddy.model.User;

@Repository
public interface UserFriendsRepository extends JpaRepository<User, Integer>
{
	User save(Optional<User> newFriend);
}