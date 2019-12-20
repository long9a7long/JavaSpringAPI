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

import com.example.demoSpBoot.model.phieuchi;
import com.example.demoSpBoot.service.PhieuchiService;

@RestController
@RequestMapping("/ShopStore")
@CrossOrigin(origins = "*")
public class PhieuchiController {
	@Autowired
	PhieuchiService phieuchiService;
	@GetMapping("/phieuchi")
	/* ---------------- GET ALL PHIEU CHI ------------------------ */
	public ResponseEntity<Page<phieuchi>> findAllPhieuchi(@RequestParam int pageNumber, @RequestParam int pageSize) {
		//return new ResponseEntity<ServiceResult>(customerService.findAll(), HttpStatus.OK);
		
		Page<phieuchi> listPC= phieuchiService.findAll(pageNumber,pageSize);
		if(listPC.isEmpty()) {
			return new ResponseEntity<Page<phieuchi>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Page<phieuchi>>(listPC, HttpStatus.OK);
	}
	/* ---------------- GET PHIEU CHI BY ID ------------------------ */
	@GetMapping("/phieuchi/{id}")
	
	public ResponseEntity<phieuchi> getPhieuchiById(
            @PathVariable("id") int id) {
        Optional<phieuchi> phieuchi = phieuchiService.findByID(id);

        if (!phieuchi.isPresent()) {
            return new ResponseEntity<>(phieuchi.get(),
                    HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(phieuchi.get(), HttpStatus.OK);
    }

	/* ---------------- CREATE NEW Phieu chi ------------------------ */
	@PostMapping("/phieuchi")
	public ResponseEntity<phieuchi> saveNCC(@Valid @RequestBody phieuchi phieuchi) {
		if(phieuchiService.create(phieuchi)) return new ResponseEntity<phieuchi>(phieuchi,HttpStatus.OK);
		else {
			return new ResponseEntity<phieuchi>(phieuchi,HttpStatus.NOT_FOUND);
		}
		
	}
	
	/* ---------------- UPDATE Phieu chi ------------------------ */
	@PutMapping("/phieuchi")

	public ResponseEntity<phieuchi> updateNCC(@RequestBody phieuchi phieuchi) {
		if(phieuchiService.update(phieuchi)) return new ResponseEntity<phieuchi>(phieuchi,HttpStatus.OK);
		else {
			return new ResponseEntity<phieuchi>(phieuchi,HttpStatus.NOT_FOUND);
		}
	}
	/* ---------------- DELETE phieu chi ------------------------ */
	
	@DeleteMapping("/phieuchi/{id}")
	public ResponseEntity<phieuchi> deleteNSX(@PathVariable("id") int id) {
		if(phieuchiService.delete(id)) return new ResponseEntity<phieuchi>(HttpStatus.OK);
		else {
			return new ResponseEntity<phieuchi>(HttpStatus.NOT_FOUND);
		}
	}
}
