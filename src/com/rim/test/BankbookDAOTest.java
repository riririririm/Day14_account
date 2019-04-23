package com.rim.test;

import static org.junit.Assert.*;

import java.sql.Connection;

import org.junit.Test;

import com.rim.bankbook.BankbookDAO;
import com.rim.bankbook.BankbookDTO;
import com.rim.util.DBConnector;
import com.rim.view.BankView;

public class BankbookDAOTest {

	//@Test
	public void insertTest() throws Exception {
		Connection conn =  DBConnector.getConnector();
		BankbookDAO dao = new BankbookDAO();
		BankbookDTO dto = new BankbookDTO();
		
		
		dto.setId("a");
		dto.setAccount("110-361-1111111");
		dto.setCreate_date("2019-04-23");
		dto.setAcc_name("a");
		dto.setBalance(1000);
		
		int result = dao.insert(dto, conn);
	}

	@Test
	public void selectTest() throws Exception {
		Connection conn =  DBConnector.getConnector();
		BankbookDTO dto = new BankbookDTO();
		BankbookDAO dao = new BankbookDAO();
		BankView view = new BankView();
		
		dto = dao.select("110-361-1111111", conn);
		view.view(dto);
		
	}
}
