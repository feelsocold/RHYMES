package com.rhymes.app.member.model.mypage;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**마이페이지 - 위시리스트 아이템 정보를 담기위한 DTO
 * @author minhj
 *
 */
@Getter
@Setter
@ToString
public class MemberWishlistDTO {
	
	//상품디테일로 이동하기 위한 용도로 활용할 정보
	private int p_seq;		//상품고유번호
	private String c1_name;	//대분류
	private String c2_name;	//중분류
	private String c3_name;	//소분류
	
	
	//마이페이지 - 위시리스트에 보여주기 위한 정보
	private String p_name;	//상품이름
	private String p_title;	//상품 제목
	private int p_price;	//상품 가격
	private String photo1_file;	//상품썸네일
	
	public MemberWishlistDTO() { }

	public MemberWishlistDTO(int p_seq, String c1_name, String c2_name, String c3_name, String p_name
			, String p_title, int p_price, String photo1_file) {
		this.p_seq = p_seq;
		this.c1_name = c1_name;
		this.c2_name = c2_name;
		this.c3_name = c3_name;
		this.p_name = p_name;
		this.p_title = p_title;
		this.p_price = p_price;
		this.photo1_file = photo1_file;
	}	
}
