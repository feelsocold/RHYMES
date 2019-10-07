<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<script type="text/javascript" src="//fast.fonts.net/jsapi/eb707d8d-32aa-4fee-b1c6-595a5dc30866.js"></script>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<title>장바구니</title>
</head>
<style>
@import url(//fonts.googleapis.com/earlyaccess/notosanskr.css);
body{
	font: Open Sans;
	min-height: 100%;
	box-sizing: border-box;
	height:100%;
	width:100%;
	
}
h2 {
  font: 100 40px Open Sans;
  text-align: center;
  margin: 50px 0px 22px 0px;
}
#cntProduct_wrap{
  font: 100 15px Open Sans;
  text-align: center;
  margin: 50px 0px 50px 0px;
  margin-top: 0px;
}
#mainContainer{
  position: absolute;
  top: 380px;
  bottom: 0;
  left: 0;
  right: 0;
  height:100%;
  width: 75%;
  background: white;
  margin: auto;
}
.listTable{
 float:left;
}
.priceTable{
 float:right;
}
#product-select-all{
 font-size:14px;
 border-top: 1px solid #DADCE0;
 width:70%;
 padding-top: 20px;
 padding-bottom:20px;
 float:left; 
}
.img_wrap{
 float:left;
 height:115px;
 width:115px;
 /* border: 2px solid green; */
}
.info_wrap{
/*  display: inline-block;
 box-sizing: border-box;
 */
  padding-left:20px;
  height:110px;
  width:500px;
  /* border:2px solid red */
  font-family:'Malgun Gothic'; 
  font-size: 13px;
  float:left;
  
}
.info2_warp{
  font-size: 13px;
  font-family:'Malgun Gothic'; 
}
.product-opt_basket{
border-top: 1px solid #DADCE0;
border-bottom: 1px solid #DADCE0;
padding-top: 15px;
padding-bottom: 15px;
width:70%;
height:115px;
float:left;
}
.pname_wrap{
font-weight: bolder;
}
a{
color:black;
}
.optionchange_wrap{
float:left;
width:160px;
height:110px;
/* border:2px solid blue; */
font-size: 14px;
}
.price_wrap{
color:#87CE00;
font-size: 14px;
float:left;
width:110px;
height:110px;
/* border:2px solid orange; */
}
.delete_wrap{
height:110px;
/* border:2px solid black; */

}
.delete_btn{
font-size: 20px;
font-weight: bold;
padding-left:75px;
cursor:pointer;

}
.sub2Container{
position:absolute;
border:1px solid #EAEAEA;
height:auto;
width:400px;
margin-left:30px;
top:0px;
left:1000px;
float:right;
padding-bottom: 10px;

}
#sub1Container{
width:70%;
height:1000px;
border:3px solid green;
float:left;
}
.gonna-pay{
background-color: #EAEAEA;
padding-left:20px;
padding-top: 20px;
padding-bottom: 25px;
font-family: "Noto Sans KR", sans-serif;
font-weight: bold;
}
.price-Table{
font-family: "Noto Sans KR", sans-serif;
width:100%;
font-size: 14px;
}
.leftTd{
text-align: left;
padding-left: 20px;
font-family: "Noto Sans KR", sans-serif;
}
.rightTd{
text-align: right;
padding-right: 25px;
font-family: "Noto Sans KR", sans-serif;
}
.orderBtn{
margin-top:34px;
background-color: #d7fd75;
/* background-color: #fb5302; */
font-weight: bold;
color: grey;
width:90%;
height:40px;
display: inline-block;
text-align: center;
font-size: 17px;
padding-top: 14px;
padding-bottom: 5px;
margin-left:17px;
margin-right:8px;
cursor:pointer;
}
.orderBtn:hover{
opacity: 0.9;
}
.basketImg{
width:300px;
height:300px;
position: absolute;
top: -390px;
bottom: 0;
left: 0;
right: 0;
margin: auto;
}
.nothingLabel{
position: relative;
font-family: "Noto Sans KR", sans-serif;
font-size: 20px;
top:520px;
bottom: 0;
left: 65px;
right: auto;
margin-left: 38%;
}
.pqSelect{
	border-radius: 10px;
	width:100px;
	border: 1px solid #DADCE0;
	display:inline-block;
	text-align: center;
	margin-top: 30px;
	position:relative;
	left:-18px;
	height:22px;
}
.goShopping{
position: relative;
background-color: black;
font-family: "Noto Sans KR", sans-serif;
font-weight: bold;
color: white;
width:180px;
height:50px;
text-align: center;
font-size: 14px;
cursor:pointer;
top:560px;
right:auto;
margin-left: 47%;
}
.goShopping:hover{
opacity:0.9;
}

