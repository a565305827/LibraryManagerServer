package web;

import constant.Constant;
import domain.Manager;
import domain.Student;
import response.entity.NoDataResponseEntity;
import response.entity.ResponseEntity;
import service.LoginService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet("/LoginServlet")
public class LoginServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//接收用户名密码
			Map<String, Object> param4Post = getParam4Post(request);
			String name = (String) param4Post.get("username");
			String pwd = (String) param4Post.get("password");
			int utype = Integer.parseInt((String) param4Post.get("utype"));
			String tempType = (String) param4Post.get("type");
			int type = tempType == null ? 0 : Integer.parseInt(tempType);
			LoginService adminService = new LoginService();
			System.out.println(name + ";" + pwd + ";" + type + ";" + utype);
			Student admin = adminService.login(String.valueOf(utype), name,pwd);
			if (type == Constant.TYPE_JSON) {
					ResponseEntity responseEntity = new ResponseEntity();
					if (admin != null) {
						responseEntity.msg = "登录成功";
						responseEntity.code = "1";
						responseEntity.data = admin;
					} else {
						responseEntity.code = "0";
						responseEntity.msg = "登录失败";
					}
					result(request, response, responseEntity);
			} else {
				//1.把用户保存到session
				HttpSession session = request.getSession();
				session.setAttribute("admin", admin);
				//2.跳转到后台首页
				//重定向，让浏览器去跳转到指定的位置
				response.sendRedirect(request.getContextPath()+"/admin/admin_index.jsp");
			}
		} catch (Exception e) {
			System.out.println("e:" + e.getMessage());
			if(e.getMessage().equals("用户名或密码错误")) {
				//跳转回登录页，回显错误信息
				request.setAttribute("err", e.getMessage());
				//转发,服务器内部的转发
				request.getRequestDispatcher(request.getContextPath()+"/admin/admin_index.jsp").forward(request, response);
			}else {
				e.printStackTrace();
			}
		}
	}

	public boolean isContainChinese(String str) {
        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher m = p.matcher(str);
        if (m.find()) {
            return true;
        }
        return false;
    }

}
