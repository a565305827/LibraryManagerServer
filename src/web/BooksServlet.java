package web;

import constant.Constant;
import domain.BookInfo;
import org.apache.commons.beanutils.BeanUtils;
import response.entity.NoDataResponseEntity;
import response.entity.ResponseEntity;
import service.BookLibraryService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.spec.EncodedKeySpec;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@WebServlet("/BooksLibrary")
public class BooksServlet extends BaseServlet {

	public String lookBook(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//获取所有参数
		Map<String, String> parameterMap = getRequestParamsMap(request);
		String tempType = String.valueOf(parameterMap.get("type"));
		String content = String.valueOf(parameterMap.get("content"));
		int type = tempType == null ? 0 : Integer.parseInt(tempType);
		try {
			BookLibraryService goodsService = new BookLibraryService();
			ResponseEntity responseEntity = new ResponseEntity();
			List<BookInfo> allBooks = goodsService.lookBook(content);
			if (type == Constant.TYPE_JSON) {
				if (allBooks.size() > 0) {
					responseEntity.code = "1";
					responseEntity.msg = "访问成功";
					responseEntity.data = allBooks;
				} else {
					responseEntity.code = "0";
					responseEntity.msg = "访问失败";
					responseEntity.data = allBooks;
				}
				result(request, response, responseEntity);
			} else if (type == Constant.TYPE_JSP) {
				request.setAttribute("allBooks", allBooks);
				return "admin/main.jsp";
			}
		}  catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	//添加书  方式post
	public String addBook(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//获取所有参数
		Map<String, String> parameterMap = getRequestParamsMap(request);
		if (parameterMap.isEmpty()) {
			parameterMap = getRequestAttributeMap(request);
		}

		System.out.println("content----" + request.getAttribute("content"));

		String tempType = parameterMap.get("type");
		int type = tempType == null ? 0 : Integer.parseInt(tempType);
		//把参数封装对象
		BookInfo bookInfo = new BookInfo();
		try {
			BeanUtils.populate(bookInfo, parameterMap);
			NoDataResponseEntity responseEntity = new NoDataResponseEntity();
			BookLibraryService goodsService = new BookLibraryService();
			boolean isOk = goodsService.addBook(bookInfo);
			if (type == Constant.TYPE_JSON) {
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
				//跳转列表，
				return "admin/main.jsp";
			}
		}  catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	//只能根据书的唯一表示去删除
	public String delBook(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1.接收参数 id
		String barcode = request.getParameter("barcode");
		String tempType = request.getParameter("type");
		int type = tempType == null ? 0 : Integer.parseInt(tempType);
		// 2.调用服务层，让其删除商品
		BookLibraryService bookService = new BookLibraryService();
		try {
			boolean isOk = bookService.deleteBooks(barcode);
			NoDataResponseEntity responseEntity = new NoDataResponseEntity();
			if (type == Constant.TYPE_JSON) {
				if (isOk) {
					responseEntity.code = "1";
					responseEntity.msg = "删除成功";
				} else {
					responseEntity.code = "0";
					responseEntity.msg = "删除失败了";
				}
				result(request, response, responseEntity);
			} else {
				return "admin/main.jsp?code=1";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	// 获取所有
	public String getListBooks(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//获取参数
		String page = request.getParameter("page");
		String pageSize = request.getParameter("pageSize");
		int type = Integer.parseInt(request.getParameter("type"));
		BookLibraryService booksService = new BookLibraryService();
		try {
			List<BookInfo> allBooks = booksService.getAllBooks(page, pageSize);
			System.out.println(allBooks.get(0).getBookname());
			if (type == Constant.TYPE_JSON) {
				Collections.reverse(allBooks);
				ResponseEntity responseEntity = new ResponseEntity();
				responseEntity.code = "1";
				responseEntity.msg = "访问成功";
				responseEntity.page = page;
				responseEntity.pageSize = pageSize;
				responseEntity.hasShowSize = (Integer.parseInt(page) - 1) * Integer.parseInt(pageSize);
				responseEntity.totalSize = booksService.getAllBooksCount();
				responseEntity.data = allBooks;
				result(request, response, responseEntity);
			} else if (type == Constant.TYPE_JSP) {
				request.setAttribute("allBooks", allBooks);
				return "admin/main.jsp";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