</style>
<body>

<h2><b>장바구니</b></h2>
<div id="cntProduct_wrap"><label id="_allCnt">${fn:length(blist) }</label>개 상품</div>

<div id="mainContainer">
<div id="product-select-all"><a href="#" class="allDeleteBtn" style="color:black">전체삭제</a></div>
<c:forEach items="${blist }" var="ba" varStatus="vs">
	<div id=sub1Container" class="sub1_${ba.b_seq }">
		<div class="product-opt_basket">
			<div class="item-info">
				<span class="img_wrap"><a href="/Rhymes/store/productDetail?p_seq=${ba.p_seq }"><img alt="사진x" src="/upload/${ba.photo1_file }" style="width:100px;height:100px;"></a></span>
				<div class="info_wrap">
					<div style="margin-top:8px; font-size: 15px;" class="pname_wrap"><a >${ba.c_name }</a></div>
					<div style="margin-bottom: 7px; margin-top:3px;" class="pname_wrap"><a >${ba.p_name }</a></div>
					<div class="info2_warp">
					사이즈 : <span>${ba.size }</span><br>
					<div style="margin-top:3px;">수량 : <span class="eachPq${ba.b_seq }" id="eachPq${vs.count }">${ba.p_quantity }</span></div>
					<div style="margin-top:3px;">단가 : <span><fmt:formatNumber type="currency" currencySymbol="" value="${ba.p_price}" /></span></div>
					</div>
				</div>
				<div class="optionchange_wrap">
					<a href="#" onclick="changeQ(${ba.stock_seq }, ${ba.b_seq } )">수량 변경</a><br>
					<div class="pqSelect">
						<span class="minus_Btn" style="cursor:pointer;" value="${ba.b_seq }">-</span>&nbsp;&nbsp;&nbsp;
							<label id="pqCnt${ba.b_seq }">${ba.p_quantity }</label>&nbsp;&nbsp;&nbsp;
						<span class="plus_btn" style="cursor:pointer;" value="${ba.b_seq }">+</span>
					</div>
				</div>
				<div class="price_wrap">
					<input type="hidden" value="${ba.p_price }" id="eachPrice${ba.b_seq }">											
					<input type="hidden" value="${ba.p_price * ba.p_quantity}" id="pMq${ba.b_seq }">
					<span class="eachMultiple${ba.b_seq }">
					<fmt:formatNumber type="currency" currencySymbol="" value="${ba.p_price * ba.p_quantity}" />
					</span> 원
				</div>
				<div class="delete_wrap">
					<label class="delete_btn" value="${ba.b_seq }">X</label>
				</div>
			</div>
		</div>
		<input type="hidden" value="${ba.stock_seq }" id="eachSs${vs.count }">
	</div>	
