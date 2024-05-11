package com;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import com.mem.model.Mem;
import com.mem.model.MemService;
import com.rental.model.RentalOrder;
import com.rental.model.RentalOrderService;

import java.util.*;

import javax.servlet.http.HttpSession;



//@PropertySource("classpath:application.properties") // 於https://start.spring.io建立Spring Boot專案時, application.properties文件預設已經放在我們的src/main/resources 目錄中，它會被自動檢測到
@Controller
public class IndexController_inSpringBoot {
	
	// @Autowired (●自動裝配)(Spring ORM 課程)
	// 目前自動裝配了EmpService --> 供第66使用
	@Autowired
	MemService memSvc;
	
	@Autowired
	RentalOrderService rentalOrderSvc;
	
	
	
	
	@GetMapping("/mem/memIndexF")
	public String memIndexF( Model model, HttpSession session) {
		Mem mem = (Mem)session.getAttribute("loginSuccess");
		model.addAttribute("mem", mem);
		return "/front_end/mem/mem_Index";
	}
    




}