package edu.njxz.project.service.impl;

import edu.njxz.project.dao.TeacherDao;
import edu.njxz.project.model.Course;
import edu.njxz.project.model.Teacher;
import edu.njxz.project.service.ITeacherService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeacherService implements ITeacherService {
	Logger logger = Logger.getLogger(TeacherService.class);

	@Autowired
	private TeacherDao teacherMapper;

	public List<Teacher> findAll() {
		return teacherMapper.findAll();
	}
	
	public Boolean findOne(Teacher teacher) {
		String tpwd=null;
		Boolean b = false;
		try {
			tpwd=teacherMapper.findOne(teacher);
			if(teacher.getTpwd().equals(tpwd)){
				b=true;
			}
		} catch (Exception e) {
			logger.error("    >>>>>>>ERROR<<<<<<<    查询密码	出现异常！");
		}
		return b;
	}
	
	public int insert(Teacher teacher) {
		int ok = 0;
		try {
			teacherMapper.insert(teacher);
			logger.info("    ------------------------------------------     添加教师信息成功");
			ok=1;
		} catch (Exception e) {
			logger.error("    >>>>>>>ERROR<<<<<<<    添加教师信息	出现异常！");
		}
		return ok;
	}
	
	public List<Course> getCoursekeys(String tname) {
		List<Course> courses = new ArrayList<Course>();

		try {
			courses=teacherMapper.getCoursekeys(tname);
		} catch (Exception e) {
			logger.error("    >>>>>>>ERROR<<<<<<<    获取课程集合	出现异常！"+e.getMessage());
		}
		return courses;
	}

	public List<Course> getAllCoursekeys() {
		List<Course> courses = new ArrayList<Course>();

		try {
			courses=teacherMapper.getAllCoursekeys();
		} catch (Exception e) {
			logger.error("    >>>>>>>ERROR<<<<<<<    获取课程集合	出现异常！"+e.getMessage());
		}
		return courses;
	}
}
