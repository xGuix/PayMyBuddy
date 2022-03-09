package com.paymybuddy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.paymybuddy.model.Transaction;
import com.paymybuddy.model.User;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer>
{
	/**
	 * {@inheritDoc}
	 */
	List<Transaction> getTransactionsBySender(User sender);

	/**
	 * {@inheritDoc}
	 */
	List<Transaction> getTransactionsByReceiver(User receiver);
}