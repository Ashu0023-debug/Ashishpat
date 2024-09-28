package cse.r.hef;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
@Entity
public class pc {
	@Id
	private int pid;
	private String pname;
	@ManyToOne
	private student std;
	
	public student getStd() {
		return std;
	}
	public void setStd(student std) {
		this.std = std;
	}
	
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	
}