</c:forEach>
		
	<div class="sub2Container">
		<div class="proudct-checkout">
			<div class="gonna-pay"><strong>주문예정금액</strong></div>
			<table class="price-Table">
				<tr>
					<td class='leftTd' style="padding-top: 20px;">상품금액</td>
					<td class='rightTd' style="padding-top: 20px;"><span id="totalP_price">${blist[0].total_price }</span> 원</td>
				</tr>
				<tr>
					<td class='leftTd' style="padding-top: 15px;">예상 배송비</td>
					<td class='rightTd' style="padding-top: 15px;">
						<c:set value="${blist[0].total_price2 }" var="total"/>
						<c:if test="${ total lt 10000}">
							<span class="post_price" value="3000">
								<fmt:formatNumber type="currency" currencySymbol="" value="${'3000'}" />
							</span> 원
						</c:if>
						<c:if test="${ total gt 10000}">
							<span class="post_price" value="0">0</span> 원
						</c:if>
												
					</td>
				</tr>
				<tr>
					<td class='leftTd' style="padding-top: 30px; font-weight: bold; font-size: 18px;">총 결제 예정 금액</td>
					<td class='rightTd' style="padding-top: 30px; font-weight: bold; font-size: 18px;">
					<input type="hidden" id="flexTotal" value="">
					<input type="hidden" id="flexPostFee" value="">
						<c:if test="${ total lt 10000}">
							<span class="pay_price">
							<fmt:formatNumber type="currency" currencySymbol="" value="${total + 3000}" />
							</span> 원
						</c:if>
						<c:if test="${ total gt 10000}">
							<span class="pay_price">
								<fmt:formatNumber type="currency" currencySymbol="" value="${total}"/>
							</span> 원
						</c:if>
					</td>
				</tr>
				<tr>
					<td colspan='2'>
						<label class="orderBtn">주문하기</label>
					</td>
				</tr>
				
			</table>
		</div>
	</div>
	
<div>
</div>	
</div>
<!-- 주문하기 -->
<form action="/Rhymes/payment/basketOrder" method="post" id="bOrderFrm">
 	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/> 
	<input type='hidden' name="blist_stockseq" id="blist_stockseq" value="">	
	<input type='hidden' name="blist_pQuantity" id="blist_pQuantity" value="">
</form>

<script>
var allCnt = ${fn:length(blist) };

// 가격 콤마
function numberWithCommas(x) {
    return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}

$(document).on('click', '.goShopping', function(){
	location.href="/main";
	
});

$(document).on('click', '.orderBtn', function(){
	alert("주문할게여");
	var len = ${fn:length(blist) };
	var blist_stockseq = "";
	var blist_pQuantity = "";
	
	//alert(len);
	for (var i = 0; i < len; i++) {
		blist_pQuantity += $("#eachPq" + (i+1)).html();
		blist_pQuantity += "/";
		blist_stockseq += $("#eachSs" + (i+1)).val();
		blist_stockseq += "/";
	}
	$("#blist_stockseq").val(blist_stockseq);
	$("#blist_pQuantity").val(blist_pQuantity);
	
	$("#bOrderFrm").submit();
	
});

$(document).on('click', '.allDeleteBtn', function(){
	$.ajax({
        type:"get",
        url:"/Rhymes/store/deleteBasketAll",
        success:function( data ){
        	$("#cntProduct_wrap").remove();
    		$("#product-select-all").remove();
    		$(".sub2Container").remove();
    		$("#sub1Container").remove();
    		$(".product-opt_basket").remove();
    		
        	var str = "";
    		str += "<hr style='margin-top:-10px;' width='100%' color='#DADCE0'>";
    		str += "<img alt='사진없음' class='basketImg' src='http://www.habitatriverside.org/wp-content/uploads/2016/10/shopping-cart-icon.png' style='width:500px; height:500px;'>";            		
    		str += "<span class='nothingLabel'>장바구니에 담긴 상품이 없습니다.</span><br>";
    		str += "<input type='button' class='goShopping' value='계속 쇼핑하기'>";
    		
    		
    		$("#mainContainer").append(str); 
        },
        error:function(){
           alert("error!!"); 
        }
	})	
});

