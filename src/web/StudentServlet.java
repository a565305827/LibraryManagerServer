package web;

import constant.Constant;
import domain.Student;
import org.apache.commons.beanutils.BeanUtils;
import response.entity.NoDataResponseEntity;
import response.entity.ResponseEntity;
import service.StudentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@WebServlet("/StudentServlet")
public class StudentServlet extends BaseServlet {

    public String addStudent(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Map<String, String> parameterMap = getRequestParamsMap(request);
        String tempType = parameterMap.get("type");
        int type = tempType == null ? 0 : Integer.parseInt(tempType);
        System.out.println(parameterMap);
        //把参数封装对象
        Student student = new Student();
        try {
            BeanUtils.populate(student, parameterMap);
            StudentService studentService = new StudentService();
            boolean isOk = studentService.addStudent(student);
            if (type == Constant.TYPE_JSON) {
                NoDataResponseEntity responseEntity = new NoDataResponseEntity();
                if (isOk) {
                    responseEntity.code = "1";
                    responseEntity.msg = "添加成功";
                    result(request, response, responseEntity);
                } else {
                    responseEntity.code = "0";
                    responseEntity.msg = "添加失败";
                    result(request, response, responseEntity);
                }
            } else if (type == Constant.TYPE_JSP) {
                return "admin/borrowing.jsp";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String delStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.接收stuid
        String stuid = request.getParameter("stuid");
        String tempType = request.getParameter("type");
        int type = tempType == null ? 0 : Integer.parseInt(tempType);
        StudentService studentService = new StudentService();
        try {
            //进行判断是否成功删除，以json格式返回给客户端
            boolean isOk = studentService.delStudent(stuid);
            if (type == Constant.TYPE_JSON) {
                NoDataResponseEntity responseEntity = new NoDataResponseEntity();
                if (isOk) {
                    responseEntity.code = "1";
                    responseEntity.msg = "删除成功";
                } else {
                    responseEntity.code = "0";
                    responseEntity.msg = "删除失败了";
                }
                result(request, response, responseEntity);
            } else if (type == Constant.TYPE_JSP) {
                return "admin/borrowing.jsp";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // 获取所有的学生
    public String getListStudent(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //获取参数
        String page = request.getParameter("page");
        String pageSize = request.getParameter("pageSize");
        int type = Integer.parseInt(request.getParameter("type"));
        // 1.调用服务层
        StudentService studentService = new StudentService();
        try {
            List<Student> allBooks = studentService.getAllStudent(page, pageSize);
            if (type == Constant.TYPE_JSON) {
                // 对集合进行反转
                Collections.reverse(allBooks);
                //封装成Json字符串
                ResponseEntity responseEntity = new ResponseEntity();
                responseEntity.code = "1";
                responseEntity.msg = "访问成功";
                responseEntity.page = page;
                responseEntity.pageSize = pageSize;
                responseEntity.hasShowSize = (Integer.parseInt(page) - 1) * Integer.parseInt(pageSize);
                responseEntity.totalSize = studentService.getStudentCount();
                //直接复制集合就是一个数组
                responseEntity.data = allBooks;
                result(request, response, responseEntity);
            } else if (type == Constant.TYPE_JSP){

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    // 获取所有的学生
    public String lookStudent(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Map<String, String> parameterMap = getRequestParamsMap(request);
        String content = String.valueOf(parameterMap.get("content"));
        int type = Integer.parseInt(request.getParameter("type"));
        // 1.调用服务层
        StudentService studentService = new StudentService();
        try {
            List<Student> allBooks = studentService.lookStudent(content);
            if (type == Constant.TYPE_JSON) {
                ResponseEntity responseEntity = new ResponseEntity();
                responseEntity.code = "1";
                responseEntity.msg = "访问成功";
                responseEntity.data = allBooks;
                result(request, response, responseEntity);
            } else if (type == Constant.TYPE_JSP){

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
