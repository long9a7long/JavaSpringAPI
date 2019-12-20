package com.example.demoSpBoot.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demoSpBoot.model.chitiethoadonbh;

public interface DetailBHRepository extends JpaRepository<chitiethoadonbh, Integer>{
	Page<chitiethoadonbh> findAll(Pageable pageable); 
	Page<chitiethoadonbh> findByidContaining(Pageable pageable, String searchTerm);
}
