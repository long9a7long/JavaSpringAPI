package com.example.demoSpBoot.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demoSpBoot.model.phieuthu;

public interface PhieuthuRepository extends JpaRepository<phieuthu, Integer>{
	Optional<phieuthu> findBymaphieuthu(String maphieuthu);
	Page<phieuthu> findAll(Pageable pageable);
	Page<phieuthu> findBymaphieuthuContaining(Pageable pageable, String searchTerm);
}
