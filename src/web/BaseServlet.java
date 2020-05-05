package web;

import com.alibaba.fastjson.JSON;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import response.BaseResponseEntity;
import response.entity.ResponseEntity;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Servlet implementation class BaseServlet
 */

public abstract class BaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		request.setCharacterEncoding("UTF-8");
//		response.setContentType("text/html;charset=UTF-8");
//		response.setCharacterEncoding("UTF-8");
		// 接收参数
		String action = request.getParameter("action");
		if (action == null) {
			action = (String) request.getAttribute("action");
		}
		try {
			//获取当前对象的字节
			Class clazz = this.getClass();
			Method method = clazz.getMethod(action, HttpServletRequest.class,HttpServletResponse.class);
			//判断有没有传入的方法
			if(method != null) {
				//如果有就调用
				String desPath =  (String)method.invoke(this, request,response);
				//转发
				if(desPath != null) {
					request.getRequestDispatcher(desPath).forward(request, response);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}


	protected void result(HttpServletRequest req, HttpServletResponse resp, BaseResponseEntity result) throws ServletException, IOException {
		// 处理子类
		BaseResponseEntity responseEntity = null;

		try{
			responseEntity = result;
		}catch(Exception e){
			responseEntity = new ResponseEntity();
		}

		PrintWriter printWriter = resp.getWriter();
		String responseJsonStr = JSON.toJSONString(responseEntity);
		printWriter.write(responseJsonStr);
		printWriter.flush();
		printWriter.close();
	}

	/**
	 * @author tianwyam
	 * @description 从POST请求中获取参数
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> getParam4Post(HttpServletRequest request) throws Exception {

		Map<String, Object> params = new HashMap<>();

		// 获取内容格式
		String contentType = request.getContentType();
		if (contentType != null && !contentType.equals("")) {
			contentType = contentType.split(";")[0];
		}

		if(contentType == null) {
			Map<String, String[]> parameterMap = request.getParameterMap();
			if (parameterMap != null) {
				for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
					params.put(entry.getKey(), entry.getValue()[0]);
				}
			}
		}

		// form表单格式  表单形式可以从 ParameterMap中获取
		if ("appliction/x-www-form-urlencoded".equalsIgnoreCase(contentType)) {
			// 获取参数
			Map<String, String[]> parameterMap = request.getParameterMap();
			if (parameterMap != null) {
				for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
					params.put(entry.getKey(), entry.getValue()[0]);
				}
			}
		}

		// json格式 json格式需要从request的输入流中解析获取
		//jsonMap:{stuid=13, expectreturndate=2020-02-03, operator=鍒樹竴宄?, bookid=4}
		if ("multipart/form-data".equalsIgnoreCase(contentType)) {
			// 使用 commons-io中 IOUtils 类快速获取输入流内容
			DiskFileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setHeaderEncoding("UTF-8");
			List items = upload.parseRequest(request);
			for(Object object:items){
				FileItem fileItem = (FileItem) object;
				if (fileItem.isFormField()) {
					params.put(fileItem.getFieldName(), fileItem.getString("utf-8"));//如果你页面编码是utf-8的
				}
			}
		}
		return params ;
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

	//解决请求参数乱码的问题
	public static Map<String, String> getRequestAttributeMap(HttpServletRequest request) {
		Map<String, String> params = new HashMap<String, String>();

		Enumeration<String> attributeNames = request.getAttributeNames();
		try {
		while (attributeNames.hasMoreElements()) {
			String element = attributeNames.nextElement();
			String valueStr = (String) request.getAttribute(element);

			params.put(element, new String(valueStr.getBytes("iso8859-1"), request.getCharacterEncoding()));

		   }
		} catch (UnsupportedEncodingException e) {
			System.out.println("字符转化失败!");
		}
		return params;
	}
}
