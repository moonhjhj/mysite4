<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	
	<link href="${pageContext.request.contextPath }/assets/bootstrap/css/bootstrap.min.css"	rel="stylesheet" type="text/css">
	
	<link href="${pageContext.request.contextPath}/assets/css/mysite.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/assets/css/guestbook.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" 	src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.12.4.js"></script>
	<script type="text/javascript"	src="${pageContext.request.contextPath }/assets/bootstrap/js/bootstrap.min.js"></script>
	
	<title>Insert title here</title>
</head>
<body>

	<div id="container">
		
		<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>
		<c:import url="/WEB-INF/views/includes/navigation.jsp"></c:import>
		
		<div id="wrapper">
			<div id="content">
				<div id="guestbook">
					
						
						<table>
							<tr>
								<td>이름</td><td><input type="text" name="name" /></td>
								<td>비밀번호</td><td><input type="password" name="password" /></td>
							</tr>
							<tr>
								<td colspan=4><textarea name="content" id="content"></textarea></td>
							</tr>
							<tr>
								<td colspan=4 align=right><input id = "btnAdd" type="submit" VALUE=" 확인 " /></td>
							</tr>
						</table>
						
<!-- 						/********************* 모달창 *****************************/ -->
<!-- 					<input id = "btnModal" type = "button" value = "삭제창"/>	 -->
						
						
					<ul id = "guestbook-list"> <!-- 방명록 리스트 -->
						
					</ul>
					
				</div><!-- /guestbook -->
			</div><!-- /content -->
		</div><!-- /wrapper -->
		
		<c:import url="/WEB-INF/views/includes/footer.jsp"></c:import>
		
	</div> <!-- /container -->


	<!-- 삭제팝업(모달)창 -->
		<div class="modal fade" id="del-pop">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title">방명록삭제</h4>
					</div>
					<div class="modal-body">
						<label>비밀번호</label> <input type="password" name="modalPassword" id="modalPassword"><br> 
						<div class = "msg"></div>
						<input type="text"	name="modalNo" value="" id="modalNo" > <br>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">취소</button>
						<button type="button" class="btn btn-danger" id="btn_del">삭제</button>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal-dialog -->
		</div>
		<!-- /.modal -->
</body>


<script type="text/javascript">

