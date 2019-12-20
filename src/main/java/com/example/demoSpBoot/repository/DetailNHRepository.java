package com.example.demoSpBoot.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demoSpBoot.model.chitiethoadonnh;

public interface DetailNHRepository extends JpaRepository<chitiethoadonnh, Integer>{
	Page<chitiethoadonnh> findAll(Pageable pageable);
	Page<chitiethoadonnh> findByidContaining(Pageable pageable, String searchTerm);
}
