package web;

import domain.StuBorrow;
import response.entity.ResponseEntity;
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
@WebServlet("/StuBorrowServlet")
public class StuBorrowServlet extends BaseServlet {

    public String getStuBorrowRecord(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String stuid = request.getParameter("stuid");
        String page = request.getParameter("page");
        String pageSize = request.getParameter("pageSize");
        StuBorrowService stuBorrowService = new StuBorrowService();
        try {
            List<StuBorrow> allBorrow = stuBorrowService.getStuBorrowRecord(stuid, page, pageSize);
            // 对集合进行反转
            Collections.reverse(allBorrow);
            //封装成Json字符串
            ResponseEntity responseEntity = new ResponseEntity();
            if (allBorrow != null || allBorrow.size() > 0) {
               responseEntity.code = "1";
               responseEntity.msg = "访问成功";
            } else {
                responseEntity.code = "0";
                responseEntity.msg = "访问失败";
            }
            responseEntity.page = page;
            responseEntity.pageSize = pageSize;
            responseEntity.hasShowSize = (Integer.parseInt(page) - 1) * Integer.parseInt(pageSize);
            responseEntity.totalSize = stuBorrowService.getAllBorrowCount(stuid);
            //直接复制集合就是一个数组
            responseEntity.data = allBorrow;
            result(request, response, responseEntity);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
