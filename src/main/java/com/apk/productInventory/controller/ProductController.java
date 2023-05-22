package com.apk.productInventory.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.apk.productInventory.entity.Product;
import com.apk.productInventory.model.AuthenticationRequest;
import com.apk.productInventory.model.AuthenticationResponse;
import com.apk.productInventory.service.ProductService;
import com.apk.productInventory.utility.JwtUtil;

@RestController
public class ProductController {

	// Following implementation : REST endpoints fetching data from H2 database

	
	@Autowired
	private ProductService productService;

	@RequestMapping("/products")
	public List<Product> getAllProducts() {
		return productService.getAllProducts();
	}

	@RequestMapping(method = RequestMethod.POST, value = "/products")
	public void addProduct(@RequestBody Product product) {
		productService.addProduct(product);
	}

	@RequestMapping("/products/{id}")
	public Optional<Product> getProduct(@PathVariable String id) {
		return productService.getProduct(id);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/products/{id}")
	public void updateProduct(@RequestBody Product product, @PathVariable String id) {
		productService.updateProduct(id, product);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{id}")
	public String deleteProduct(@PathVariable String id) {
		productService.deleteProduct(id);

		return "Deleted";
	}
	 

	// Following implementation : Spring Security with custom configs (role based
	// access)

	
	/*
	 * @RequestMapping("/") public String home() { return
	 * ("<h1>This is a sample home page!"); }
	 * 
	 * @RequestMapping("/user") public String user() { return
	 * ("<h1>This is a sample user page!"); }
	 * 
	 * @RequestMapping("/admin") public String admin() { return
	 * ("<h1>This is a sample admin page!</h1>"); }
	 */
	 
	
	// Following implementation : Authorization through JWT token

	@RequestMapping("/")
	public String home() {
		return ("<h1>This is a sample home page!");
	}

	@RequestMapping("/hello")
	public String hello() {
		return ("<h1>This is a sample jwt hello page!");
	}

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private JwtUtil jwtTokenUtil;

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticateRequest)
			throws Exception {

		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticateRequest.getUsername(), authenticateRequest.getPassword()));

		} catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password", e);
		}

		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticateRequest.getUsername());

		final String jwt = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new AuthenticationResponse(jwt));

	}

}
