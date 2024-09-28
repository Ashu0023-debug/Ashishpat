package cse.r.hef;

import jakarta.persistence.Entity;

import jakarta.persistence.Id;

@Entity
public class emloyees {
	@Id
	private int id;
	private int eid;
	private String fname;
	private String lname;
	private emp_details ed;
	
	
	public emp_details getEd() {
		return ed;
	}
	public void setEd(emp_details ed) {
		this.ed = ed;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getEid() {
		return eid;
	}
	public void setEid(int eid) {
		this.eid = eid;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	@Override
	public String toString() {
		return "employees [id=" + id + ", eid=" + eid + ", fname=" + fname + ", lname=" + lname + "]";
	}
	

}
