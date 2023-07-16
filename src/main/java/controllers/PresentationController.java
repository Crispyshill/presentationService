package controllers;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import domain.Product;
import services.PresentationService;
import services.ProductService;



@Controller
@RequestMapping("/")
public class PresentationController {
	
	@Autowired 
	PresentationService presentationService;
	@Autowired
	ProductService productService;
	
    
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
        
        List<Product> products = presentationService.getProducts();

        // Add the template content to the model
        model.addAttribute("page", "products");
        model.addAttribute("templateinclude", "products.html");
        model.addAttribute("products", products);
        model.addAttribute("isAdmin", false); //TODO implement

        // Return the view name for rendering
        return "home";
    }
    
    @GetMapping("/about")
    public String about(Model model) {
    	model.addAttribute("page", "about");
    	model.addAttribute("templateinclude", "about.html");
    	return "home";
    }
    
    @GetMapping("/cart")
    public String shoppingcart(Model model) {
    	 
    	model.addAttribute("page", "cart");
        //model.addAttribute("cartItems", cart);

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
