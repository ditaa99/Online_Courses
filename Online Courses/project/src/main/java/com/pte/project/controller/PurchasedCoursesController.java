package com.pte.project.controller;

import com.pte.project.model.PurchasedCourses;
import com.pte.project.service.PurchasedCoursesService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class PurchasedCoursesController {
    
    @Autowired
    private PurchasedCoursesService service;
    
    @GetMapping(value="/getPurchasedCourses/spq")
    public List getPurchasedCoursesSpq(){
        return service.getPurchasedCoursesSpq(); 
    }
    
    @GetMapping(value="/getCoursesforUser/{id}")
    public List getCoursesforUserSpq(@PathVariable Integer id){
        return service.getCoursesforUserSpq(id); 
    }
    
    @PostMapping(value="/purchasedCourses/spq")
    public void purchaseCourseSpq(@RequestBody PurchasedCourses c){
        service.purchaseCourseSpq(c);
    }
}
