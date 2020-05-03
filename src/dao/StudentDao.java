package dao;

import domain.BookInfo;
import domain.Student;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import util.JdbcUtil;

import java.sql.SQLException;
import java.util.List;

public class StudentDao {

    private QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());

    //添加一个学生
    public boolean addStudent(Student student) throws SQLException {
        String sql = "insert into tb_student(name, sex, birthday, tel, email, createDate, operator, utype, pwd) VALUES(?, ?, ?, ?, ?, NOW(), ?, ?, ?);";
        int update = qr.update(sql, student.getName(), student.getSex(), student.getBirthday(), student.getTel(), student.getEmail(), student.getOperator(), student.getUtype(), student.getPwd());
        if (update == 1) {
            return true;
        }
        return false;
    }

    //3.根据id从数据库当中删除一个学生
    public boolean delStudent(String stuid) throws SQLException {
        //删除操作
        String sql = "delete FROM tb_student WHERE stuid = ?;";
        int update = qr.update(sql, stuid);
        if (update == 1){
            return true;
        }
        return false;
    }

    public List<Student> getAllStudent(String page, String pageSize) throws SQLException {

        //1.查询操作
        String sql = "select * from tb_student LIMIT ?,?";
        //2执行sql
        List<Student> allbooks = null;
        allbooks = qr.query(sql, new BeanListHandler<Student>(Student.class), (Integer.parseInt(page) - 1) * Integer.parseInt(pageSize), Integer.parseInt(pageSize));
        return allbooks;
    }

    public String getAllStudentCount() throws SQLException {
        //1.查询操作
        String sql = "select * from tb_student";
        //2执行sql
        List<Student> allStudent = null;
        allStudent = qr.query(sql, new BeanListHandler<Student>(Student.class));
        return "" + allStudent.size();
    }
}
