package cse.r.hef;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity

public class student {
	@Id
	private int rollno;
	private String sname;
	private int year;
	@OneToMany(mappedBy = "std")
	
	private List<pc> p = new ArrayList<>();
	
	public List<pc> getP() {
		return p;
	}
	public void setP(List<pc> p) {
		this.p = p;
	}
	
	
	public int getRollno() {
		return rollno;
	}
	public void setRollno(int rollno) {
		this.rollno = rollno;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	

}
