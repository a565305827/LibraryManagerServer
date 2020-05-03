package service;

import dao.AdminManagerDao;
import domain.EditPwd;
import domain.Manager;

import java.sql.SQLException;
import java.util.List;

public class AdminManagerService {

    private AdminManagerDao adminManagerDao = new AdminManagerDao();
    public List<Manager> getAllManagers(String utype) throws SQLException {
        //从数据库当中获取所有的数据
        List<Manager> allManagers = adminManagerDao.getAllManagers(utype);
        return allManagers;
    }

    public boolean deleteManager(String name) throws Exception {
        //调用dao让其删除商品
        return adminManagerDao.delManager(name);
    }

    public Boolean addManager(Manager manager) throws Exception {
        //调用dao 插入操作
       return adminManagerDao.addManager(manager);
    }

    //根据用户名和密码更改密码
    public boolean updateManager(EditPwd editPwd) throws Exception {
        return adminManagerDao.updateManager(editPwd);
    }

    public boolean checkAdminFromData(Manager manager) throws Exception{
        return adminManagerDao.checkAdminFromData(manager);
    }
}
