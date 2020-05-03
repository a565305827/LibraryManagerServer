package dao;

import domain.Ticket;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import util.JdbcUtil;

import java.sql.SQLException;
import java.util.List;

public class TicketDao {

    private QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());

    public List<Ticket> getAllTicket() throws SQLException {
        String sql = "SELECT * from ticket;";
        List<Ticket> alltickets;
        alltickets = qr.query(sql, new BeanListHandler<Ticket>(Ticket.class));
        return alltickets;
    }

    public String getStudentCount() throws SQLException{
        String sql = "SELECT count(*) from ticket;";
        long count = (long) qr.query(sql, new ScalarHandler());
        return "" + count;
    }

    //支付罚金
}
