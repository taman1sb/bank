package com.rab3.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rab3.controller.dto.Image;

@RestController
public class ImageController {
	
	@GetMapping("/images")
	public Image getImage() {
		Image image=new  Image();
		image.setName("Hoilla");
		image.setType("png");
		return image;
	}

}
