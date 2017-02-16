package com.bit2017.emaillist.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bit2017.emaillist.web.EmailListActionFactory;
import com.bit2017.web.Action;
import com.bit2017.web.ActionFactory;

@WebServlet("/el")
public class EmailListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	@Override
	protected void service(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		System.out.println( "service() called");
		super.service(arg0, arg1);
	}

	@Override
	public void init() throws ServletException {
		System.out.println( "init() called");
		super.init();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println( "doGet() called");
		// post 방식으로 넘어오는 문자열 데이터의 엔코딩
		request.setCharacterEncoding( "utf-8" );
		
		String actionName = request.getParameter( "a" );
		
		ActionFactory af = new EmailListActionFactory();
		Action action = af.getAction( actionName );
		action.execute( request, response );
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println( "doPost() called");
		doGet(request, response);
	}

}
