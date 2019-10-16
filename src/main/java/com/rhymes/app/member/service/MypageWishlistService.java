package com.rhymes.app.member.service;

import java.util.List;

import com.rhymes.app.member.model.mypage.MemberWishlistDTO;

public interface MypageWishlistService {

	/**매개변수로 받은 ID에 맞는 찜목록 리턴
	 * @param userid
	 * @return
	 */
	public List<MemberWishlistDTO> getWishlistById(String userid);	
	
	/**매개변수로 받은 ID와 p_seq에 맞는 찜 아이템 삭제
	 * @param dto
	 * @return
	 */
	public int deleteWishItemByIdAndP_Seq(MemberWishlistDTO dto);
}
