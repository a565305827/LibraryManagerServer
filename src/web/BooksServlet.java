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
		//��ȡ���в���
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
					responseEntity.msg = "���ʳɹ�";
					responseEntity.data = allBooks;
				} else {
					responseEntity.code = "0";
					responseEntity.msg = "����ʧ��";
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

	//�����  ��ʽpost
	public String addBook(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//��ȡ���в���
		Map<String, String> parameterMap = getRequestParamsMap(request);
		if (parameterMap.isEmpty()) {
			parameterMap = getRequestAttributeMap(request);
		}

		System.out.println("content----" + request.getAttribute("content"));

		String tempType = parameterMap.get("type");
		int type = tempType == null ? 0 : Integer.parseInt(tempType);
		//�Ѳ�����װ����
		BookInfo bookInfo = new BookInfo();
		try {
			BeanUtils.populate(bookInfo, parameterMap);
			NoDataResponseEntity responseEntity = new NoDataResponseEntity();
			BookLibraryService goodsService = new BookLibraryService();
			boolean isOk = goodsService.addBook(bookInfo);
			if (type == Constant.TYPE_JSON) {
				if (isOk) {
					responseEntity.code = "1";
					responseEntity.msg = "��ӳɹ�";
					result(request, response, responseEntity);
				} else {
					responseEntity.code = "0";
					responseEntity.msg = "���ʧ��";
					result(request, response, responseEntity);
				}
			} else if (type == Constant.TYPE_JSP) {
				//��ת�б�
				return "admin/main.jsp";
			}
		}  catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	//ֻ�ܸ������Ψһ��ʾȥɾ��
	public String delBook(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1.���ղ��� id
		String barcode = request.getParameter("barcode");
		String tempType = request.getParameter("type");
		int type = tempType == null ? 0 : Integer.parseInt(tempType);
		// 2.���÷���㣬����ɾ����Ʒ
		BookLibraryService bookService = new BookLibraryService();
		try {
			boolean isOk = bookService.deleteBooks(barcode);
			NoDataResponseEntity responseEntity = new NoDataResponseEntity();
			if (type == Constant.TYPE_JSON) {
				if (isOk) {
					responseEntity.code = "1";
					responseEntity.msg = "ɾ���ɹ�";
				} else {
					responseEntity.code = "0";
					responseEntity.msg = "ɾ��ʧ����";
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

	// ��ȡ����
	public String getListBooks(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//��ȡ����
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
				responseEntity.msg = "���ʳɹ�";
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
