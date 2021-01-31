package com.rab3.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rab3.dao.entity.ProfileEntity;

public interface ProfileDaoRepository extends JpaRepository<ProfileEntity, Integer> {
	
	public Optional<ProfileEntity>   findByUsernameAndPassword(String username,String password);
	public Optional<ProfileEntity>   findByEmail(String Email);
	
}
