package dao;

import domain.Manager;
import domain.Student;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import util.JdbcUtil;

import java.sql.SQLException;

public class LoginDao {

	//1.连接
	private QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());

	public Student checkAdmin(String utype, String name, String pwd) throws SQLException {
		
		//到数据库当中查询
		//2.查询
		String sql ="select * from tb_student where name=? and pwd=? and utype=?";
		//3.执行查询
		Student admin = null;
		admin = qr.query(sql, new BeanHandler<Student>(Student.class),name,pwd,utype);
		//返回查询结果
		return admin;
	}


}
