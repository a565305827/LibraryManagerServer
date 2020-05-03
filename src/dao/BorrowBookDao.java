package dao;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import org.apache.commons.dbutils.QueryRunner;
import util.JdbcUtil;

import java.sql.SQLException;

public class BorrowBookDao {

    private QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());

    /*
       call proc_borrow("5", "4", NOW(), "2019-18-23", 0, "刘一峰");
       ifback  0：未还   1：已还
     */
    public boolean borrowBook(String stuid, String bookid, String expectreturndate, String operator) throws SQLException {
        String sql = " call proc_borrow(?, ?, NOW(), ?, ?);";
        int update = qr.update(sql, stuid, bookid, expectreturndate, operator);
        if (update == 1) {
            return true;
        }
        return false;
    }
}
