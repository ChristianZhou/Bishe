package edu.njxz.project.service.impl;

import edu.njxz.project.dao.CourseDao;
import edu.njxz.project.dao.StudentDao;
import edu.njxz.project.model.Sc;
import edu.njxz.project.model.Student;
import edu.njxz.project.model.UploadStudent;
import edu.njxz.project.service.IStudentService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService implements IStudentService {


	private Logger logger = Logger.getLogger(StudentService.class);

	@Autowired
	private StudentDao studentDao;
	@Autowired private CourseDao courseDao;

	public List<Student> findAll() {
		logger.info("---------- 查询所有学生信息");
		List students = null;
		try {
			students = studentDao.findAll();
		} catch (Exception e) {
			logger.error("    >>>>>>>ERROR<<<<<<<    查询所有学生	出现异常！");
		}
		return students;
	}



	public List<Sc> findAllSc(String tname) {
		logger.info("---------- 查询所有学生信息");
		List students = null;
		try {
			students = studentDao.findAllSc(tname);
		} catch (Exception e) {
			logger.error("    >>>>>>>ERROR<<<<<<<    查询所有学生	出现异常！");
		}
		return students;
	}
	public void update(Student student) {
		logger.info("---------- 更新学生信息");
		try {
			studentDao.update(student);
		} catch (Exception e) {
			logger.error("    >>>>>>>ERROR<<<<<<<    修改学生信息	出现异常！"+e.getMessage());
		}
	}




	public void upload(UploadStudent uploadStudent) {
		try {
			studentDao.upload(uploadStudent);
		} catch (Exception e) {
			logger.error("    >>>>>>>ERROR<<<<<<<    更新学生考勤信息	出现异常！"+e.getMessage());
			return;
		}
		logger.info("---------- 更新完成");
	}

	public void uploadXX(UploadStudent uploadStudent) {
		try {
			studentDao.uploadXX(uploadStudent);
		} catch (Exception e) {
			logger.error("    >>>>>>>ERROR<<<<<<<    更新学生考勤信息	出现异常！"+e.getMessage());
			return;
		}
		logger.info("---------- 更新完成");
	}




	public void insert(Student student,int cid,int cnum) {
		try {
			studentDao.insert(student);
			courseDao.insertsc(student.getStunum(),cid,cnum);
		} catch (Exception e) {
			logger.error("    >>>>>>>ERROR<<<<<<<    添加学生	"+student.getStunum()+","+student.getStuname()+"	出现异常！");
		}
	}

	public void insert(Student student) {
		try {
			studentDao.insert(student);
		} catch (Exception e) {
			logger.error("    >>>>>>>ERROR<<<<<<<    添加学生	"+student.getStunum()+","+student.getStuname()+"	出现异常！");
		}
	}
	
	public void delete(int stunum) {
		logger.info("---------- 删除学生"+stunum);
		try {
			studentDao.delete(stunum);
			studentDao.deletesc(stunum);
		} catch (Exception e) {
			logger.error("    >>>>>>>ERROR<<<<<<<    删除学生出现异常！");
		}


	}

	public void deleteCourse(int[] cid) {
		logger.info("    ---------- 删除课程名单");


	}





	public List<Student> findByClass(int cid,int cnum) {

		List<Student> list = new ArrayList<Student>();
		try {
			list = studentDao.findByClass(cid, cnum);
		} catch (Exception e) {
			logger.error("    >>>>>>>ERROR<<<<<<<    按照班级查询学生列表失败！");
		}
		return list;
	}


	public List getTotal(int cid,int cnum){
		List list = new ArrayList();

		try {
			list=studentDao.getClassSum(cid, cnum);
		} catch (Exception e) {
			logger.error("    >>>>>>>ERROR<<<<<<<    计算缺勤记录总和失败！"+e.getMessage());
		}


		return list;
	}
}
