package domain;

public class Ticket {

    private String stuid;
    private String bookid;
    private String over_days;
    private String ticket_fre;
    private String payoff;

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

    public String getOver_days() {
        return over_days;
    }

    public void setOver_days(String over_days) {
        this.over_days = over_days;
    }

    public String getTicket_fre() {
        return ticket_fre;
    }

    public void setTicket_fre(String ticket_fre) {
        this.ticket_fre = ticket_fre;
    }

    public String getPayoff() {
        return payoff;
    }

    public void setPayoff(String payoff) {
        this.payoff = payoff;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "stuid='" + stuid + '\'' +
                ", bookid='" + bookid + '\'' +
                ", over_days='" + over_days + '\'' +
                ", ticket_fre='" + ticket_fre + '\'' +
                ", payoff='" + payoff + '\'' +
                '}';
    }
}
