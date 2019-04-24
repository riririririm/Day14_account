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
		//입금만 or 출금만 내역 조회
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

	public ArrayList<HistoryDTO> select(String account, String start, String end, Connection conn) throws Exception{
		//입출금 내역 사용자 지정 기간 조회
		int result=0;
		ArrayList<HistoryDTO> arr = new ArrayList<HistoryDTO>();
		HistoryDTO dto;
		
		String sql = "select * from history where account=? and io_date between ? and to_date(?)+1";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setString(1, account);
		pst.setString(2, start);
		pst.setString(3, end);
		
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
	
	public ArrayList<HistoryDTO2> selectMonth(String account, int month, Connection conn) throws Exception{
		//입출금 내역 조회(1개월/2개월/3개월...)
		int result=0;
		ArrayList<HistoryDTO2> arr = new ArrayList<HistoryDTO2>();
		HistoryDTO2 dto;
		
//		String sql = "select * from history where account=? and io_date between "
//				+ "to_char(add_months(sysdate,?)) and sysdate";
		String sql = "select h.account, h.io_date, h.inout, h.money, b.balance from history h " + 
				"join bankbook b on h.account = b.account " + 
				"where h.account=? " + 
				"and io_date between to_char(add_months(sysdate,?)) and sysdate";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setString(1, account);
		pst.setInt(2, month*-1);
		ResultSet rs = pst.executeQuery();
		System.out.println(11111111);
		while(rs.next()) {
			
			dto = new HistoryDTO2();
			dto.setAccount(rs.getString(1));
			dto.setIo_date(rs.getString(2));
			dto.setIo(rs.getInt(3));
			dto.setMoney(rs.getInt(4));
			dto.setBalance(rs.getInt(5));
			arr.add(dto);
			
		}
		DBConnector.disConnect(rs,pst);
		
		return arr;
	}
	
	
}
