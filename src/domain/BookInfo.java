package domain;

import javax.xml.crypto.Data;
import java.util.Date;

public class BookInfo {
    private String bookid;
    private String bookname;
    private String price;
    private String barcode;
    private String inTime;
    private String author;
    private String press;
    private String operator;
    private String bookcase;

    public String getBookid() {
        return bookid;
    }

    public void setBookid(String bookid) {
        this.bookid = bookid;
    }

    public String getBookcase() {
        return bookcase;
    }

    public void setBookcase(String bookcase) {
        this.bookcase = bookcase;
    }

    public int getBooknum() {
        return booknum;
    }

    public void setBooknum(int booknum) {
        this.booknum = booknum;
    }

    private int booknum;

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barCode) {
        this.barcode = barCode;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getInTime() {
        return inTime;
    }

    public void setInTime(String inTime) {
        this.inTime = inTime;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPress() {
        return press;
    }

    public void setPress(String press) {
        this.press = press;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    @Override
    public String toString() {
        return "BookInfo{" +
                "bookname='" + bookname + '\'' +
                ", price=" + price +
                ", inTime=" + inTime +
                ", author='" + author + '\'' +
                ", press='" + press + '\'' +
                ", operator='" + operator + '\'' +
                '}';
    }
}
