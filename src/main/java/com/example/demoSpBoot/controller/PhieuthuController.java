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

import com.example.demoSpBoot.model.phieuthu;
import com.example.demoSpBoot.service.PhieuthuService;

@RestController
@RequestMapping("/ShopStore")
@CrossOrigin(origins = "*")
public class PhieuthuController {
	@Autowired
	PhieuthuService phieuthuService;
	@GetMapping("/phieuthu")
	/* ---------------- GET ALL PHIEU THU ------------------------ */
	public ResponseEntity<Page<phieuthu>> findAllPhieuchi(@RequestParam int pageNumber, @RequestParam int pageSize) {
		
		Page<phieuthu> listPT= phieuthuService.findAll(pageNumber,pageSize);
		if(listPT.isEmpty()) {
			return new ResponseEntity<Page<phieuthu>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Page<phieuthu>>(listPT, HttpStatus.OK);
	}
	/* ---------------- GET PHIEU THU BY ID ------------------------ */
	@GetMapping("/phieuthu/{id}")
	
	public ResponseEntity<phieuthu> getPhieuchiById(
            @PathVariable("id") int id) {
        Optional<phieuthu> phieuthu = phieuthuService.findByID(id);

        if (!phieuthu.isPresent()) {
            return new ResponseEntity<>(phieuthu.get(),
                    HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(phieuthu.get(), HttpStatus.OK);
    }

	/* ---------------- CREATE NEW phieu thu ------------------------ */
	@PostMapping("/phieuthu")
	public ResponseEntity<phieuthu> saveNCC(@Valid @RequestBody phieuthu phieuthu) {
		if(phieuthuService.create(phieuthu)) return new ResponseEntity<phieuthu>(phieuthu,HttpStatus.OK);
		else {
			return new ResponseEntity<phieuthu>(phieuthu,HttpStatus.NOT_FOUND);
		}
		
	}
	
	/* ---------------- UPDATE Phieu thu ------------------------ */
	@PutMapping("/phieuthu")

	public ResponseEntity<phieuthu> updateNCC(@RequestBody phieuthu phieuthu) {
		if(phieuthuService.update(phieuthu)) return new ResponseEntity<phieuthu>(phieuthu,HttpStatus.OK);
		else {
			return new ResponseEntity<phieuthu>(phieuthu,HttpStatus.NOT_FOUND);
		}
	}
	/* ---------------- DELETE phieu thu ------------------------ */
	
	@DeleteMapping("/phieuthu/{id}")
	public ResponseEntity<phieuthu> deleteNSX(@PathVariable("id") int id) {
		if(phieuthuService.delete(id)) return new ResponseEntity<phieuthu>(HttpStatus.OK);
		else {
			return new ResponseEntity<phieuthu>(HttpStatus.NOT_FOUND);
		}
	}
}
