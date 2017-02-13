package com.bit2017.emaillist.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bit2017.web.Action;
import com.bit2017.web.util.WebUtil;

public class FormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// forward ( request 연장, request dispatch )
		WebUtil.forward(
			"/WEB-INF/views/form.jsp",
			request, 
			response );
	}
}
