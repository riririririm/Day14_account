package com.rim.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.rim.util.DBConnector;

public class MemberDAO {
	
	public int insert(MemberDTO dto, Connection conn) throws Exception {
		//회원가입
		int result=0;
		String sql = "insert into member values(?,?,?,?,?)";
		
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setString(1, dto.getId());
		pst.setString(2, dto.getPw());
		pst.setString(3, dto.getName());
		pst.setString(4, dto.getPhone());
		pst.setString(5, dto.getEmail());
		
		result = pst.executeUpdate();		
		DBConnector.disConnect(pst, conn);
		return result;		
	}
	

}
