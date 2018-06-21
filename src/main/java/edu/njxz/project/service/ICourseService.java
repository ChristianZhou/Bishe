package edu.njxz.project.service;



import edu.njxz.project.model.Course;

import java.util.List;

public interface ICourseService {
	void insert(Course course, List<Integer> stunums);
}
