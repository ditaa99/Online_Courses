package com.pte.project.model;

import com.pte.project.model.Courses;
import com.pte.project.model.Users;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2022-11-10T16:03:46")
@StaticMetamodel(PurchasedCourses.class)
public class PurchasedCourses_ { 

    public static volatile SingularAttribute<PurchasedCourses, Date> purchasedDate;
    public static volatile SingularAttribute<PurchasedCourses, Integer> id;
    public static volatile SingularAttribute<PurchasedCourses, Users> userId;
    public static volatile SingularAttribute<PurchasedCourses, Courses> courseId;

}