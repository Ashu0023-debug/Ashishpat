package cse.r.hef;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ename;
    private String email;
    private String eaddress;
    private String workRole;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEaddress() {
        return eaddress;
    }

    public void setEaddress(String eaddress) {
        this.eaddress = eaddress;
    }

    public String getWorkRole() {
        return workRole;
    }

    public void setWorkRole(String workRole) {
        this.workRole = workRole;
    }

    @Override
    public String toString() {
        return "Employee{" +
               "id=" + id +
               ", ename='" + ename + '\'' +
               ", email='" + email + '\'' +
               ", eaddress='" + eaddress + '\'' +
               ", workRole='" + workRole + '\'' +
               '}';
    }
}
