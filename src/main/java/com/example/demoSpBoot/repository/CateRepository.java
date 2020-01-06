package com.example.demoSpBoot.repository;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demoSpBoot.model.danhmucsp;

@Repository
public interface CateRepository extends JpaRepository<danhmucsp, Integer>{
	Page<danhmucsp> findAll(Pageable pageable);
	@Query( value = "SELECT b.* FROM danhmucsp b WHERE b.tendanhmuc LIKE %:searchTerm%",
			countQuery = "SELECT COUNT(*) FROM (SELECT b.* FROM danhmucsp b WHERE b.tendanhmuc LIKE %:searchTerm%) as temp",
			nativeQuery = true)
	Page<danhmucsp> findBytendanhmuc(Pageable pageable, String searchTerm);
	@Modifying
	@Transactional
	@Query( value = "DELETE FROM chitietdanhmuc WHERE id_danhmuc= :id", nativeQuery = true)
	void deleteDetailCate( int id);
}
