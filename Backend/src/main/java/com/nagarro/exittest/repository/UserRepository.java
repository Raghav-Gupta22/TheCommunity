package com.nagarro.exittest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nagarro.exittest.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>
{
	public User findByEmailAndPassword(String email, String password);
	
	@Query("select u from User u where u.email = :email")
	public User getUserByEmail(@Param("email") String email);
	
}
