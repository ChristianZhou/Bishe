package edu.njxz.project.dao;


import edu.njxz.common.annotation.Dao;
import edu.njxz.project.model.Course;
import org.apache.ibatis.annotations.Param;

@Dao
public interface CourseDao {
	//将课程实体类插入课程信息表
	void insert(@Param("course") Course course);
	//从课程信息中取出teacher、course关系插入tc表
	void inserttc(@Param("course") Course course);
	// 将学生学号、课程编号、班级 关系  插入sc表
	void insertsc(@Param("stunum") int stunum, @Param("cid") int cid, @Param("cnum") int cnum);

	void delete(@Param("cid") int cid,@Param("cnum") int cnum);
	void deletetc(@Param("cid") int cid,@Param("cnum") int cnum);
	void deletesc(@Param("cid") int cid,@Param("cnum") int cnum);




}
