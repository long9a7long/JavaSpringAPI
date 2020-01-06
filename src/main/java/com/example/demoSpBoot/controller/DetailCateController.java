package com.example.demoSpBoot.controller;

import java.util.Optional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demoSpBoot.model.chitietdanhmuc;
import com.example.demoSpBoot.service.DetailCateService;

@RestController
@RequestMapping("/ShopStore")
@CrossOrigin(origins = "http://localhost:4200")
public class DetailCateController {
	@Autowired
	DetailCateService detailCateService;
	@GetMapping("/detailCate")
	/* ---------------- GET ALL DETAILCATE ------------------------ */
	public ResponseEntity<Page<chitietdanhmuc>> getAllDetailCatePage(@RequestParam int pageNumber, @RequestParam int pageSize) {
		
		Page<chitietdanhmuc> listdetailCate= detailCateService.findAll(pageNumber,pageSize);
		if(listdetailCate.isEmpty()) {
			return new ResponseEntity<Page<chitietdanhmuc>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Page<chitietdanhmuc>>(listdetailCate, HttpStatus.OK);
	}
	/* ---------------- GET DETAILCATE BY ID ------------------------ */
	@GetMapping("/detailCate/{id}")
	
	public ResponseEntity<chitietdanhmuc> getDetailCateById(
            @PathVariable("id") int id) {
        Optional<chitietdanhmuc> detailCate = detailCateService.findByID(id);

        if (!detailCate.isPresent()) {
            return new ResponseEntity<>(detailCate.get(),
                    HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(detailCate.get(), HttpStatus.OK);
    }

	/* ---------------- CREATE NEW DETAILCATE ------------------------ */
	@PostMapping("/detailCate")
	public ResponseEntity<Boolean> createDetailCate(@Valid @RequestBody chitietdanhmuc[] detailCate) {
		if(detailCateService.create(detailCate)) return new ResponseEntity<Boolean>(HttpStatus.OK);
		else {
			return new ResponseEntity<Boolean>(HttpStatus.BAD_REQUEST);
		}
		
	}
	
	/* ---------------- UPDATE DETAILCATE ------------------------ */
	@PutMapping("/detailCate")

	public ResponseEntity<chitietdanhmuc> updateDetailCate(@RequestBody chitietdanhmuc detailCate) {
		if(detailCateService.update(detailCate)) return new ResponseEntity<chitietdanhmuc>(detailCate,HttpStatus.OK);
		else {
			return new ResponseEntity<chitietdanhmuc>(detailCate,HttpStatus.NOT_FOUND);
		}
	}
	/* ---------------- DELETE DETAILCATE ------------------------ */
	
	@DeleteMapping("/detailCate/{id}")
	public ResponseEntity<chitietdanhmuc> deleteDetailCate(@PathVariable("id") int id) {
		if(detailCateService.delete(id)) return new ResponseEntity<chitietdanhmuc>(HttpStatus.OK);
		else {
			return new ResponseEntity<chitietdanhmuc>(HttpStatus.NOT_FOUND);
		}
	}
}
