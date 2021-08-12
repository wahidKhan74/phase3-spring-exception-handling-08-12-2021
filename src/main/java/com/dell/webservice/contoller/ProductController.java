package com.dell.webservice.contoller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dell.webservice.entity.Product;

@RestController
@RequestMapping("/api")
public class ProductController {

	List<Product> products = new ArrayList<Product>();

	// crud operations for product.

	// get one product
	@GetMapping("/products/{id}")
	public Product getOneProduct(@PathVariable("id") int id) {
		for (Product product : products) {
			if (product.getId() == id) {
				return product;
			}
		}
		return null;
	}

	// get one product by name
	@GetMapping("/product")
	public Product getOneProduct(@RequestParam("name") String name) {
		for (Product product : products) {
			if (product.getName().equals(name)) {
				return product;
			}
		}
		return null;
	}

	// get all product
	@GetMapping("/products")
	public List<Product> getAllProducts() {
		return products;
	}

	// create a product
	@PostMapping("/products")
	public List<Product> addProduct(@RequestBody(required = false) Product productObj) {
		products.add(productObj);
		return products;
	}

	// update product
	@PutMapping("/products/{id}")
	public Product updateOneProduct(@PathVariable("id") int id, @RequestBody(required = false) Product productObj ) {
		for (int i=0; i<products.size(); i++) {
			if (products.get(i).getId() == id) {
				products.set(i, productObj);
				return products.get(i);
			}
		}
		return null;
	}
	
	// delete product
	@DeleteMapping("/products/{id}")
	public List<Product>  updateOneProduct(@PathVariable("id") int id ) {
		for (int i=0; i<products.size(); i++) {
			if (products.get(i).getId() == id) {
				products.remove(i);
				return products;
			}
		}
		return null;
	}
	
}
