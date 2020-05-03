package web;


import response.entity.NoDataResponseEntity;
import service.BorrowBookService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

@WebServlet("/BorrowServlet")
public class BorrowServlet extends BaseServlet {

    public String borrowBook(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Object s = IOUtils.toString(request.getInputStream(), "UTF-8");
        //System.out.println("result:" + s.toString());
        Map<String, Object> param4Post = null;
        try {
            param4Post = getParam4Post(request);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String stuid = request.getParameter("stuid");
        String bookid = request.getParameter("bookid");
        String expectreturndate = request.getParameter("expectreturndate");
        String operator = request.getParameter("operator");
        if (param4Post != null && !param4Post.isEmpty()){
            stuid = (String) param4Post.get("stuid");
            bookid = (String) param4Post.get("bookid");
            expectreturndate = (String) param4Post.get("expectreturndate");
            operator = (String) param4Post.get("operator");
        }
        System.out.println(stuid + ";" + bookid + ";" + expectreturndate + ";" + operator);
        BorrowBookService borrowBookService = new BorrowBookService();
        try {
            NoDataResponseEntity responseEntity = new NoDataResponseEntity();
            if (borrowBookService.borrowBook(stuid, bookid, expectreturndate, operator)) {
                responseEntity.code = "1";
                responseEntity.msg = "借阅成功";
                result(request, response, responseEntity);
            } else {
                responseEntity.code = "0";
                responseEntity.msg = "借阅失败";
                result(request, response, responseEntity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
