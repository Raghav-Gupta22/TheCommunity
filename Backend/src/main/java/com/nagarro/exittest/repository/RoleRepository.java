package com.nagarro.exittest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nagarro.exittest.models.Role;

public interface RoleRepository extends JpaRepository<Role, Long>
{

}
