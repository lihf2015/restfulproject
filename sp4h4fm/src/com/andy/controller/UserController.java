package com.andy.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.andy.entity.User;
import com.andy.model.UserModel;
import com.andy.service.UserService;
import com.andy.util.Constants;
import com.andy.util.Util;
import com.andy.util.page.PageResultSet;

@Controller
@RequestMapping("/user")
public class UserController {

	@Resource
	private UserService userService;
	
	@RequestMapping("/userLogin")
	public String userLogin(UserModel userModel, Model model){
		userModel.setPassword(Util.encryptMD5(userModel.getPassword()));
		User user = userService.findUserByByCondition(userModel);
		if (user == null) {
			model.addAttribute("errorInfo", "you password is error!");
			return "login";
		}
		
		Constants.LOGIN_USER = user.getUsername();
		return "redirect:/user/index";
	}
	@RequestMapping("/addUser")
	public String addUser(UserModel userModel) {
		Date date = new Date();
		User user = new User();
		BeanUtils.copyProperties(userModel, user); //实体属性复制，将userModel中的属性值复制到User中
		user.setPassword(Util.encryptMD5(userModel.getPassword()));
		user.setCreateTime(date);
		user.setUpdateTime(date);
		user.setUpdateUser(Constants.LOGIN_USER);
		user.setCreateUser(Constants.LOGIN_USER);
		String vName ="";
		try {
			 vName = new String(user.getUsername().getBytes("iso-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		user.setUsername(vName);
		userService.saveUser(user);
		return "redirect:/user/index";
	}
	@RequestMapping("/index")
	public ModelAndView index(UserModel userModel, HttpServletRequest request) {  
		if(Util.isNull(Constants.LOGIN_USER)){
			ModelAndView mav = new ModelAndView("login");
			mav.addObject("errorInfo", "user is overdue!");
			return mav;
		}
	    return new ModelAndView("userList");  
	 }  
	
	@RequestMapping("/getUser")
	public void getUser(HttpServletRequest request, HttpServletResponse response, Model model) {  
	    int id = Integer.parseInt(request.getParameter("userId"));
	    User user = userService.getUserById(id);
	    Map<String, Object> map = new HashMap<String, Object>();
	    map.put("user", user);
	    model.addAttribute("user", user);
	 }  
	
	@RequestMapping("/updateUser")
	public String updateUser(UserModel userModel){
		Date date = new Date();
		User user = userService.getUserById(userModel.getId());
		if (Util.isNull(userModel.getPassword())) {
			userModel.setPassword(user.getPassword());
		}else {
			userModel.setPassword(Util.encryptMD5(userModel.getPassword()));
		}
		userModel.setCreateTime(user.getCreateTime());
		userModel.setUpdateTime(date);
		userModel.setCreateUser(Constants.LOGIN_USER);
		userModel.setUpdateUser(Constants.LOGIN_USER);
		BeanUtils.copyProperties(userModel, user);
		userService.updateUser(user);
		return "redirect:/user/index";
	}
	
	@RequestMapping("/deleteUser")
	public String deleteUser(HttpServletRequest request){
		  int id = Integer.parseInt(request.getParameter("userId"));
		  User user = userService.getUserById(id);
		  userService.deleteUser(user);
		  return "redirect:/user/index";
	}
	
	@RequestMapping("/exit")
	public String exit(){
		Constants.LOGIN_USER = null;
		return "redirect:/";
	}
	//返回json
	@RequestMapping("/getJsonUserList")
	@ResponseBody
	public ModelMap getJsonUserList(UserModel userModel, HttpServletRequest request, HttpServletResponse response) {
		ModelMap modelMap = new ModelMap();
		response.setHeader("Access-Control-Allow-Origin", "*");
		String sPage = request.getParameter("page");
		int page = 1;
		if (!Util.isNull(sPage)) {
			page = Integer.parseInt(sPage);
		}
		PageResultSet<User> userPageResult = userService.findPageUserList(userModel, page, Constants.PAGE_SIZE);
		
		modelMap.addAttribute("event",userPageResult.getList());
		modelMap.addAttribute("pageBean", userPageResult.getPage());
		modelMap.addAttribute("pageCount",userPageResult.getPage().getTotalPage());
		return modelMap;
	}
	//返回json
	@RequestMapping("/getJson")
	@ResponseBody
	public ModelMap getJson( HttpServletRequest request, HttpServletResponse response) {
		ModelMap modelMap = new ModelMap();
		response.setHeader("Access-Control-Allow-Origin", "*");
		List<UserModel> list=new ArrayList<UserModel>();
		UserModel userModel =new UserModel();
		userModel.setUsername("张三");
		userModel.setEmail("lih@126.com");
		list.add(userModel);
		UserModel userModel1 =new UserModel();
		userModel1.setUsername("张三1");
		userModel1.setEmail("lih1@126.com");
		list.add(userModel1);
		modelMap.addAttribute("event","aa");
		modelMap.addAttribute("pageBean", userModel);
		modelMap.addAttribute("pageCount",list);
		return modelMap;
	}
	   @RequestMapping(value="/check", method=RequestMethod.GET)
	    public String check (
	            Model model, HttpServletRequest request, HttpSession session) {
	        
	        System.out.println("");
	        System.out.println("");
	        System.out.println("inside check");
	        
	        System.out.println("");
	        System.out.println("--- Model data ---");
	        Map modelMap = model.asMap();
	        for (Object modelKey : modelMap.keySet()) {
	            Object modelValue = modelMap.get(modelKey);
	            System.out.println(modelKey + " -- " + modelValue);
	        }
	        
	        System.out.println("");
	        System.out.println("*** Session data ***");
	        Enumeration<String> e = session.getAttributeNames();
	        while (e.hasMoreElements()) {
	            String s = e.nextElement();
	            System.out.println(s + " == " + session.getAttribute(s));
	        }

	        return "/test1";
	    }
}
