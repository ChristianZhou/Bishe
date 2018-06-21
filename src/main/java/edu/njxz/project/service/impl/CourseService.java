package edu.njxz.project.service.impl;


import edu.njxz.project.dao.CourseDao;
import edu.njxz.project.model.Course;
import edu.njxz.project.service.ICourseService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * @author 周桂星
 * @time 2018/1/16 11:11
 * @description CourseService层
**/
@Service
public class CourseService implements ICourseService {

	private  Logger logger = Logger.getLogger(CourseService.class);
	@Autowired
	private CourseDao courseDao;
	
	/**
	 * @author 周桂星
	 * @time 2018/1/16 11:11
	 * @description 更新  课程信息表course、教师课程联系表tc、学生课程联系表sc
	**/
	public void insert(Course course, List<Integer> stunums) {

		try {
			courseDao.insert(course);
		} catch (Exception e) {
			logger.error("    >>>>>>>ERROR<<<<<<<	insert   添加课程信息	出现异常！");
		}

		try {
			courseDao.inserttc(course);
		} catch (Exception e) {
			logger.error("    >>>>>>>ERROR<<<<<<<	insert    更新教师课程联系表tc	出现异常！");
		}

		int cid=course.getCid();
		int cnum=course.getCnum();

		try {
			for(Integer stunum:stunums) {
                courseDao.insertsc(stunum, cid, cnum);
            }
		} catch (Exception e) {
			logger.error("    >>>>>>>ERROR<<<<<<<	insert    更新学生课程联系表	出现异常！");
		}
	}


	public void delete(int cid,int cnum){
		try {
			courseDao.delete(cid,cnum);
			logger.info("    ------------------------------------------     删除course表记录成功");
		} catch (Exception e) {
			logger.error("    >>>>>>>ERROR<<<<<<<    删除course表记录出现异常！");
		}

		try {
			courseDao.deletesc(cid,cnum);
			logger.info("    ------------------------------------------     删除sc表记录成功");
		} catch (Exception e) {
			logger.error("    >>>>>>>ERROR<<<<<<<    删除sc表记录出现异常！");
		}

		try {
			courseDao.deletetc(cid,cnum);
			logger.info("    ------------------------------------------     删除tc表记录成功");
		} catch (Exception e) {
			logger.error("    >>>>>>>ERROR<<<<<<<    删除tc表记录出现异常");
		}
	}


}
