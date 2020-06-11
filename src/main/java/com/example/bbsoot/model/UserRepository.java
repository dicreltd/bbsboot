package com.example.bbsoot.model;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository <User, Integer>{
	Optional<User> findByUnameAndPass(String uname,String pass);

	@Query(value="SELECT * FROM User WHERE uname = :uname and pass = :pass", nativeQuery = true)
	Optional<User> login(@Param("uname") String uname,@Param("pass") String pass);
}
