package com.mem.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mem.model.Mem;
import com.mem.model.MemService;

@Controller
@RequestMapping("/mem")
public class MemContorller {

	@Autowired
	MemService memSvc;

	//
	@GetMapping("addMem")
	public String addMem(ModelMap model) {
		Mem mem = new Mem();
		model.addAttribute("mem", mem);
		return "back_end/mem/addMem";
	}

	@PostMapping("insert")
	public String insert(@Valid Mem mem, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("mem", mem);
			model.addAttribute("errorMsgs", result.getAllErrors());
			return "back_end/mem/addMem";
		}
		Mem existingMemE = memSvc.getMemByEmail(mem.getMemEmail());
		Mem existingMemA = memSvc.getMemByAccount(mem.getMemAcount());

		if (existingMemE != null) {
			model.addAttribute("errorMsg", "已存在之會員信箱");
			return "back_end/mem/addMem";
		} else if (existingMemA != null) {
			model.addAttribute("errorMsg", "已存在之會員帳號");
			return "back_end/mem/addMem";
		}
		memSvc.addMem(mem);
		model.addAttribute("mem", mem);
		model.addAttribute("successMsgs", "- (新增成功)");
		return "back_end/mem/listOneMem";
	}

	//
	@PostMapping("getOne_For_Update")
	public String getOne_For_Update(@RequestParam("memId") Integer memId, Model model) {
		Mem mem = memSvc.getMemById(memId);
		model.addAttribute("mem", mem);
		return "back_end/mem/updateMem";
	}

	@PostMapping("update")
	public String update(@Valid Mem mem, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("errorMsgs", result.getAllErrors());
			return "back_end/mem/updateMem";
		}

		Mem existingMem = memSvc.getMemByEmail(mem.getMemEmail());
		if (existingMem != null) {
			model.addAttribute("errorMsg", "已存在之會員信箱");
			return "back_end/mem/updateMem";
		}

		memSvc.updateMem(mem);

		model.addAttribute("successMsgs", "- (修改成功)");
		model.addAttribute("mem", mem);
		return "back_end/mem/listOneMem";
	}

	@PostMapping("listMems_ByCompositeQuery")
	public String listAllMem(HttpServletRequest req, Model model) {
		Map<String, String[]> map = req.getParameterMap();
		List<Mem> list = memSvc.getAllMem(map);
		List<Mem> list2 = memSvc.getAllMem();
		model.addAttribute("AllMemListData", list2);
		if (list.isEmpty()) {
			model.addAttribute("errorMsgs", "查無相關資料");
			return "back_end/mem/select";
		} else {
			model.addAttribute("AllMemListData", list);
			return "back_end/mem/listAllMem";
		}
	}

	@PostMapping("getOne_For_Display")
	public String getOne_For_Display(@RequestParam("memId") Integer memId, Model model) {
		Mem mem = memSvc.getMemById(memId);
		model.addAttribute("mem", mem);
		return "back_end/mem/listOneMem";
	}
	
	
	
//========FrontEnd===========================================================================
	
  @GetMapping("/login")
	public String login(Model model) {
		return "front_end/mem/mem_login";
	}
	
	@PostMapping("memLogin")
	public String loginMem(@RequestParam("memAcount") String memAcount, @RequestParam("memPassword") String memPassword,
			Model model, HttpSession session) {

		Mem mem = memSvc.getMemByAccount(memAcount);

		if (mem != null && mem.getMemPassword().equals(memPassword)) {
			if(mem.getMemStatus().equals("已驗證")) {
				session.setAttribute("loginSuccess", mem);
				return "front_end/mem/mem_Index";				
			}else if(mem.getMemStatus().equals("已停權") || mem.getMemStatus().equals("已註銷")){
				model.addAttribute("errorMsgs", "無使用權限，詳情請洽客服");
				return "front_end/mem/mem_login";
			}else {
				model.addAttribute("errorMsgs", "未驗證");		//		
				return "front_end/mem/mem_login";
			}
		} else {
			model.addAttribute("errorMsgs", "會員帳號或密碼錯誤");
			return "front_end/mem/mem_login";
		}
	}
	//====================================
	  @GetMapping("/logout")
		public String logout(Model model, HttpSession session) {
			session.removeAttribute("loginSuccess");
		  	return "front_end/mem/mem_login";
		}
	
	//=========================================================================================
	@GetMapping("memUpdateF")
	public String updateMemF( Model model, HttpSession session) {
		Mem mem = (Mem)session.getAttribute("loginSuccess");
		model.addAttribute("mem", mem);
		return "front_end/mem/updateMemF";
	}

	@PostMapping("updateF")
	public String updateF(@Valid Mem mem, BindingResult result, Model model, HttpSession session) {
		if (result.hasErrors()) {
			model.addAttribute("errorMsgs", result.getAllErrors());
			return "front_end/mem/updateMemF";
		}

		Mem existingMem = memSvc.getMemByEmail(mem.getMemEmail());
		if (existingMem != null && !existingMem.getMemId().equals(mem.getMemId())) {
			model.addAttribute("errorMsg", "已存在之會員信箱");
			return "front_end/mem/updateMemF";
		}

		memSvc.updateMem(mem);

		model.addAttribute("successMsgs", "- (修改成功)");
		model.addAttribute("mem", mem);
		session.setAttribute("loginSuccess", mem);
		return "front_end/mem/updateMemF";
	}
	
//    @GetMapping("/signup")
//    public String signupMem(Model model) {
//    	Mem mem = new Mem();
//		model.addAttribute("mem", mem);
//    	return "front_end/mem/mem_signup";
//    }
//	@PostMapping("memSignUp")
//	public String signup(@Valid Mem mem, BindingResult result, Model model) {
//		
//		if (result.hasErrors()) {
//			model.addAttribute("mem", mem);
//			model.addAttribute("errorMsgs", result.getAllErrors());
//			return "front_end/mem/mem_signup";
//		}
//		Mem existingMemE = memSvc.getMemByEmail(mem.getMemEmail());
//		Mem existingMemA = memSvc.getMemByAccount(mem.getMemAcount());
//
//		if (existingMemE != null) {
//			model.addAttribute("errorMsg", "已存在之會員信箱");
//			return "front_end/mem/mem_signup";
//		} else if (existingMemA != null) {
//			model.addAttribute("errorMsg", "已存在之會員帳號");
//			return "front_end/mem/mem_signup";
//		}
//		memSvc.addMem(mem);
//		return "front_end/mem/success";
//	}
}
