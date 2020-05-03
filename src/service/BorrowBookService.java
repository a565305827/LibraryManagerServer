package service;

import dao.BorrowBookDao;

import java.sql.SQLException;

public class BorrowBookService {

    private BorrowBookDao mBorrowBookDao = new BorrowBookDao();

    public boolean borrowBook(String stuid, String bookid, String expectreturndate, String operator)
        throws SQLException {
        return mBorrowBookDao.borrowBook(stuid, bookid, expectreturndate, operator);
    }
}
