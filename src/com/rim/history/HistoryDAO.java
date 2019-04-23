package com.rim.history;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.rim.util.DBConnector;

public class HistoryDAO {

	public int insert(HistoryDTO dto, Connection conn) throws Exception{
		//입금내역 추가
		int result=0;
		String sql = "insert into history  values("
				+ "history_seq.nextval,?,sysdate,?,?)";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setString(1, dto.getAccount());
		pst.setInt(2, dto.getIo());
		pst.setInt(3, dto.getMoney());
		
		result=pst.executeUpdate();
		DBConnector.disConnect(pst);
		
		return result;
	}
	
	public ArrayList<HistoryDTO> select(String account, int io, Connection conn) throws Exception{
		//입출금 내역 조회
		int result=0;
		ArrayList<HistoryDTO> arr = new ArrayList<HistoryDTO>();
		HistoryDTO dto;
		
		String sql = "select * from history where account=? and inout=?";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setString(1, account);
		pst.setInt(2, io);
		
		ResultSet rs = pst.executeQuery();
		
		while(rs.next()) {
			dto = new HistoryDTO();
			dto.setIdx(rs.getInt(1));
			dto.setAccount(rs.getString(2));
			dto.setIo_date(rs.getString(3));
			dto.setIo(rs.getInt(4));
			dto.setMoney(rs.getInt(5));
			arr.add(dto);
			
		}
		DBConnector.disConnect(rs,pst, conn);
		
		return arr;
	}
	
	public ArrayList<HistoryDTO> select(String account, Connection conn) throws Exception{
		//입출금 내역 조회
		int result=0;
		ArrayList<HistoryDTO> arr = new ArrayList<HistoryDTO>();
		HistoryDTO dto;
		
		String sql = "select * from history where account=?";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setString(1, account);
		
		ResultSet rs = pst.executeQuery();
		
		while(rs.next()) {
			dto = new HistoryDTO();
			dto.setIdx(rs.getInt(1));
			dto.setAccount(rs.getString(2));
			dto.setIo_date(rs.getString(3));
			dto.setIo(rs.getInt(4));
			dto.setMoney(rs.getInt(5));
			arr.add(dto);
			
		}
		DBConnector.disConnect(rs,pst);
		
		return arr;
	}
	
	
	
}
