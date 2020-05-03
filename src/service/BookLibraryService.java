package service;

import dao.BookLibraryDao;
import domain.BookInfo;

import java.sql.SQLException;
import java.util.List;

public class BookLibraryService {

    private BookLibraryDao mBookLibraryDao = new BookLibraryDao();

    public List<BookInfo> getAllBooks(String page, String pageSize) throws SQLException {
        return mBookLibraryDao.getAllBooks(page, pageSize);
    }

    public boolean addBook(BookInfo bookInfo) throws SQLException {
        return mBookLibraryDao.addBook(bookInfo);
    }

    public void updateBooks(BookInfo bookInfo) {
    }

    public boolean deleteBooks(String barrcode) throws SQLException{
        return mBookLibraryDao.delBook(barrcode);
    }

    public String getAllBooksCount() throws SQLException{
        return mBookLibraryDao.getAllBooksCount();
    }
}
