package com.pte.project.repository;

import com.pte.project.model.Users;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UsersRepository extends JpaRepository<Users, Integer> {

//Creating another function that for any reason we don't want to have the procedure in the database    
    //Create a query to get costumer by their names
    @Query(value = "SELECT u FROM Users u WHERE u.username = :username")
    public List<Users> findUsersByUsername(@Param("username") String username);
    
    
}
