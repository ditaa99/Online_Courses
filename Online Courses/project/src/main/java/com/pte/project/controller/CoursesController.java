package com.pte.project.controller;

//import com.pte.project.aspect.HasAuthorities;
//import com.pte.project.aspect.SecurityAuthorities;
import com.pte.project.model.Courses;
import com.pte.project.service.CoursesService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CoursesController {
    
    @Autowired
    
    private CoursesService service;
    
    
        
    @GetMapping(value="/courses/spq")
    public List<Courses> getCoursesSpq(){
        return service.getCoursesSpq();
    }
    //@HasAuthorities(authorities = {SecurityAuthorities.ADMIN}) //add authorization to the user
    @GetMapping(value="/courses/jpa")
    public List<Courses> getCoursesJpa(){
        return service.getCoursesJpa();
    }
    
    @PostMapping(value="/addCourse/spq")
    public void addCourseSpq(@RequestBody Courses c){
        service.addCourseSpq(c);
    }
    
    @PostMapping(value="/addCourses/jpa")
    public void addCourseJpa(@RequestBody Courses c){
        service.addCourseJpa(c);
    }
    
    
    @PutMapping(value="/editCourse/spq/{id}")
    public void editCourseSpq(@RequestBody Courses c, @PathVariable Integer id) {
        service.editCourseSpq(c, id);
    }
    
    /*@PutMapping(value="/editCourse/jpa/{id}")
    public void editCourseJpa(@RequestBody Courses c, @PathVariable Integer id) {
        service.editCourseJpa(c, id);
    }*/
    
    @DeleteMapping(value="/deleteCourse/spq/{id}")
    public void deleteCourseSpq(@PathVariable Integer id) {
        service.deleteCourseSpq(id);
    }
    
    @DeleteMapping(value="/deleteCourse/jpa/{id}")
    public void deleteCourseJpa(@PathVariable Integer id) {
        service.deleteCourseJpa(id);
    } 
 
    @GetMapping(value="/findCoursesByName/{name}")
    public List<Courses> findCoursesByName(@PathVariable String name){
        return service.findCoursesByName(name);
    }
    
    
    @GetMapping(value="/findCoursesById/{id}")
    public List<Courses> findCoursesById(@PathVariable Integer id){
        return service.findCoursesById(id);
    }
    
    @GetMapping(value="/orderByCheapest")
    public List<Courses> orderByCheapest(){
        return service.orderByCheapest();
    }
    
    
    @GetMapping(value="/nrOfCourses")
    public List<Courses> nrOfCourses(){
        return service.nrOfCourses();
    }
    
    
    
    @PutMapping(value= "/editCourse/jpa/{id}")
    public void editCourseJpa(@RequestBody Courses course, @PathVariable Integer id){
        service.editCourseJpa(course,id);
    }
    
}
