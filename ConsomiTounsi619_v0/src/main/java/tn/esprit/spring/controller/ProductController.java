package tn.esprit.spring.controller;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import tn.esprit.spring.entity.Product;
import tn.esprit.spring.entity.ProductCategory;
import tn.esprit.spring.entity.ProductSearch;

import tn.esprit.spring.sevice.interfece.IProductCategoryService;
import tn.esprit.spring.sevice.interfece.IProductService;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RestController
public class ProductController {
	private static final String UPLOADED_FOLDER = System.getProperty("user.dir");
	Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	IProductService iProductService;
	@Autowired
	IProductCategoryService iProductCategoryService;
	
	
	@GetMapping("/view/Products")
	@ResponseBody

	public List<Product> getProducts() {
		return iProductService.findAll();   
	}
	
	@GetMapping("/view/Product/{barCode}")
	@ResponseBody

    public Product getProduct(@PathVariable("barCode") Long barCode){
		Product product = iProductService.getProductBybarCode(barCode);
		return product;
    }
	
	@PutMapping("/view/searchProduct")
	@ResponseBody
    public List<Product> searchProducts(@RequestBody ProductSearch productSearch){
		String productName=productSearch.getProductName();
		String categoryName= productSearch.getCategoryName();
		List<Product> products = iProductService.searchProducts(productName,categoryName);
		return products;
    }
	@PostMapping("/manage/addProduct")
	@ResponseBody
	public Product addProduct(@RequestBody Product product
			//,@RequestParam("file") MultipartFile file
			)
	{
		if (!iProductService.existsById(product.getBarCode())) {
			product.setCategory(validCategory(product.getCategory()));
			iProductService.addProduct(product);
			return product;
		}else {
			throw new IllegalArgumentException("Product already exist");
		}
	}


	
	 
	 
	
	
	@PutMapping(value = "/manage/updateProduct/{barCode}") 
	@ResponseBody
	public Product updateProduct(@PathVariable("barCode") Long barCode,@RequestBody Product product) {
		Product p= product;
		Product prod= iProductService.findById(barCode).get();
		if (p.getPrice()>0) prod.setPrice(p.getPrice());
		if (p.getName()!=null) prod.setName(p.getName());
		if (p.getCategory()!=null) prod.setCategory(validCategory(p.getCategory()));
		 prod.setExprdate(p.getExprdate());
		return iProductService.updateProduct(prod, barCode);
	}
	
	@DeleteMapping("/manage/removeProduct/{barCode}")
	@ResponseBody
    public boolean removeProductCategory(@PathVariable("barCode") Long barCode){
		return iProductService.removeProduct(barCode);
    }
	
	ProductCategory validCategory(ProductCategory category) {
		if (category==null) throw new IllegalArgumentException("Product Category can not be empty");
		if((category.getId()!=null)&&(iProductCategoryService.existsById(category.getId()))) {
			return iProductCategoryService.findById(category.getId()).get();
		}else if ((category.getName()!=null)&&(iProductCategoryService.findCategoryByName(category.getName()).size()>0)) {
			return iProductCategoryService.findCategoryByName(category.getName()).get(0);			
		}else throw new IllegalArgumentException("Invalid  Product Category, could not find reference");
	}
	
}


