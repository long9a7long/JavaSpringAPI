package com.example.demoSpBoot.controller;


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

import com.example.demoSpBoot.dto.KhachHangDTO;
import com.example.demoSpBoot.model.hoadonbanhang;
import com.example.demoSpBoot.model.khachhang;
import com.example.demoSpBoot.service.CustomerService;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/ShopStore")
public class CustomerController {
	@Autowired
	CustomerService customerService;
	/* ---------------- GET ALL CUSTOMER ------------------------ */
	@GetMapping("/customers")
	public ResponseEntity<Page<KhachHangDTO>> findAllCustomers(@RequestParam int pageNumber, @RequestParam int pageSize) {	
		Page<KhachHangDTO> listCustomers = customerService.findListAll(pageNumber, pageSize);
		if(listCustomers.isEmpty()) {
			return new ResponseEntity<Page<KhachHangDTO>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Page<KhachHangDTO>>(listCustomers, HttpStatus.OK);
	}
	
	@GetMapping("/allcustomers")
	public ResponseEntity<List<khachhang>> getAllCustomers() {	
		List<khachhang> listCustomers = customerService.getListAllNonPage();
		if(listCustomers.isEmpty()) {
			return new ResponseEntity<List<khachhang>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<khachhang>>(listCustomers, HttpStatus.OK);
	}
	
	/* ---------------- GET CUSTOMER BY NAME ------------------------ */
	@GetMapping("/customers/search")
	
	public ResponseEntity<Page<KhachHangDTO>> findCustomersList(@RequestParam int pageNumber, @RequestParam int pageSize,@RequestParam String searchTerm) {
        Page<KhachHangDTO> list = customerService.findByName(pageNumber, pageSize,searchTerm);
        if (list.isEmpty()) {
            return new ResponseEntity<Page<KhachHangDTO>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Page<KhachHangDTO>>(list,HttpStatus.OK);
    }
	
	
	/* ---------------- GET CUSTOMER BY MAKHCHHANG ------------------------ */
	
	@GetMapping("/customers/{makhachhang}")
	
	public ResponseEntity<Optional<khachhang>> findCustomer(@PathVariable("makhachhang") String makhachhang) {
        Optional<khachhang> kh = customerService.findBymakhachhang(makhachhang);
        if (!kh.isPresent()) {
            return new ResponseEntity<Optional<khachhang>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Optional<khachhang>>(kh,HttpStatus.OK);
    }
	
	/* ---------------- GET BILL CUSTOMER BY MAKHCHHANG ------------------------ */
	
	@GetMapping("/customers/bill/{makhachhang}")
	
	public ResponseEntity<Page<hoadonbanhang>> findBillByCustomer(@PathVariable("makhachhang") String makhachhang,@RequestParam int pageNumber, @RequestParam int pageSize) {
		Page<hoadonbanhang> kh = customerService.findBillByCustomer2(makhachhang,pageNumber, pageSize);
        if (kh.isEmpty()) {
        	
            return new ResponseEntity<Page<hoadonbanhang>>(HttpStatus.NO_CONTENT);
        }
        
        return new ResponseEntity<Page<hoadonbanhang>>(kh,HttpStatus.OK);
    }
	
/* ---------------- GET BILL CUSTOMER BY MAKHCHHANG ------------------------ */
	
	@GetMapping("/customers/bill/debt/{makhachhang}")
	
	public ResponseEntity<Page<hoadonbanhang>> findBillCustomerDebt(@PathVariable("makhachhang") String makhachhang,@RequestParam int pageNumber, @RequestParam int pageSize) {
		Page<hoadonbanhang> kh = customerService.findBillCusDebt(makhachhang,pageNumber, pageSize);
        if (kh.isEmpty()) {
        	
            return new ResponseEntity<Page<hoadonbanhang>>(HttpStatus.NO_CONTENT);
        }
        
        return new ResponseEntity<Page<hoadonbanhang>>(kh,HttpStatus.OK);
    }
	
	@GetMapping("/customers/totaldebt/{makhachhang}")
	public ResponseEntity<KhachHangDTO> getCustomerDebt(@PathVariable("makhachhang") String makhachhang) {
		Optional<KhachHangDTO> kh = customerService.getCustomerDebt(makhachhang);
        if (!kh.isPresent()) {
            return new ResponseEntity<KhachHangDTO>(HttpStatus.NO_CONTENT);
        }
        
        return new ResponseEntity<KhachHangDTO>(kh.get(),HttpStatus.OK);
    }
	
	/* ---------------- CREATE NEW CUSTOMER ------------------------ */
	@PostMapping("/customers")
	public ResponseEntity<khachhang> saveCustomer(@Valid @RequestBody khachhang customer){
		if(customerService.create(customer)) return new ResponseEntity<khachhang>(customer,HttpStatus.OK);
		else {
			return new ResponseEntity<khachhang>(HttpStatus.BAD_REQUEST);
		}
		
	}
		
	/* ---------------- UPDATE CUSTOMER ------------------------ */
	@PutMapping("/customers")

	public ResponseEntity<khachhang> updateCustomer(@RequestBody khachhang customer) {
		if(customerService.update(customer)) return new ResponseEntity<khachhang>(customer,HttpStatus.OK);
		else {
			return new ResponseEntity<khachhang>(customer,HttpStatus.BAD_REQUEST);
		}
	}
	/* ---------------- DELETE CUSTOMER ------------------------ */
	
	@DeleteMapping("/customers/{makhachhang}")
	public ResponseEntity<khachhang> deleteCustomer(@PathVariable("makhachhang")String makhachhang) {
		if(customerService.delete(makhachhang)) return new ResponseEntity<khachhang>(HttpStatus.OK);
		else {
			return new ResponseEntity<khachhang>(HttpStatus.NOT_FOUND);
		}
	}

	
	
}
