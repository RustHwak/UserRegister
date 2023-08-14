package com.reactspring.fullstackregistration.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reactspring.fullstackregistration.models.User;

public interface UserRepository extends JpaRepository<User,Long> {

    
}
