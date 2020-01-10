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

import com.example.demoSpBoot.model.hoadonnhaphang;
import com.example.demoSpBoot.model.nhacungcap;
import com.example.demoSpBoot.repository.HoadonNHRepository;
import com.example.demoSpBoot.repository.NhacungcapRepository;
import com.example.demoSpBoot.repository.PhieuchiRepository;

@Service
public class NhacungcapService {
	@Autowired
	NhacungcapRepository nhaccRepo;
	@Autowired
	HoadonNHRepository hoadonNHRepo;
	@Autowired
	PhieuchiRepository phieuchiRepo;
	public List<nhacungcap> findAllSup(){
		return (List<nhacungcap>) nhaccRepo.findAll();
	}
	public Page<nhacungcap> findAll(int pageNumber,int pageSize){
		Sort sortable = Sort.by("id").ascending();
		Pageable phantrang = (Pageable) PageRequest.of(pageNumber, pageSize, sortable);
		return (Page<nhacungcap>) nhaccRepo.findAll( phantrang);
	}
	public Optional<nhacungcap> findByID(int id) {
        return nhaccRepo.findById(id);
    }
	public Optional<nhacungcap> findByMancc(String mancc) {
        return nhaccRepo.findBymancc(mancc);
    }

	public boolean create(nhacungcap ncc) {
		String randomString=(new Date()).getTime()+"";
		ncc.setCreatedAt(new Date());
		ncc.setMancc("NCC"+randomString);
		nhaccRepo.save(ncc);
		return true;
	}

	public boolean update(nhacungcap ncc) {

		if (!nhaccRepo.findById(ncc.getId()).isPresent()) {
			return false;
		} else {
			nhaccRepo.save(ncc);
			return true;
		}
	}

	public boolean delete(int id) {

		nhacungcap ncc = nhaccRepo.findById(id).orElse(null);
		if (ncc == null) {
			return false;
		} else {
			List<hoadonnhaphang> listhoadonnh = hoadonNHRepo.findBynhacungcap(ncc);
			if(!listhoadonnh.isEmpty()) {
				for(hoadonnhaphang item: listhoadonnh) {
					nhaccRepo.deletePhieuchi(item.getId());
					hoadonNHRepo.delete(item);
				}
			}
			nhaccRepo.delete(ncc);
			return true;
		}
	}
	
	public Page<nhacungcap> searchSupProd( int pageNumber, int pageSize, String searchTerm){
		Sort sortable = Sort.by("id").descending();
		Pageable phantrang = (Pageable) PageRequest.of(pageNumber, pageSize, sortable);
		return (Page<nhacungcap>) nhaccRepo.findBymanccOrtenncc(phantrang, searchTerm);
	}
}
