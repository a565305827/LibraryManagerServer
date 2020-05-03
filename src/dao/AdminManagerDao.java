package dao;

import domain.EditPwd;
import domain.Manager;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import util.JdbcUtil;

import java.sql.SQLException;
import java.util.List;

public class AdminManagerDao {

    //用到的框架是 dbutils
    private QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());
    //1.从数据库当中查询所有商品列表
    public List<Manager> getAllManagers(String utype) throws SQLException {

        //1.查询操作
        String sql = "select * from tb_student where utype=?";
        //2执行sql
        List<Manager> allmanagers = null;
        allmanagers = qr.query(sql, new BeanListHandler<Manager>(Manager.class), utype);
        return allmanagers;
    }
    //2.添加管理员到数据库当中
    public boolean addManager(Manager managers) throws SQLException {
        //插入操作9
        String sql = "insert into tb_student(name,pwd,utype) value (?,?,?)";
        int update = qr.update(sql, managers.getName(), managers.getPwd(), managers.getUtype());
        if (update == 1) {
            return true;
        }
        return false;
    }

    //3.根据用户名从数据库当中删除对应的管理员
    public boolean delManager(String name) throws SQLException {
        //删除操作
        String sql = "delete from tb_student where id=?";
        int update = qr.update(sql, name);
        if (update == 1) {
            return true;
        }
        return false;
    }

    //4.根据用户名和密码更改用户密码
    public boolean updateManager(EditPwd editPwd) throws SQLException {
        //更新操作
        String sql = "update tb_student set pwd=? where pwd=? and id=?";
        int update = qr.update(sql, editPwd.getNewPwd(), editPwd.getOldPwd(), editPwd.getId());
        if (update == 1) {
            return true;
        }
        return false;
    }

    public boolean checkAdminFromData(Manager manager) throws SQLException {
        //1.查询操作
        String sql = "select * from tb_student where id=? and pwd=?";
        //2执行sql
        Manager query = qr.query(sql, new BeanHandler<Manager>(Manager.class), new Object[]{manager.getId(), manager.getPwd()});
        if (query != null) {
            return false;
        }
        return true;
    }
}
