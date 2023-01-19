package com.pte.project.service;


import com.pte.project.model.Courses;
import com.pte.project.repository.CoursesRepository;
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

public class CoursesService {
    
    @Autowired
    private CoursesRepository repo;
    
    
    @PersistenceContext
    public EntityManager em;
    
    //Get a list of all courses
    public List<Courses> getCoursesSpq() {
        StoredProcedureQuery spq = em.createStoredProcedureQuery("getCourses");
        return spq.getResultList();
    }
    public List<Courses> getCoursesJpa(){
        return repo.findAll();
    }
    
    //Add a course
    public void addCourseSpq(Courses c){ 
        StoredProcedureQuery spq = em.createStoredProcedureQuery("addCourse");
        
        //All parameters should get registered in your Java application
        spq.registerStoredProcedureParameter("nameIN", String.class, ParameterMode.IN);
        spq.registerStoredProcedureParameter("priceIN", Float.class, ParameterMode.IN);
        spq.registerStoredProcedureParameter("descIN", String.class, ParameterMode.IN);
        
        //Set values for parameters
        spq.setParameter("nameIN", c.getName());
        spq.setParameter("priceIN", c.getPrice());
        spq.setParameter("descIN", c.getDescription());
        
        //supports execute when information is being passed
        spq.execute();
    }
    public void addCourseJpa(Courses c) {
        repo.save(c);
    }
    
    //Edit Course
    public void editCourseSpq(Courses c, Integer id){ //doesnt work in swagger
        StoredProcedureQuery spq = em.createStoredProcedureQuery("editCourse"); 
        
        spq.registerStoredProcedureParameter("idIN", Integer.class, ParameterMode.IN);
        spq.registerStoredProcedureParameter("nameIN", String.class, ParameterMode.IN);
        spq.registerStoredProcedureParameter("priceIN", Float.class, ParameterMode.IN);
        spq.registerStoredProcedureParameter("descIN", String.class, ParameterMode.IN);

        
        spq.setParameter("idIN", id);
        spq.setParameter("nameIN", c.getName());
        spq.setParameter("priceIN", c.getPrice());
        spq.setParameter("descIN", c.getDescription());
        
       
        spq.execute();
    }
    
    
    
    //Delete Course
    public void deleteCourseSpq(Integer id){
        StoredProcedureQuery spq = em.createStoredProcedureQuery("deleteCourse"); 
        spq.registerStoredProcedureParameter("idIN", Integer.class, ParameterMode.IN);
        spq.setParameter("idIN", id);
        
        spq.execute();
    } 
    public void deleteCourseJpa(Integer id) {
        repo.deleteById(id);
    }  
    

    
    
     
 

    
    public List<Courses> findCoursesByName(@Param("name") String name){
        List<Courses> result = repo.findCoursesByName(name);
        if(CollectionUtils.isEmpty(result)){
           throw new NoResultException();
        } else{
            return result;
        }
    }

    public List<Courses> findCoursesById(@Param("id") Integer id) {
        List<Courses> result = repo.findCoursesById(id);
        if(CollectionUtils.isEmpty(result)){
           throw new NoResultException();
        } else{
            return result;
        }
    }

    
    public List<Courses> orderByCheapest() {
        StoredProcedureQuery spq = em.createStoredProcedureQuery("orderByCheapest");
        return spq.getResultList();
    }

    public List<Courses> nrOfCourses(){
        StoredProcedureQuery spq = em.createStoredProcedureQuery("nrOfCourses"); 
        
        return spq.getResultList();
    }

    
    
    
    
    public Courses getCourseById(Integer id){
        return repo.findById(id).get();
    }

    public void editCourseJpa(Courses course, Integer id){
        Courses existing = getCourseById(id);
                
        existing.setName(course.getName());
        existing.setPrice(course.getPrice());
        existing.setDescription(course.getDescription());

        repo.save(existing);
    }
}
    
    
    

    
