package com.david.webapp;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.david.dto.User;
import com.david.service.UserService;
import com.david.util.constants.Constants;

@Controller
@RequestMapping("index")
public class HomeController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/epcc_notify.json", method = {RequestMethod.POST, RequestMethod.GET})
	public String epccNotify(HttpServletRequest request) throws IOException {
		System.out.println("epcc网联返回支付公司回调信息-Start ");
		System.out.println("receive [流的形式返回的]流的形式request : " + request);
		request.setAttribute("Content-Type", "application/xml;charset=utf-8");
		String result = IOUtils.toString(request.getInputStream(), Charset.forName(Constants.UTF_8));
		System.out.println("result=" + result);

		String respXmlData = result.substring(result.indexOf("<root"), result.indexOf("\r\n"));
		System.out.println("网联返回支付公司的明文respXmlData =====" + respXmlData);
		String respSignData = result.substring(result.indexOf("{S:"), result.indexOf("}")).replace("{S:", "");
		System.out.println("网联返回支付公司的密文respSignData =====" + respSignData);

		System.out.println("receive [验证签名通过网联返回给支付公司的回调信息是] : " + result);
		return result;
	}

	@ResponseBody
	@RequestMapping(value = "/helloworld.json", method = {RequestMethod.POST, RequestMethod.GET})
	public String helloWorldJson() {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("name", "JsonSuffix");
		hashMap.put("age", 30);
		return JSON.toJSONString(hashMap);
	}

	@ResponseBody
	@RequestMapping(value = "/helloworld.form", method = {RequestMethod.POST, RequestMethod.GET})
	public String helloWorldForm() {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("name", "formSuffix");
		hashMap.put("age", 88);
		return JSON.toJSONString(hashMap);
	}

	@RequestMapping(value = "/helloworld", method = {RequestMethod.POST, RequestMethod.GET})
	public String helloWorld() {
		return "helloworld";
	}

	@RequestMapping(value = "/angular_sample.html", method = {RequestMethod.POST, RequestMethod.GET})
	public String angularSample(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("angularSample");
		return "angular";
	}

	@RequestMapping(value = "/angular", method = {RequestMethod.POST, RequestMethod.GET})
	public String angular() {
		return "angular";
	}

	@RequestMapping(value = "/helloworld_dynamic", method = {RequestMethod.POST, RequestMethod.GET})
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
