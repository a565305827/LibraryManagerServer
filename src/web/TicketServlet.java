package web;

import domain.Student;
import domain.Ticket;
import response.entity.ResponseEntity;
import service.StudentService;
import service.TicketService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

@WebServlet("/TicketServlet")
public class TicketServlet extends BaseServlet{

    private TicketService mTicketService = new TicketService();

    public String getAllTicket(HttpServletRequest request, HttpServletResponse response)
     throws ServletException, IOException {

        //获取参数
        String page = request.getParameter("page");
        String pageSize = request.getParameter("pageSize");
        // 1.调用服务层
        try {
            List<Ticket> allTicket = mTicketService.getAllTicket(page, pageSize);
            // 对集合进行反转
            Collections.reverse(allTicket);
            //封装成Json字符串
            ResponseEntity responseEntity = new ResponseEntity();
            responseEntity.code = "1";
            responseEntity.msg = "访问成功";
            responseEntity.page = page;
            responseEntity.pageSize = pageSize;
            responseEntity.hasShowSize = (Integer.parseInt(page) - 1) * Integer.parseInt(pageSize);
            responseEntity.totalSize = mTicketService.getStudentCount();
            //直接复制集合就是一个数组
            responseEntity.data = allTicket;
            result(request, response, responseEntity);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String submitTicket(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException{


        return null;
    }
}
