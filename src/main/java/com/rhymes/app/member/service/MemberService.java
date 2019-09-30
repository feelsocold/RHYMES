package com.rhymes.app.member.service;

import com.rhymes.app.member.model.MemBean;
import com.rhymes.app.member.model.MemberDTO;
import com.rhymes.app.member.model.P_MemberDTO;
import com.rhymes.app.member.model.SellerBean;
import com.rhymes.app.member.model.SellerCRnumDTO;

public interface MemberService{
	
	// 아이디 체크
	public int getIDCheck(MemberDTO mem);
	
	// 일반회원가입 insert
	public void getAddmem(MemBean bean);
	
	// 사업자번호 체크
	public String getCRCheck(SellerCRnumDTO crdto);
	
	// 라임즈에 사업자번호 등록되어있는지 확인
	public int getCRCYN(SellerCRnumDTO crnum);
	
	// 사업자 회원가입 insert
	public void getAddSeller(SellerBean sellerbean, MemberDTO mem);
	
	// id찾기
	public String getFindID(MemBean mbean);
	
	// 비밀번호 찾기
	public String getusertel(P_MemberDTO pmem);
	
	// 비밀번호 재설정
	public void getuserpwreset(MemberDTO mem);
	
	
}
