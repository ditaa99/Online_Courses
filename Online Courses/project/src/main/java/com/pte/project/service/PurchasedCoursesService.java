package com.pte.project.service;

import com.pte.project.model.PurchasedCourses;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PurchasedCoursesService {
    
    @PersistenceContext
    
    public EntityManager em;
    
    public List getPurchasedCoursesSpq() {
        StoredProcedureQuery spq = em.createStoredProcedureQuery("getPurchasedCourses"); 
        return spq.getResultList();
    }
    
    
    public List getCoursesforUserSpq(Integer id) {
        StoredProcedureQuery spq = em.createStoredProcedureQuery("getCoursesforUser");
        
        spq.registerStoredProcedureParameter("useridIN", Integer.class, ParameterMode.IN);
        spq.setParameter("useridIN", id);
        return spq.getResultList();
    }
    
    
    
    public void purchaseCourseSpq(PurchasedCourses c){ 
        StoredProcedureQuery spq = em.createStoredProcedureQuery("purchase_course");
        
        //All parameters should get registered in your Java application
        spq.registerStoredProcedureParameter("user_idIN", Integer.class, ParameterMode.IN);
        spq.registerStoredProcedureParameter("course_idIN", Integer.class, ParameterMode.IN);

        //Set values for parameters
        spq.setParameter("user_idIN", c.getUserId());
        spq.setParameter("course_idIN", c.getCourseId());
        //supports execute when information is being passed
        spq.execute();
    }
    
}
