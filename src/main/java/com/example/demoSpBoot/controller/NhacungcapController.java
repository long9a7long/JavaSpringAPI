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

import com.example.demoSpBoot.model.nhacungcap;
import com.example.demoSpBoot.service.NhacungcapService;

@RestController
@RequestMapping("/ShopStore")
@CrossOrigin(origins = "*")
public class NhacungcapController {
	@Autowired
	NhacungcapService nhaccService;
	@GetMapping("/NCCs")
	/* ---------------- GET ALL NCC ------------------------ */
	public ResponseEntity<Page<nhacungcap>> findAllNCCs(@RequestParam int pageNumber, @RequestParam int pageSize) {
		//return new ResponseEntity<ServiceResult>(customerService.findAll(), HttpStatus.OK);
		
		Page<nhacungcap> listNCC= nhaccService.findAll(pageNumber,pageSize);
		if(listNCC.isEmpty()) {
			return new ResponseEntity<Page<nhacungcap>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Page<nhacungcap>>(listNCC, HttpStatus.OK);
	}
	/* ---------------- GET NCC BY ID ------------------------ */
	@GetMapping("/NCCs/{id}")
	
	public ResponseEntity<nhacungcap> getNCCById(
            @PathVariable("id") int id) {
        Optional<nhacungcap> ncc = nhaccService.findByID(id);

        if (!ncc.isPresent()) {
            return new ResponseEntity<>(ncc.get(),
                    HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(ncc.get(), HttpStatus.OK);
    }

	/* ---------------- CREATE NEW NCC ------------------------ */
	@PostMapping("/NCCs")
	public ResponseEntity<nhacungcap> saveNCC(@Valid @RequestBody nhacungcap ncc) {
		if(nhaccService.create(ncc)) return new ResponseEntity<nhacungcap>(ncc,HttpStatus.OK);
		else {
			return new ResponseEntity<nhacungcap>(ncc,HttpStatus.NOT_FOUND);
		}
		
	}
	
	/* ---------------- UPDATE NCC ------------------------ */
	@PutMapping("/NCCs")

	public ResponseEntity<nhacungcap> updateNCC(@RequestBody nhacungcap ncc) {
		if(nhaccService.update(ncc)) return new ResponseEntity<nhacungcap>(ncc,HttpStatus.OK);
		else {
			return new ResponseEntity<nhacungcap>(ncc,HttpStatus.NOT_FOUND);
		}
	}
	/* ---------------- DELETE NCC ------------------------ */
	
	@DeleteMapping("/NCCs/{id}")
	public ResponseEntity<nhacungcap> deleteNCC(@PathVariable("id") int id) {
		if(nhaccService.delete(id)) return new ResponseEntity<nhacungcap>(HttpStatus.OK);
		else {
			return new ResponseEntity<nhacungcap>(HttpStatus.NOT_FOUND);
		}
	}
}
