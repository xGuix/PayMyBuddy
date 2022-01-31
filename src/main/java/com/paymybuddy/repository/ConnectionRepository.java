package com.paymybuddy.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.paymybuddy.model.Connection;

@Repository
public interface ConnectionRepository extends CrudRepository<Connection, Integer>
{
	
}