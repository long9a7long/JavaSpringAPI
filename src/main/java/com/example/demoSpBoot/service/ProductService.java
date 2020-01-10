package com.example.demoSpBoot.service;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demoSpBoot.model.chitiethoadonbh;
import com.example.demoSpBoot.model.hoadonbanhang;
import com.example.demoSpBoot.model.nhasanxuat;
import com.example.demoSpBoot.model.sanpham;
import com.example.demoSpBoot.repository.DetailBHRepository;
import com.example.demoSpBoot.repository.HoadonBHRepository;
import com.example.demoSpBoot.repository.NhasanxuatRepository;
import com.example.demoSpBoot.repository.PhieuthuRepository;
import com.example.demoSpBoot.repository.ProductRepository;

@Service
public class ProductService {
	@Autowired
	ProductRepository productRepo;
	@Autowired
	NhasanxuatRepository nhasxRepo;
	@Autowired
	DetailBHRepository chitiethdbhRepo;
	@Autowired
	HoadonBHRepository hoadonRepo;
	@Autowired
	PhieuthuRepository phieuthuRepo;
	public List<sanpham> findAllProd(){
		return (List<sanpham>) productRepo.findAll();
	}
	
	public Page<sanpham> findAll(int pageNumber,int pageSize){
		Sort sortable = Sort.by("id").descending();
		Pageable phantrang = (Pageable) PageRequest.of(pageNumber, pageSize, sortable);
		return (Page<sanpham>) productRepo.findAll( phantrang);
	}
	public Optional<sanpham> findByID(int id) {
        return productRepo.findById(id);
    }
	public Optional<sanpham> findByMasp(String masp) {
        return productRepo.findBymasp(masp);
    }

	public boolean create(sanpham product) {		
			String randomString=(new Date()).getTime()+"";
			product.setCreatedAt(new Date());
			product.setMasp("SP"+randomString);
			product.setTrangthai(1);
			productRepo.save(product);
			return true;
	}

	public boolean update(sanpham product) {

		if (!productRepo.findById(product.getId()).isPresent()) {
			return false;
		} else {
			productRepo.save(product);
			return true;
		}
	}

	public boolean delete(int id) {

		sanpham product = productRepo.findById(id).orElse(null);
		if (product == null) {
			return false;
		} else {
			productRepo.deleteDetailCate(id);
			List<chitiethoadonbh> listchitiet = chitiethdbhRepo.findBySanpham(product);
			if(!listchitiet.isEmpty()) {
				for(chitiethoadonbh item : listchitiet) {
					productRepo.deletephieuthu(item.getId_hoadon());
					hoadonRepo.delete(item.getId_hoadon());
				}
			}
			productRepo.deleteDetailBillBH(id);
			productRepo.deleteDetailBillNH(id);
			productRepo.delete(product);
			return true;
		}
	}
	public Page<sanpham> searchProduct( int pageNumber, int pageSize, String searchTerm){
		Sort sortable = Sort.by("id").descending();
		Pageable phantrang = (Pageable) PageRequest.of(pageNumber, pageSize, sortable);
		return (Page<sanpham>) productRepo.findBymasportensp(phantrang,searchTerm);
	}
	
	public Page<sanpham> filterProduct( int pageNumber, int pageSize, int trangthai, int nhasx){
		Optional<nhasanxuat> nhasanxuat= nhasxRepo.findById(nhasx);
		Sort sortable = Sort.by("id").descending();
		Pageable phantrang = (Pageable) PageRequest.of(pageNumber, pageSize, sortable);
		if(trangthai==0) {
			return (Page<sanpham>) productRepo.findByNhasanxuatIs(phantrang, nhasanxuat.get());
		}else if(nhasx==0) {
			int tt = trangthai==1?1:0;
			return (Page<sanpham>) productRepo.findByTrangthaiIs(phantrang, tt);
		}else {
			int tt = trangthai==1?1:0;
			return (Page<sanpham>) productRepo.findByTrangthaiAndNhasanxuatIs(phantrang, tt, nhasanxuat.get());
		}
	}
}
