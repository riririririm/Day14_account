package com.rim.bankbook;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.rim.history.HistoryDTO;
import com.rim.util.DBConnector;

public class BankbookDAO {
	
	public int insert(BankbookDTO dto, Connection conn) throws Exception {
		//통장개설
		int result=0;
		String sql = "insert into bankbook values(?,?,sysdate,?,?)";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setString(1,dto.getId());
		pst.setString(2, dto.getAccount());
		pst.setString(3, dto.getAcc_name());
		pst.setInt(4, dto.getBalance());
		
		result = pst.executeUpdate();
		
		DBConnector.disConnect(pst, conn);		
		return result;
	}
	
	public int update(HistoryDTO dto, Connection conn) throws Exception{
		String sql ="update bankbook set balance=balance+? where account=?";
		PreparedStatement pst = conn.prepareStatement(sql);
		
		if(dto.getIo()==1) //입금이 들어오면
			pst.setInt(1, dto.getMoney());
		else if(dto.getIo()==0) //출금하면
			pst.setInt(1, -dto.getMoney());
		
		pst.setString(2, dto.getAccount());
		
		int result=pst.executeUpdate();
		
		DBConnector.disConnect(pst);	
		return result;
	
	}
	
	public BankbookDTO select(String account, Connection conn) throws Exception {
		//회원의 특정 통장 정보 조회
		BankbookDTO dto = null;
		String sql ="select * from bankbook where account=?";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setString(1, account);
		ResultSet rs = pst.executeQuery();
		
		if(rs.next()) {
			dto = new BankbookDTO();
			dto.setId(rs.getString(1));
			dto.setAccount(rs.getString(2));
			dto.setCreate_date(rs.getString(3));
			dto.setAcc_name(rs.getString(4));
			dto.setBalance(rs.getInt(5));
		}
		
		DBConnector.disConnect(rs, pst);		
		return dto;		
	}
	
	
	
	

}
