package service;

import dao.StuBorrowDao;
import domain.StuBorrow;

import java.sql.SQLException;
import java.util.List;

public class StuBorrowService {

    private StuBorrowDao mStuBorrowDao = new StuBorrowDao();

    public List<StuBorrow> getStuBorrowRecord(String stuid, String page, String pageSize) throws SQLException {
        return mStuBorrowDao.getStuBorrowRecord(stuid, page, pageSize);
    }

    public String getAllBorrowCount(String stuid) throws SQLException{
        return mStuBorrowDao.getAllBorrowCount(stuid);
    }
}
