package edu.njxz.project.model;

public class Teacher {
    private String tname;
    private String tpwd;

	/**
	 * Getter and Setter
	**/
	public String getTname() {
		return tname;
	}
	public void setTname(String tname) {
		this.tname = tname;
	}
	public String getTpwd() {
		return tpwd;
	}
	public void setTpwd(String tpwd) {
		this.tpwd = tpwd;
	}
	public Teacher() {
		
	}

	public Teacher(String tname, String tpwd) {
		this.tname = tname;
		this.tpwd = tpwd;
	}

	public Teacher(int tnum, String tname, String tpwd) {
		super();
		this.tname = tname;
		this.tpwd = tpwd;
	}
    
    
    
}
