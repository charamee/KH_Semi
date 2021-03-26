package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.biz.LoginBiz;

/**
 * Servlet implementation class SnsLoginController
 */
@WebServlet("/sns.do")
public class SnsLoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SnsLoginController() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		String command = request.getParameter("command");
		LoginBiz biz = new LoginBiz();

		if (command.equals("naverlogin")) {
			String name = request.getParameter("name");
			System.out.println(name);

			
			int res = biz.tokenchk(name);
			
			
			if(res>0) {
			
				HttpSession session = request.getSession();// 세션 객체 만들기

				// session scope에 객체 담기
				session.setAttribute("name", name); // 세션 생성
				// 만료되는 시간 설정 (default : 30분)
				session.setMaxInactiveInterval(10 * 60);

				response.sendRedirect("index.jsp");

				
				
			} else {
				
				HttpSession session = request.getSession();// 세션 객체 만들기

				// session scope에 객체 담기
				session.setAttribute("name", name); // 세션 생성
				response.sendRedirect("join_sns.jsp?name="+name);
				
				
					
				}
			}
		
		}

	

	private void jsResponse(HttpServletResponse response, String url, String msg) throws IOException {
		String s = "<script type='text/javascript'>" + "alert('" + msg + "');" + "location.href='" + url + "';"
				+ "</script>";

		response.getWriter().print(s);
	}

}
