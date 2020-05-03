package service;

import dao.StuBorrowReturnDao;
import domain.StuBorrowReturn;

import java.sql.SQLException;
import java.util.List;

public class StuBorrowReturnService {

    private StuBorrowReturnDao mStuBorrowReturnDao = new StuBorrowReturnDao();

    public List<StuBorrowReturn> getStuBorrowReturn(String stuid, String page, String pageSize) throws SQLException {
        return mStuBorrowReturnDao.getStuBorrowReturn(stuid, page, pageSize);
    }

    public String getStuBorrowReturnCount(String stuid) throws SQLException{
        return mStuBorrowReturnDao.getStuBorrowReturnCount(stuid);
    }


}

