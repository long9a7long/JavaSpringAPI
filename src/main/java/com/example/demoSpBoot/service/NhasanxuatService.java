package com.example.demoSpBoot.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demoSpBoot.model.nhasanxuat;
import com.example.demoSpBoot.model.sanpham;
import com.example.demoSpBoot.repository.NhasanxuatRepository;
import com.example.demoSpBoot.repository.ProductRepository;

@Service
public class NhasanxuatService {
	@Autowired
	NhasanxuatRepository nhasxRepo;
	@Autowired
	ProductRepository prodRepo;
	@Autowired
	ProductService prodService;
	public List<nhasanxuat> findAllNSX(){
		return (List<nhasanxuat>) nhasxRepo.findAll();
	}
	public Page<nhasanxuat> findAll(int pageNumber,int pageSize){
		Sort sortable = Sort.by("id").ascending();
		Pageable phantrang = (Pageable) PageRequest.of(pageNumber, pageSize, sortable);
		return (Page<nhasanxuat>) nhasxRepo.findAll( phantrang);
	}
	public Optional<nhasanxuat> findByID(int id) {
        return nhasxRepo.findById(id);
    }

	public boolean create(nhasanxuat nsx) {
		nsx.setCreatedAt(new Date());
		nhasxRepo.save(nsx);
		return true;
	}

	public boolean update(nhasanxuat nsx) {

		if (!nhasxRepo.findById(nsx.getId()).isPresent()) {
			return false;
		} else {
			nhasxRepo.save(nsx);
			return true;
		}
	}

	public boolean delete(int id) {

		nhasanxuat nsx = nhasxRepo.findById(id).orElse(null);
		if (nsx == null) {
			return false;
		} else {
			List<sanpham> listprod = prodRepo.findByNhasanxuat(nsx);
			if(!listprod.isEmpty()) {
				for (sanpham item: listprod) {
					prodService.delete(item.getId());
					System.out.println("abc");
				}
			}
			nhasxRepo.delete(nsx);
			return true;
		}
	}
	public Page<nhasanxuat> searchManu( int pageNumber, int pageSize, String searchTerm){
		Sort sortable = Sort.by("id").descending();
		Pageable phantrang = (Pageable) PageRequest.of(pageNumber, pageSize, sortable);
		return (Page<nhasanxuat>) nhasxRepo.findByidContaining(phantrang,searchTerm);
	}
}
