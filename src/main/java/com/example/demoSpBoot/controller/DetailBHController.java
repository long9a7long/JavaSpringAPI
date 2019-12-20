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

import com.example.demoSpBoot.model.chitiethoadonbh;
import com.example.demoSpBoot.model.sanpham;
import com.example.demoSpBoot.service.DetailBHService;
import com.example.demoSpBoot.service.ProductService;

@RestController
@RequestMapping("/ShopStore")
@CrossOrigin(origins = "http://localhost:4200")
public class DetailBHController {
	@Autowired
	DetailBHService detailBHService;
	@Autowired
	ProductService prdService;
	@GetMapping("/detailBH")
	public ResponseEntity<Page<chitiethoadonbh>> findAllCates(@RequestParam int pageNumber, @RequestParam int pageSize) {
		//return new ResponseEntity<ServiceResult>(customerService.findAll(), HttpStatus.OK);
		
		Page<chitiethoadonbh> listDetail= detailBHService.findAll(pageNumber,pageSize);
		if(listDetail.isEmpty()) {
			return new ResponseEntity<Page<chitiethoadonbh>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Page<chitiethoadonbh>>(listDetail, HttpStatus.OK);
	}
	/* ---------------- GET CATE BY ID ------------------------ */
	@GetMapping("/detailBH/{id}")
	
	public ResponseEntity<chitiethoadonbh> getcateById(
            @PathVariable("id") int id) {
        Optional<chitiethoadonbh> chitiet = detailBHService.findByID(id);

        if (!chitiet.isPresent()) {
            return new ResponseEntity<>(chitiet.get(),
                    HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(chitiet.get(), HttpStatus.OK);
    }

	/* ---------------- CREATE NEW CATE ------------------------ */
	@PostMapping("/detailBH")
	public ResponseEntity<chitiethoadonbh> saveCate(@Valid @RequestBody chitiethoadonbh chitiet) {
		if(detailBHService.create(chitiet)) return new ResponseEntity<chitiethoadonbh>(chitiet,HttpStatus.OK);
		else {
			return new ResponseEntity<chitiethoadonbh>(chitiet,HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/detailsBH")
	public ResponseEntity<Boolean> saveDetailsBill(@Valid @RequestBody chitiethoadonbh[] chitiets) {
		if (checkSoLuong(chitiets)) {
			for( @Valid chitiethoadonbh chitiet : chitiets) {
					Optional<sanpham> sp= prdService.findByID(chitiet.getSanpham().getId());
					if(!detailBHService.create(chitiet)) return new ResponseEntity<>(false,HttpStatus.BAD_GATEWAY);
					int soluong = sp.get().getSoluong() - chitiet.getSoluong();
					sp.get().setSoluong(soluong);
					prdService.update(sp.get());
			}
			return new ResponseEntity<>(true,HttpStatus.OK);
		}
		return new ResponseEntity<>(true,HttpStatus.BAD_REQUEST);
		
	}
	
	private boolean checkSoLuong(chitiethoadonbh[] chitiets) {
		for(chitiethoadonbh chitiet : chitiets) {
			Optional<sanpham> sp= prdService.findByID(chitiet.getSanpham().getId());
			if (sp.get().getSoluong() < chitiet.getSoluong()) return false;
		}
		return true;
	}
}
