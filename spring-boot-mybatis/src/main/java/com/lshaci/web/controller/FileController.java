package com.lshaci.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/file/")
public class FileController {
	
	@RequestMapping("index")
	public void index() { }

	@ResponseBody
	@RequestMapping("save")
	public String save(MultipartFile imgFile) {
		System.out.println(imgFile);
		return "成功";
	}
}
