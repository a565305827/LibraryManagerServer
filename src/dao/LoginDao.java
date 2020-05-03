package dao;

import domain.Manager;
import domain.Student;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import util.JdbcUtil;

import java.sql.SQLException;

public class LoginDao {

	//1.����
	private QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());

	public Student checkAdmin(String utype, String name, String pwd) throws SQLException {
		
		//�����ݿ⵱�в�ѯ
		//2.��ѯ
		String sql ="select * from tb_student where name=? and pwd=? and utype=?";
		//3.ִ�в�ѯ
		Student admin = null;
		admin = qr.query(sql, new BeanHandler<Student>(Student.class),name,pwd,utype);
		//���ز�ѯ���
		return admin;
	}


}
