package com.openapi.definition.dao;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.openapi.definition.model.ImageDetails;


@Repository
public interface ImageRepository extends JpaRepository<ImageDetails, Integer>{
	
	void deleteById(Integer id);
	
	Optional<ImageDetails> findById(Integer id);
}
