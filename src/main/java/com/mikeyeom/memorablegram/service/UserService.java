package com.mikeyeom.memorablegram.service;

import org.springframework.stereotype.Service;

import com.mikeyeom.memorablegram.common.MD5HashingEncoder;
import com.mikeyeom.memorablegram.common.SHA256HashingEncoder;
import com.mikeyeom.memorablegram.domain.User;
import com.mikeyeom.memorablegram.repository.UserRepository;

@Service
public class UserService {
	
	private UserRepository userRepository;
	
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public boolean addUser(
			String loginId
			, String password
			, String name
			, String email) {
		
		String encodingPassword = MD5HashingEncoder.encode(password);
		
		int count = userRepository.insertUser(loginId, encodingPassword, name, email);
		
		if(count == 1) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isDuplicatedId(String loginId) {
		
		int count = userRepository.selectCountLoginId(loginId);
		
		if(count > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public User getUser(
			String loginId
			, String password) {
		
	    String encodingPassword = MD5HashingEncoder.encode(password);
		
	    return userRepository.selectUser(loginId, encodingPassword);
		
		
	}
	
}