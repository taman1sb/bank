package com.rab3.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rab3.controller.dto.ApplicationVO;
import com.rab3.controller.dto.ProfileDTO;
import com.rab3.service.ProfileService;

@RestController
@RequestMapping("/api2")
public class ProfileRestController {
	
	@Autowired
	private ProfileService profileService;
	
	/***
				 * {
			aid: 1,
			username: "javatech1000@gmail.com",
			password: "test",
			name: "JavaHunk Technologies",
			email: "javahunk2020@gmail.com",
			gender: "Female",
			role: "admin"
			}
	 * @param profileDTO
	 * @param model
	 * @return
	 */
	@PostMapping("/profiles")
	public ApplicationVO registerPostPage(@RequestBody ProfileDTO profileDTO) {
		profileService.persist(profileDTO);
		ApplicationVO applicationVO=new ApplicationVO();
		applicationVO.setCode("O8182");
		applicationVO.setMessage("profile is created ");
		/*{
			"code":"O8182",
			"message":profile is created""
		}*/
		return applicationVO;
	}
	
	@PutMapping("/profiles")
	public ApplicationVO updateProfile(@RequestBody ProfileDTO profileDTO) {
		profileService.updateProfila(profileDTO);
		ApplicationVO applicationVO=new ApplicationVO();
		applicationVO.setCode("O8182");
		applicationVO.setMessage("profile is update ");
		return applicationVO;
	}

	
	//Jackson Mapper or GSON
	@GetMapping("/profiles")
	public List<ProfileDTO> showProfiles() {
		List<ProfileDTO> profileDTOs = profileService.findProfiles();
		return profileDTOs;
	}
	
	//api2/profiles/3
	@GetMapping("/profiles/{pid}")
	public ProfileDTO editProfile(@PathVariable("pid") int gid) {
		ProfileDTO profileDTO = profileService.findProfileById(gid);
		return profileDTO;
	}
	
	@DeleteMapping("/profiles/{pid}")
	public ApplicationVO deleteProfile(@PathVariable("pid") int gid) {
		ApplicationVO applicationVO=new ApplicationVO();
		applicationVO.setCode("O8182");
		applicationVO.setMessage("profile is deleted ");
		return applicationVO;
	}
	

}
