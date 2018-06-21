package edu.njxz.project.dao;

import edu.njxz.common.annotation.Dao;
import edu.njxz.project.model.Course;
import edu.njxz.project.model.Teacher;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Dao
public interface TeacherDao {
	List<Teacher> findAll();
	void insert(@Param("teacher") Teacher teacher);
	String findOne(@Param("teacher") Teacher teacher);
	List<Course> getCoursekeys(@Param("tname") String tname);
	List<Course> getAllCoursekeys();
}
