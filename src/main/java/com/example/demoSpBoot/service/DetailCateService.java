package com.example.demoSpBoot.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demoSpBoot.model.chitietdanhmuc;
import com.example.demoSpBoot.repository.DetailCateRepository;

@Service
public class DetailCateService {
	@Autowired
	DetailCateRepository detailCateRepo;
	public Page<chitietdanhmuc> findAll(int pageNumber,int pageSize){
		Sort sortable = Sort.by("id").ascending();
		Pageable phantrang = (Pageable) PageRequest.of(pageNumber, pageSize, sortable);
		return (Page<chitietdanhmuc>) detailCateRepo.findAll( phantrang);
	}
	public Optional<chitietdanhmuc> findByID(int id) {
        return detailCateRepo.findById(id);
    }

	public boolean create(chitietdanhmuc detailcate) {
			detailCateRepo.save(detailcate);
			return true;
	}

	public boolean update(chitietdanhmuc detailcate) {

		if (!detailCateRepo.findById(detailcate.getId()).isPresent()) {
			return false;
		} else {
			detailCateRepo.save(detailcate);
			return true;
		}
	}

	public boolean delete(int id) {

		chitietdanhmuc detailcate = detailCateRepo.findById(id).orElse(null);
		if (detailcate == null) {
			return false;
		} else {
			detailCateRepo.delete(detailcate);
			return true;
		}
	}
}
