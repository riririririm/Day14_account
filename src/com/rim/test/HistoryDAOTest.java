package com.rim.test;

import static org.junit.Assert.*;

import java.sql.Connection;

import org.junit.Test;

import com.rim.bankbook.BankbookDAO;
import com.rim.bankbook.BankbookDTO;
import com.rim.util.DBConnector;

public class HistoryDAOTest {

	@Test
	public void insertTest() throws Exception {
		//입금내역추가
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

}
