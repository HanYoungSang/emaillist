package com.bit2017.emaillist.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bit2017.emaillist.dao.EmailListDao;
import com.bit2017.emaillist.vo.EmailListVo;
import com.bit2017.web.Action;
import com.bit2017.web.util.WebUtil;

public class ListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		/* default request = list*/
		EmailListDao dao = new EmailListDao();
		List<EmailListVo> list = dao.getList();
		
		// forward ( request 연장, request dispatch )
		request.setAttribute( "list", list );
		WebUtil.forward( 
			"/WEB-INF/views/list.jsp",
			request, 
			response );
	}
}
