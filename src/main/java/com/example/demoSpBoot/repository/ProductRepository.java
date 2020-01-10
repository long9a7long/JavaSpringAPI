package com.example.demoSpBoot.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.demoSpBoot.model.nhasanxuat;
import com.example.demoSpBoot.model.sanpham;

public interface ProductRepository extends JpaRepository<sanpham, Integer>{
	Optional<sanpham> findBymasp(String masp);
	List<sanpham> findByNhasanxuat(nhasanxuat nsx);
	Page<sanpham> findAll(Pageable pageable);
	
	@Query( value = "SELECT b.* FROM sanpham b WHERE b.masp=:key OR b.tensp LIKE %:key%",
			countQuery = "SELECT COUNT(*) FROM (SELECT b.* FROM sanpham b WHERE b.masp=:key OR b.tensp LIKE %:key%) as temp",
			nativeQuery = true)
	  
	Page<sanpham> findBymasportensp(Pageable pageable, String key);
	
	@Modifying
	@Transactional
	@Query( value = "DELETE FROM chitietdanhmuc WHERE id_sanpham= :id", nativeQuery = true)
	void deleteDetailCate( int id);
	@Modifying
	@Transactional
	@Query( value = "DELETE FROM chitiethoadonbh WHERE id_sanpham= :id", nativeQuery = true)
	void deleteDetailBillBH( int id);
	@Modifying
	@Transactional
	@Query( value = "DELETE FROM chitiethoadonnh WHERE id_sanpham= :id", nativeQuery = true)
	void deleteDetailBillNH( int id);
	@Modifying
	@Transactional
	@Query( value = "DELETE FROM phieuthu WHERE idhoadon= :id", nativeQuery = true)
	void deletephieuthu( int id);
	
	
	Page<sanpham> findByTrangthaiAndNhasanxuatIs(Pageable pageable, int trangthai, nhasanxuat nhasx);
	Page<sanpham> findByTrangthaiIs(Pageable pageable, int trangthai);
	Page<sanpham> findByNhasanxuatIs(Pageable pageable, nhasanxuat nhasx);
	
}
