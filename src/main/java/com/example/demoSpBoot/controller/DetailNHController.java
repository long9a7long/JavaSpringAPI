package com.example.demoSpBoot.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demoSpBoot.model.chitiethoadonnh;
import com.example.demoSpBoot.service.DetailNHService;

@RestController
@RequestMapping("/ShopStore")
@CrossOrigin(origins = "http://localhost:4200")
public class DetailNHController {
	@Autowired
	DetailNHService detailNHService;
	@GetMapping("/detailNH")
	public ResponseEntity<Page<chitiethoadonnh>> findAllCates(@RequestParam int pageNumber, @RequestParam int pageSize) {
		//return new ResponseEntity<ServiceResult>(customerService.findAll(), HttpStatus.OK);
		
		Page<chitiethoadonnh> listDetail= detailNHService.findAll(pageNumber,pageSize);
		if(listDetail.isEmpty()) {
			return new ResponseEntity<Page<chitiethoadonnh>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Page<chitiethoadonnh>>(listDetail, HttpStatus.OK);
	}
	/* ---------------- GET CATE BY ID ------------------------ */
	@GetMapping("/detailNH/{id}")
	
	public ResponseEntity<chitiethoadonnh> getcateById(
            @PathVariable("id") int id) {
        Optional<chitiethoadonnh> chitiet = detailNHService.findByID(id);

        if (!chitiet.isPresent()) {
            return new ResponseEntity<>(chitiet.get(),
                    HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(chitiet.get(), HttpStatus.OK);
    }

	/* ---------------- CREATE NEW CATE ------------------------ */
	@PostMapping("/detailNH")
	public ResponseEntity<chitiethoadonnh> saveCate(@Valid @RequestBody chitiethoadonnh chitiet) {
		if(detailNHService.create(chitiet)) return new ResponseEntity<chitiethoadonnh>(chitiet,HttpStatus.OK);
		else {
			return new ResponseEntity<chitiethoadonnh>(chitiet,HttpStatus.NOT_FOUND);
		}
		
	}
}
