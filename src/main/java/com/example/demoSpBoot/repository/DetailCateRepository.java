package com.example.demoSpBoot.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demoSpBoot.model.chitietdanhmuc;

public interface DetailCateRepository extends JpaRepository<chitietdanhmuc, Integer>{
	Page<chitietdanhmuc> findAll(Pageable pageable);
	Page<chitietdanhmuc> findByidContaining(Pageable pageable, String searchTerm);
	
}
