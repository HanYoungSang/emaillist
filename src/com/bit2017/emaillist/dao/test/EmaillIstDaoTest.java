package com.bit2017.emaillist.dao.test;

import com.bit2017.emaillist.dao.EmailListDao;
import com.bit2017.emaillist.vo.EmailListVo;

public class EmaillIstDaoTest {

	public static void main(String[] args) {
		insertTest();
	}
	
	public static void insertTest() {
		//mock data
		EmailListVo vo = new EmailListVo();
		vo.setFirstName( "둘" );
		vo.setLastName( "리" );
		vo.setEmail( "dooly@gmail.com" );
		
		//
		EmailListDao dao = new EmailListDao();
		dao.insert( vo );
	}

}
