package com.rim.controller;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Scanner;

import com.rim.bankbook.BankbookDAO;
import com.rim.bankbook.BankbookDTO;
import com.rim.history.HistoryDAO;
import com.rim.history.HistoryDTO;
import com.rim.input.BankInput;
import com.rim.member.MemberDAO;
import com.rim.member.MemberDTO;
import com.rim.util.DBConnector;
import com.rim.view.BankView;

public class BankController {
	Scanner sc;
	BankView v;
	BankInput i;
	BankbookDTO bDto;
	BankbookDAO bDao;
	HistoryDAO hDao;
	HistoryDTO hDto;
	MemberDAO mDao;
	MemberDTO mDto;
	Connection conn;
	SearchController controller;

	public BankController() {
		// TODO Auto-generated constructor stub
		sc = new Scanner(System.in);
		v = new BankView();
		i = new BankInput();
		controller = new SearchController();
	}

	public void start() throws Exception {
		int result;
		String str = null;

		MemberController mc = new MemberController();
		mc.start();

	}

}
