package com.rim.input;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Scanner;

import com.rim.bankbook.BankbookDTO;
import com.rim.history.HistoryDTO;
import com.rim.member.MemberDAO;
import com.rim.member.MemberDTO;
import com.rim.util.DBConnector;
import com.rim.util.Session;
import com.rim.view.BankView;

public class BankInput {
	private Scanner sc;
	private BankView v;
	
	public BankInput() {
		sc = new Scanner(System.in);
		v = new BankView();
	}
	
	public int monthInput() {
		//n개월 간의 입출금 내역 조회시 개월 수를 입력받는 메소드
		int month=0;
		v.view("몇 개월 ?");
		month = sc.nextInt();
		
		return month;
	}
	
	public ArrayList<String> dateSearchInput() {
		//기간별 조회시 사용자에게 조회할 기간 입력받는 메소드
		ArrayList<String> date = new ArrayList<String>();
		
		v.view("조회할 기간을 입력하세요");
		v.view("시작날짜:");
		date.add(sc.next());
		v.view("마지막 날짜:");
		date.add(sc.next());
		
		return date;
	}
	
	public String accountInput() {
		//조회할 계좌번호를 입력받는 메소드
		String account=null;
		
		v.view("조회하실 계좌번호:");
		account = sc.next();
		
		return account;				
	}
	
	public String memberInput() {
		//회원가입시 회원정보를 입력받는 메소드
		MemberDTO dto = new MemberDTO();
		MemberDAO dao = new MemberDAO();
		String msg = null;
		
		v.view("ID: ");
		dto.setId(sc.next());
		v.view("PW:");
		dto.setPw(sc.next());
		v.view("이름:");
		dto.setName(sc.next());
		v.view("전화번호:");
		dto.setPhone(sc.next());
		v.view("이메일:");
		dto.setEmail(sc.next());
		
		int result=0;

		try {
			result = dao.insert(dto);
			if(result>0)
				msg="회원가입 성공";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			msg="회원가입 실패";
		}
		
		return msg;
		
	}
	
	public String login() {
		String msg = null;
		MemberDTO dto = new MemberDTO();
		MemberDAO dao = new MemberDAO();
		
		v.view("ID:");
		dto.setId(sc.next());
		v.view("PW:");
		dto.setPw(sc.next());
		
		String message="로그인 실패";
		try {
			dto = dao.login(dto);
			Session.member=dto;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(dto != null) {
			message="로그인 성공";
		}
		return message;
	}
	
	public BankbookDTO bankbookInput() {
		//통장 개설시 통장 정보를 입력받는 메소드
		BankbookDTO dto = new BankbookDTO();
		
		//v.view("ID: ");
		dto.setId(((MemberDTO)Session.member).getId());
		v.view("계좌번호:");
		dto.setAccount(sc.next());
		v.view("통장이름:");
		dto.setAcc_name(sc.next());
		
		return dto;
		
	}
	
	public HistoryDTO ioInput() {
		//입금할지 출금할지를 입력받고
		//입출금을 할 계좌번호와 금액을 입력받는 메소드
		v.view("1.입금 / 2.출금");
		HistoryDTO dto = new HistoryDTO();
		
		int select = sc.nextInt();
		
		v.view("계좌번호:");
		dto.setAccount(sc.next());
		
		if(select==1) {
			//입금=1
			dto.setIo(1);
			v.view("입금하실 금액:");
		}else if(select==2) {
			//출금=0
			dto.setIo(0);
			v.view("출금하실 금액:");
		}
		
		dto.setMoney(sc.nextInt());
		
		return dto;
		
	}

}
