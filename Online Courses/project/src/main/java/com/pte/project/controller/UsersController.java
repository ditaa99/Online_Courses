package com.pte.project.controller;


import com.pte.project.model.Users;
import com.pte.project.service.UsersService;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController

class UsersController {
    
    
    @Autowired
    
    private UsersService service;
    
    
    @GetMapping(value="/users/spq")
    public List<Users> getUsersSpq(){
        return service.getUsersSpq();
    }
    
    @GetMapping(value="/users/jpa")
    public List<Users> getUsersJpa(){
        return service.getUsersJpa();
    }
    
    @PostMapping(value="/addUser/spq")
    public void addUserSpq(@RequestBody Users c){
        service.addUserSpq(c);
    }
    
    @PostMapping(value="/addUser/jpa")
    public void addUserJpa(@RequestBody Users c){
        service.addUserJpa(c);
    }

    
    @PutMapping(value="/editUser/spq/{id}")
    public void editUserSpq(@RequestBody Users c, @PathVariable Integer id) {
        service.editUserSpq(c, id);
    }
    
    /*@PutMapping(value="/editUser/jpa/{id}")
    public void editUserJpa(@RequestBody Users c, @PathVariable Integer id) {
        service.editUserJpa(c, id);
    }*/
    
    @DeleteMapping(value="/deleteUser/spq/{id}")
    public void deleteUserSpq(@PathVariable Integer id) {
        service.deleteUserSpq(id);
    }
    
    @DeleteMapping(value="/deleteUser/jpa/{id}")
    public void deleteUserJpa(@PathVariable Integer id) {
        service.deleteUserJpa(id);
    }
    
    @GetMapping(value="/findUsersByUsername/{username}")
    public List<Users> findUsersByUsername(@PathVariable String username){
        return service.findUsersByUsername(username);
    }
    
    @PutMapping(value= "/editUser/jpa/{id}")
    public void editUserJpa(@RequestBody Users user, @PathVariable Integer id){
        service.editUserJpa(user,id);
    }
    

    @GetMapping(value="/findUserByEmail/spq/{email}")
    public List<Users> findUserByEmailSpq(@PathVariable String email){
        return service.findUserByEmailSpq(email);
    }
    
    @GetMapping(value="/nrOfUsers")
    public List<Users> nrOfUsers(){
        return service.nrOfUsers();
    }
    
}
