package com.openapi.definition.dao;





import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.openapi.definition.model.UserDetails;


@Repository
public interface UserRepository extends JpaRepository<UserDetails, Integer>{

	Optional<UserDetails> findById(Integer id);

	void deleteById(Integer id);

	boolean existsById(int id);

	UserDetails save(UserDetails userDetails);

	List<UserDetails> findAll();
	
}
