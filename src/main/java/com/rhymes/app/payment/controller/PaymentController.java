package com.rhymes.app.payment.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rhymes.app.payment.util.Coolsms;

@Controller
@RequestMapping("/Rhymes")
public class PaymentController {
	
	// 처음
	@GetMapping("/daraewelcome")
	public String welcome() {
		System.out.println("daraewelcome");
		
		return "/payment/welcome";
	}
	
	// 결제페이지로 이동
	@GetMapping("/payment")
	public String payment(Model model) {
		System.out.println("daraepayment");
		
		if(true) {
			// 로그인 되어있으면 결제 페이지로 이동
			return "/payment/payment";
		}else {
			// 로그인 안되어있으면 로그인창으로 이동
			return "/payment/nomembership";
		}
		
	}
	
	// 결제 완료
	@GetMapping("/paymentAf")
	public String paymentAf(Model model) {
		System.out.println("daraepaymentAf");

		return "/payment/paymentAf";
	}

	// 주소찾기 팝업창 띄우기
	@GetMapping("/addresssearch")
	public String addresssearch(Model model) {
		System.out.println("daraeaddresssearch");
		
		return "/payment/addresssearch";
	}
	
	// 본인인증
	@ResponseBody
	@RequestMapping(value = "/sendsms", method = RequestMethod.GET)
	public String sendSms(HttpServletRequest request) throws Exception {
		System.out.println("본인인증");
		System.out.println("받는번호 : " + (String) request.getParameter("to"));
		System.out.println("인증번호 : " + (String) request.getParameter("text"));

		String api_key = "NCS4ZFTWNHGBIKUM";
		String api_secret = "TPH57XEIXFFDAUXJV3EBNDDS633YNFG2";

		Coolsms coolsms = new Coolsms(api_key, api_secret);

		HashMap<String, String> set = new HashMap<String, String>();

		set.put("to", (String) request.getParameter("to")); // 받는 사람
		set.put("from", "01083256825"); // 발신번호
		set.put("text", "안녕하세요 인증번호는 [" + (String) request.getParameter("text") + "]입니다"); // 문자내용
		set.put("type", "sms"); // 문자 타입
		
		System.out.println(set);
		
		JSONObject result = coolsms.send(set); // 보내기&전송결과받기

		if ((boolean) result.get("status") == true) {
			// 메시지 보내기 성공 및 전송결과 출력
			System.out.println("성공");
			System.out.println(result.get("group_id")); // 그룹아이디
			System.out.println(result.get("result_code")); // 결과코드
			System.out.println(result.get("result_message")); // 결과 메시지
			System.out.println(result.get("success_count")); // 메시지아이디
			System.out.println(result.get("error_count")); // 여러개 보낼시 오류난 메시지 수
		} else {
			// 메시지 보내기 실패
			System.out.println("실패");
			System.out.println(result.get("code")); // REST API 에러코드
			System.out.println(result.get("message")); // 에러메시지
		}

		return "success";
	}

}





