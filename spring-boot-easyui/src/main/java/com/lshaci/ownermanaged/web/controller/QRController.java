package com.lshaci.ownermanaged.web.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.binarywang.utils.qrcode.QrcodeUtils;

@Controller
@RequestMapping("/qr/")
public class QRController {
	
	@RequestMapping("index")
	public void index() {
		
	}
	
	@RequestMapping("{content}")
	public void createQR(HttpServletResponse response, @PathVariable("content") String content) throws IOException {
		System.err.println(content);
		File log = new File("C:\\Users\\Administrator\\Pictures\\123.jpg");
		byte[] qrcode = QrcodeUtils.createQrcode(content, log);
		ServletOutputStream out = response.getOutputStream();
		out.write(qrcode);
		out.close();
	}

}
