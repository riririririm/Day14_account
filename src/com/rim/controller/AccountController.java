package com.rim.controller;

import java.sql.Connection;
import java.util.Scanner;

import com.rim.bankbook.BankbookDAO;
import com.rim.bankbook.BankbookDTO;
import com.rim.history.HistoryDAO;
import com.rim.history.HistoryDTO;
import com.rim.input.BankInput;
import com.rim.member.MemberDAO;
import com.rim.member.MemberDTO;
import com.rim.util.DBConnector;
import com.rim.util.Session;
import com.rim.view.BankView;

public class AccountController {

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

	public AccountController() {
	
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
			System.out.println("1.통장개설/ 2.입출금 / 3.조회 / 4.종료");
			int select = sc.nextInt();


			 if (select == 1) {
				bDao = new BankbookDAO();
				bDto = new BankbookDTO();
				conn = DBConnector.getConnector();

				bDto = i.bankbookInput();
				result = bDao.insert(bDto, conn);
				if (result > 0)
					v.view("통장개설 성공");
				else
					v.view("통장개설 실패");

			} else if (select == 2) {
				hDto = new HistoryDTO();
				hDao = new HistoryDAO();

				try {
					conn = DBConnector.getConnector();
					conn.setAutoCommit(false);

					bDao = new BankbookDAO();
					bDto = new BankbookDTO();
					hDto = i.ioInput();
					bDto = bDao.select(hDto.getAccount(), conn);
					result=0;
					
					if(hDto.getIo()==0 && bDto.getBalance()-hDto.getMoney()<0) {
						//출금시 출금하려는 액수가 잔고보다 많으면
						v.view("잔고가 부족합니다"); //result는 그대로 0 -> 아래에서 exception 발생
					}else
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
					//e.printStackTrace();
					v.view("입출금실패");
					conn.rollback();
				}

			} else if (select == 3) {
				//조회할 계좌 입력받고
				String account = i.accountInput();
				//어떤 조회를 할지 고르게하기 -> searchController로 이동
				controller.searchSelect(account);

			} else if (select == 4) {
				conn.close(); //DB연결 끊기
				v.view("프로그램 종료");
				break;
			} else
				System.out.println("1~4번중에 고르세요.");
		} // end of while
	}

}
