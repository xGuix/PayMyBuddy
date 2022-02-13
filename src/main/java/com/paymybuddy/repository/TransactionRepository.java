package com.paymybuddy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.paymybuddy.model.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer>
{
//	/**
//	 * {@inheritDoc}
//	 */
//	List<Transaction> findSenderTransactionByEmail(String sender);
}