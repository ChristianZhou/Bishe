package edu.njxz.project.model;


/**
 * @author 周桂星
 * @time 2018/1/16 13:44
 * @description 学生实体类
**/
public class Student {
    private int stunum;//学号
    private String stuname;//姓名
    private String time;//时间
    private String classname;//班级名称
    private int cid ;
    private int cnum;

    private int class1;
    private int class2;
    private int class3;
    private int class4;
    private int class5;
    private int class6;
    private int class7;
    private int class8;
    private int class9;
    private int class10;
    private int class11;
    private int class12;
    private int class13;
    private int class14;
    private int class15;
    private int class16;
    private int class17;
    private int class18;
    /**
     * Getter and Setter
    **/
    public int getStunum() {
        return stunum;
    }
    public void setStunum(int stunum) {
        this.stunum = stunum;
    }
    public String getStuname() {
        return stuname;
    }
    public void setStuname(String stuname) {
        this.stuname = stuname;
    }
    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }
    
    public String getClassname() {
		return classname;
	}
	public void setClassname(String classname) {
		this.classname = classname;
	}



	public Student() {
    	
    }
	
	public Student(int stunum, String stuname, String classname) {
		super();
		this.stunum=stunum;
		this.stuname=stuname;
        this.classname = classname;
	}
	
	public Student(int stunum, String stuname, String status,String classname) {
		super();
		this.stunum = stunum;
		this.stuname = stuname;
		this.classname=classname;
	}
	public Student(  String status,String time,int stunum) {
		super();
		this.stunum = stunum;
		this.time = time;
	}


    public Student(int stunum, int cid, int cnum) {
        this.stunum = stunum;
        this.cid = cid;
        this.cnum = cnum;
    }

    public int getClass1() {
        return class1;
    }

    public void setClass1(int class1) {
        this.class1 = class1;
    }

    public int getClass2() {
        return class2;
    }

    public void setClass2(int class2) {
        this.class2 = class2;
    }

    public int getClass3() {
        return class3;
    }

    public void setClass3(int class3) {
        this.class3 = class3;
    }

    public int getClass4() {
        return class4;
    }

    public void setClass4(int class4) {
        this.class4 = class4;
    }

    public int getClass5() {
        return class5;
    }

    public void setClass5(int class5) {
        this.class5 = class5;
    }

    public int getClass6() {
        return class6;
    }

    public void setClass6(int class6) {
        this.class6 = class6;
    }

    public int getClass7() {
        return class7;
    }

    public void setClass7(int class7) {
        this.class7 = class7;
    }

    public int getClass8() {
        return class8;
    }

    public void setClass8(int class8) {
        this.class8 = class8;
    }

    public int getClass9() {
        return class9;
    }

    public void setClass9(int class9) {
        this.class9 = class9;
    }

    public int getClass10() {
        return class10;
    }

    public void setClass10(int class10) {
        this.class10 = class10;
    }

    public int getClass11() {
        return class11;
    }

    public void setClass11(int class11) {
        this.class11 = class11;
    }

    public int getClass12() {
        return class12;
    }

    public void setClass12(int class12) {
        this.class12 = class12;
    }

    public int getClass13() {
        return class13;
    }

    public void setClass13(int class13) {
        this.class13 = class13;
    }

    public int getClass14() {
        return class14;
    }

    public void setClass14(int class14) {
        this.class14 = class14;
    }

    public int getClass15() {
        return class15;
    }

    public void setClass15(int class15) {
        this.class15 = class15;
    }

    public int getClass16() {
        return class16;
    }

    public void setClass16(int class16) {
        this.class16 = class16;
    }

    public int getClass17() {
        return class17;
    }

    public void setClass17(int class17) {
        this.class17 = class17;
    }

    public int getClass18() {
        return class18;
    }

    public void setClass18(int class18) {
        this.class18 = class18;
    }

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

}
