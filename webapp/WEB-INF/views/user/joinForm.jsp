<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/user.css" rel="stylesheet" type="text/css">

<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery-1.12.4.js"></script>

</head>

<body>
		<div id="wrap">

	<c:import url="/WEB-INF/views/include/header.jsp"></c:import>
		<div id="container" class="clearfix">
				<c:import url="/WEB-INF/views/include/userside.jsp"></c:import>

			<!-- //aside -->

			<div id="content">
			
				<div id="content-head">
					<h3>로그인</h3>
					<div id="location">
						<ul>
							<li>홈</li>
							<li>회원</li>
							<li class="last">로그인</li>
						</ul>
					</div>
					<div class="clear"></div>
				</div>
				 <!-- //content-head -->

					<div id="user">
						<div id="joinForm">
							<form action="${pageContext.request.contextPath}/user/join" method="get">

								<!-- 아이디 -->
								<div class="form-group">
									<label class="form-text" for="input-uid">아이디</label> <input type="text" id="input-uid" name="id" value="" placeholder="아이디를 입력하세요">
									<button type="button" id="btnCheck">중복체크</button>
								</div>
								<!-- 비밀번호 -->
								<div class="form-group">
									<label class="form-text" for="input-pass">패스워드</label> <input type="text" id="input-pass" name="password" value="" placeholder="비밀번호를 입력하세요">
								</div>

								<!-- 이름 -->
								<div class="form-group">
									<label class="form-text" for="input-name">이름</label> <input type="text" id="input-name" name="name" value="" placeholder="이름을 입력하세요">
								</div>

								<!-- //나이 -->
								<div class="form-group">
									<span class="form-text">성별</span> <label for="rdo-male">남</label> <input type="radio" id="rdo-male" name="gender" value="male"> <label for="rdo-female">여</label> <input type="radio" id="rdo-female" name="gender" value="female">

								</div>

								<!-- 약관동의 -->
								<div class="form-group">
									<span class="form-text">약관동의</span> <input type="checkbox" id="chk-agree" value="" name=""> <label for="chk-agree">서비스 약관에 동의합니다.</label>
								</div>

								<!-- 버튼영역 -->
								<div class="button-area">
									<button type="submit" id="btn-submit">회원가입</button>
								</div>
							</form>
						</div>
						<!-- //joinForm -->
					</div>
					<!-- //user -->
				</div>
				<!-- //content  -->
			</div>
			<!-- //container  -->

			<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>

		</div>
	</div>
	<!-- //wrap -->
</body>

<script type="text/javascript">



	$("#btn-submit").on("click",function(){
		console.log("회원가입버튼 클릭")
		
		var id =$("#input-uid").val();
		var pw =$("#input-pass").val();
		
		if(id== ""){
			alert("아이디를 입력해")
			return false;
		}if(pw== ""){
			alert("비밀번호를 입력해")
			return false;
		}
		return true;
	})
	

	//중복체크를 클릭할때
    	$("#btnCheck").on("click", function() {
    		console.log("클릭");
    		//입력한 아이디 값을 가져온다.
    		var id = $("#input-uid").val();
    		$.ajax({
    			//요청할때
    			url : "${pageContext.request.contextPath}/user/compare",
    			type : "post",
    			contentType : "application/json",
    			data : {id:id},
    			//응답받을때
    			//dataType : "json",
    			success : function(userVo) {//json --> js로 변환되서 result에 담김
				/*성공시 처리해야될 코드 작성*/
				console.log(userVo);
				if (userVo.id == null) {//중복 없음
					console.log("중복아님");
    				alert("사용 가능한 아이디입니다")
				} else {//중복 있음
					console.log("중복");
    				alert("중복된 아이디입니다")
				}
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
    			
    		});
    	})
    	
	
</script>











</html>