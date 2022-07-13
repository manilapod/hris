package org.goup10.hris.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Employees {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    Integer employeesId;
    Integer benefitsId;
    String firstName;
    String lastName;
    String email;
    Date birthdate;
    String jobRole;
    String address;
    String telephoneNumber;
    String state;
    Boolean inTraining;
    Integer performance;
    Date startedDate;
    String lastUpdates;

    public Integer getEmployeesId() {
        return employeesId;
    }

    public void setEmployeesId(Integer employeeId) {
        this.employeesId = employeeId;
    }

    public Integer getBenefitsId() {
        return benefitsId;
    }

    public void setBenefitsId(Integer benefitsId) {
        this.benefitsId = benefitsId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthDate) {
        this.birthdate = birthDate;
    }

    public String getJobRole() {
        return jobRole;
    }

    public void setJobRole(String jobRole) {
        this.jobRole = jobRole;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Boolean getInTraining() {
        return inTraining;
    }

    public void setInTraining(Boolean inTraining) {
        this.inTraining = inTraining;
    }

    public Integer getPerformance() {
        return performance;
    }

    public void setPerformance(Integer performance) {
        this.performance = performance;
    }

    public Date getStartedDate() {
        return startedDate;
    }

    public void setStartedDate(Date startedDate) {
        this.startedDate = startedDate;
    }

    public String getLastUpdates() {
        return lastUpdates;
    }

    public void setLastUpdates(String lastUpdates) {
        this.lastUpdates = lastUpdates;
    }
}
