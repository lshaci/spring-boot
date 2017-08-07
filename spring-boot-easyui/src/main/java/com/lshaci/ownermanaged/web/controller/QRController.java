package com.lshaci.ownermanaged.web.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.github.binarywang.utils.qrcode.QrcodeUtils;
import com.lshaci.ownermanaged.utils.ContextUtils;

@Controller
@RequestMapping("/qr/")
public class QRController {
	
	@RequestMapping("index")
	public void index() {
		
	}
	
	@RequestMapping("{content}")
	@ResponseBody
	public void createQR(@PathVariable("content") String content) throws IOException {
		System.err.println(content);
		File log = new File("C:\\Users\\Administrator\\Pictures\\123.jpg");
		byte[] qrcode = QrcodeUtils.createQrcode(content, log);
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest(); 
		HttpServletRequest request2 = ContextUtils.getRequest();
		System.err.println(request == request2);
		ContextUtils.responseWrite(qrcode);
	}

}
