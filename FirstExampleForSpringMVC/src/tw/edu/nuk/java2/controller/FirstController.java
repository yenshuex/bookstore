package tw.edu.nuk.java2.controller;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;
import tw.edu.nuk.java2.model.Product;

@Controller
public class FirstController {
	
	@RequestMapping("/hello/{member}")
	public String doHello(@PathVariable("member") String MemberName, Model model) {
		
		model.addAttribute("member", MemberName);
		
		return "HelloView";
	}
	
	@RequestMapping(value="/hello", method=RequestMethod.POST)
	public String showSuccess(@ModelAttribute("prod") Product product, Model model) {
		product.setDescription("New description");
		
		return "ShowProductResult";
	}
	
	
	@RequestMapping(value="/welcome")
	public String doWelcome(@RequestParam(value="member", defaultValue="User") String MemberName, Model model) {
		
		model.addAttribute("member", MemberName);
		
		return "WelcomeView";
	}
	
	@RequestMapping("/news")
	public String doRedirect(@RequestParam(value="url", defaultValue="http://www.yahoo.com.tw") String url) {
		
		return "redirect:"+url;
	}
	
	@RequestMapping(value={"/Hi/{member}","/Aloha/{member}"})
	public String doMultiURL(@PathVariable("member") String MemberName, Model model, HttpServletRequest request) {
		
		model.addAttribute("member", MemberName);
		model.addAttribute("uri", request.getRequestURI());
		model.addAttribute("url", request.getRequestURL());
		
		return "MultiValuesView";
	}
	
	@RequestMapping("/productlist")
	public String showProductList(Model model) {
		
		List<Product> products = new ArrayList<Product>();
		
		Product prod1 = new Product();
		prod1.setpNo(1);
		prod1.setName("Head First Servlet");
		prod1.setDescription("The introduction to servlet");
		prod1.setCategory("Book");
		products.add(prod1);
		
		Product prod2 = new Product();
		prod2.setpNo(2);
		prod2.setName("看見台灣");
		prod2.setDescription("空中鳥瞰台灣");
		prod2.setCategory("DVD");
		products.add(prod2);
		
		Product prod3 = new Product();
		prod3.setpNo(3);
		prod3.setName("杜蘭多公主");
		prod3.setDescription("普契尼歌劇");
		prod3.setCategory("CD");
		products.add(prod3);
		
		model.addAttribute("products",products);
		
		
		return "ProductList";
	}
	
	@RequestMapping("/product")
	public String showProduct(Model model) {
		
		Product product = new Product();
		
		product.setpNo(1);
		product.setName("Head First Servlet");
		product.setDescription("The introduction to servlet");
		model.addAttribute("prod", product);
		
		return "ProductView";
	}
	
	@RequestMapping("/resource")
	public void showResourceView() {
		
	}
	
	@RequestMapping("/WelcomeRedirect") 
	public String doWelcomeRedirect() {
		
		return "welcomeRedirect";	
	}
	
	@ModelAttribute("categoryList")
	public List<String> populateCatetory() {
		List<String> categories = new ArrayList<String>();
		
		categories.add("Book");
		categories.add("CD");
		categories.add("DVD");
	
		return categories;
	}
	
}
