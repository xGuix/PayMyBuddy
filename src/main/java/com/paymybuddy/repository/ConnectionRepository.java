package com.paymybuddy.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.paymybuddy.model.UserFriends;

@Repository
public interface ConnectionRepository extends CrudRepository<UserFriends, Integer>
{
	
}