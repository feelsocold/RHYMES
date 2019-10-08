<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- jar 파일 두개 추가, 링크 추가 -->
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<!-- jQuery -->
<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
<!-- payment.js -->
<script type="text/javascript" src="<%=request.getContextPath() %>/js/payment/payment.js"></script>
<!-- iamport.payment.js -->
<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>

<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

</head>
<body>





<div class="divfirst" align="left">
<div class="divback">
<h1>주문서</h1>
<h6 style="padding: 10px 10px 10px 10px;">주문하실 상품명 및 수량을 정확하게 확인해 주세요</h6>
</div>
<br><br><br><br><br><br>





<%-- 상품번호 : ${b.stock_seq }, 수량 : ${b.p_quantity } --%>
<div class="divback">
<h4>상품 정보</h4>
<table>
<tr style="border-bottom: 1px solid #dbdbdb;">
	<td colspan="2" align="center" width="50%">상품 정보</td>
	<td width="20%" align="center">상품 금액</td>
</tr>

<%-- <c:forEach var="b" items="${bOlist }" varStatus="i"> --%>
<tr>
	<td rowspan="2">상품 이미지</td>
	<td width="50%" align="left">[돌쇠]무항생제 한우사골 (고기)곰탕</td>
	<td rowspan="2" width="10%" align="center">11,600</td>
</tr>
<tr>
	<td align="left">1개/개 당 11,600원</td>
</tr>
<%-- </c:forEach> --%>

</table>
</div>
<br><br><br><br><br><br>





<div class="divback">
<h4>주문자 정보</h4>
<table>
<!--
-->
<tr>
	<th>휴대폰 번호</th>
	<td>
	<input type="text" id="to" placeholder="전화번호 입력">   <!-- 인증번호 받을사람 휴대폰 번호 -->
	<input type="button" id="oneselfConfirmBtn" value="본인인증">
	&nbsp;비회원으로 구매를 원하시면, 본인인증이 필요합니다.<br>
	
	<input type="text" id="userNum" placeholder="인증번호 입력">   <!-- 인증번호 입력창 -->
	<input type="button" id="enterBtn" value="확인">

	<!-- <input type="text" name="text" id="text"> -->   <!-- 인증번호를 히든으로 저장해서 보낸다 -->

	</td>
</tr>
<tr>
	<th>보내는 분 *</th>
	<td><input type="text" size="26" id="sendid"></td>
</tr>
<tr>
	<th>휴대폰 *</th>
	<td><input type="text" size="5" id="sendphone1">&nbsp;-&nbsp;
	<input type="text" size="5" id="sendphone2">&nbsp;-&nbsp;
	<input type="text" size="5" id="sendphone3"></td>
</tr><tr>
	<th>이메일 *</th>
	<td><input type="text" size="26"></td>
</tr><tr>
	<td></td>
	<td>이메일을 통해 주문처리과정을 보내드립니다.<br>이메일 주소란에는 반드시 수신가능한 이메일 주소를 입력해 주세요.</td>
</tr>
</table>
</div>
<br><br><br><br><br><br>




<div class="divback">
<h4>배송 정보</h4>
<table>
<tr>
	<th>주소 *</th>
	<td>새 배송지 추가 <input type="radio" onclick="sample4_execDaumPostcode()">
	&nbsp;&nbsp;&nbsp;&nbsp;위(주문자) 정보와 같음 <input type="radio" name="address" id="oldaddress"></td>
</tr>
<tr>
	<th></th>
	<td><input type="text" size="20" id="sample4_postcode" placeholder="우편번호">
	<input type="button" onclick="sample4_execDaumPostcode()" value="우편번호 찾기"><br>
	<input type="text" size="40" id="sample4_roadAddress" placeholder="도로명주소">
	<input type="text" size="40" id="sample4_jibunAddress" placeholder="지번주소"><br>
	<input type="text" size="40" id="sample4_detailAddress" placeholder="상세주소">
	<input type="text" size="40" id="sample4_extraAddress" placeholder="참고항목">
	</td>
</tr>
<tr>
	<th>수령인 이름 *</th>
	<td><input type="text" size="26" id="receiveid"></td>
</tr>
<tr>
	<th>휴대폰 *</th>
	<td><input type="text" size="5" id="receivephone1">&nbsp;-&nbsp;
	<input type="text" size="5" id="receivephone2">&nbsp;-&nbsp;
	<input type="text" size="5" id="receivephone3"></td>
</tr>
<tr>
	<th>배송 요청사항</th>
	<td><textarea rows="3" cols="70"></textarea>0지/50자</td>
</tr>
</table>
</div>
<br><br><br><br><br><br>





<!-- 로그인 했을때만 보이기 -->
<div class="divback">
<h4>쿠폰 적립금</h4>
<table>
<tr>
	<th rowspan="2">쿠폰 적용</th>
	<td>
	<select>
	<option>쿠폰 적용 안함</option>
	<option>사용할 쿠폰</option>
	</select>
	</td>
</tr>
<tr>
	<td>(보유쿠폰 : 1개)</td>
</tr>
<tr>
	<th>적립금 적용</th>
	<td>사용 가능한 적립금이 없습니다</td>
</tr>
</table>
</div>
<br><br><br><br><br><br>





<div class="divback">
<h4>결제 수단</h4>
<table>
<tr style="background-color: #fafafa">
	<th>결제 수단 선택</th>
	<td>
	신용카드 <input type="radio" name="payment" value="card">&nbsp;&nbsp;&nbsp;&nbsp;
	무통장입금 <input type="radio" name="payment" value="vbank">&nbsp;&nbsp;&nbsp;&nbsp;
	카카오페이 <input type="radio" name="payment" value="kakaopay">&nbsp;&nbsp;&nbsp;&nbsp;
	실시간 계좌이체 <input type="radio" name="payment" value="trans">&nbsp;&nbsp;&nbsp;&nbsp;
	휴대요금 결제 <input type="radio" name="payment" value="phone">
	</td>
</tr>
<tr>
	<td></td>
	<td></td>
</tr>
<tr>
	<th>결제하실 금액</th>
	<td>49,900원</td>
</tr>
</table><br>
</div>
<input type="button" id="paymentBtn" value="결제하기" onclick="paymens()"><br><br>
</div>



</body>
</html>