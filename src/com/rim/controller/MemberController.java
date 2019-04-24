package com.rim.controller;

import java.util.Scanner;

import com.rim.input.BankInput;
import com.rim.member.MemberDAO;
import com.rim.member.MemberDTO;
import com.rim.util.Session;
import com.rim.view.BankView;

public class MemberController {

	Scanner sc;
	MemberDAO dao;
	MemberDTO dto;
	BankView v;
	BankInput i;

	public MemberController() {
		// TODO Auto-generated constructor stub
		sc = new Scanner(System.in);
		dao = new MemberDAO();
		v = new BankView();
		i = new BankInput();
	}

	public void start() throws Exception {
		
		while(true)
		if (Session.member != null) {
			v.view("1.My Page / 2.로그아웃 / 3.계좌관리");
			int select = sc.nextInt();
			
			if(select==1) {
				v.view();
			}else if(select==2) {
				Session.member=null;
			}else if(select==3) {
				new AccountController().start();
				
			}
			
		} else {
			v.view("1.회원가입 / 2.로그인 / 3.종료");
			int select = sc.nextInt();
			String str = null;

			if (select == 1) {
				str = i.memberInput();
				v.view(str);
			} else if (select == 2) {
				str = i.login();
				v.view(str);
			}else
				break;
		}
	}

}
