package com.pte.project.service;

import com.pte.project.model.Users;
import com.pte.project.repository.UsersRepository;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;


@Service
@Transactional
public class UsersService {
    
    @Autowired
    private UsersRepository repo;
    
    //need to inject entity manager
    @PersistenceContext
    //To manage or search data in relational database
    public EntityManager em;
    
    //Get a list of all costumers
    public List<Users> getUsersSpq(){
        StoredProcedureQuery spq = em.createStoredProcedureQuery("getUsers");
        return spq.getResultList();
    }
    
    public List<Users> getUsersJpa(){
        return repo.findAll();
    }
    
    //Add a costumer
    public void addUserSpq(Users c){
        StoredProcedureQuery spq = em.createStoredProcedureQuery("addUser");
        
        //All parameters should get registered in your Java application
        spq.registerStoredProcedureParameter("usernameIN", String.class, ParameterMode.IN);
        spq.registerStoredProcedureParameter("nameIN", String.class, ParameterMode.IN);
        spq.registerStoredProcedureParameter("emailIN", String.class, ParameterMode.IN);
        spq.registerStoredProcedureParameter("ageIN", Integer.class, ParameterMode.IN);
        
        //Set values for parameters
        spq.setParameter("usernameIN", c.getUsername());
        spq.setParameter("nameIN", c.getName());
        spq.setParameter("emailIN", c.getEmail());
        spq.setParameter("ageIN", c.getAge());
        
        //supports execute when information is being passed
        spq.execute();
    }
    // the first function
    public void addUserJpa(Users c) {
        repo.save(c);
    }

    
    public void editUserSpq(Users c, Integer id) {
        StoredProcedureQuery spq = em.createStoredProcedureQuery("editUser"); 
        
        spq.registerStoredProcedureParameter("useridIN", Integer.class, ParameterMode.IN);
        spq.registerStoredProcedureParameter("usernameIN", String.class, ParameterMode.IN);
        spq.registerStoredProcedureParameter("nameIN", String.class, ParameterMode.IN);
        spq.registerStoredProcedureParameter("emailIN", String.class, ParameterMode.IN);
        spq.registerStoredProcedureParameter("ageIN", Integer.class, ParameterMode.IN);
        
        spq.setParameter("useridIN", id);
        spq.setParameter("usernameIN", c.getUsername());
        spq.setParameter("nameIN", c.getName());
        spq.setParameter("emailIN", c.getEmail());
        spq.setParameter("ageIN", c.getAge());
        
        spq.execute();
    }
    

    
    public void deleteUserSpq(Integer id) {
        StoredProcedureQuery spq = em.createStoredProcedureQuery("deleteUser"); 
        spq.registerStoredProcedureParameter("useridIN", Integer.class, ParameterMode.IN);
        spq.setParameter("useridIN", id);
        
        spq.execute();
    }

    public void deleteUserJpa(Integer id) {
        repo.deleteById(id);
    }
    
    
    
    public List<Users> findUsersByUsername(@Param("username") String username){
        List<Users> result = repo.findUsersByUsername(username);
        if(CollectionUtils.isEmpty(result)){
           throw new NoResultException();
        } else{
            return result;
        }
    }
    


    public List<Users> findUserByEmailSpq(String email) {
        StoredProcedureQuery spq = em.createStoredProcedureQuery("findUserByEmail"); 
        
        spq.registerStoredProcedureParameter("emailIN", String.class, ParameterMode.IN);
        spq.setParameter("emailIN", email);
        
        return spq.getResultList();
    }
    
    public List<Users> nrOfUsers(){
        StoredProcedureQuery spq = em.createStoredProcedureQuery("nrOfUsers"); 
        
        return spq.getResultList();
    }
    
    
    
    //The second function
    public Users getUserById(Integer id){
        return repo.findById(id).get();
    }

    public void editUserJpa(Users user, Integer id){
        Users existing = getUserById(id);
                
        existing.setUsername(user.getUsername());
        existing.setName(user.getName());
        existing.setEmail(user.getEmail());
        existing.setAge(user.getAge());
        
        repo.save(existing);
    }
}
