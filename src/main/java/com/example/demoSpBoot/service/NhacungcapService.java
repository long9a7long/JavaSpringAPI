package com.example.demoSpBoot.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demoSpBoot.model.nhacungcap;
import com.example.demoSpBoot.repository.NhacungcapRepository;

@Service
public class NhacungcapService {
	@Autowired
	NhacungcapRepository nhaccRepo;
	public Page<nhacungcap> findAll(int pageNumber,int pageSize){
		Sort sortable = Sort.by("id").ascending();
		Pageable phantrang = (Pageable) PageRequest.of(pageNumber, pageSize, sortable);
		return (Page<nhacungcap>) nhaccRepo.findAll( phantrang);
	}
	public Optional<nhacungcap> findByID(int id) {
        return nhaccRepo.findById(id);
    }

	public boolean create(nhacungcap ncc) {
		if(!nhaccRepo.findBymancc(ncc.getMancc()).isPresent())
		{
		nhaccRepo.save(ncc);
			return true;
		}else return false;
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
			nhaccRepo.delete(ncc);
			return true;
		}
	}
}
