//package web;
//
//import javax.servlet.annotation.WebServlet;
//
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import response.entity.ResponseEntity;
//
///**
// * 用户发表评论的 Servlet
// * @author hcDarren
// *
// */
//@WebServlet("/web.UserCommentServlet")
//public class UserCommentServlet extends BaseJsonServlet{
//
//	private static final long serialVersionUID = 1L;
//
//
//	protected ResponseEntity onHandler(HttpServletRequest req, HttpServletResponse resp) throws Exception {
//		// 判断该用户有没有登录，没有登录或者登录过期都不让评论
//
//		ResponseEntity responseEntity = new ResponseEntity();
//		responseEntity.code = "0033";
//		responseEntity.msg = "评论失败，用户未登录";
//
//		Cookie[] cookies = req.getCookies();
//		if(cookies!=null){
//			for(Cookie cookie : cookies){
//				String userName = cookie.getName();
//				if(userName != null){
//					responseEntity.code = "0000";
//					responseEntity.msg = "评论成功";
//					return responseEntity;
//				}
//			}
//		}
//
//		return responseEntity;
//	}
//
//}
