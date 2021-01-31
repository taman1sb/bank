package com.rab3.service;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rab3.controller.dto.ProfileDTO;
import com.rab3.dao.ProfileDaoRepository;
import com.rab3.dao.entity.ProfileEntity;
import com.rab3.dao.utils.Utils;

@Service
@Transactional
public class ProfileServiceImpl  implements  ProfileService{
   
	@Autowired
	private ProfileDaoRepository profileDao;
	
	@Override
	public  ProfileDTO auth(String username, String password) {
		Optional<ProfileEntity> result=profileDao.findByUsernameAndPassword(username,password);
		ProfileDTO profileDTO=null;
		if(result.isPresent()) {
			 profileDTO=new ProfileDTO();
			BeanUtils.copyProperties(result.get(), profileDTO);	
		}
		return profileDTO;
	}
	
	
	@Override
	public byte[] findPhotoById(int aid) {
		Optional<ProfileEntity> result=profileDao.findById(aid);
		return result.get().getHphoto();
	}
	
	@Override
	public String findPassword(String email) {
		return profileDao.findByEmail(email).get().getPassword();
	}
	
	@Override
	public String persist(ProfileDTO profileDTO) {
		ProfileEntity profileEntity=new ProfileEntity();
		BeanUtils.copyProperties(profileDTO, profileEntity);
		try {
			if(profileDTO.getPhoto()!=null) {
				profileEntity.setHphoto(profileDTO.getPhoto().getBytes());	
			}
			profileEntity.setRole("customer");
			profileEntity.setDoe(new Timestamp(new Date().getTime()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		profileDao.save(profileEntity);
		return "success";
	}
	
	@Override
	public ProfileDTO findProfileById(int aid) {
		ProfileEntity entity=profileDao.findById(aid).get();
		ProfileDTO profileDTO=new  ProfileDTO();
		BeanUtils.copyProperties(entity, profileDTO);
		return profileDTO;
	}
	
	@Override
public 	String updateProfila(ProfileDTO profileDTO) {
		
		ProfileEntity profileEntity=new ProfileEntity();
		BeanUtils.copyProperties(profileDTO, profileEntity);
		try {
			if(profileDTO.getPhoto()!=null &&  profileDTO.getPhoto().getBytes()!=null & profileDTO.getPhoto().getBytes().length>5) {
				profileEntity.setHphoto(profileDTO.getPhoto().getBytes());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		//data is in Session 
		ProfileEntity dbprofileEntity=profileDao.findById(profileDTO.getAid()).get();
		Utils.copyNonNullProperties(profileEntity, dbprofileEntity);
		
		return "done";
	}
	
	@Override
	public long findCounts() {
		return profileDao.count();
	}
	
	@Override
	public List<ProfileDTO> findProfiles(){
		 List<ProfileEntity> lista=profileDao.findAll();
		 List<ProfileDTO> profileDTOs=new ArrayList<>();
		 for(ProfileEntity entity:lista) {
			 ProfileDTO profileDTO=new  ProfileDTO();
				BeanUtils.copyProperties(entity, profileDTO);
				profileDTO.setPphoto(entity.getHphoto());
				profileDTOs.add(profileDTO);
		 }
		 return profileDTOs;
	}
}
