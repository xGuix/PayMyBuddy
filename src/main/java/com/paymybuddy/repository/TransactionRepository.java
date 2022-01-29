package com.paymybuddy.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.paymybuddy.model.Transaction;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Integer>
{
	
}