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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.andy.entity.User;
import com.andy.model.UserModel;
import com.andy.service.UserService;
import com.andy.util.Constants;
import com.andy.util.Util;
import com.andy.util.page.PageResultSet;

@Controller
@RequestMapping("/route")
@SessionAttributes("userId")
public class RouteController {

	@Resource
	private UserService userService;
	@RequestMapping(value="/login", method=RequestMethod.GET)
    public String login (
            int id, Model model, HttpServletRequest request, HttpSession session) {
        
        model.addAttribute("userId", id);
        
        System.out.println("");
        System.out.println("");
        System.out.println("inside login");
        
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

        return "/user/restf";
        //return "test1";
    }
	@RequestMapping(value="/logina",method=RequestMethod.POST)   
    public String login(@RequestParam String name,@RequestParam String password,Model model,HttpServletRequest request) throws Exception{   
        //TUser user1 = userService.getUserByName(name);  
		User user1=null;
        if(user1 != null) {   
            model.addAttribute("message", "用户不存在");   
            return "login";   
        }else if(password == null || !password.equals(user1.getPassword()) ){    

       
     model.addAttribute("message", "密码错误");   
            return "login";   
        }else {   
            request.getSession().setAttribute("user", user1);   
            return "welcome";   
        }   
    }   
       
    @RequestMapping(value="/login1",method=RequestMethod.POST)   
    public String login1(User user,HttpServletRequest request,Model model) throws Exception{   
        User user1 = user;//userService.getUserByName(user.getName());   
        if(user1 == null) {   
            model.addAttribute("message", "用户不存在");   
            return "login";   
        }else if(user.getPassword() == null || !user.getPassword().equals(user1.getPassword()) ){   
            model.addAttribute("message", "密码错误");   
            return "login";   
        }else {   
            request.getSession().setAttribute("user", user1);   
            return "welcome";   
        }   
    }   
       
    @RequestMapping(value="/list")   
    public String list(Model model,HttpServletRequest request) throws Exception {   
//        List<TUser> userList = userService.getUserList();   
//        model.addAttribute("userList", userList);   
//        List<TDept> deptList = deptService.getDeptList();   
//        model.addAttribute("deptList", deptList);   
//        if(StringUtils.isNotBlank(request.getParameter("resMess")) && StringUtils.isNotBlank(request.getParameter("opeMess"))) {   
//            model.addAttribute("message",setOperateMessage(request.getParameter("resMess"),request.getParameter("opeMess"),"用户"));   
//        }   
        return "user/list";   
    }   
   
    private String setOperateMessage(String resMess,String opeMess,String modMess) {   
        //TODO 以后可以和写日志结合在一起   
        String ope = "";   
        String res = "";   
        if("add".equals(opeMess)) {   
            ope = "增加";   
        }else if("update".equals(opeMess)) {   
            ope = "更新";   
        }else if("del".equals(opeMess)) {   
            ope = "删除";   
        }   
           
        if("success".equals(resMess)) {   
            res = "成功";   
        }else if("false".equals(resMess)) {   
            res = "失败";   
        }   
        return ope + modMess + res;   
    }   
       
       
    @RequestMapping(value="/adda",method=RequestMethod.GET)   
    public String toAdd(Model model) throws Exception{   
//        List<TDept> deptList = deptService.getDeptList();   
//        model.addAttribute("deptList", deptList);   
        return "user/add";   
    }   
    @RequestMapping(value="/add",method=RequestMethod.POST)   
    public String doAdd(User user,Model model) throws Exception{   
        try {   
           // userService.addUser(user);   
            model.addAttribute("resMess", "success");   
        } catch (Exception e) {   
            e.printStackTrace();   
            model.addAttribute("resMess", "false");   
            throw e;   
        }   
        model.addAttribute("opeMess", "success");   
           
        //重定向，防止重复提交，当然这样不能完全解决重复提交的问题，只是简单处理一下，若要较好的防止重复提交可以结合token做，   
        //以“/”开关，相对于当前项目根路径，不以“/”开关，相对于当前路径   
        //return "redirect:/user/list";    
        return "redirect:list";    
    }   
       
       
    @RequestMapping(value="/update/{id}",method=RequestMethod.GET)   
    public String toUpdate(@PathVariable("id") int id, Model model) throws Exception{   
        model.addAttribute("user",userService.getUserById(id));   
      //  model.addAttribute("deptList", deptService.getDeptList());   
        return "user/update";   
    }   
    @RequestMapping(value="/update/{id}",method=RequestMethod.POST)   
    public String doUpdate(@PathVariable("id") int id, User user,Model model) throws Exception{   
        try {   
            userService.updateUser(user);   
            model.addAttribute("resMess", "success");    
        } catch (Exception e) {   
            e.printStackTrace();   
            model.addAttribute("resMess", "false");
            throw e;   
        }   
        model.addAttribute("opeMess","success");    
        //return "redirect:../list";    
        //重定向，防止重复提交，以“/”开关，相对于当前项目根路径，不以“/”开关，相对于当前路径   
        return "redirect:/user/list";    
    }   
       
    @RequestMapping(value="/delete/{id}")   
    public String delete(@PathVariable("id") int id,Model model)throws Exception{   
        try {   
           // userService.deleteUser(id);   
        	   model.addAttribute("resMess", "success");    
        } catch (Exception e) {   
            e.printStackTrace();   
            model.addAttribute("resMess", "false");
            throw e;   
        }   
        model.addAttribute("opeMess","success");   
        return "redirect:/user/list";//重定向   
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
	  
}
