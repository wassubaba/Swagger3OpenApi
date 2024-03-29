package com.openapi.definition.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openapi.definition.model.UserDetails;
import com.openapi.definition.service.UserService;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/openApi")
public class ImageController {
	
	@Autowired
	UserService userService;
	
	@Operation(
			tags = "SaveUserDetails",
			description = "Save User and Image Details in Database...")
	@ApiResponses(value = { 
			  @ApiResponse(responseCode = "201", description = "User Created"),
			  @ApiResponse(responseCode = "400", description = "Invalid User Details"), 
			  @ApiResponse(responseCode = "404", description = "User not found")})
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/saveUserDetails")
	public ResponseEntity<?> saveUser(@RequestBody UserDetails userDetails) {
		UserDetails userDetailsSaved = userService.createUser(userDetails);
		return new ResponseEntity<UserDetails>(userDetailsSaved, HttpStatus.CREATED);
	}
	
	@PreAuthorize("hasRole('NORMAL')")
	@GetMapping("/getUserDetails/{id}")
	public ResponseEntity<?> getUserDetailsById(@PathVariable int id) {
		Optional<UserDetails> userDetailsRetrieved = userService.getUserDetailsById(id);
		if (userDetailsRetrieved == null)
			return new ResponseEntity<String>("No Value is Present in DB", HttpStatus.NOT_FOUND);
		return new ResponseEntity<UserDetails>(HttpStatus.OK);
	}
	
	
	@Operation(
			tags = "getAllUsers",
			description = "Get All the UserDetails along with ImageDetails...")
	@ApiResponses(value = { 
			  @ApiResponse(responseCode = "200", description = "Succefully Get All UserDetails and ImageDetsils."),
			  @ApiResponse(responseCode = "400", description = "Invalid Request."), 
			  @ApiResponse(responseCode = "404", description = "User not found")})
	@PreAuthorize("hasRole('NORMAL')")
	@GetMapping("/getAllUsers")
	public ResponseEntity<List<UserDetails>> getAllUsers(){
		List<UserDetails> userDetailsList = userService.getAllUserDetails();
		return new ResponseEntity<List<UserDetails>>(userDetailsList, HttpStatus.OK);
	}
	
	// @Hidden -- To hide the End point on Swagger-UI
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/updateUser")
	public ResponseEntity<UserDetails> updateUser(@RequestBody UserDetails userDetails) {
		UserDetails userDetailsUpdated = userService.updateUser(userDetails);
		return new ResponseEntity<UserDetails>(userDetailsUpdated, HttpStatus.CREATED);
	}
	
	// @Hidden -- To hide the End point on Swagger-UI
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/deleteUser/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable int id) {
		userService.deleteUserById(id);
		return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
	}
	
	// @Hidden -- To hide the End point on Swagger-UI
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/deleteUser/{userId}/Image/{imageId}")
	public ResponseEntity<String> deleteUser(@PathVariable int userId, @PathVariable int imageId) {
		String message = userService.deleteUserImageById(userId, imageId);
		return new ResponseEntity<String>(message, HttpStatus.ACCEPTED);
	}
	
}
