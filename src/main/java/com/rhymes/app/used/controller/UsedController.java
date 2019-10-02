package com.rhymes.app.used.controller;


import java.io.FileOutputStream;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.rhymes.app.member.model.P_MemberDTO;
import com.rhymes.app.used.Service.UsedService;
import com.rhymes.app.used.model.CommentsDto;
import com.rhymes.app.used.model.ProductsDto;
import com.rhymes.app.used.util.Coolsms;


@RequestMapping("/used/*")
@Controller
public class UsedController {

	@Autowired UsedService usedService;
	
	
	@GetMapping("/hello") 
	public String test(HttpServletRequest req) {
		// 아직 구축이 제대로 안되서 임의로 로그인 아이디 설정해 놓음
		P_MemberDTO Mdto = usedService.getMemberDto("sujin123");
		Mdto.setUserid("sujin123");
		System.out.println(Mdto.toString());
		
		req.getSession().setAttribute("login", Mdto);
		return "used/test";
	}
	
	@GetMapping("usedlist")
	public String usedlist(Model model) {
		
		List<ProductsDto> list = usedService.getUsedList();
		
		model.addAttribute("list", list);
		
		return "usedlist.tiles";
	}
	
	@GetMapping("useddetail")
	public String useddetail(Model model,int seq, HttpServletRequest req) {
		
		ProductsDto dto = usedService.getUsedDetail(seq);	// 게시글 정보 받아오기
		dto.setLikes( usedService.getboardlikes(seq));		// 게시글의 좋아요 개수 설정
		
		String id = "";
		
		 if( ((P_MemberDTO)req.getSession().getAttribute("login"))  == null	) {
			 id = "";
		 }  
		 else {		// 세션에 로그인 정보가 있을 때 
			 
		    id = ((P_MemberDTO)req.getSession().getAttribute("login")).getUserid();
			 
			Map<String,Object> map = new HashMap<String, Object>();
			 
			map.put("bno", dto.getSeq());
			map.put("mname", id);
			 
			 boolean b = usedService.getlikes(map);

			 if(b) {	// 회원의 좋아요 여부 확인
				 ((P_MemberDTO)req.getSession().getAttribute("login")).setIslike(true);
			 } else {
				 ((P_MemberDTO)req.getSession().getAttribute("login")).setIslike(false);
			 }
		 }

		String str = dto.getPhoto_sys();
		String arr[] = str.split(",");
		dto.setPhoto_list(arr);

		model.addAttribute("dto", dto);
		
		return "useddetail.tiles";
	
		
	}
	
	@GetMapping(value="/getCommentsList",produces = "application/json; charset=utf8")
	@ResponseBody
	public ResponseEntity getCommentsList(int seq) throws Exception{
		
		HttpHeaders responseHeaders = new HttpHeaders();
		ArrayList<HashMap> hmlist = new ArrayList<HashMap>();
		
		List<CommentsDto> clist = usedService.getComments(seq);
		
		if(clist.size() > 0) {
			for(int i = 0 ; i < clist.size() ; i++) {
				HashMap hm = new HashMap();
				hm.put("seq", clist.get(i).getSeq());
				hm.put("comments", clist.get(i).getComments());
				hm.put("id", clist.get(i).getId());
				hm.put("date", clist.get(i).getDate());
				
				hmlist.add(hm);
			}
		}
		
		JSONArray json = new JSONArray(hmlist);
		
		
		
		return new ResponseEntity(json.toString(), responseHeaders , HttpStatus.CREATED);
	};
	
	@GetMapping("popup")
	public String popup(Principal prc) {
		
		return "popup";
	}
	
	@PostMapping("popupAf")
	public void popupAf(String s_id, @RequestParam(required = false, defaultValue="") String postcode,
			@RequestParam(required = false, defaultValue="") String address, @RequestParam(required = false, defaultValue="") String detailaddress,
			HttpServletRequest req) {
		
		if(postcode.equals("") || postcode == null || address.equals("") || address == null || 
				detailaddress.equals("") || detailaddress == null) {
			boolean b  = usedService.setSellerMember(s_id);
			if(b) {
				System.out.println("등록 완료");
			} else {
				System.out.println("등록 실패");
			}
		
		} else {
			
			((P_MemberDTO)req.getSession().getAttribute("login")).setPostcode(postcode);
			((P_MemberDTO)req.getSession().getAttribute("login")).setAddress(address);
			((P_MemberDTO)req.getSession().getAttribute("login")).setDetailAddress(detailaddress);		// 세션에 담겨있는 정보를 수정해 줌..(일단 작업하기위해서)
			
			boolean b = usedService.setSellerMember((P_MemberDTO)req.getSession().getAttribute("login"));		// 오버라이딩해서 매개변수를 다르게 설정해주었다 (복습)
			
			if(b) {
				System.out.println("등록 완료");
			} else {
				System.out.println("등록 실패");
			}
		}
		
	}
	
