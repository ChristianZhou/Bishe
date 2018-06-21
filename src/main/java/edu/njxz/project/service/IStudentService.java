package edu.njxz.project.service;


import edu.njxz.project.model.Student;
import edu.njxz.project.model.UploadStudent;

import java.util.List;

public interface IStudentService {
	 List<Student> findAll();
	 void update(Student student);
	 void upload(UploadStudent uploadStudent);

	void insert(Student student);
	 void insert(Student student,int cid,int cnum);
	 void delete(int stunum);

	void deleteCourse(int[] cid);
}
