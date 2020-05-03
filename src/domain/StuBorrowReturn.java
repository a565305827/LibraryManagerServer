package domain;

/**
 * 学生借阅书籍还书记录表
 */
public class StuBorrowReturn {

    private String stuid;
    private String name;
    private String bookid;
    private String bookname;
    private String borrowdate;
    private String returnDate;

    public String getStuid() {
        return stuid;
    }

    public void setStuid(String stuid) {
        this.stuid = stuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBookid() {
        return bookid;
    }

    public void setBookid(String bookid) {
        this.bookid = bookid;
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

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    @Override
    public String toString() {
        return "StuBorrowReturn{" +
                "stuid='" + stuid + '\'' +
                ", name='" + name + '\'' +
                ", bookid='" + bookid + '\'' +
                ", bookname='" + bookname + '\'' +
                ", borrowdate='" + borrowdate + '\'' +
                ", returnDate='" + returnDate + '\'' +
                '}';
    }
}
