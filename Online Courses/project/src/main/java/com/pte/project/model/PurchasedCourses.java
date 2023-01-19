package com.pte.project.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;


@Entity
@Table(name = "purchased_courses")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PurchasedCourses.findAll", query = "SELECT p FROM PurchasedCourses p"),
    @NamedQuery(name = "PurchasedCourses.findById", query = "SELECT p FROM PurchasedCourses p WHERE p.id = :id"),
    @NamedQuery(name = "PurchasedCourses.findByPurchasedDate", query = "SELECT p FROM PurchasedCourses p WHERE p.purchasedDate = :purchasedDate")})
public class PurchasedCourses implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "purchased_date")
    @Temporal(TemporalType.DATE)
    private Date purchasedDate;
    @Basic(optional = false)
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "course_id")
    @Basic(optional = false)
    private Integer courseId;

    public PurchasedCourses() {
    }

    public PurchasedCourses(Integer id) {
        this.id = id;
    }

    public PurchasedCourses(Integer id, Date purchasedDate) {
        this.id = id;
        this.purchasedDate = purchasedDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getPurchasedDate() {
        return purchasedDate;
    }

    public void setPurchasedDate(Date purchasedDate) {
        this.purchasedDate = purchasedDate;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PurchasedCourses)) {
            return false;
        }
        PurchasedCourses other = (PurchasedCourses) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.pte.project.model.PurchasedCourses[ id=" + id + " ]";
    }
    
}
