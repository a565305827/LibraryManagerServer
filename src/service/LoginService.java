package service;


import dao.LoginDao;
import domain.Student;

public class LoginService {

	public Student login(String utype, String name, String pwd) throws Exception {
		//����dao�㵽���ݿ⵱�в�ѯ
		LoginDao adminDao = new LoginDao();
		Student admin = adminDao.checkAdmin(utype, name,pwd);
		if(admin != null) {
			return admin;
		}else {
			throw new Exception("�û������������");
		}
	}

}
