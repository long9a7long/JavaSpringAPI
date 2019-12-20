package com.example.demoSpBoot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demoSpBoot.model.users;

@Repository
public interface UsersRepository extends JpaRepository<users, String>{
	//users findByMNV(String manhanvien);
	users findByUsername(String username);
}
