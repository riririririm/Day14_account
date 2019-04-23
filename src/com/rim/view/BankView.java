package com.rim.view;

import java.util.ArrayList;

import com.rim.bankbook.BankbookDTO;
import com.rim.history.HistoryDTO;

public class BankView {
	
	public void view(BankbookDTO dto) {
		System.out.println("[통장 정보]");
		System.out.println("회원ID: "+dto.getId());
		System.out.println("계좌번호: "+dto.getAccount());
		System.out.println("개설일: "+dto.getCreate_date());
		System.out.println("통장이름: "+dto.getAcc_name());
		System.out.println("잔고: "+dto.getBalance());
		System.out.println("--------------------------------------");
	}
	
	public void view(String str) {
		System.out.println(str);
	}

	public void view (HistoryDTO dto) {
		String str = null;
		
		System.out.println("계좌번호: "+dto.getAccount());
		System.out.println("거래날짜: "+dto.getIo_date());
		if(dto.getIo()==1)
			str="입금";
		else if(dto.getIo()==0)
			str="출금";
		System.out.println("입/출: "+str);
		System.out.println(str+"액: "+dto.getMoney());
		System.out.println("--------------------------------------");
	}
	
	
	public void view(ArrayList<HistoryDTO> arr) {
		for(HistoryDTO dto: arr)
			view(dto);
	}
}
