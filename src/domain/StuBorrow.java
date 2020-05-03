package domain;

/**
 * 学生借阅书籍表
 */
public class StuBorrow {

    private String stuid;
    private String bookid;
    private String name;
    private String bookname;
    private String borrowdate;
    private String expect_return_date;

    public String getStuid() {
        return stuid;
    }

    public void setStuid(String stuid) {
        this.stuid = stuid;
    }

    public String getBookid() {
        return bookid;
    }

    public void setBookid(String bookid) {
        this.bookid = bookid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public String getBorrowdate() {
        return borrowdate;
    }

    public void setBorrowdate(String borrowdate) {
        this.borrowdate = borrowdate;
    }

    public String getExpect_return_date() {
        return expect_return_date;
    }

    public void setExpect_return_date(String expect_return_date) {
        this.expect_return_date = expect_return_date;
    }

    @Override
    public String toString() {
        return "StuBorrow{" +
                "stuid='" + stuid + '\'' +
                ", bookid='" + bookid + '\'' +
                ", name='" + name + '\'' +
                ", bookname='" + bookname + '\'' +
                ", borrowdate='" + borrowdate + '\'' +
                ", expect_return_date='" + expect_return_date + '\'' +
                '}';
    }
}
