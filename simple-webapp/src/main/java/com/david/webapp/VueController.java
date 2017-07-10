package com.david.webapp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("vue")
public class VueController {

	@RequestMapping(value = "/vue_home", method = { RequestMethod.POST, RequestMethod.GET })
	public String vueHome() {
		return "vue";
	}
}
