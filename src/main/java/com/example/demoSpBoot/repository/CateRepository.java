package com.example.demoSpBoot.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demoSpBoot.model.danhmucsp;

@Repository
public interface CateRepository extends JpaRepository<danhmucsp, Integer>{
	Page<danhmucsp> findAll(Pageable pageable);	  
	Page<danhmucsp> findBytendanhmucContaining(Pageable pageable, String searchTerm);
}
