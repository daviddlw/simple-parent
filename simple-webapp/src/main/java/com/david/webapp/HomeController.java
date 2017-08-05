package com.david.webapp;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.david.dto.User;
import com.david.service.UserService;

@Controller
@RequestMapping("index")
public class HomeController {

	@Autowired
	private UserService userService;

	@ResponseBody
	@RequestMapping(value = "/helloworld.json", method = { RequestMethod.POST, RequestMethod.GET })
	public String helloWorldJson() {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("name", "JsonSuffix");
		hashMap.put("age", 30);
		return JSON.toJSONString(hashMap);
	}

	@ResponseBody
	@RequestMapping(value = "/helloworld.form", method = { RequestMethod.POST, RequestMethod.GET })
	public String helloWorldForm() {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("name", "formSuffix");
		hashMap.put("age", 88);
		return JSON.toJSONString(hashMap);
	}

	@RequestMapping(value = "/helloworld", method = { RequestMethod.POST, RequestMethod.GET })
	public String helloWorld() {
		return "helloworld";
	}

	@RequestMapping(value = "/angular_sample.html", method = { RequestMethod.POST, RequestMethod.GET })
	public String angularSample(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("angularSample");
		return "angular";
	}

	@RequestMapping(value = "/angular", method = { RequestMethod.POST, RequestMethod.GET })
	public String angular() {
		return "angular";
	}

	@RequestMapping(value = "/helloworld_dynamic", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView helloWorldModelAndView() {
		String list = userService.userList();
		ModelAndView mav = new ModelAndView("helloworld_dynamic");
		User user = new User();
		user.setId(1);
		user.setUserName(list);
		mav.addObject("user", user);
		return mav;
	}
}
