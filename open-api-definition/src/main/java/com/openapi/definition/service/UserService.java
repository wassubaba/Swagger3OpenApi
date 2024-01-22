package com.openapi.definition.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openapi.definition.dao.ImageRepository;
import com.openapi.definition.dao.UserRepository;
import com.openapi.definition.exception.EmptyInputException;
import com.openapi.definition.model.ImageDetails;
import com.openapi.definition.model.UserDetails;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ImageRepository imageRepository;
	
	public UserDetails createUser(UserDetails userDetails) {
		
		if (userDetails.getUserName().isEmpty() || userDetails.getUserName().length() == 0) {
			throw new EmptyInputException("400", "Input Fields are empty");
		}
		return userRepository.save(userDetails);
	}
	
	public Optional<UserDetails> getUserDetailsById(int id) {
		return userRepository.findById(id);
	}
	
	public List<UserDetails> getAllUserDetails(){
		return userRepository.findAll();
	}
	
	public UserDetails updateUser(UserDetails userDetails) {
		UserDetails oldUser = null;
		Optional<UserDetails> optionalUser = Optional.empty();
		if(optionalUser.isPresent()) {
			oldUser=optionalUser.get();
			oldUser.setUserName(userDetails.getUserName());
			oldUser.setEmailAdd(userDetails.getEmailAdd());
			oldUser.setImgDetails(userDetails.getImgDetails());
			userRepository.save(oldUser);
		}else {
			return new UserDetails();
		}
		return oldUser;
	}
	
	public void deleteUserById(int id) {
		Optional<UserDetails> optionalUser = Optional.empty();
		if(optionalUser.isPresent()) {
			userRepository.deleteById(id);
		} else
		{
			throw new NoSuchElementException();
		}
	}

	public String deleteUserImageById(int id, int imgId) {
		
		if(!userRepository.existsById(id)) {
			throw new NoSuchElementException();
		}
		
		Optional<ImageDetails> imageDetails = Optional.empty();
		if(imageDetails.isPresent()) {
			imageRepository.deleteById(imgId);
		} else {
			throw new NoSuchElementException();
		}
		return "Image Got Deleted!!!";
	}

}
