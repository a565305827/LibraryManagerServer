package service;

import dao.TicketDao;
import domain.Ticket;

import java.sql.SQLException;
import java.util.List;

public class TicketService {

    private TicketDao mTicketDao = new TicketDao();

    public List<Ticket> getAllTicket(String page, String pageSize) throws SQLException {
        return mTicketDao.getAllTicket();
    }

    public String getStudentCount() throws SQLException{
        return mTicketDao.getStudentCount();
    }
}
