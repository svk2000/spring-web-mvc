package com.theironyard.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.theironyard.models.Person;
import com.theironyard.models.Product;
import com.theironyard.services.ProductsDao;

@Controller
@RequestMapping("/products")
public class ProductController {
	private ProductsDao dao;
	
	public ProductController(ProductsDao dao) {
		 this.dao = dao;
	}
	
	@RequestMapping("")
	public String list(Model model) {
		model.addAttribute("products", dao.getProducts());
		return "products/list";
	}
	
	@RequestMapping("/new")
	public String newish(Model model) {
		model.addAttribute("product", new Product());
		return "products/new";
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public String update(Product product) {
		dao.update(product);
		return "redirect:/products";
	}
	
	@RequestMapping(value="/{id}/edit")
	public String edit(Model model, @PathVariable Integer id) {
		Product product = dao.get(id);
		model.addAttribute("product", product);
		return "products/edit";
	}
	
	@RequestMapping(value="", method=RequestMethod.POST)
	public String create(Product product) {
		product = dao.insert(product);
		return "redirect:/products";
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public String delete(@PathVariable Integer id) {
		dao.deleteById(id);
		return "redirect:/products";
	}
	
}
















