package com.example.demoSpBoot.repository;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.demoSpBoot.model.chitietdanhmuc;

public interface DetailCateRepository extends JpaRepository<chitietdanhmuc, Integer>{
	Page<chitietdanhmuc> findAll(Pageable pageable);
	Page<chitietdanhmuc> findByidContaining(Pageable pageable, String searchTerm);
	@Modifying
	@Transactional
	@Query( value = "DELETE FROM chitietdanhmuc WHERE id_sanpham= :id", nativeQuery = true)
	void deleteDetailCateByCate( int id);
}
