package com.rhymes.app.payment.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rhymes.app.member.model.P_MemberDTO;
import com.rhymes.app.member.model.mypage.MemberCouponDTO;
import com.rhymes.app.payment.model.OrderDTO;
import com.rhymes.app.payment.model.PaymentAfDTO;
import com.rhymes.app.payment.model.PaymentDTO;
import com.rhymes.app.payment.model.PaymentDetailsDTO;
import com.rhymes.app.payment.model.PaymentParamDTO;
import com.rhymes.app.payment.service.PaymentService;
import com.rhymes.app.payment.util.Coolsms;
import com.rhymes.app.payment.util.PaymentEmail;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class PaymentController {

	@Autowired
	private PaymentService PaymentService;

	// 단일제품 구매
	@RequestMapping(value = "/payment", method = { RequestMethod.POST, RequestMethod.GET })
	public String payment(Model model, String stock_seq, String p_quantity, Principal pcp) throws Exception {
		
		// 회원 비회원 구분
		String userid = pcp.getName();

		OrderDTO order_dto = new OrderDTO();
		order_dto.setStock_seq(Integer.parseInt(stock_seq));

		List<OrderDTO> basketList = new ArrayList<OrderDTO>();		
		PaymentParamDTO payment_param = new PaymentParamDTO();
		

		// db 상품 정보 가져오기 + 주문수량 + 구매회원 id
		basketList.add(PaymentService.getOrder(order_dto, Integer.parseInt(p_quantity), userid));

		// DB 적립금, 쿠폰 개수 가져오기
		payment_param = PaymentService.getPointAndCountCoupon(userid, basketList);

		
		// 총 상품 금액, 유효 쿠폰 개수, 유효 적립금, 배송비, 장바구니로 왔는지 구분자(장바구니면 1)
		payment_param = new PaymentParamDTO(payment_param.getProduct_price(),
											payment_param.getCoupon_count(),
											payment_param.getPoint_amount(),
											payment_param.getDelivery_price(),
											0);
		
		// 회원이면 주문자 정보에 자동 입력하기 위해서
		if(userid != null) {
			log.warn("123");
			P_MemberDTO p_mem = PaymentService.getMemberInfo(userid);
			model.addAttribute("p_mem", p_mem);
		}
		
		model.addAttribute("basketList", basketList);
		model.addAttribute("payment_param", payment_param);

		return "/payment/payment";
	}

	// 장바구니 리스트 구매
	@RequestMapping("/payment/basketOrder")
	public String basketOrder(Model model, String blist_stockseq, String blist_pQuantity, Principal pcp) throws Exception {

		String userid = pcp.getName();
		// 장바구니에서 문자열로 데이터를 가져왔다
		// 예) 신발/청바지/티셔츠			// blist_stockseq : 주문한 상품의 재고번호
		// 예) 2/5/1					// blist_pQuantity : 주문 수량
		

		
		// 재고번호와 수량을 구분자인 /를 기준으로 배열로 바꿔서 리스트에 넣기
		// 매개변수로 받은 데이터를 /를 구분자로 자르면 데이터의 개수가 나온다
		String[] _stockseq_arr = blist_stockseq.split("/");
		int[] stockseq_arr = Arrays.stream(_stockseq_arr).mapToInt(Integer::parseInt).toArray();
		String[] _pQuantity_arr = blist_pQuantity.split("/");
		int[] pQuantity_arr = Arrays.stream(_pQuantity_arr).mapToInt(Integer::parseInt).toArray();

		
		
		// 장바구니에서 결제페이지로 데이터를 가져갈 리스트
		// sqArr의 길이만큼 즉, 데이터의 개수만큼 for문을 돌린다
		// 예) 신발/청바지/티셔츠			라면 데이터의 개수는 3이다
		List<OrderDTO> basketList = new ArrayList<OrderDTO>();

		for (int i = 0; i < stockseq_arr.length; i++) {
			OrderDTO order_dto = new OrderDTO();
		
			// 데이터를 가져오기 위해서 주문한 상품의 재고번호를 dto에 담고 매개변수로 DB에 보낸다
			order_dto.setStock_seq(stockseq_arr[i]);

			// DB 주문한 상품의 정보 가져오기 장바구니로 데이터를 가져갈 리스트에 넣는다
			// db 상품 정보 가져오기 + 주문수량 + 구매회원 id
			basketList.add(PaymentService.getOrder(order_dto, pQuantity_arr[i], pcp.getName()));
		}
			


		// 총금액 계산
		int product_price = 0;
		for (OrderDTO dto : basketList) { product_price += dto.getP_price() * dto.getQuantity(); }

		// 총금액이 10,000원 미만이면 배송비 = 3,000원이다
		int delivery_price = 0;
		if(product_price < 10000) { delivery_price = 3000; }
		
		// DB 적립금, 쿠폰 개수 가져오기
		PaymentParamDTO dto = PaymentService.getPointAndCountCoupon(userid, basketList);

		// 총 상품 금액, 유효 쿠폰 개수, 유효 적립금, 배송비, 장바구니로 왔는지 구분자(장바구니면 1)
		PaymentParamDTO payment_param = new PaymentParamDTO(product_price, dto.getCoupon_count(), dto.getPoint_amount(), delivery_price, 1);
		

		
		// 회원이면 주문자 정보에 자동 입력하기 위해서
		if(userid != null) {
			P_MemberDTO p_mem = PaymentService.getMemberInfo(userid);
			model.addAttribute("p_mem", p_mem);
			log.info("123");
			log.warn("p_mem : " + p_mem.toString());
			//회원정보 가져오기 다시
		}
		
		model.addAttribute("basketList", basketList);
		model.addAttribute("payment_param", payment_param);

		return "/payment/payment";
	}

	// 결제 후 결제완료창으로 이동
	@RequestMapping("/paymentAf")
	public String paymentAf(Model model, PaymentDTO dto, PaymentAfDTO dtoAf, Principal pcp) {
		dto.setUserid( pcp.getName() );
		
		// 상품 재고번호와 수량을 ,를 기준으로 가져온다
		String[] stock_seq = dtoAf.getStock_seq().split(",");
		String[] quantity = dtoAf.getQuantity().split(",");
		
		for (int i = 0; i < dtoAf.getStock_quantity(); i++) {

			// 주문한 상품수량만큼 재고수량에서 차감한다
			boolean b = PaymentService.disc_stock_quantity(stock_seq[i], quantity[i]);
			
			// 상품의 가격
			int price = PaymentService.getPrice(Integer.parseInt(stock_seq[i]));
			
			// db에 결제 디테일을 저장한다(후기 여부는 false)
			PaymentDetailsDTO dtoDt = new PaymentDetailsDTO(Integer.parseInt(stock_seq[i]), Integer.parseInt(quantity[i]), price, dto.getPayment_code());
			
			boolean b3 = PaymentService.payment_detail_save(dtoDt);
		}
		
		// 적립금 차감한다
		boolean b = PaymentService.disc_point(dto);

		// rhy_payment db에 결제내역을 저장한다
		boolean b2 = PaymentService.payment_save(dto);
		
		// rhy_payment_after db에 결제내역을 저장한다
		boolean b3 = PaymentService.payment_after(dtoAf);

		// 사용한 쿠폰을 사용으로 변환
		boolean b4 = PaymentService.update_isused_coupon(dto);

		// 배송내역 저장 -- 운송장번호 어떻게?
		boolean b5 = PaymentService.delivery_save(dto);

		// 일반 결제말고 미니 장바구니와 장바구니 페이지에서 갈때만 내역 제거
		// rhy_store_basket 에서 구매한 id를 삭제
		if(dtoAf.getBasket_del() == 1) {
			boolean b6 = PaymentService.delete_basket(pcp.getName());
		}

		// 이메일로 결제내역을 보낸다
		PaymentEmail mail = new PaymentEmail();
		try {
			//mail.PaymentEmailSend(dto);
		} catch (Exception e) {
			e.printStackTrace();
		}		
		
		model.addAttribute("dto", dto);
		model.addAttribute("dtoAf", dtoAf);

		return "/payment/paymentAf";
	}

	// 결제 페이지에서 비회원으로 주문할때 본인인증
	@ResponseBody
	@RequestMapping(value = "/sendsms", method = RequestMethod.GET)
	public String sendSms(HttpServletRequest request) throws Exception {
		log.warn("받는번호 : " + (String) request.getParameter("to"));
		log.warn("인증번호 : " + (String) request.getParameter("text"));

		String api_key = "NCS4ZFTWNHGBIKUM";
		String api_secret = "TPH57XEIXFFDAUXJV3EBNDDS633YNFG2";

		Coolsms coolsms = new Coolsms(api_key, api_secret);

		HashMap<String, String> set = new HashMap<String, String>();

		set.put("to", (String) request.getParameter("to")); // 받는 사람
		set.put("from", "01083256825"); // 발신번호
		set.put("text", "안녕하세요 인증번호는 [" + (String) request.getParameter("text") + "]입니다"); // 문자내용
		set.put("type", "sms"); // 문자 타입

		log.warn(set+"");

		JSONObject result = coolsms.send(set); // 보내기&전송결과받기

		if ((boolean) result.get("status") == true) {
			// 메시지 보내기 성공 및 전송결과 출력
			log.warn("성공");
			log.warn(result.get("group_id")+"");			// 그룹아이디
			log.warn(result.get("result_code")+"");			// 결과코드
			log.warn(result.get("result_message")+"");		// 결과 메시지
			log.warn(result.get("success_count")+"");		// 메시지아이디
			log.warn(result.get("error_count")+"");			// 여러개 보낼시 오류난 메시지 수
		} else {
			// 메시지 보내기 실패
			log.warn("실패");
			log.warn(result.get("code")+"");				// REST API 에러코드
			log.warn(result.get("message")+"");				// 에러메시지
		}

		return "success";
	}
	
	// 결제페이지에서 window.open으로 쿠폰 가져오기
	@RequestMapping(value = "/payment_coupon", method = RequestMethod.GET)
	public String payment_coupon(Model model, Principal pcp, String product_price, String delivery_price, String input_disc_point) {
		String userid = pcp.getName();
		
		// DB 유효 쿠폰 전부 가져오기
		List<MemberCouponDTO> coupon_code = PaymentService.getAllCoupon(userid);

		// 배송비
		if(!delivery_price.equals("0")) { product_price = (Integer.parseInt(product_price) + 3000) + ""; }

		model.addAttribute("coupon_code", coupon_code);
		model.addAttribute("input_disc_point", input_disc_point);
		model.addAttribute("product_price", product_price);
		
		return "/payment/coupon";
	}

}
