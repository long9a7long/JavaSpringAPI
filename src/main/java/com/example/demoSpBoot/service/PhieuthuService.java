package com.example.demoSpBoot.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demoSpBoot.model.phieuthu;
import com.example.demoSpBoot.repository.PhieuthuRepository;

@Service
public class PhieuthuService {
	@Autowired
	PhieuthuRepository phieuthuRepo;
	public Page<phieuthu> findAll(int pageNumber,int pageSize){
		Sort sortable = Sort.by("id").ascending();
		Pageable phantrang = (Pageable) PageRequest.of(pageNumber, pageSize, sortable);
		return (Page<phieuthu>) phieuthuRepo.findAll( phantrang);
	}
	public Optional<phieuthu> findByID(int id) {
        return phieuthuRepo.findById(id);
    }

	public boolean create(phieuthu phieuthu) {
		if(!phieuthuRepo.findBymaphieuthu(phieuthu.getMaphieuthu()).isPresent())
		{
		phieuthuRepo.save(phieuthu);
			return true;
		}else return false;
	}

	public boolean update(phieuthu phieuthu) {

		if (!phieuthuRepo.findById(phieuthu.getId()).isPresent()) {
			return false;
		} else {
			phieuthuRepo.save(phieuthu);
			return true;
		}
	}

	public boolean delete(int id) {

		phieuthu phieuthu = phieuthuRepo.findById(id).orElse(null);
		if (phieuthu == null) {
			return false;
		} else {
			phieuthuRepo.delete(phieuthu);
			return true;
		}
	}
}
