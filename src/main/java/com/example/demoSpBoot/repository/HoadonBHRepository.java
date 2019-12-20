package com.example.demoSpBoot.repository;

import org.springframework.data.domain.Pageable;
import java.sql.Date;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demoSpBoot.model.hoadonbanhang;
import com.example.demoSpBoot.model.khachhang;
import com.example.demoSpBoot.model.users;

@Repository
public interface HoadonBHRepository extends JpaRepository<hoadonbanhang, Integer>{
		
	Page<hoadonbanhang> findAll(Pageable pageable);
		
	//@Query("SELECT e FROM hoadonbanhang e WHERE e.mahoadon = :mahoadon")
	  Optional<hoadonbanhang> findBymahoadon(String mahoadon);
	  
	  Page<hoadonbanhang> findByMahoadonContainingAndCreatedAtBetween(Pageable pageable, String searchTerm, Date fromdate,Date todate);
	  
	  Page<hoadonbanhang> findByMahoadonContaining(Pageable pageable, String searchTerm);
	  
	  Page<hoadonbanhang> findHoadonbanhangByKhachhangIs(Pageable pageable, khachhang khachhang);
	  
	  @Modifying
	  @Transactional
	  @Query(value="DELETE FROM chitiethoadonbh d WHERE d.id_hoadon= ?1")
	  void deletechitiethoadon( int ma);
	  
	  @Modifying
	  @Transactional
	  @Query(value="DELETE FROM hoadonbanhang d WHERE d.id= ?1")
	  void delete(int ma);
	  
	  @Modifying
	  @Transactional
	  @Query(value="update hoadonbanhang set giamgia= ?1 , makhachhang= ?2 , khachhangtra= ?3 , loaithanhtoan= ?4 , nguoisua= ?5 , tonggia= ?6 , trangthai= ?7 , updated_at= ?8, ghichu= ?10 where id= ?9 ")
	  void update(long giamgia, String makhachhang, long khachhangtra, int loaithanhtoan, users nguoisua, long tonggia, int trangthai, java.util.Date update, int id, String ghichu);
	  
	  @Query(value = "SELECT * FROM hoadonbanhang  INNER JOIN users ON hoadonbanhang.nguoitao=users.manhanvien  WHERE makhachhang=:makhachhang AND (hoadonbanhang.tonggia-hoadonbanhang.khachhangtra)>0   ORDER BY hoadonbanhang.created_at DESC",
			  countQuery = "SELECT COUNT(*) FROM (SELECT * FROM hoadonbanhang  INNER JOIN users ON hoadonbanhang.nguoitao=users.manhanvien  WHERE makhachhang=:makhachhang AND (hoadonbanhang.tonggia-hoadonbanhang.khachhangtra)>0   ORDER BY hoadonbanhang.created_at DESC) as temp ",
			  nativeQuery = true)
	  Page<hoadonbanhang> fintCustomerBillDebt(String makhachhang, Pageable pageable);
	  
	  
}
