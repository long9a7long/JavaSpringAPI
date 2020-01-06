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

import com.example.demoSpBoot.model.nhacungcap;
import com.example.demoSpBoot.service.NhacungcapService;

@RestController
@RequestMapping("/ShopStore")
@CrossOrigin(origins = "*")
public class NhacungcapController {
	@Autowired
	NhacungcapService nhaccService;
	@GetMapping("/allSUPs")
	/* ---------------- GET ALL NCC ------------------------ */
	public ResponseEntity<List<nhacungcap>> getAllNCC() {
		List<nhacungcap> listSUP= nhaccService.findAllSup();
		if(listSUP.isEmpty()) {
			return new ResponseEntity<List<nhacungcap>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<nhacungcap>>(listSUP, HttpStatus.OK);
	}
	@GetMapping("/NCCs")
	/* ---------------- GET NCC PAGE ------------------------ */
	public ResponseEntity<Page<nhacungcap>> getAllNCCPage(@RequestParam int pageNumber, @RequestParam int pageSize) {
		
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
	@GetMapping("/NCCs/{mancc}")
	public ResponseEntity<nhacungcap> getNCCByMancc(
            @PathVariable("mancc") String mancc) {
        Optional<nhacungcap> ncc = nhaccService.findByMancc(mancc);

        if (!ncc.isPresent()) {
            return new ResponseEntity<>(ncc.get(),
                    HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(ncc.get(), HttpStatus.OK);
    }

	/* ---------------- CREATE NEW NCC ------------------------ */
	@PostMapping("/NCCs")
	public ResponseEntity<nhacungcap> createNCC(@Valid @RequestBody nhacungcap ncc) {
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
	@GetMapping("/suppliers/search")
	/* ---------------- SEARCH ------------------------ */
	public ResponseEntity<Page<nhacungcap>> findNCC(@RequestParam int pageNumber, @RequestParam int pageSize, @RequestParam String searchTerm) throws ParseException {
		Page<nhacungcap> listSUP = null;
			listSUP= nhaccService.searchSupProd(pageNumber,pageSize,searchTerm);
		
			if(listSUP.isEmpty()) {
				return new ResponseEntity<Page<nhacungcap>>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<Page<nhacungcap>>(listSUP, HttpStatus.OK);
	}
}
