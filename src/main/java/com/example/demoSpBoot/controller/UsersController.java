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
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/ShopStore")
public class UsersController {
	@Autowired
	UsersService usersService;
	@Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;
	/* ---------------- GET ALL USER ------------------------ */
	@GetMapping("/users")
	public ResponseEntity<List<users>> getAllUser() {
		//return new ResponseEntity<ServiceResult>(customerService.findAll(), HttpStatus.OK);
		
		List<users> listUser= usersService.findAll();
		if(listUser.isEmpty()) {
			return new ResponseEntity<List<users>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<users>>(listUser, HttpStatus.OK);
	}
	/* ---------------- GET USER BY ID ------------------------ */
	@GetMapping("/users/{manhanvien}")
	public ResponseEntity<users> getUserByMNV(
            @PathVariable("manhanvien") String manhanvien) {
        Optional<users> user = usersService.findByMNV(manhanvien);

        if (!user.isPresent()) {
            return new ResponseEntity<>(user.get(),
                    HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(user.get(), HttpStatus.OK);
    }

	/* ---------------- CREATE NEW USER ------------------------ */
	@PostMapping("/users")
	public ResponseEntity<users> createUser(@Valid @RequestBody users user) {
		String salt=randomSalt();
		user.setSalt(salt);
		user.setPassword(BCrypt.hashpw(user.getPassword().concat(salt), BCrypt.gensalt(12)));
		if(usersService.create(user)) return new ResponseEntity<users>(user,HttpStatus.OK);
		else {
			return new ResponseEntity<users>(user,HttpStatus.NOT_FOUND);
		}
		
	}
	
	/* ---------------- UPDATE USER ------------------------ */
	@PutMapping("/users")
	public ResponseEntity<Boolean> updateUser(@RequestBody users user) {
		return new ResponseEntity<Boolean>(usersService.update(user), HttpStatus.OK);
	}
	public void updateUsers(@RequestBody users user) {
		usersService.update(user);
	}
	/* ---------------- DELETE USER ------------------------ */
	
	@DeleteMapping("/users/{manhanvien}")
	public void deleteUser(@PathVariable("manhanvien") String manhanvien) {
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
