package edu.njxz.project.model;


/**
 * @author 周桂星
 * @time 2018/1/16 11:07
 * @description 课程实体类
**/
public class Course {
	int cid;//课程编号
	int cnum;//班级
	double credit;//学分
	int period;//课时
	String cname;//课程名
	String cunit;//开课单位
	String stuamount;//学生人数
	String tname;//授课教师
	String timeandplace;//授课时间地点
	int tnum;//教师编号

	/**
	 * Getter and Setter
	**/
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public int getCnum() {
		return cnum;
	}
	public void setCnum(int cnum) {
		this.cnum = cnum;
	}
	public double getCredit() {
		return credit;
	}
	public void setCredit(double credit) {
		this.credit = credit;
	}
	public int getPeriod() {
		return period;
	}
	public void setPeriod(int period) {
		this.period = period;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getCunit() {
		return cunit;
	}
	public void setCunit(String cunit) {
		this.cunit = cunit;
	}
	public String getStuamount() {
		return stuamount;
	}
	public void setStuamount(String stuamount) {
		this.stuamount = stuamount;
	}
	public String getTname() {
		return tname;
	}
	public void setTname(String tname) {
		this.tname = tname;
	}
	public String getTimeandplace() {
		return timeandplace;
	}
	public void setTimeandplace(String timeandplace) {
		this.timeandplace = timeandplace;
	}
	public int getTnum() {
		return tnum;
	}
	public void setTnum(int tnum) {
		this.tnum = tnum;
	}
	
	public Course() {
		
	}
	public Course(int cid, int cnum) {
		super();
		this.cid=cid;
		this.cnum=cnum;
	}
	public Course(int cid, int cnum, double credit, int period, String cname, String cunit, String stuamount,
			String tname, String timeandplace,int tnum) {
		super();
		this.cid = cid;
		this.cnum = cnum;
		this.credit = credit;
		this.period = period;
		this.cname = cname;
		this.cunit = cunit;
		this.stuamount = stuamount;
		this.tname = tname;
		this.timeandplace = timeandplace;
		this.tnum=tnum;
	}
	@Override
	public String toString() {
	    return "Course [cid=" + cid + ", cnum=" + cnum + ", credit=" + credit + ", period=" + period + ", cname="
		    + cname + ", cunit=" + cunit + ", stuamount=" + stuamount + ", tname=" + tname + ", timeandplace="
		    + timeandplace + ", tnum=" + tnum + "]";
	}
	
	
}
