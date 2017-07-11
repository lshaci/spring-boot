package com.lshaci.ownermanaged.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/owner/")
public class OwnerController {

	@RequestMapping("index")
	public void index() { }
}
