package com.pte.project.repository;

import com.pte.project.model.Courses;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CoursesRepository extends JpaRepository<Courses, Integer> {
   
    @Query(value = "SELECT c FROM Courses c WHERE c.name = :name")
    public List<Courses> findCoursesByName(@Param("name") String id);
    
    @Query(value = "SELECT c FROM Courses c WHERE c.id = :id") 
    public List<Courses> findCoursesById(@Param("id") Integer id);
    
}
