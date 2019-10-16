/**
 * 
 */
var userid = '';
var ctx = '';
$(function(){
	// ajax 통신을 위한 csrf 설정
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	$(document).ajaxSend(function(e, xhr, options) {
		xhr.setRequestHeader(header, token);
	});
	
	//컨텍스트 패스
	ctx = window.location.pathname.substring(0, window.location.pathname.indexOf("/", 2));
	
	//현재 유저 아이디 저장
	userid = $("#_currUserId").val();
	
	//체크박스 제어
	$("#_chk_head").click(function(){
		if( $("#_chk_head").is(":checked") === true ){
			$(".chk_item").attr('checked', 'checked');
			$(".chk_item").prop('checked', 'checked');
			$(".chk_item").attr('after', 'after');
		}else{
			$(".chk_item").removeAttr('checked');
			$(".chk_item").removeAttr('after');
		}		
	});
	
	//개별삭제 버튼 클릭 이벤트. Ajax 통신을 통해 삭제
	$(".btn_delete_item").click(function(){
		var rowId = $(this).parent().parent().parent().attr('id');
		var p_seq = rowId.split('_')[1];
		
		var isDone = deleteWishitem(p_seq);
	});
	
	//선택된 아이템 다수 삭제 버튼 클릭 이벤트. Ajax 통신
	$("#_btn_delete_all").click(function(){
		var idString = '';
		$(".body_wishlist").children().each(function(index, item){ 
			rowId = $(".body_wishlist").children().eq(index).attr('id');
			if( $("#" + rowId + " .chk_item").is(":checked") === true ){
				idString += rowId.split('_')[1] + ',';
			} 			
		});
		
		console.log(idString);
		deleteMultiWishitems(idString);
	});
});

//p_seq에 해당하는 아이템을 삭제하여 완료여부를 리턴하는 함수
function deleteWishitem(p_seq){
	// 배열 초기화
	var viewData = {};
	// data[키] = 밸류
	viewData["pd_seq"] = p_seq;

	$.ajax({
		contentType : 'application/json',
		dataType : 'json',
		url : ctx + '/wishlist/delete',
		type : 'post',
		async : false,
		data : JSON.stringify(viewData),
		success : function( resp ) {
			var result = resp + '';
			if(result === '1'){
				alert('삭제가 완료되었습니다. ');
				$("#itemrow_" + p_seq).remove();
			}else{
				console.log('세상에...0이라니..' + result);
			}
		},
		error : function() {
			alert('오류 발생. 고객센터로 문의하세요.');
		}
	});
}

//wishlist 다중삭제 함수
function deleteMultiWishitems(seqArr){
	// 배열 초기화
	var viewData = {};
	// data[키] = 밸류
	viewData["pd_seq"] = seqArr;
	var seqArrStr = seqArr.split(',');
	
	$.ajax({
		contentType : 'application/json',
		dataType : 'json',
		url : ctx + '/wishlist/delete/multiitem',
		type : 'post',
		async : false,
		data : JSON.stringify(viewData),
		success : function( resp ) {
			var result = resp + '';
			if(result === '1'){
				alert('삭제가 완료되었습니다. ');
				$.each(seqArrStr, function(index, item){
					$("#itemrow_" + item).remove();					
					if( $(".body_wishlist").children().length < 1 ){
						$(".body_wishlist").append("<div class='col-md-12 chk_wishlist_item'>찜한 물품이 없습니다.</div>");
					}
				});
			}else{
				console.log('세상에...0이라니..' + result);
			}			
		},
		error : function() {
			alert('오류 발생. 고객센터로 문의하세요.');
		}
	});
}