/* 수량변경 */
function changeQ(stock_seq, b_seq){
	//alert(b_seq);
	var p_quantity = Number($("#pqCnt"+b_seq).html());
	//alert(p_quantity);
	
	var eachPrice =  $("#eachPrice"+b_seq).val();
	//alert(eachPrice);
	
	var udtQ = p_quantity * eachPrice;
	//alert(udtQ);
	
	$.ajax({
        type:"get",
        data: "stock_seq=" + stock_seq + "&p_quantity=" + p_quantity + "&b_seq=" + b_seq,
        url:"/Rhymes/store/updateBasketQ",
        success:function( data ){
			//alert(data);
        	$(".eachPq"+b_seq).html(p_quantity);
        	$(".eachMultiple"+b_seq).html(numberWithCommas(udtQ));
        	$("#pMq"+b_seq).val(udtQ);
        	
        	if(data>=10000){
        		$("#totalP_price").html(numberWithCommas(data));
        		$(".pay_price").html(numberWithCommas(data));
        		$(".post_price").html("0");
        	}else if(data<10000){
        		$("#totalP_price").html(numberWithCommas(data));
        		$(".pay_price").html(numberWithCommas(data+3000));
        		$(".post_price").html("3,000");
        	}
        },
        error:function(){
           alert("error!!"); 
        }
	})
}
$(document).on('click', '.plus_btn', function(){
	var b_seq = $(this).attr("value");
	//alert(b_seq);
	var cnt = Number($("#pqCnt" + b_seq).html());
	if(cnt<9){
		$("#pqCnt" + b_seq).html(cnt+1);	
	}else{
		alert("최대 구매수량을 초과했습니다.");
	}
});

$(document).on('click', '.minus_Btn', function(){
	var b_seq = $(this).attr("value");
	var cnt = Number($("#pqCnt" + b_seq).html());
	
	if(cnt!=1){
		$("#pqCnt" + b_seq).html(cnt-1);
	}	 
});

</script>
<script>
$(document).on('click', '.delete_btn', function(){
	var b_seq = $(this).attr("value");
	//alert(b_seq);
	$.ajax({
        type:"get",
        data: "b_seq=" + b_seq,
        url:"/Rhymes/store/deleteBasket",
        success:function( data ){
            $(".sub1_" + b_seq).remove();
            //alert("삭제 후 총 결제금액 : " + data);
		    /* var udtTotal = numberWithCommas(data);
		    alert("변환 총액 : " + udtTotal); */
            var postfee = $(".post_price").attr("value");
            //alert("배송비 : " + postfee);
            
            if(isNaN(data)){
            	//alert("장바구니 아무것도 없음");	
            }else{
            	//alert("총 결제금액 : " + (data + Number(postfee)));
            	
            	//배송비 + (총금액-삭제금액)
            	var udtPp = data + Number(postfee);
            	$("#flexTotal").val(udtPp);
 				
            /* 1.배송비 부과될 때 */	           	
            	if(data<10000){
            		alert("배송비O");
            		/* 수정된 총 결제 예상 금액 */
            		udtPp = data + 3000;
            		      				    		
            		/* 상품금액 */
            		$("#totalP_price").html(numberWithCommas(data));
            		/* 예상 배송비 */
            		$(".post_price").attr("value", "3000");
            		$(".post_price").html("3,000");
            		/* 총 결제 예정 금액 */
            		$(".pay_price").html(numberWithCommas(udtPp));
            		
            	}
            /* 2.무료배송 */	
            	else if(data>=10000){
            		$("#totalP_price").html(numberWithCommas(udtPp));
            		$(".pay_price").html(numberWithCommas(udtPp));
            	}
            /* 3.장바구니 리스트 없을 때 */	
            	//alert("allCnt -1 : " + (allCnt-1));
            	
            	$("#_allCnt").text(allCnt-1);
            	allCnt--;
            	
            	if(allCnt == 0){
         			$("#cntProduct_wrap").remove();
            		$("#product-select-all").remove();
            		$(".sub2Container").remove();
            		
            		var str = "";
            		str += "<hr style='margin-top:-10px;' width='100%' color='#DADCE0'>";
            		str += "<img alt='사진없음' class='basketImg' src='http://www.habitatriverside.org/wp-content/uploads/2016/10/shopping-cart-icon.png' style='width:500px; height:500px;'>";            		
            		//str += "<label class='nothingLabel' value='장바구니에 담긴 상품이 없습니다.'></label>";
            		str += "<span class='nothingLabel'>장바구니에 담긴 상품이 없습니다.</span><br>";
            		str += "<input type='button' class='goShopping' value='계속 쇼핑하기'>";
            		
            		$("#mainContainer").append(str); 
            	}
            }
            //minusallCnt();
        },
        error:function(){
           alert("error!!"); 
        }
   })
	
});
</script>

</body>
</html>