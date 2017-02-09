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
		String actionName = request.getParameter( "a" );
		
		if( "form".equals( actionName ) ) {
			
		} else if( "add".equals( actionName ) ) {
			
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
