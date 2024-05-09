package com.rental.controller;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mem.model.Mem;
import com.mem.model.MemService;
import com.rental.model.*;


@Controller
@RequestMapping("/rental")
public class RentalOrderController {
	
	@Autowired
	RentalOrderService rentalOrderSvc;
	
	@Autowired
	MemService memSvc;
	
	
    @GetMapping("/select")
	public String select_page(Model model) {
		return "back_end/rental/selectRentalOrder";
	}
	
	@PostMapping("getOne_For_Display")
	public String getOne_For_Display(@RequestParam("rentalId") Integer rentalId, Model model) {
		RentalOrder rentalOrder = rentalOrderSvc.getRentalOrderById(rentalId);
		model.addAttribute("rentalOrder", rentalOrder);
		return "back_end/rental/listOneRentalOrder";
	}
	
	@PostMapping("listRentalOrders_ByCompositeQuery")
	public String listAllRentalOrder(HttpServletRequest req, Model model) {
		Map<String, String[]> map = req.getParameterMap();
		List<RentalOrder> list = rentalOrderSvc.getAllRentalOrder(map);
		List<RentalOrder> list2 = rentalOrderSvc.getAllRentalOrder();
		model.addAttribute("AllRentalOrderListData", list2);
		if (list.isEmpty()) {
			model.addAttribute("errorMsgs", "查無相關資料");
			return "back_end/rental/selectRentalOrder";
		} else {
			model.addAttribute("AllRentalOrderListData", list);
			return "back_end/rental/listAllRentalOrder";
		}
	}
	
    @GetMapping("/listAllRentalOrder")
	public String listAllRentalOrder(Model model) {
		return "back_end/rental/listAllRentalOrder";
	}
    @ModelAttribute("AllRentalOrderListData")  
	protected List<RentalOrder> referenceListData(Model model) {
		
    	List<RentalOrder> list = rentalOrderSvc.getAllRentalOrder();
		return list;
	}
    
    @GetMapping("get_RentalOrder_For_Review")
    public String getRentalOrderForReview(Model model) {
    	List<RentalOrder> rentalOrderForR = new ArrayList<>();
    	rentalOrderForR.addAll(rentalOrderSvc.getRentalOrderByResult("審核中"));
    	
    	List<RentalOrder> list = rentalOrderSvc.getAllRentalOrder();
    	model.addAttribute("AllRentalOrderListData", list);
    	
    	if (rentalOrderForR.isEmpty()) {
			model.addAttribute("errorMsgs", "查無相關資料");
			return "back_end/rental/select";
		} else {
			model.addAttribute("listAllRentalOrder", rentalOrderForR);
			return "back_end/rental/listAllRentalOrder";
		}
    	
    	
    }
    
}
