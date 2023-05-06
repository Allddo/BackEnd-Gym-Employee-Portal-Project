package com.resumeProject.fullstackbackend.controller;

import com.resumeProject.fullstackbackend.exception.EmployeeNotFoundException;
import com.resumeProject.fullstackbackend.exception.UserNotFoundException;
import com.resumeProject.fullstackbackend.model.Employee;
import com.resumeProject.fullstackbackend.model.User;
import com.resumeProject.fullstackbackend.repository.EmployeeRepository;
import com.resumeProject.fullstackbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class Controller {
     @Autowired
    private UserRepository userRepository;
     @Autowired
     private EmployeeRepository employeeRepository;

     @PostMapping("/user")
    User newUser(@RequestBody User newUser){
        return userRepository.save(newUser);
     }

     @GetMapping("/users")
    List<User> getAllUsers(){
         return userRepository.findAll();
    }

    @GetMapping("/user/{id}")
    User getUserById(@PathVariable Long id){
         return userRepository.findById(id)
                 .orElseThrow(()->new UserNotFoundException(id));
    }

    @GetMapping("/user-count")
    Long getUserCount(){
        return userRepository.count();
    }

    @PutMapping("/user/{id}")
    User updateUser(@RequestBody User newUser,@PathVariable Long id){
         return userRepository.findById(id)
                 .map(user -> {
                     user.setMembershipOption(newUser.getMembershipOption());
                     user.setEmail(newUser.getEmail());
                     user.setName(newUser.getName());
                     return userRepository.save(user);
                 }).orElseThrow(()->new UserNotFoundException(id));
    }

    @DeleteMapping("/user/{id}")
    String deleteUser(@PathVariable Long id){
         if(!userRepository.existsById(id)){
             throw new UserNotFoundException(id);
         }
         userRepository.deleteById(id);
         return "User with id " + id + " had been deleted successfully!";
    }

    @PostMapping("/employee")
    Employee registerEmployee(@RequestBody Employee newEmployee){
         return employeeRepository.save(newEmployee);
    }

    @GetMapping("/employee/{username}")
    Employee getEmployeeByUsername(@PathVariable String username){
         if(employeeRepository.findByUsername(username)==null){
             throw new EmployeeNotFoundException(username);
         }
         else {
             return employeeRepository.findByUsername(username);
         }
    }
}
