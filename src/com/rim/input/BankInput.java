package com.rim.input;

import java.util.Scanner;

import com.rim.bankbook.BankbookDTO;
import com.rim.history.HistoryDTO;
import com.rim.member.MemberDTO;
import com.rim.view.BankView;

public class BankInput {
	private Scanner sc;
	private BankView v;
	
	public BankInput() {
		sc = new Scanner(System.in);
		v = new BankView();
	}
	
	public String accountInput() {
		String account=null;
		
		v.view("조회하실 계좌번호:");
		account = sc.next();
		
		return account;				
	}
	
	public MemberDTO memberInput() {
		MemberDTO dto = new MemberDTO();
		
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
		
		return dto;
		
	}
	
	public BankbookDTO bankbookInput() {
		BankbookDTO dto = new BankbookDTO();
		
		v.view("ID: ");
		dto.setId(sc.next());
		v.view("계좌번호:");
		dto.setAccount(sc.next());
		v.view("통장이름:");
		dto.setAcc_name(sc.next());
		
		return dto;
		
	}
	
	public HistoryDTO ioInput() {
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
