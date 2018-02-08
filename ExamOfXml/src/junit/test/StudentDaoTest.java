package junit.test;

import org.junit.Test;

import com.itcase.Exception.StudentNotExistException;
import com.itcase.dao.StudentDao;
import com.itcase.domain.Student;


public class StudentDaoTest {
	
	@Test
	public void TestAdd () {
		StudentDao dao = new StudentDao();
		Student s = new Student();
		s.setExamid("444");
		s.setIdcard("444");
		s.setName("王二麻子");
		s.setLocation("黑龙江");
		s.setGrade(99.9);
		dao.add(s);
	}
	
	@Test
	public void TestFind () {
		StudentDao dao = new StudentDao();
		dao.find("111");
	}
	
	@Test
	public void TestDelete () throws StudentNotExistException {
		StudentDao dao = new StudentDao();
		dao.delete("王二麻子");
	}
	
}
