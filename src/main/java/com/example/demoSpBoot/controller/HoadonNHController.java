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

import com.example.demoSpBoot.model.hoadonnhaphang;
import com.example.demoSpBoot.service.HoadonNHService;

@RestController
@RequestMapping("/ShopStore")
@CrossOrigin(origins = "http://localhost:4200")
public class HoadonNHController {
	@Autowired
	HoadonNHService hoadonNHService;
	@GetMapping("/billNHs")
	/* ---------------- GET ALL BILL ------------------------ */
	public ResponseEntity<Page<hoadonnhaphang>> getAllBillNHPage(@RequestParam int pageNumber, @RequestParam int pageSize) {
		
		Page<hoadonnhaphang> listBillNH= hoadonNHService.findAll(pageNumber,pageSize);
		if(listBillNH.isEmpty()) {
			return new ResponseEntity<Page<hoadonnhaphang>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Page<hoadonnhaphang>>(listBillNH, HttpStatus.OK);
	}
	/* ---------------- GET BILL BY ID ------------------------ */
	@GetMapping("/billNHs/{id}")
	
	public ResponseEntity<hoadonnhaphang> getBillNHById(
            @PathVariable("id") int id) {
        Optional<hoadonnhaphang> billNH = hoadonNHService.findByID(id);

        if (!billNH.isPresent()) {
            return new ResponseEntity<>(billNH.get(),
                    HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(billNH.get(), HttpStatus.OK);
    }

	/* ---------------- CREATE NEW BILL ------------------------ */
	@PostMapping("/billNHs")
	public ResponseEntity<hoadonnhaphang> createBillNH(@Valid @RequestBody hoadonnhaphang billNH) {
		if(hoadonNHService.create(billNH)) return new ResponseEntity<hoadonnhaphang>(billNH,HttpStatus.OK);
		else {
			return new ResponseEntity<hoadonnhaphang>(billNH,HttpStatus.NOT_FOUND);
		}
		
	}
	
	/* ---------------- UPDATE BILL ------------------------ */
	@PutMapping("/billNHs")

	public ResponseEntity<hoadonnhaphang> updateBillNH(@RequestBody hoadonnhaphang billNH) {
		if(hoadonNHService.update(billNH)) return new ResponseEntity<hoadonnhaphang>(billNH,HttpStatus.OK);
		else {
			return new ResponseEntity<hoadonnhaphang>(billNH,HttpStatus.NOT_FOUND);
		}
	}
	/* ---------------- DELETE CATE ------------------------ */
	
	@DeleteMapping("/billNHs/{id}")
	public ResponseEntity<hoadonnhaphang> deleteBillNH(@PathVariable("id") int id) {
		if(hoadonNHService.delete(id)) return new ResponseEntity<hoadonnhaphang>(HttpStatus.OK);
		else {
			return new ResponseEntity<hoadonnhaphang>(HttpStatus.NOT_FOUND);
		}
	}
}
