package com.resumeProject.fullstackbackend.repository;

import com.resumeProject.fullstackbackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

}
