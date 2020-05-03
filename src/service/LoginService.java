package service;


import dao.LoginDao;
import domain.Student;

public class LoginService {

	public Student login(String utype, String name, String pwd) throws Exception {
		//调用dao层到数据库当中查询
		LoginDao adminDao = new LoginDao();
		Student admin = adminDao.checkAdmin(utype, name,pwd);
		if(admin != null) {
			return admin;
		}else {
			throw new Exception("用户名或密码错误");
		}
	}

}
