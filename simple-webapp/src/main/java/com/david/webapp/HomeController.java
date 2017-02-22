package com.david.webapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.david.dto.User;
import com.david.facade.UserService;

@Controller
@RequestMapping("/home")
public class HomeController {

	@Autowired
	private UserService userService;

	@ResponseBody
	@RequestMapping(value = "/helloworld.json", method = { RequestMethod.POST, RequestMethod.GET })
	public String helloWorldJson() {
		String list = userService.userList();
		return JSON.toJSONString(list);
	}

	@RequestMapping(value = "/helloworld", method = { RequestMethod.POST, RequestMethod.GET })
	public String helloWorld() {
		return "helloworld";
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
