package com.example.demoSpBoot.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demoSpBoot.model.phieuchi;
import com.example.demoSpBoot.repository.PhieuchiRepository;

@Service
public class PhieuchiService {
	@Autowired
	PhieuchiRepository phieuchiRepo;
	public Page<phieuchi> findAll(int pageNumber,int pageSize){
		Sort sortable = Sort.by("id").ascending();
		Pageable phantrang = (Pageable) PageRequest.of(pageNumber, pageSize, sortable);
		return (Page<phieuchi>) phieuchiRepo.findAll( phantrang);
	}
	public Optional<phieuchi> findByID(int id) {
        return phieuchiRepo.findById(id);
    }

	public boolean create(phieuchi phieuchi) {
		if(!phieuchiRepo.findBymaphieuchi(phieuchi.getMaphieuchi()).isPresent())
		{
		phieuchiRepo.save(phieuchi);
			return true;
		}else return false;
	}

	public boolean update(phieuchi phieuchi) {

		if (!phieuchiRepo.findById(phieuchi.getId()).isPresent()) {
			return false;
		} else {
			phieuchiRepo.save(phieuchi);
			return true;
		}
	}

	public boolean delete(int id) {

		phieuchi phieuchi = phieuchiRepo.findById(id).orElse(null);
		if (phieuchi == null) {
			return false;
		} else {
			phieuchiRepo.delete(phieuchi);
			return true;
		}
	}
}
