package service;

import dao.ReturnTableDao;

import java.sql.SQLException;

public class ReturnTableService {

    private ReturnTableDao mReturnTableDao = new ReturnTableDao();

    public boolean returnBook(String stuid, String bookid) throws SQLException {
        return mReturnTableDao.returnBook(stuid, bookid);
    }
}
