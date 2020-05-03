package web;

import constant.Constant;
import domain.EditPwd;
import domain.Manager;
import org.apache.commons.beanutils.BeanUtils;
import response.entity.NoDataResponseEntity;
import response.entity.ResponseEntity;
import service.AdminManagerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

@WebServlet("/AdminManager")
public class AdminManagerServlet extends BaseServlet {

    //添加管理员
    public String add(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Map<String, String> parameterMap = getRequestParamsMap(request);
        int type = Integer.parseInt(parameterMap.get("type"));
        Manager manager = new Manager();
        try {
            BeanUtils.populate(manager, parameterMap);
            System.out.println(manager);
            //调用服务层
            AdminManagerService adminManagerService = new AdminManagerService();
            NoDataResponseEntity noDataResponseEntity = new NoDataResponseEntity();
            if (adminManagerService.checkAdminFromData(manager)) {
                //数据库中还没有这个用户
                Boolean isOK = adminManagerService.addManager(manager);
                    if (type == Constant.TYPE_JSON) {
                        if (isOK) {
                            noDataResponseEntity.msg = "添加成功";
                            noDataResponseEntity.code = "1";
                        } else {
                            noDataResponseEntity.msg = "添加失败";
                            noDataResponseEntity.code = "0";
                        }
                        result(request, response, noDataResponseEntity);
                    } else if (type == Constant.TYPE_JSP) {
                        request.removeAttribute("adderror");
                        return "/AdminManager?action=getListManager";
                    }
            } else {
                if (type == Constant.TYPE_JSON) {
                    noDataResponseEntity.msg = "用户已经存在，请登录";
                    noDataResponseEntity.code = "3";
                } else {
                    request.setAttribute("adderror", "adderror");
                    return "/AdminManager?action=getListManager";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //解决请求参数乱码的问题
    public static Map<String, String> getRequestParamsMap(HttpServletRequest request) {
        Map<String, String> params = new HashMap<String, String>();
        Map requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
            }
            try {
                //将获取到的value值字符编码进行转换
                valueStr = new String(valueStr.getBytes("iso8859-1"), request.getCharacterEncoding());

            } catch (Exception e) {
                System.out.println("字符转化失败!");
            }
            // 乱码解决，这段代码在出现乱码时使用。
            params.put(name, valueStr);
        }
        return params;
    }


    // 删除管理员
    public String delManager(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 1.接收参数 name
        String name = new String(request.getParameter("id").getBytes("ISO8859-1"), "UTF-8");
        int type = Integer.parseInt(new String(request.getParameter("type").getBytes("ISO8859-1"), "UTF-8"));

        // 2.调用服务层，让其删除商品
        AdminManagerService adminManagerService = new AdminManagerService();
        try {
            boolean isOK = adminManagerService.deleteManager(name);
            if (type == Constant.TYPE_JSON) {
                NoDataResponseEntity noDataResponseEntity = new NoDataResponseEntity();
                if (isOK) {
                    noDataResponseEntity.msg = "删除成功";
                    noDataResponseEntity.code = "1";
                } else {
                    noDataResponseEntity.msg = "删除失败";
                    noDataResponseEntity.code = "0";
                }
                result(request, response, noDataResponseEntity);
            } else if (type == Constant.TYPE_JSP) {
                return "/AdminManager?action=getListManager";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    //根据旧的密码修改新的密码
    public String updateManager(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Map<String, String> parameterMap = getRequestParamsMap(request);
        int type = Integer.parseInt(parameterMap.get("type"));
        //把参数封装对象
        EditPwd editPwd = new EditPwd();
        try {
            BeanUtils.populate(editPwd, parameterMap);
            AdminManagerService adminManagerService = new AdminManagerService();
            boolean isOk = adminManagerService.updateManager(editPwd);
            if (type == Constant.TYPE_JSON) {
                NoDataResponseEntity noDataResponseEntity = new NoDataResponseEntity();
                if (isOk) {
                    noDataResponseEntity.msg = "修改成功";
                    noDataResponseEntity.code = "1";
                } else {
                    noDataResponseEntity.msg = "修改失败";
                    noDataResponseEntity.code = "0";
                }
                result(request, response, noDataResponseEntity);
            } else if (type == Constant.TYPE_JSP) {
                //跳转列表，
                return "/AdminManager?action=getListManager";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public String getListManager(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 1.调用服务层
        int type = Integer.parseInt(request.getParameter("type"));
        int utype = Integer.parseInt(request.getParameter("utype"));
        String page = request.getParameter("page");
        String pageSize = request.getParameter("pageSize");
        AdminManagerService adminManagerService = new AdminManagerService();
        try {
            List<Manager> allManagers = adminManagerService.getAllManagers(String.valueOf(utype));
            Collections.reverse(allManagers);
            if (type == Constant.TYPE_JSON) {
                ResponseEntity responseEntity = new ResponseEntity();
                responseEntity.code = "1";
                responseEntity.msg = "访问成功";
                responseEntity.page = page;
                responseEntity.pageSize = pageSize;
                responseEntity.hasShowSize = (Integer.parseInt(page) - 1) * Integer.parseInt(pageSize);
                responseEntity.data = allManagers;
                result(request, response, responseEntity);
                return null;
            } else if (type == Constant.TYPE_JSP) {
                request.setAttribute("allManagers", allManagers);
                return "admin/account.jsp";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
