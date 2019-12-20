package com.example.demoSpBoot.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demoSpBoot.model.chitiethoadonnh;
import com.example.demoSpBoot.repository.DetailNHRepository;

@Service
public class DetailNHService {
	@Autowired
	DetailNHRepository detailNHRepo;
	public Page<chitiethoadonnh> findAll(int pageNumber,int pageSize){
		Sort sortable = Sort.by("id").ascending();
		Pageable phantrang = (Pageable) PageRequest.of(pageNumber, pageSize, sortable);
		return (Page<chitiethoadonnh>) detailNHRepo.findAll( phantrang);
	}
	public Optional<chitiethoadonnh> findByID(int id) {
        return detailNHRepo.findById(id);
    }

	public boolean create(chitiethoadonnh bill) {
			detailNHRepo.save(bill);
			return true;
	}
}
