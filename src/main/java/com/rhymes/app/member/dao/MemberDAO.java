package com.rhymes.app.member.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.rhymes.app.member.model.AuthoritiesDTO;
import com.rhymes.app.member.model.MemBean;
import com.rhymes.app.member.model.MemberDTO;
import com.rhymes.app.member.model.P_MemberDTO;
import com.rhymes.app.member.model.SellerBean;
import com.rhymes.app.member.model.SellerCRnumDTO;
import com.rhymes.app.member.model.SellerDTO;

public interface MemberDAO {
	
	public MemberDTO getOneMemberByMemberId(String id);
	
	// 아이디 체크
	public int getIDCheck(MemberDTO mem);
	
	// 일반 회원가입 insert
	public boolean getAddmem(MemberDTO mem);		// 공통
	public void getPAddmem(P_MemberDTO pmem);		// 추가
	public void getAuthAddmem(AuthoritiesDTO amem);	// 권한
	
	// 사업자번호 체크
	public String getCRCheck(SellerCRnumDTO crdto);
	
	// 라임즈에 사업자번호 등록되어있는지 확인
	public int getCRCYN(SellerDTO sdto);
	
	// 사업자 회원가입
	public void getAddSeller_C(SellerDTO sel);	// 사업자 추가
	
	// id찾기
	public String getFindID_E(MemBean mbean);
	// 비밀번호 불러오기
	public String getFindID_P(MemberDTO mem);
	
	// 비밀번호 찾기
	public String getusertel(P_MemberDTO pmem);
	
	// 비밀번호 재설정
	public void getuserpwreset(MemberDTO mem);
	
	// 사업자 id찾기
	public SellerDTO getfindid_seller(SellerDTO sdto);
	
	// 사업자 pw찾기
	public boolean getfindpw_seller(SellerDTO sdto);
	
}
