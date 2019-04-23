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

		while (true) {
			System.out.println("1.회원가입 / 2.통장개설/ 3.입출금 / 4.조회 / 5.종료");
			int select = sc.nextInt();

			if (select == 1) {
				mDao = new MemberDAO();
				mDto = new MemberDTO();
				conn = DBConnector.getConnector();

				mDto = i.memberInput();
				result = mDao.insert(mDto, conn);

				if (result > 0)
					v.view("회원가입 성공");
				else
					v.view("회원가입 실패");

			} else if (select == 2) {
				bDao = new BankbookDAO();
				bDto = new BankbookDTO();
				conn = DBConnector.getConnector();

				bDto = i.bankbookInput();
				result = bDao.insert(bDto, conn);
				if (result > 0)
					v.view("통장개설 성공");
				else
					v.view("통장개설 실패");

			} else if (select == 3) {
				hDto = new HistoryDTO();
				hDao = new HistoryDAO();

				try {
					conn = DBConnector.getConnector();
					conn.setAutoCommit(false);

					hDto = i.ioInput();
					result = hDao.insert(hDto, conn);

					if (result >= 1) {
						if (hDto.getIo() == 1) {
							// 입금
							str = "입금";
						} else if (hDto.getIo() == 0) {
							// 출금
							str = "출금";
						}
						v.view(str + " 성공");
					} else if (result < 1)
						throw new Exception();

					// 통장 잔고 업데이트
					bDao = new BankbookDAO();
					result = bDao.update(hDto, conn);

					if (result < 1)
						throw new Exception();
					
					conn.commit();

				} catch (Exception e) {
					v.view("입출금실패");
					conn.rollback();
				}

			} else if (select == 4) {
				String account = i.accountInput();
				controller.searchSelect(account);

			} else if (select == 5) {
				conn.close();
				v.view("프로그램 종료");
				break;
			} else
				System.out.println("1~5번중에 고르세요.");
		} // end of while
	}

}
