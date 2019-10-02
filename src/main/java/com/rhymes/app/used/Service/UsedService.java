package com.rhymes.app.used.Service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.rhymes.app.member.model.MemberDTO;
import com.rhymes.app.member.model.P_MemberDTO;
import com.rhymes.app.used.model.CommentsDto;
import com.rhymes.app.used.model.ProductsDto;

public interface UsedService {
	public boolean UsedWrite(ProductsDto dto);

	public List<ProductsDto> getUsedList();

	public ProductsDto getUsedDetail(int seq);

	public int getSellerCount(String parameter);

	public P_MemberDTO getMemberDto(String userid);

	public boolean setSellerMember(P_MemberDTO p_MemberDTO);

	public boolean setSellerMember(String s_id);

	public boolean addlikes(Map<String, Object> map);

	public boolean getlikes(Map<String, Object> map);

	public int getboardlikes(int seq);

	public boolean deletelikes(Map<String, Object> map);

	public List<CommentsDto> getComments(int seq);

}