package com.example.demoSpBoot.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

import com.example.demoSpBoot.model.chitiethoadonbh;
import com.example.demoSpBoot.model.hoadonbanhang;
import com.example.demoSpBoot.model.sanpham;
import com.example.demoSpBoot.service.HoadonBHService;
import com.example.demoSpBoot.service.ProductService;

@RestController
@RequestMapping("/ShopStore")
@CrossOrigin(origins = "*")
public class HoadonBHController {
	@Autowired
	HoadonBHService hoadonService;
	@Autowired
	ProductService prdService;
	@GetMapping("/billBHs")
	/* ---------------- GET ALL BILL ------------------------ */
	public ResponseEntity<Page<hoadonbanhang>> findAllBills(@RequestParam int pageNumber, @RequestParam int pageSize) {
		Page<hoadonbanhang> listBill= hoadonService.findAll(pageNumber,pageSize);
		if(listBill.isEmpty()) {
			return new ResponseEntity<Page<hoadonbanhang>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Page<hoadonbanhang>>(listBill, HttpStatus.OK);
	}
	
	@GetMapping("/billBHs/search")
	/* ---------------- GET ALL BILL ------------------------ */
	public ResponseEntity<Page<hoadonbanhang>> findBills(@RequestParam int pageNumber, @RequestParam int pageSize, @RequestParam String searchTerm, @RequestParam String fromdate,@RequestParam String todate) throws ParseException {
		Page<hoadonbanhang> listBill = null;
		if(fromdate == "" &&todate=="") {
			listBill= hoadonService.searchBillNoDate(pageNumber,pageSize,searchTerm);
		}else {
			Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(fromdate);  
			Date date2=new SimpleDateFormat("dd/MM/yyyy").parse(todate);
			listBill= hoadonService.searchBill(pageNumber,pageSize,searchTerm,date1,date2);
		}
		
		if(listBill.isEmpty()) {
			return new ResponseEntity<Page<hoadonbanhang>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Page<hoadonbanhang>>(listBill, HttpStatus.OK);
	}
	
	

	/* ---------------- GET BILL BY ID ------------------------ */
	@GetMapping("/billBHs/{id}")
	
	public ResponseEntity<hoadonbanhang> getBillById(
            @PathVariable("id") int id) {
        Optional<hoadonbanhang> bill = hoadonService.findByID(id);

        if (!bill.isPresent()) {
            return new ResponseEntity<>(bill.get(),
                    HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(bill.get(), HttpStatus.OK);
    }

	/* ---------------- CREATE NEW BILL ------------------------ */
	@PostMapping("/billBHs")
	public ResponseEntity<hoadonbanhang> saveBill(@Valid @RequestBody hoadonbanhang bill) {
		if(hoadonService.create(bill)) return new ResponseEntity<hoadonbanhang>(bill,HttpStatus.OK);
		else {
			return new ResponseEntity<hoadonbanhang>(bill,HttpStatus.NOT_FOUND);
		}
		
	}
	
	/* ---------------- UPDATE BILL ------------------------ */
	@PutMapping("/billBHs")
	public ResponseEntity<hoadonbanhang> updateBill(@RequestBody hoadonbanhang bill) {
		if(checkSoLuong(bill.getChitiethoadons())) {
			if(hoadonService.update(bill)) return new ResponseEntity<hoadonbanhang>(HttpStatus.OK);
			else {
				return new ResponseEntity<hoadonbanhang>(HttpStatus.BAD_GATEWAY);
			}
		}
		return new ResponseEntity<hoadonbanhang>(HttpStatus.BAD_REQUEST);
		
	}
	
	@PutMapping("/billBHs/cancle")
	public ResponseEntity<hoadonbanhang> cancleBill(@RequestBody hoadonbanhang bill) {
		if(hoadonService.cancleBill(bill)) return new ResponseEntity<hoadonbanhang>(HttpStatus.OK);
		else {
			return new ResponseEntity<hoadonbanhang>(HttpStatus.BAD_GATEWAY);
		}
	}
	
	private boolean checkSoLuong(List<chitiethoadonbh> chitiets) {
		for(chitiethoadonbh chitiet : chitiets) {
			Optional<sanpham> sp= prdService.findByID(chitiet.getSanpham().getId());
			if (sp.get().getSoluong() < chitiet.getSoluong()) return false;
		}
		return true;
	}
	
	/* ---------------- DELETE CATE ------------------------ */
	
	@DeleteMapping("/billBHs/{id}")
	public ResponseEntity<hoadonbanhang> deleteCate(@PathVariable("id") int id) {
		if(hoadonService.delete(id)) return new ResponseEntity<hoadonbanhang>(HttpStatus.OK);
		else {
			return new ResponseEntity<hoadonbanhang>(HttpStatus.NOT_FOUND);
		}
	}
}
