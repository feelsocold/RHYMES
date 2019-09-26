package com.rhymes.app.member.dao.impl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rhymes.app.member.dao.MemberDAO;
import com.rhymes.app.member.model.AuthoritiesDTO;
import com.rhymes.app.member.model.MemberDTO;
import com.rhymes.app.member.model.P_MemberDTO;
import com.rhymes.app.member.model.SellerCRnumDTO;
import com.rhymes.app.member.model.SellerDTO;

@Repository
public class MemberDAOImpl implements MemberDAO {

	@Autowired
	private SqlSession sqlSession;
	
	private String ns = "member.";
	
	@Override
	public MemberDTO getOneMemberByMemberId(String id) {
		return sqlSession.selectOne( ns + "getOneMemberByMemberId", id);
	}
	
	// id체크
		@Override
		public int getIDCheck(MemberDTO mem) {
			return sqlSession.selectOne(ns+"getIDCheck", mem);
		}

		// 회원가입
		@Override
		public boolean getAddmem(MemberDTO mem) {
			int n = sqlSession.insert(ns+"getAddmem", mem);
			return n>0?true:false;			
		}
		
		@Override
		public void getPAddmem(P_MemberDTO pmem) {
			sqlSession.insert(ns+"getPAddmem", pmem);		
		}
		
		@Override
		public void getAuthAddmem(AuthoritiesDTO amem) {
			sqlSession.insert(ns+"getAuthAddmem", amem);			
		}

		// 사업자번호 체크
		@Override
		public String getCRCheck(SellerCRnumDTO crdto) {
			return sqlSession.selectOne(ns+"getCRCheck", crdto);
		}

		// 사업자 추가 회원가입
		@Override
		public void getAddSeller_C(SellerDTO sel) {
			sqlSession.insert(ns+"getAddSeller_C", sel);
		}

		@Override
		public int getCRCYN(SellerDTO sdto) {
			return sqlSession.selectOne(ns+"getCRCYN", sdto);
		}
	
}
