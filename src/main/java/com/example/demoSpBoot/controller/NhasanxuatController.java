package com.example.demoSpBoot.controller;

import java.text.ParseException;
import java.util.List;
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

import com.example.demoSpBoot.model.nhasanxuat;
import com.example.demoSpBoot.service.NhasanxuatService;

@RestController
@RequestMapping("/ShopStore")
@CrossOrigin(origins = "http://localhost:4200")
public class NhasanxuatController {
	@Autowired
	NhasanxuatService nsxService;
	@GetMapping("/allNSX")
	/* ---------------- GET ALL NSX ------------------------ */
	public ResponseEntity<List<nhasanxuat>> getAllNSX() {
		
		List<nhasanxuat> listNSX= nsxService.findAllNSX();
		if(listNSX.isEmpty()) {
			return new ResponseEntity<List<nhasanxuat>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<nhasanxuat>>(listNSX, HttpStatus.OK);
	}
	
	@GetMapping("/NSXs")
	/* ---------------- GET ALL NSX PAGE ------------------------ */
	public ResponseEntity<Page<nhasanxuat>> getAllNSXPage(@RequestParam int pageNumber, @RequestParam int pageSize) {
		//return new ResponseEntity<ServiceResult>(customerService.findAll(), HttpStatus.OK);
		
		Page<nhasanxuat> listNSX= nsxService.findAll(pageNumber,pageSize);
		if(listNSX.isEmpty()) {
			return new ResponseEntity<Page<nhasanxuat>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Page<nhasanxuat>>(listNSX, HttpStatus.OK);
	}
	/* ---------------- GET NSX BY ID ------------------------ */
	@GetMapping("/NSXs/{id}")
	
	public ResponseEntity<nhasanxuat> getNSXById(
            @PathVariable("id") int id) {
        Optional<nhasanxuat> nsx = nsxService.findByID(id);

        if (!nsx.isPresent()) {
            return new ResponseEntity<>(nsx.get(),
                    HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(nsx.get(), HttpStatus.OK);
    }

	/* ---------------- CREATE NEW NSX ------------------------ */
	@PostMapping("/NSXs")
	public ResponseEntity<nhasanxuat> createNSX(@Valid @RequestBody nhasanxuat nsx) {
		if(nsxService.create(nsx)) return new ResponseEntity<nhasanxuat>(nsx,HttpStatus.OK);
		else {
			return new ResponseEntity<nhasanxuat>(nsx,HttpStatus.NOT_FOUND);
		}
		
	}
	
	/* ---------------- UPDATE NSX ------------------------ */
	@PutMapping("/NSXs")

	public ResponseEntity<nhasanxuat> updateNSX(@RequestBody nhasanxuat nsx) {
		if(nsxService.update(nsx)) return new ResponseEntity<nhasanxuat>(nsx,HttpStatus.OK);
		else {
			return new ResponseEntity<nhasanxuat>(nsx,HttpStatus.NOT_FOUND);
		}
	}
	/* ---------------- DELETE NSX ------------------------ */
	
	@DeleteMapping("/NSXs/{id}")
	public ResponseEntity<nhasanxuat> deleteNSX(@PathVariable("id") int id) {
		if(nsxService.delete(id)) return new ResponseEntity<nhasanxuat>(HttpStatus.OK);
		else {
			return new ResponseEntity<nhasanxuat>(HttpStatus.NOT_FOUND);
		}
	}
	@GetMapping("/ManuProd/search")
	/* ---------------- SEARCH ------------------------ */
	public ResponseEntity<Page<nhasanxuat>> findNSX(@RequestParam int pageNumber, @RequestParam int pageSize, @RequestParam String searchTerm) throws ParseException {
		Page<nhasanxuat> listManu = null;
			listManu= nsxService.searchManu(pageNumber,pageSize,searchTerm);
		
			if(listManu.isEmpty()) {
				return new ResponseEntity<Page<nhasanxuat>>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<Page<nhasanxuat>>(listManu, HttpStatus.OK);
	}
}
