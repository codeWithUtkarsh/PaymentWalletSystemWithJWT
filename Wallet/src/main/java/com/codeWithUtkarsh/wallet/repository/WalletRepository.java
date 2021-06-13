package com.codeWithUtkarsh.wallet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codeWithUtkarsh.wallet.model.entity.Wallet;

public interface WalletRepository extends JpaRepository<Wallet, Integer>{

}
