package com.example.demoSpBoot.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.demoSpBoot.model.phieuchi;

public interface PhieuchiRepository extends JpaRepository<phieuchi, Integer>{
	Optional<phieuchi> findBymaphieuchi(String maphieuchi);
	Page<phieuchi> findAll(Pageable pageable);
	 
	Page<phieuchi> findBymaphieuchiContaining(Pageable pageable, String searchTerm);

}
