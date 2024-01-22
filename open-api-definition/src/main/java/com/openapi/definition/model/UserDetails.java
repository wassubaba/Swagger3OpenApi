package com.openapi.definition.model;


import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDetails {
	@Id
	@GeneratedValue
	private int id;
	private String userName;
	private String emailAdd;
	

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(foreignKey = @ForeignKey(name = "user_fk_id"), name = "user_id")
	private Set<ImageDetails> imgDetails;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmailAdd() {
		return emailAdd;
	}

	public void setEmailAdd(String emailAdd) {
		this.emailAdd = emailAdd;
	}

	public Set<ImageDetails> getImgDetails() {
		return imgDetails;
	}

	public void setImgDetails(Set<ImageDetails> imgDetails) {
		this.imgDetails = imgDetails;
	}

	@Override
	public String toString() {
		return "UserDetails [id=" + id + ", userName=" + userName + ", emailAdd=" + emailAdd + ", imgDetails="
				+ imgDetails + "]";
	}
	
	
}
