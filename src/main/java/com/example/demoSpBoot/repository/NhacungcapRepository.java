package com.example.demoSpBoot.repository;

import java.sql.Date;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demoSpBoot.model.hoadonbanhang;
import com.example.demoSpBoot.model.nhacungcap;

public interface NhacungcapRepository extends JpaRepository<nhacungcap, Integer>{
	Optional<nhacungcap> findBymancc(String mancc);
	Page<nhacungcap> findAll(Pageable pageable);
	Page<hoadonbanhang> findBymanccContainingAndCreatedAtBetween(Pageable pageable, String searchTerm, Date fromdate,Date todate);
	  
	  Page<hoadonbanhang> findBymanccContaining(Pageable pageable, String searchTerm);
}