	@GetMapping("usedwrite")
	public String usedwrite(HttpServletRequest req, Model model) {
		
		return "usedwrite.tiles";
	}
	
	@RequestMapping(value="usedwriteAf", method = RequestMethod.POST)
	public String usedwriteAf(ProductsDto Pdto, MultipartHttpServletRequest mfreq,
			HttpServletRequest req) throws Exception {
		
		ProductsDto dto = new ProductsDto(0,
				Pdto.getS_id(),
				Pdto.getCategory(),
				Pdto.getTitle(),
				Pdto.getContent(),
				Pdto.getPrice(),
				Pdto.getQuantity(),
				Pdto.getPlace(),
				Pdto.getPhoto(),
				Pdto.getPhoto_sys(),
				Pdto.getDivision(),
				Pdto.getLikes());

		List<MultipartFile> list = mfreq.getFiles("files");

		int size = list.size();
		
		Iterator<String> files = mfreq.getFileNames();
		
		MultipartFile mpf = mfreq.getFile(files.next());
		
		String path = req.getServletContext().getRealPath("/upload");
		
		
		String photo = "";
		String photo_sys = "";
         
		if(list != null && size > 0) {
			for(MultipartFile mf : list) {
				String originFileName = mf.getOriginalFilename();
	            String systemFileName = System.currentTimeMillis() + originFileName;	            
   
	            photo += originFileName + ",";
	            photo_sys += systemFileName + ",";
	            
	            long fileSize = mf.getSize();
      
				FileOutputStream fs = new FileOutputStream(path + "/" + systemFileName);
				
				System.out.println(path); // 업로드 경로
				
				fs.write(mf.getBytes());
				fs.close();

			}
		}
		dto.setPhoto(photo);
		dto.setPhoto_sys(photo_sys);
		
		boolean b = usedService.UsedWrite(dto);
		
		if(b) {
			System.out.println("성공~~");
		} else {
			System.out.println("공부하자..");
		}
		
		return "redirect:/used/hello";		

	}
	
	@GetMapping(value="/addlikes")
	@ResponseBody
		public String addlikes(int bno, String mname) {
		
		Map<String,Object> map = new HashMap<String, Object>();
		
		map.put("bno", bno);
		map.put("mname", mname);
		
		 boolean b = usedService.getlikes(map);
		 int num;	// ajax 리턴 변수
		 if(b) {	// 회원의 좋아요 여부 확인
			 usedService.deletelikes(map);
			 num = 0;
		 } else {
			 usedService.addlikes(map);
			 num = 1;
		 }

		return num+"";
	}
		
	@GetMapping(value="/likeCount")
	@ResponseBody
		public String likeCount(int bno) {
		
		int count = usedService.getboardlikes(bno);
		return count+"";
	}
	
	@GetMapping(value = "/SendSms")
	@ResponseBody
	  public String sendSms(HttpServletRequest request) throws Exception {
	
		int count = usedService.getSellerCount( (String)request.getParameter("id") );

		if(count <= 3) {

	    String api_key = "NCSVKDEE4KHSNBFN";
	    String api_secret = "KDPVGPJQJMGFBVB4BQPRZFSAHLQF9DLM";
	  
	    
	    Coolsms coolsms = new Coolsms(api_key, api_secret);
	    
	    HashMap<String, String> set = new HashMap<String, String>();

	    set.put("to", (String)request.getParameter("to")); // 받는사람
	    set.put("from", "01024943936"); // 보내는사람
	    set.put("text", "안녕하세요 인증번호는 ["+(String)request.getParameter("text")+"]입니다"); // 문자내용
	    set.put("type", "sms"); // 문자 타입

	    System.out.println(set);

	    JSONObject result = coolsms.send(set); // 보내기&전송결과받기
	    
	    
	    
	    if ((boolean)result.get("status") == true) {
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
	    
	    return "해당 번호로 문자를 발송하였습니다 인증횟수("+count+"/3)회";
	    
	}
	    
	    
	    else {
			return "인증한도 3회를 초과하였습니다 고객센터에 문의하세요";
		}
		
	    
	  }
}

