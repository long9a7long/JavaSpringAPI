package com.example.demoSpBoot.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demoSpBoot.model.chitiethoadonbh;
import com.example.demoSpBoot.repository.DetailBHRepository;

@Service
public class DetailBHService {
	@Autowired
	DetailBHRepository detailBHRepo;
	public Page<chitiethoadonbh> findAll(int pageNumber,int pageSize){
		Sort sortable = Sort.by("id").ascending();
		Pageable phantrang = (Pageable) PageRequest.of(pageNumber, pageSize, sortable);
		return (Page<chitiethoadonbh>) detailBHRepo.findAll(phantrang);
	}
	public Optional<chitiethoadonbh> findByID(int id) {
        return detailBHRepo.findById(id);
    }

	public boolean create(chitiethoadonbh bill) {
			detailBHRepo.save(bill);
			return true;
	}
}
