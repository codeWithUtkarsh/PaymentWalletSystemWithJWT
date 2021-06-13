package com.codeWithUtkarsh.wallet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codeWithUtkarsh.wallet.model.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, String>{

}
