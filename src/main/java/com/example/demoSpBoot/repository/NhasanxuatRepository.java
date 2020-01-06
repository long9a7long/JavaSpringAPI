package com.example.demoSpBoot.repository;

import java.sql.Date;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.demoSpBoot.model.nhasanxuat;

public interface NhasanxuatRepository extends JpaRepository<nhasanxuat, Integer>{
	Page<nhasanxuat> findAll(Pageable pageable);
	@Query( value = "SELECT b.* FROM nhasanxuat b WHERE b.tennsx LIKE %:searchTerm%",
			countQuery = "SELECT COUNT(*) FROM (SELECT b.* FROM nhasanxuat b WHERE b.tennsx LIKE %:searchTerm%) as temp",
			nativeQuery = true)
	Page<nhasanxuat> findByidContaining(Pageable pageable, String searchTerm);
	@Modifying
	@Transactional
	@Query( value = "DELETE FROM sanpham WHERE nhasx= :id", nativeQuery = true)
	void deleteProduct( int id);
}
