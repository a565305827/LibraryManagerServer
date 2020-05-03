package web;

import response.entity.NoDataResponseEntity;
import service.ReturnTableService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

@WebServlet("/ReturnTableServlet")
public class ReturnTableServlet extends BaseServlet {

    public String returnBook(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
        Map<String, Object> param4Post = null;
        try {
            param4Post = getParam4Post(request);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String stuid = request.getParameter("stuid");
        String bookid = request.getParameter("bookid");
        if (param4Post != null && !param4Post.isEmpty()) {
            stuid = (String) param4Post.get("stuid");
            bookid = (String) param4Post.get("bookid");
        }
        System.out.println("ReturnTableServlet__" + "stuid:" + stuid + "\nbookid:" + bookid);
        ReturnTableService returnTableService = new ReturnTableService();
        NoDataResponseEntity responseEntity = new NoDataResponseEntity();
        try{
            if (returnTableService.returnBook(stuid, bookid)) {
                responseEntity.code = "1";
                responseEntity.msg = "还书成功";
                result(request, response, responseEntity);
            } else {
                responseEntity.code = "0";
                responseEntity.msg = "还书失败";
                result(request, response, responseEntity);
            }
        } catch (SQLException e) {
           e.printStackTrace();
        }
        return null;
    }
}
