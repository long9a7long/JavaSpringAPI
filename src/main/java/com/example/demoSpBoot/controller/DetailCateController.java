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
@CrossOrigin(origins = "*")
public class DetailCateController {
	@Autowired
	DetailCateService detailCateService;
	@GetMapping("/detailCate")
	/* ---------------- GET ALL BILL ------------------------ */
	public ResponseEntity<Page<chitietdanhmuc>> findAlls(@RequestParam int pageNumber, @RequestParam int pageSize) {
		//return new ResponseEntity<ServiceResult>(customerService.findAll(), HttpStatus.OK);
		
		Page<chitietdanhmuc> listdetailCate= detailCateService.findAll(pageNumber,pageSize);
		if(listdetailCate.isEmpty()) {
			return new ResponseEntity<Page<chitietdanhmuc>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Page<chitietdanhmuc>>(listdetailCate, HttpStatus.OK);
	}
	/* ---------------- GET BILL BY ID ------------------------ */
	@GetMapping("/detailCate/{id}")
	
	public ResponseEntity<chitietdanhmuc> getBillById(
            @PathVariable("id") int id) {
        Optional<chitietdanhmuc> detailCate = detailCateService.findByID(id);

        if (!detailCate.isPresent()) {
            return new ResponseEntity<>(detailCate.get(),
                    HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(detailCate.get(), HttpStatus.OK);
    }

	/* ---------------- CREATE NEW BILL ------------------------ */
	@PostMapping("/detailCate")
	public ResponseEntity<chitietdanhmuc> saveBill(@Valid @RequestBody chitietdanhmuc detailCate) {
		if(detailCateService.create(detailCate)) return new ResponseEntity<chitietdanhmuc>(detailCate,HttpStatus.OK);
		else {
			return new ResponseEntity<chitietdanhmuc>(detailCate,HttpStatus.NOT_FOUND);
		}
		
	}
	
	/* ---------------- UPDATE BILL ------------------------ */
	@PutMapping("/detailCate")

	public ResponseEntity<chitietdanhmuc> updateBill(@RequestBody chitietdanhmuc detailCate) {
		if(detailCateService.update(detailCate)) return new ResponseEntity<chitietdanhmuc>(detailCate,HttpStatus.OK);
		else {
			return new ResponseEntity<chitietdanhmuc>(detailCate,HttpStatus.NOT_FOUND);
		}
	}
	/* ---------------- DELETE CATE ------------------------ */
	
	@DeleteMapping("/detailCate/{id}")
	public ResponseEntity<chitietdanhmuc> deleteCate(@PathVariable("id") int id) {
		if(detailCateService.delete(id)) return new ResponseEntity<chitietdanhmuc>(HttpStatus.OK);
		else {
			return new ResponseEntity<chitietdanhmuc>(HttpStatus.NOT_FOUND);
		}
	}
}
