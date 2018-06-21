package edu.njxz.project.controller;

import edu.njxz.project.model.Course;
import edu.njxz.project.model.Teacher;
import edu.njxz.project.service.impl.TeacherService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("teacher")
public class TeacherController {

	Logger logger = Logger.getLogger(TeacherController.class);

	@Autowired private TeacherService teacherService;

	/**
	 * @author 周桂星
	 * @time 2018/2/10 21:43
	 * @description    教师信息注册
	**/
	@RequestMapping(value = "insert", method = RequestMethod.POST)
	public void insert(@ModelAttribute Teacher teacher,HttpServletResponse response) throws IOException {
		int data = teacherService.insert(teacher);
		response.getWriter().print(data);
	}


	
	
	/**
	 * @author 周桂星
	 * @time 2018/2/14 13:53
	 * @description 登陆验证、若成功
	**/
	@RequestMapping(value = "find/one", method = RequestMethod.POST)
	public ModelAndView findOne(@ModelAttribute Teacher teacher,HttpServletRequest request) throws UnsupportedEncodingException {
		String tname=teacher.getTname();
		Boolean b = teacherService.findOne(teacher);
		if (b) {

			request.getSession().setAttribute("tinfo", tname);
			request.getSession().removeAttribute("courses");

			List<Course> list = new ArrayList<Course>();

			try {
				if("卡卡西".equals(tname)){
					list = teacherService.getAllCoursekeys();
				}else{
					list = teacherService.getCoursekeys(tname);
				}
			} catch (Exception e) {
				logger.error("    >>>>>>>ERROR<<<<<<<    获取课程集合	出现异常！");
			}

			request.getSession().setAttribute("courses", list);

			ModelAndView mav=new ModelAndView("classList");
			return mav;
		}else {
			request.getSession().setAttribute("tinfo", "账号或密码不正确");
			ModelAndView mav=new ModelAndView("redirect:/login.jsp");
			return mav;
		}
	}
	
	/**
	 * @author 周桂星
	 * @time 2018/2/14 13:56
	 * @description 根据session免账户密码登陆
	**/
	@RequestMapping(value = "find/one", method = RequestMethod.GET)
	public ModelAndView findOne(HttpServletRequest request) throws UnsupportedEncodingException {
		String  tinfo= request.getSession().getAttribute("tinfo").toString();
		if (!"账号或密码不正确".equals(tinfo)) {
			ModelAndView mav=new ModelAndView("classList");
			return mav;
		}else {
			ModelAndView mav=new ModelAndView("redirect:/login.jsp");
			return mav;
		}
	}


	/**
	 * @author 周桂星
	 * @time 2018/2/21 19:25
	 * @description 验证app登陆
	**/
	@RequestMapping(value = "appLogin", method = RequestMethod.POST)
	public void appLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();

		String tname = request.getParameter("tname");
		String tpwd = request.getParameter("tpwd");
		System.out.println(tname+tpwd);

		Teacher teacher = new Teacher(tname,tpwd);

		Boolean b = false;
		try {
			b = teacherService.findOne(teacher);
		} catch (Exception e) {
			logger.error("    >>>>>>>ERROR<<<<<<<    验证教师信息	出现异常！");
		}
		if (b) {
			out.print("ok");
		}else {
			out.print("no");
		}

	}
}
