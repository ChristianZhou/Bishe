package edu.njxz.project.model;

/**
 * Created by 周桂星 on 2018/2/17.16:39
 *
 * @Description:
 */
public class UploadStudent {
    private int stunum;
    private int cid;
    private int cnum;
    private int classnum;

    public UploadStudent(int stunum, int cid, int cnum, int classnum) {
        this.stunum = stunum;
        this.cid = cid;
        this.cnum = cnum;
        this.classnum = classnum;
    }


    public int getStunum() {
        return stunum;
    }

    public void setStunum(int stunum) {
        this.stunum = stunum;
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

    public int getClassnum() {
        return classnum;
    }

    public void setClassnum(int classnum) {
        this.classnum = classnum;
    }
}
