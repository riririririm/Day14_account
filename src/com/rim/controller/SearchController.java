package com.rim.controller;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Scanner;

import com.rim.bankbook.BankbookDAO;
import com.rim.bankbook.BankbookDTO;
import com.rim.history.HistoryDAO;
import com.rim.history.HistoryDTO;
import com.rim.history.HistoryDTO2;
import com.rim.input.BankInput;
import com.rim.util.DBConnector;
import com.rim.view.BankView;

public class SearchController {

	BankView v;
	Scanner sc;
	BankInput bi;
	BankbookDAO bDao;
	BankbookDTO bDto;
	HistoryDAO hDao;
	HistoryDTO hDto;
	HistoryDTO2 hDto2;
	Connection conn;

	public SearchController() {
		v = new BankView();
		sc = new Scanner(System.in);
		bi = new BankInput();
		bDao = new BankbookDAO();
		bDto = new BankbookDTO();
		hDao = new HistoryDAO();
		hDto = new HistoryDTO();
		hDto2 = new HistoryDTO2();
	}

	public void searchSelect(String account) throws Exception {
		conn = DBConnector.getConnector();
		ArrayList<HistoryDTO> arr;
		ArrayList<String> date;
		v.view("1.전체조회 || 2.입금조회 || 3.출금조회  || 4.기간으로 조회  || 5.n개월 조회");
		int select = sc.nextInt();

		switch (select) {
		case 1:
			bDto = bDao.select(account, conn);
			arr = hDao.select(account, conn);
			conn.close();
			if (!bDto.equals(null) && !arr.equals(null)) {
				v.view(bDto);
				v.view(arr);
			}else
				v.view("정보가 없음");
			break;
		case 2:
			arr = hDao.select(account, 1, conn);
			v.view(arr);
			break;
		case 3:
			arr = hDao.select(account, 0, conn);
			v.view(arr);
			break;
		case 4:
			date = bi.dateSearchInput();
			arr = hDao.select(account, date.get(0), date.get(1), conn);
			v.view(arr);
		case 5:
			int month = bi.monthInput();
			ArrayList<HistoryDTO2> arr2 = hDao.selectMonth(account, month, conn);
			if(arr2.size()>0)
				v.view2(arr2);
			else
				v.view("정보가 없습니다");
			break;
		default:
			v.view("1~4중 선택하세요");
		}

	}
}
