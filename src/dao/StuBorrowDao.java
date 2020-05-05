package dao;

import domain.StuBorrow;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import util.JdbcUtil;

import java.sql.SQLException;
import java.util.List;

public class StuBorrowDao {

    private QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());

    //获取某个学生借书的记录表
    public List<StuBorrow> getStuBorrowRecord(String stuid, String page, String pageSize) throws SQLException {
        String sql = "SELECT * FROM stu_borrow WHERE stuid=? ORDER BY id DESC limit ?,?;";
        List<StuBorrow> allBorrow;
        allBorrow = qr.query(sql, new BeanListHandler<StuBorrow>(StuBorrow.class), stuid, (Integer.parseInt(page) - 1) * Integer.parseInt(pageSize), Integer.parseInt(pageSize));
        return allBorrow;
    }

    public String getAllBorrowCount(String stuid) throws SQLException{
        String sql = "SELECT count(*) FROM stu_borrow where stuid = ?;";
        long count = (long) qr.query(sql, new ScalarHandler(), stuid);
        System.out.println("stuborrowdao count:" + count);
        return "" + count;
    }
}
