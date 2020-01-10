package com.example.demoSpBoot.repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demoSpBoot.model.hoadonnhaphang;
import com.example.demoSpBoot.model.nhacungcap;

public interface HoadonNHRepository extends JpaRepository<hoadonnhaphang, Integer>{
	Optional<hoadonnhaphang> findBymahoadon(String mahoadon);
	Page<hoadonnhaphang> findByMahoadonContainingAndCreatedAtBetween(Pageable pageable, String searchTerm, Date fromdate,Date todate);
	  
	  Page<hoadonnhaphang> findByMahoadonContaining(Pageable pageable, String searchTerm);
	  List<hoadonnhaphang> findBynhacungcap (nhacungcap ncc);
}
