package com.resumeProject.fullstackbackend.repository;

import com.resumeProject.fullstackbackend.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    @Query("SELECT e FROM Employee e WHERE e.username = :username")
    Employee findByUsername(@Param("username") String username);
}
