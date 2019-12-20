package com.example.demoSpBoot.repository;

import java.sql.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demoSpBoot.model.nhasanxuat;

public interface NhasanxuatRepository extends JpaRepository<nhasanxuat, Integer>{
	Page<nhasanxuat> findAll(Pageable pageable);
	 Page<nhasanxuat> findByidContainingAndCreatedAtBetween(Pageable pageable, String searchTerm, Date fromdate,Date todate);
	  
	  Page<nhasanxuat> findByidContaining(Pageable pageable, String searchTerm);
}
