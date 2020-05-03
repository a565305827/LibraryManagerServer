package dao;

import domain.StuBorrowReturn;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import util.JdbcUtil;

import java.sql.SQLException;
import java.util.List;

public class StuBorrowReturnDao {

    private QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());

    public List<StuBorrowReturn> getStuBorrowReturn(String stuid, String page, String pageSize) throws SQLException {
        String sql = "SELECT * FROM stu_borrow_return WHERE stuid = ? limit ?,?";
        List<StuBorrowReturn> allBorrowReturns;
        allBorrowReturns = qr.query(sql, new BeanListHandler<StuBorrowReturn>(StuBorrowReturn.class), stuid, (Integer.parseInt(page) - 1) * Integer.parseInt(pageSize), Integer.parseInt(pageSize));
        return allBorrowReturns;
    }

    public String getStuBorrowReturnCount(String stuid) throws SQLException{
        String sql = "SELECT count(*) FROM stu_borrow_return where stuid = ?;";
        long count = (long) qr.query(sql, new ScalarHandler(), stuid);
        System.out.println("stuBorrowReturn count:" + count);
        return "" + count;
    }
}
