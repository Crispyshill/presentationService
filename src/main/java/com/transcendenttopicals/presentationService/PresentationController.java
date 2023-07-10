package com.transcendenttopicals.presentationService;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;



@Controller
@RequestMapping("/")
public class PresentationController {
	
	@Autowired 
	PresentationService presentationService;

	
	private RestTemplate restTemplate = new RestTemplate();
    private String productServiceUrl = "http://localhost:8001"; // Replace with the actual URL of the ProductService

    
    @GetMapping("/")
    public String root(Model model) {
        model.addAttribute("page", "home");

    	return "home";
    }
    
    @GetMapping("/account")
    public String account(Model model) {
    	model.addAttribute("page", "account");
    	model.addAttribute("templateinclude", "account.html");
    	return "home";
    }
    
    @GetMapping("/products")
    public String getProducts(Model model) {
        // Make an HTTP GET request to the ProductService API to retrieve the filled-in Thymeleaf template
        ResponseEntity<String> response = restTemplate.getForEntity(productServiceUrl + "/products", String.class);
        String template = response.getBody();

        // Add the template content to the model
        model.addAttribute("template", template);
        model.addAttribute("page", "products");
        model.addAttribute("isAdmin", presentationService.isAdmin());

        // Return the view name for rendering
        return "home";
    }
    
    @GetMapping("/about")
    public String about(Model model) {
    	model.addAttribute("page", "about");
    	model.addAttribute("templateinclude", "about.html");
    	return "home";
    }
    
    @GetMapping("/shoppingcart")
    public String shoppingcart(Model model) {
    	model.addAttribute("page", "cart");

    	return "shoppingcart";
    }
    
    @GetMapping("/contact")
    public String contact(Model model) {
    	model.addAttribute("page", "contact");
    	model.addAttribute("templateinclude", "contactus.html");
    	return "home";
    }
    
    @GetMapping("/register")
    public String showRegistrationForm(Model model){
        // create model object to store form data
        model.addAttribute("user");
    	model.addAttribute("page", "register");

        return "register";
    }


	
	 @GetMapping("/login") 
	 public String login(Model model) {
	    	model.addAttribute("page", "login");
	    	model.addAttribute("templateinclude", "login.html");

	 return "home"; }
	 

}
