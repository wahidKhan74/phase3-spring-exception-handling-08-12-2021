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
import com.dell.webservice.exception.InvalidProductException;
import com.dell.webservice.exception.ProductAlreadyExistException;
import com.dell.webservice.exception.ProductNotFoundException;

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
		throw new ProductNotFoundException("Product does not exist with id "+id);
	}

	// get one product by name
	@GetMapping("/product")
	public Product getOneProduct(@RequestParam("name") String name) {
		for (Product product : products) {
			if (product.getName().equals(name)) {
				return product;
			}
		}
		throw new ProductNotFoundException("Product does not exist with name ' "+name+"'");
	}

	// get all product
	@GetMapping("/products")
	public List<Product> getAllProducts() {
		if(products.isEmpty()) {
			throw new ProductNotFoundException("Product list is  empty !");
		}
		return products;
	}

	// create a product
	@PostMapping("/products")
	public List<Product> addProduct(@RequestBody(required = false) Product productObj) {
		if(productObj==null) {
			throw new InvalidProductException("Product body cannot be empty !");
		}
		//validation for product already exist.
		for (Product product : products) {
			if (product.getId() == productObj.getId()) {
				throw new ProductAlreadyExistException(" Product already exist with id "+productObj.getId());
			}
		}
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
		throw new ProductNotFoundException("Product does not exist with id "+id);
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
		throw new ProductNotFoundException("Product does not exist with id "+id);
	}
	
}
