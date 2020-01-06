package com.example.demoSpBoot.repository;

import java.sql.Date;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demoSpBoot.model.hoadonbanhang;
import com.example.demoSpBoot.model.nhacungcap;

public interface NhacungcapRepository extends JpaRepository<nhacungcap, Integer>{
	Optional<nhacungcap> findBymancc(String mancc);
	Page<nhacungcap> findAll(Pageable pageable);
	@Query( value = "SELECT b.* FROM nhacungcap b WHERE b.mancc=:key OR b.tenncc LIKE %:key%",
			countQuery = "SELECT COUNT(*) FROM (SELECT b.* FROM nhacungcap b WHERE b.mancc=:key OR b.tenncc LIKE %:key%) as temp",
			nativeQuery = true)
	Page<nhacungcap> findBymanccOrtenncc(Pageable pageable, String key);
}
