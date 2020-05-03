package service;

import dao.StudentDao;
import domain.Student;

import java.sql.SQLException;
import java.util.List;

public class StudentService {

    private StudentDao studentDao = new StudentDao();

    public boolean addStudent(Student student) throws SQLException {
        return studentDao.addStudent(student);
    }

    public boolean delStudent(String stuid) throws SQLException{
        return studentDao.delStudent(stuid);
    }

    public List<Student> getAllStudent(String page, String pageSize) throws SQLException{
        return studentDao.getAllStudent(page, pageSize);
    }

    public String getStudentCount() throws SQLException {
        return studentDao.getAllStudentCount();
    }
}
