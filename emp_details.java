package cse.r.hef;

import jakarta.persistence.Embeddable;

@Embeddable
public class emp_details {
	private String email;
	private String department;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	

}
