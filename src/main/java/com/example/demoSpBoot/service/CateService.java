package com.example.demoSpBoot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demoSpBoot.model.danhmucsp;
import com.example.demoSpBoot.model.sanpham;
import com.example.demoSpBoot.repository.CateRepository;

@Service
public class CateService {
	@Autowired
	CateRepository cateRepository;
	public List<danhmucsp> findAllCate(){
		return (List<danhmucsp>) cateRepository.findAll();
	}
	public Page<danhmucsp> findAll(int pageNumber,int pageSize){
		Sort sortable = Sort.by("id").ascending();
		Pageable phantrang = (Pageable) PageRequest.of(pageNumber, pageSize, sortable);
		return (Page<danhmucsp>) cateRepository.findAll(phantrang);
	}
	public Optional<danhmucsp> findByID(int id) {
        return cateRepository.findById(id);
    }

	public boolean create(danhmucsp cate) {
			cateRepository.save(cate);
			return true;
	}

	public boolean update(danhmucsp cate) {

		if (!cateRepository.findById(cate.getId()).isPresent()) {
			return false;
		} else {
			cateRepository.save(cate);
			return true;
		}
	}

	public boolean delete(int id) {

		danhmucsp cate = cateRepository.findById(id).orElse(null);
		if (cate == null) {
			return false;
		} else {
			cateRepository.delete(cate);
			return true;
		}
	}
	public Page<danhmucsp> searchCate( int pageNumber, int pageSize, String searchTerm){
		Sort sortable = Sort.by("id").descending();
		Pageable phantrang = (Pageable) PageRequest.of(pageNumber, pageSize, sortable);
		return (Page<danhmucsp>) cateRepository.findBytendanhmucContaining(phantrang,searchTerm);
	}
}
