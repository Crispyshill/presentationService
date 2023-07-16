package controllers;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

import domain.Product;
import services.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
	@Autowired
	private ProductService productService;
	
	
	
	@PostMapping("/addDefaultProduct")
	public ResponseEntity<?> addDefaultProduct(){
		productService.addDefaultProduct();
		return new ResponseEntity<String>("Congrats!", HttpStatus.OK);
	}
	
	@PostMapping("/addProduct")
	public ResponseEntity<String> addProduct(@ModelAttribute Product product, @RequestParam("productImage") MultipartFile productImage) {
	    // Process the uploaded image if needed
	    if (!productImage.isEmpty()) {
	        // Handle the uploaded image, e.g., save it to a specific location or process it further
	        // You can access the image using the `productImage` MultipartFile object
	    }

	    productService.addProduct(product);
	    return new ResponseEntity<>("Product add attempted!", HttpStatus.OK);
	}

	
	@PutMapping("/updateProduct")
	public ResponseEntity<?> updateProduct(@RequestBody Product product){
		productService.updateProduct( product);
		return new ResponseEntity<String>("Product update attempt", HttpStatus.OK);
	}
	
	@GetMapping("/getProduct/{productId}")
	public ResponseEntity<?> getProduct(@PathVariable String productId){
		Product product = productService.getProduct(productId);
		return new ResponseEntity<Product>(product, HttpStatus.OK);
	}
	
	@GetMapping("/getProducts")
	public ResponseEntity<List<Product>> getProducts(){
		List<Product> products = productService.getProducts();
		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}
	
	@DeleteMapping("/removeProduct/{productId}")
	public ResponseEntity<?> removeProduct(@PathVariable String productId){
		productService.removeProduct(productId);
		return new ResponseEntity<String>("Product removed", HttpStatus.OK);
	}
	

	


	
	
}
