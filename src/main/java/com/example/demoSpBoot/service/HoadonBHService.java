package com.example.demoSpBoot.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demoSpBoot.model.chitiethoadonbh;
import com.example.demoSpBoot.model.hoadonbanhang;
import com.example.demoSpBoot.model.sanpham;
import com.example.demoSpBoot.repository.DetailBHRepository;
import com.example.demoSpBoot.repository.HoadonBHRepository;

@Service
public class HoadonBHService {
	@Autowired
	HoadonBHRepository hoadonBHRepo;
	@Autowired
	DetailBHRepository chitietRepo;
	@Autowired
	ProductService prdService;
	
	public Page<hoadonbanhang> findAll(int pageNumber,int pageSize){
		Sort sortable = Sort.by("id").descending();
		Pageable phantrang = (Pageable) PageRequest.of(pageNumber, pageSize, sortable);
		return (Page<hoadonbanhang>) hoadonBHRepo.findAll( phantrang);
	}
	
	public Optional<hoadonbanhang> findByID(int id) {
        return hoadonBHRepo.findById(id);
    }

	public boolean create(hoadonbanhang bill) {
		if(!hoadonBHRepo.findBymahoadon(bill.getMahoadon()).isPresent()) {
			bill.setCreatedAt(new Date());
			String randomStr= (new Date()).getTime() +"";
			bill.setMahoadon("HD"+randomStr);
			hoadonBHRepo.save(bill);
			return true;
			}
		else return false;
	}
	
	public boolean update(hoadonbanhang bill) {
		
		if (!hoadonBHRepo.findById(bill.getId()).isPresent()) {
			return false;
		} else {
			List<chitiethoadonbh> chitiethds = bill.getChitiethoadons();
			List<chitiethoadonbh> new_chitiethds = new ArrayList<chitiethoadonbh>();
			for( @Valid chitiethoadonbh chitiet : chitiethds) {
				Optional<sanpham> sp= prdService.findByID(chitiet.getSanpham().getId());
				int soluongdetailbill_old=0;
				Optional<chitiethoadonbh> chitiethd_old = chitietRepo.findById(chitiet.getId());
				if ( chitiethd_old.isPresent()) {
					soluongdetailbill_old = chitiethd_old.get().getSoluong();
				}
				int soluong = sp.get().getSoluong() + soluongdetailbill_old - chitiet.getSoluong();
				sp.get().setSoluong(soluong);
				chitiethoadonbh new_chitiet= new chitiethoadonbh();
				new_chitiet.setGia(chitiet.getGia());
				new_chitiet.setGiamgia(chitiet.getGiamgia());
				new_chitiet.setId_hoadon(chitiet.getId_hoadon());
				new_chitiet.setSanpham(chitiet.getSanpham());
				new_chitiet.setSoluong(chitiet.getSoluong());
				new_chitiethds.add(new_chitiet);
				prdService.update(sp.get());
				
			}
			hoadonBHRepo.deletechitiethoadon(bill.getId());
			for (chitiethoadonbh chitiet : new_chitiethds) {
				chitietRepo.save(chitiet);
			}
			
			bill.setUpdatedAt(new Date());
			hoadonBHRepo.update(bill.getGiamgia(),bill.getKhachhang().getMakhachhang(),bill.getKhachhangtra(), bill.getLoaithanhtoan(),bill.getNguoisua(),bill.getTonggia(),bill.getTrangthai(),bill.getUpdatedAt(),bill.getId(),bill.getGhichu());
			
			return true;
		}
	}
	
	public boolean cancleBill(hoadonbanhang bill) {
		
		if (!hoadonBHRepo.findById(bill.getId()).isPresent()) {
			return false;
		} else {
			List<chitiethoadonbh> chitiethds = bill.getChitiethoadons();
			List<chitiethoadonbh> new_chitiethds = new ArrayList<chitiethoadonbh>();
			for( @Valid chitiethoadonbh chitiet : chitiethds) {
				Optional<sanpham> sp= prdService.findByID(chitiet.getSanpham().getId());
				int soluongdetailbill_old=0;
				Optional<chitiethoadonbh> chitiethd_old = chitietRepo.findById(chitiet.getId());
				if ( chitiethd_old.isPresent()) {
					soluongdetailbill_old = chitiethd_old.get().getSoluong();
				}
				int soluong = sp.get().getSoluong() + soluongdetailbill_old;
				sp.get().setSoluong(soluong);
				prdService.update(sp.get());
				
			}
			bill.setUpdatedAt(new Date());
			hoadonBHRepo.update(bill.getGiamgia(),bill.getKhachhang().getMakhachhang(),bill.getKhachhangtra(), bill.getLoaithanhtoan(),bill.getNguoisua(),bill.getTonggia(),bill.getTrangthai(),bill.getUpdatedAt(),bill.getId(),bill.getGhichu());
			
			return true;
		}
	}
	
	public boolean delete(int id) {

		hoadonbanhang bill = hoadonBHRepo.findById(id).orElse(null);
		if (bill == null) {
			return false;
		} else {
			hoadonBHRepo.deletechitiethoadon(id);
			hoadonBHRepo.delete(id);
			
			return true;
		}
	}
	
	public Page<hoadonbanhang> searchBill( int pageNumber, int pageSize, String searchTerm, Date fromdate, Date todate){
		Sort sortable = Sort.by("id").descending();
		Pageable phantrang = (Pageable) PageRequest.of(pageNumber, pageSize, sortable);
		java.sql.Date sDate1 = convertUtilToSql(fromdate);
		java.sql.Date sDate2 = convertUtilToSql(todate);
		return (Page<hoadonbanhang>) hoadonBHRepo.findByMahoadonContainingAndCreatedAtBetween(phantrang,searchTerm,sDate1,sDate2);
	}
	
	public Page<hoadonbanhang> searchBillNoDate( int pageNumber, int pageSize, String searchTerm){
		Sort sortable = Sort.by("id").descending();
		Pageable phantrang = (Pageable) PageRequest.of(pageNumber, pageSize, sortable);
		return (Page<hoadonbanhang>) hoadonBHRepo.findByMahoadonContaining(phantrang,searchTerm);
	}
	
	private static java.sql.Date convertUtilToSql(java.util.Date uDate) {
        java.sql.Date sDate = new java.sql.Date(uDate.getTime());
        return sDate;
    }
	
	
}
