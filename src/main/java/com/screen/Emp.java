package com.screen;

import org.springframework.boot.autoconfigure.batch.BatchProperties;

import com.rental.model.RentalOrder;

import java.math.BigDecimal;
import java.sql.Date;
import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "emp")
public class Emp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "emp_id")
    private Integer empId;

    @Column(name = "emp_name", length = 10)
    private String empName;

    @Column(name = "emp_password", length = 12,nullable = false)
    private String empPassword;

    @Column(name = "emp_email", length = 40)
    private String empEmail;

    @Column(name = "hiredate")
    private Date hireDate;

    @ManyToOne
    @JoinColumn(name = "job_id")
    private Job job;

    @Column(name = "emp_status", length = 3,nullable = false)
    private String empStatus;


	@OneToMany(mappedBy ="emp",cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private Set<RentalOrder> rentalOrder;

    public Integer getEmpId() {
        return empId;
    }

    public Set<RentalOrder> getRentalOrder() {
		return rentalOrder;
	}

	public void setRentalOrder(Set<RentalOrder> rentalOrder) {
		this.rentalOrder = rentalOrder;
	}

	public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpPassword() {
        return empPassword;
    }

    public void setEmpPassword(String empPassword) {
        this.empPassword = empPassword;
    }

    public String getEmpEmail() {
        return empEmail;
    }

    public void setEmpEmail(String empEmail) {
        this.empEmail = empEmail;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public String getEmpStatus() {
        return empStatus;
    }

    public void setEmpStatus(String empStatus) {
        this.empStatus = empStatus;
    }

    @Override
    public String toString() {
        return "Emp [empId=" + empId + ", empName=" + empName + ", empPassword=" + empPassword + ", empEmail="
                + empEmail + ", hireDate=" + hireDate + ", job=" + job + ", empStatus=" + empStatus + "]";
    }






}
