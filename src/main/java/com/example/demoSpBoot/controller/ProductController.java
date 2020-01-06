package com.example.demoSpBoot.controller;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.core.io.ClassPathResource;

import javax.validation.Valid;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demoSpBoot.dto.UploadResponse;
import com.example.demoSpBoot.model.sanpham;
import com.example.demoSpBoot.service.ProductService;

@RestController
@RequestMapping("/ShopStore")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	@GetMapping("/allproducts")
	/* ---------------- GET ALL PRODUCT ------------------------ */
	public ResponseEntity<List<sanpham>> getAllProduct() {
		
		List<sanpham> listProduct= productService.findAllProd();
		if(listProduct.isEmpty()) {
			return new ResponseEntity<List<sanpham>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<sanpham>>(listProduct, HttpStatus.OK);
	}
	
	@GetMapping("/product")
	/* ---------------- GET ALL PRODUCT ------------------------ */
	public ResponseEntity<Page<sanpham>> getAllProductPage(@RequestParam int pageNumber, @RequestParam int pageSize) {
		//return new ResponseEntity<ServiceResult>(customerService.findAll(), HttpStatus.OK);
		
		Page<sanpham> listProduct= productService.findAll(pageNumber,pageSize);
		if(listProduct.isEmpty()) {
			return new ResponseEntity<Page<sanpham>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Page<sanpham>>(listProduct, HttpStatus.OK);
	}
	/* ---------------- GET PRODUCT BY ID ------------------------ */
	@GetMapping("/product/{id}")
	
	public ResponseEntity<sanpham> getProductById(
            @PathVariable("id") int id) {
        Optional<sanpham> product = productService.findByID(id);

        if (!product.isPresent()) {
            return new ResponseEntity<>(product.get(),
                    HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(product.get(), HttpStatus.OK);
    }
	/* ---------------- GET PRODUCT BY MASP ------------------------ */
	@GetMapping("/productbymasp/{masp}")
	
	public ResponseEntity<sanpham> getProductByMasp(
            @PathVariable("masp") String masp) {
        Optional<sanpham> product = productService.findByMasp(masp);

        if (!product.isPresent()) {
            return new ResponseEntity<>(product.get(),
                    HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(product.get(), HttpStatus.OK);
    }

	/* ---------------- CREATE NEW PRODUCT ------------------------ */
	@PostMapping("/product")
	public ResponseEntity<sanpham> createProduct(@Valid @RequestBody sanpham product) {
		if(productService.create(product)) {
			product.getId();
			return new ResponseEntity<sanpham>(product,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<sanpham>(product,HttpStatus.BAD_REQUEST);
		}
		
	}
	
	/* ---------------- UPDATE PRODUCT ------------------------ */
	@PutMapping("/product")

	public ResponseEntity<sanpham> updateProduct(@RequestBody sanpham product) {
		if(productService.update(product)) return new ResponseEntity<sanpham>(product,HttpStatus.OK);
		else {
			return new ResponseEntity<sanpham>(product,HttpStatus.NOT_FOUND);
		}
	}
	/* ---------------- DELETE PRODUCT ------------------------ */
	
	@DeleteMapping("/product/{id}")
	public ResponseEntity<sanpham> deleteProduct(@PathVariable("id") int id) {
		if(productService.delete(id)) return new ResponseEntity<sanpham>(HttpStatus.OK);
		else {
			return new ResponseEntity<sanpham>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/product/search")
	/* ---------------- SEARCH ------------------------ */
	public ResponseEntity<Page<sanpham>> findProduct(@RequestParam int pageNumber, @RequestParam int pageSize, @RequestParam String searchTerm) throws ParseException {
		Page<sanpham> listProduct = null;
			listProduct= productService.searchProduct(pageNumber,pageSize,searchTerm);
		
			if(listProduct.isEmpty()) {
				return new ResponseEntity<Page<sanpham>>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<Page<sanpham>>(listProduct, HttpStatus.OK);
	}
	
	@GetMapping("/product/filter")
	/* ---------------- Filter ------------------------ */
	public ResponseEntity<Page<sanpham>> filterProduct(@RequestParam int pageNumber, @RequestParam int pageSize, @RequestParam int trangthai,@RequestParam int nhasx) throws ParseException {
		Page<sanpham> listPro = null;
		listPro= productService.filterProduct(pageNumber,pageSize,trangthai, nhasx);
		if(listPro.isEmpty()) {
			return new ResponseEntity<Page<sanpham>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Page<sanpham>>(listPro, HttpStatus.OK);
	}
	
	//upload anhsp
	@PostMapping("/upload")
	public ResponseEntity<UploadResponse> uploadProImage(@RequestParam("file") MultipartFile file) throws Exception {
		if (file == null) {
			throw new RuntimeException("You must select the a file for uploading");
		}
		MultipartFile multipartFile = file;
		String fileName = multipartFile.getOriginalFilename();
		String[] arrOfStr = fileName.split("[.]+");
		String newName = "";
		for (int i = 0; i < arrOfStr.length-1; i++) {
			newName +=arrOfStr[i];
		}
		newName += "_" + (new Date()).getTime() + "." + arrOfStr[arrOfStr.length-1] ;
		File folder = new File(this.getFolderUpload(),newName);
		multipartFile.transferTo(folder);
		UploadResponse uploadResponse=new UploadResponse(newName, "http://localhost:8090/ShopStore/images?fileName="+ newName);
		// Do processing with uploaded file data in Service layer
		return new ResponseEntity<UploadResponse>(uploadResponse, HttpStatus.OK);
	}
	public File getFolderUpload() throws IOException {
		ClassPathResource imgFile = new ClassPathResource("uploads");
	    File folderUpload = imgFile.getFile();
	    if (!folderUpload.exists()) {
	      folderUpload.mkdirs();
	    }
	    return folderUpload;
	  }
	
	
	@RequestMapping(value = "/images", method = RequestMethod.GET,
            produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getProImage(@RequestParam("fileName") String fileName) throws IOException {

    	ClassPathResource imgFile = new ClassPathResource("uploads/" + fileName);
    	File file = imgFile.getFile();
    	String contentType = Files.probeContentType(file.toPath());
        byte[] bytes = StreamUtils.copyToByteArray(((ClassPathResource) imgFile).getInputStream());
        MediaType mt;
        switch (contentType) {
	        case "image/png":
	        	mt = MediaType.IMAGE_PNG;
	        	break;
	        case "image/jpg":
	        	mt = MediaType.IMAGE_JPEG;
	        	break;
	        case "image/jpeg":
	        	mt = MediaType.IMAGE_JPEG;
	        	break;
	        case "image/gif":
	        	mt = MediaType.IMAGE_GIF;
	        	break;
	        default:
	        	mt = MediaType.IMAGE_JPEG;
	        	break;
        }
        return ResponseEntity
                .ok()
                .contentType(mt)
                .body(bytes);
    }

}
