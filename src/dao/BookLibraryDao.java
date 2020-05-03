package dao;

import domain.BookInfo;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import util.JdbcUtil;

import java.sql.SQLException;
import java.util.List;

public class BookLibraryDao {

    private QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());
    //1.从数据库当中查询所有商品列表
    public List<BookInfo> getAllBooks(String page, String pageSize) throws SQLException {

        //1.查询操作
        String sql = "select * from tb_bookinfo LIMIT ?,?";
        //2执行sql
        List<BookInfo> allbooks = null;
        allbooks = qr.query(sql, new BeanListHandler<BookInfo>(BookInfo.class), (Integer.parseInt(page) - 1) * Integer.parseInt(pageSize), Integer.parseInt(pageSize));
        return allbooks;
    }

    //2.添加一本书到数据库当中
    public boolean addBook(BookInfo bookInfo) throws SQLException {
        String sql = "\n" +
                "INSERT INTO tb_bookinfo(bookname, price, inTime, author, barcode, operator, press, bookcase, booknum) VALUES(?, ?, NOW(), ?,  RAND()*1000, ?, ?, ?, ?);";
        int update = qr.update(sql, bookInfo.getBookname(), bookInfo.getPrice(), bookInfo.getAuthor(), bookInfo.getOperator(), bookInfo.getPress(), bookInfo.getBookcase(), bookInfo.getBooknum());
        if (update == 1) {
            return true;
        }
        return false;
    }

    //3.根据id从数据库当中删除一个商品
    public boolean delBook(String barcode) throws SQLException {
        //删除操作
        String sql = "delete from tb_bookinfo where barcode=?";
        int update = qr.update(sql, barcode);
        if (update == 1){
            return true;
        }
        return false;
    }

    public String getAllBooksCount() throws SQLException {
        //1.查询操作
        String sql = "select * from tb_bookinfo";
        //2执行sql
        List<BookInfo> allbooks = null;
        allbooks = qr.query(sql, new BeanListHandler<BookInfo>(BookInfo.class));
        return "" + allbooks.size();
    }
}
