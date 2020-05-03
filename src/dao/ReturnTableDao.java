package dao;

import domain.StuBorrowReturn;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import util.JdbcUtil;

import java.sql.SQLException;
import java.util.List;

public class ReturnTableDao {

    private QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());

    public boolean returnBook(String stuid, String bookid) throws SQLException {
        String sql = "call pro_return(?, ?, NOW());";
        int update = qr.update(sql, stuid, bookid);
        if (update == 1) {
            return true;
        }
        return false;
    }

    public List<StuBorrowReturn> getReturnAll() throws SQLException{
        String sql = "SELECT * from stu_borrow_return;";
        List<StuBorrowReturn> allBReturns;
        allBReturns = qr.query(sql, new BeanListHandler<StuBorrowReturn>(StuBorrowReturn.class));
        return allBReturns;
    }
}
