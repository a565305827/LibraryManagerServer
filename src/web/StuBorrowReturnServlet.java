package web;

import domain.StuBorrow;
import domain.StuBorrowReturn;
import response.entity.ResponseEntity;
import service.StuBorrowReturnService;
import service.StuBorrowService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

/**
 * 查询学生借阅书表
 */
@WebServlet("/StuBorrowReturnServlet")
public class StuBorrowReturnServlet extends BaseServlet {

    public String getStuBorrowReturn(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String stuid = request.getParameter("stuid");
        String page = request.getParameter("page");
        String pageSize = request.getParameter("pageSize");
        StuBorrowReturnService stuBorrowReturnService = new StuBorrowReturnService();
        try {
            List<StuBorrowReturn> allBorrowReturn = stuBorrowReturnService.getStuBorrowReturn(stuid, page, pageSize);
            // 对集合进行反转
            Collections.reverse(allBorrowReturn);
            //封装成Json字符串
            ResponseEntity responseEntity = new ResponseEntity();
            responseEntity.code = "1";
            responseEntity.msg = "访问成功";
            responseEntity.page = page;
            responseEntity.pageSize = pageSize;
            responseEntity.hasShowSize = (Integer.parseInt(page) - 1) * Integer.parseInt(pageSize);
            responseEntity.totalSize = stuBorrowReturnService.getStuBorrowReturnCount(stuid);
            //直接复制集合就是一个数组
            responseEntity.data = allBorrowReturn;
            result(request, response, responseEntity);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getStuBorrowReturnAll(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException{

        return null;
    }
}
