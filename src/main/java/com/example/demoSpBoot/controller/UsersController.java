package com.example.demoSpBoot.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
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
import com.example.demoSpBoot.service.UsersService;
import com.example.demoSpBoot.jwt.JwtTokenProvider;
import com.example.demoSpBoot.jwt.LoginRespone;
import com.example.demoSpBoot.model.users;
import com.example.demoSpBoot.jwt.CustomUserDetails;

import org.apache.commons.lang3.RandomStringUtils;

import com.example.demoSpBoot.model.LoginForm;
import com.example.demoSpBoot.model.khachhang;
import com.example.demoSpBoot.model.users;


@RestController
//http://localhost:4200
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/ShopStore")
public class UsersController {
	@Autowired
	UsersService usersService;
	@Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;
	/* ---------------- GET ALL CUSTOMER ------------------------ */
	@GetMapping("/users")
	public ResponseEntity<List<users>> findAllUsers() {
		//return new ResponseEntity<ServiceResult>(customerService.findAll(), HttpStatus.OK);
		
		List<users> listUser= usersService.findAll();
		if(listUser.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<users>>(listUser, HttpStatus.OK);
	}
	/* ---------------- GET CUSTOMER BY ID ------------------------ */
	@GetMapping("/users/{manhanvien}")
	public ResponseEntity<users> getProductById(
            @PathVariable("manhanvien") String manhanvien) {
        Optional<users> product = usersService.findByMNV(manhanvien);

        if (!product.isPresent()) {
            return new ResponseEntity<>(product.get(),
                    HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(product.get(), HttpStatus.OK);
    }

	/* ---------------- CREATE NEW CUSTOMER ------------------------ */
	@PostMapping("/users")
	public ResponseEntity<users> saveCustomer(@Valid @RequestBody users customer) {
		String salt=randomSalt();
		customer.setSalt(salt);
		customer.setPassword(BCrypt.hashpw(customer.getPassword().concat(salt), BCrypt.gensalt(12)));
		if(usersService.create(customer)) return new ResponseEntity<users>(customer,HttpStatus.OK);
		else {
			return new ResponseEntity<users>(customer,HttpStatus.NOT_FOUND);
		}
		
	}
	
	/* ---------------- UPDATE CUSTOMER ------------------------ */
	@PutMapping("/users")
	public ResponseEntity<Boolean> update(@RequestBody users user) {
		return new ResponseEntity<Boolean>(usersService.update(user), HttpStatus.OK);
	}
	public void updateCustomer(@RequestBody users customer) {
		usersService.update(customer);
	}
	/* ---------------- DELETE CUSTOMER ------------------------ */
	
	@DeleteMapping("/users/{manhanvien}")
	public void deleteCustomer(@PathVariable("manhanvien") String manhanvien) {
		usersService.delete(manhanvien);
	}
	/* ---------------- USER LOGIN ------------------------ */
	
	@PostMapping("/login")
	public LoginRespone authenticateUser (@Valid @RequestBody LoginForm loginform) throws Exception{
		    Optional<users> usertemp=usersService.findByMNV(loginform.getManhanvien());
		    //System.out.println(usersService.passwordEncoder(pass+usertemp.get().getSalt()));
			Authentication authentication = authenticationManager.authenticate(
	                new UsernamePasswordAuthenticationToken(
	                		loginform.getManhanvien(),
	                		loginform.getPassword()+usertemp.get().getSalt()
	                )
	        );
			SecurityContextHolder.getContext().setAuthentication(authentication);
	        
	        // Trả về jwt cho người dùng.
			CustomUserDetails user =(CustomUserDetails) authentication.getPrincipal();
	        String jwt = tokenProvider.generateToken(user);
	        return new LoginRespone(jwt,user,usertemp.get());
		
	}
    
	@PostMapping("/logout")
	public ResponseEntity<Object> logout(HttpServletRequest request, HttpServletResponse response)
	{
		try {
			SecurityContextHolder.getContext().getAuthentication().setAuthenticated(false); 
			return new ResponseEntity<>(
	              HttpStatus.OK);
		}
		catch (Exception e) {
			return new ResponseEntity<>(
		              HttpStatus.NO_CONTENT);
		}
		
		
	}

        // Nếu không xảy ra exception tức là thông tin hợp lệ
        // Set thông tin authentication vào Security Context
        
//		Optional<users> user=usersService.findByMNV(manhanvien);
//		if(user.isPresent()) {
//			String password=user.get().getPassword();
//			boolean valuate = BCrypt.checkpw(pass,password);
//			if(valuate==true) {
//				return new ResponseEntity<>(user.get(), HttpStatus.OK);
//			}
//			else return new ResponseEntity<>(
//                    HttpStatus.UNAUTHORIZED);
//		}
//		else return new ResponseEntity<>(
//                HttpStatus.UNAUTHORIZED);
  
        
	public ResponseEntity<users>login (@RequestParam String manhanvien, @RequestParam(name="password") String pass){
		Optional<users> user=usersService.findByMNV(manhanvien);
		if(user.isPresent()) {
			String salt=user.get().getSalt();
			String password=user.get().getPassword();
			boolean valuate = BCrypt.checkpw(pass,password);
			if(valuate==true) {
				return new ResponseEntity<>(user.get(), HttpStatus.OK);
			}
			else return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		else return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	}
	
	/* ---------------- RANDOM STRING ------------------------ */
	public String randomSalt() {
		String salt=RandomStringUtils.randomAlphabetic(6);
		return salt;
	}
}
