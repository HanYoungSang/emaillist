package com.bit2017.emaillist.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bit2017.emaillist.dao.EmailListDao;
import com.bit2017.emaillist.vo.EmailListVo;

@WebServlet("/el")
public class EmailListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// post 방식으로 넘어오는 문자열 데이터의 엔코딩
		request.setCharacterEncoding( "utf-8" );
		
		String actionName = request.getParameter( "a" );
		
		if( "form".equals( actionName ) ) {
			// forward ( request 연장, request dispatch )
			RequestDispatcher rd = 
					request.getRequestDispatcher( "/WEB-INF/views/form.jsp" );
			rd.forward( request, response );
			
		} else if( "add".equals( actionName ) ) {
			String firstName = request.getParameter( "fn" );
			String lastName = request.getParameter( "ln" );
			String email = request.getParameter( "email" );
			
			EmailListVo vo = new EmailListVo();
			vo.setFirstName(firstName);
			vo.setLastName(lastName);
			vo.setEmail(email);
			
			EmailListDao dao = new EmailListDao();
			dao.insert(vo);
			
			response.sendRedirect( request.getContextPath() + "/el" );
			
		} else {
			/* default request = list*/
			EmailListDao dao = new EmailListDao();
			List<EmailListVo> list = dao.getList();
			
			// forward ( request 연장, request dispatch )
			request.setAttribute( "list", list );
			RequestDispatcher rd = 
					request.getRequestDispatcher( "/WEB-INF/views/list.jsp" );
			rd.forward( request, response );
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
