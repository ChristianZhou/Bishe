package edu.njxz.project.dao;

import edu.njxz.common.annotation.Dao;
import edu.njxz.project.model.Sc;
import edu.njxz.project.model.Student;
import edu.njxz.project.model.UploadStudent;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Dao
public interface StudentDao {
	List<Student> findAll();

	List<Sc> findAllSc(@Param("tname") String tname);
	void update(@Param("student") Student student);
	void upload(@Param("uploadStudent")UploadStudent uploadStudent);
	void uploadXX(@Param("uploadStudent")UploadStudent uploadStudent);

	void insert(@Param("student")Student student);
	void delete(@Param("stunum") int stunum);
	void deletesc(@Param("stunum") int stunum);
	List<Student> findByClass(@Param("cid") int cid, @Param("cnum") int cnum);


	List getClassSum(@Param("cid") int cid, @Param("cnum") int cnum);


}
