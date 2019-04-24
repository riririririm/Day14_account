
package com.rim.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.rim.util.DBConnector;

public class MemberDAO {
	
	public MemberDTO login(MemberDTO dto) throws Exception{
		Connection conn = DBConnector.getConnector();
		String sql ="select * from member where id=? and pw=?";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setString(1, dto.getId());
		pst.setString(2, dto.getPw());
		
		ResultSet rs = pst.executeQuery();
		
		if(rs.next()) {
			dto.setId(rs.getString("id"));
			dto.setName(rs.getString("name"));
			dto.setPhone(rs.getString("phone"));
			dto.setEmail(rs.getString("email"));
		}else {
			dto = null;
		}
		
		return dto;
	}
	
	public int insert(MemberDTO dto) throws Exception {
		//회원가입
		Connection conn = DBConnector.getConnector();
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