$("#btn_del").on("click", function(){
	
	var password = $("#modalPassword").val();
	var no = $("#modalNo").val();
	
	$.ajax({
			
	// 		url : "${pageContext.request.contextPath }/api/gb/add?name=name&password=password", //원래 이렇게 써야 되는데 data : 뒤에 정의해줬으니까 add만써도됨.		
			url : "${pageContext.request.contextPath }/api/gb/delete",		
			type : "post",
			data : {password : password, no : no},
			dataType : "json",
	// 		여기까지 controller로 감. 갔다가 성공했을 시 success로 옴.
			success : function(result){
				/*성공시 처리해야될 코드 작성*/
				if(result != 0){
					$("#" + result).remove();
					$("#del-pop").modal('hide');
					
				}else{
					$(".msg").html("틀림").css("color", "red");
				}
				
				
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
			
			
		});
	
});

$(document).ready(function(){
	
	fetchList();
	//원래 이 안에 ajax 있었음. =>ready안을 간단하게 바꾼것.
});

$("ul").on("click", "input", function(){
	console.log("모달");
	$("#del-pop").modal();
	
	var no = $(this).attr("class");
	console.log(no);
	$("#modalNo").val(no);
	$("#modalPassword").val("");
	$(".msg").html("");
	
});
/*********************** 저장버튼 클릭 시 *************************/
$("#btnAdd").on("click", function(){
// 	event.preventDefault();
	console.log("btnAdd");
	
	/* 데이터 모으기 */
	var name = $("[name=name]").val();
	var password = $("[name=password]").val();
	var content = $("[name=content]").val();
	console.log(name, password, content);
	
	
	
	guestbookVo = { name : $("input[name = 'name']").val(),
					password : $("input[name = 'password']").val(),
					content : $("textarea[name = 'content']").val()};
	
	
	/* 리스트 요청 ajax */
	$.ajax({
		
// 		url : "${pageContext.request.contextPath }/api/gb/add?name=name&password=password", //원래 이렇게 써야 되는데 data : 뒤에 정의해줬으니까 add만써도됨.		
		url : "${pageContext.request.contextPath }/api/gb/add",		
		type : "post",
		/* contentType : "application/json" */
 		data : {name : name, password : password, content : content},
		/* data : JSON.stringify(guestbookVo), */
		dataType : "json",
		success : function(guestbookVo){ //guestbookList가 apiGuestbookController의 guestbookList랑 같은 애는 아님.
			/*성공시 처리해야될 코드 작성*/
			
					render(guestbookVo, "up");
					var name = $("[name=name]").val("");
					var password = $("[name=password]").val("");
					var content = $("[name=content]").val(""); 
			
					/* if(guestbookVo.result == "success"){
						
						render(resultVo.data, "up");
						
						$("#write-form input").val("");
						$("#write-form textArea").val("");
					}else{
						
					} */
		},
		error : function(XHR, status, error) {
			console.error(status + " : " + error);
		}
	});
});

function fetchList(){ //재사용가능성은 없으나 기능끼리 모아둔것.
	/***************** 1. 리스트 요청 ajax방식으로 *************************/
	//얘는 이 안에서만 쓸 수 있음. 만약 add하려면 어딘가에 이 코드를 또 써야함. 그래서 맨 밑에 function으로 만들어둠.
	/******************* 2. 데이터 수신 *****************************/
	/********************* 3. 리스트 그려야함*********************** */
	$.ajax({
		
		url : "${pageContext.request.contextPath }/api/gb/list",		
		type : "post",

		dataType : "json",
		success : function(guestbookList){ //guestbookList가 apiGuestbookController의 guestbookList랑 같은 애는 아님.
			/*성공시 처리해야될 코드 작성*/
			console.log(guestbookList);
			for(var i = 0; i<guestbookList.length; i++){
				/* var str = "";
				str += "<li>";
				str += "	<table>";
				str += "		<tr>";
				str += "			<td>[" + guestbookList[i].no + "]</td>";
				str += "			<td>[" + guestbookList[i].name + "]</td>";
				str += "			<td><a href=''>삭제</a></td>";
				str += "		</tr>";
				str += "		<tr>";
				str += "			<td colspan=4>";
				str +=  				guestbookList[i].content ;
				str += "			</td>";
				str += "		</tr>";
				str += "	</table>";
				str += "	<br>";
				str += "</li>";
			
				$("#guestbook-list").append(str); */
				
					render(guestbookList[i], "down");
			}
		},
		error : function(XHR, status, error) {
			console.error(status + " : " + error);
		}
	});
	
	
	
}

/**************** 재사용 가능성 높음 ********************/
function render(guestbookVo, updown){
	
	var str = "";
	str += "<li id = "+ guestbookVo.no + ">";
	str += "	<table>";
	str += "		<tr>";
	str += "			<td>[" + guestbookVo.no + "]</td>";
	str += "			<td>[" + guestbookVo.name + "]</td>";
	str += "			<td><input type = 'button' id = 'btnModal' value = '삭제' class = '" + guestbookVo.no + "'></td>";
	str += "		</tr>";
	str += "		<tr>";
	str += "			<td colspan=4>";
	str +=  				guestbookVo.content ;
	str += "			</td>";
	str += "		</tr>";
	str += "	</table>";
	str += "	<br>";
	str += "</li>";
	
	if(updown == "up"){
		//add할 때 up 필요.
		$("#guestbook-list").prepend(str);
		
	}else if(updown == "down"){
		
		$("#guestbook-list").append(str);
		
	}else{
		
		console.log("오류");
	}

}




</script>
</html>