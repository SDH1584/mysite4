<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/gallery.css" rel="stylesheet" type="text/css">


<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery-1.12.4.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/bootstrap/js/bootstrap.js"></script>

</head>


<body>
	<div id="wrap">

		<c:import url="/WEB-INF/views/include/header.jsp"></c:import>
		<!-- //header -->
		<!-- //nav -->

		<c:import url="/WEB-INF/views/include/asideGallery.jsp"></c:import>
		<!-- //aside -->


		<div id="content">

			<div id="content-head">
				<h3>갤러리</h3>
				<div id="location">
					<ul>
						<li>홈</li>
						<li>갤러리</li>
						<li class="last">갤러리</li>
					</ul>
				</div>
				<div class="clear"></div>
			</div>
			<!-- //content-head -->


			<div id="gallery">
				<div id="list">

					<c:if test="${!(empty sessionScope.authUser)}">
						<button id="btnImgUpload">이미지올리기</button>
					</c:if>

					<div class="clear"></div>


					<ul id="viewArea">

						<c:forEach items="${requestScope.galleryList}" var="vo">
							<li id="l${vo.no}">
								<div class="view">
									<img class="imgItem" src="${pageContext.request.contextPath}/upload/${vo.saveName}" data-no="${vo.no}">
									<div class="imgWriter">
										작성자: <strong>${vo.name}</strong>
									</div>
								</div>
							</li>
						</c:forEach>

					</ul>
				</div>
				<!-- //list -->
			</div>
			<!-- //board -->
		</div>
		<!-- //content  -->
		<div class="clear"></div>

		<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>
		<!-- //footer -->

	</div>
	<!-- //wrap -->



	<!-- 이미지등록 팝업(모달)창 -->
	<div class="modal fade" id="addModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">이미지등록</h4>
				</div>

				<form action="${pageContext.request.contextPath}/gallery/upload" method="post" enctype="multipart/form-data">
					<div class="modal-body">
						<div class="form-group">
							<label class="form-text">글작성</label> <input id="addModalContent" type="text" name="content" value="">
						</div>
						<div class="form-group">
							<label class="form-text">이미지선택</label> <input id="file" type="file" name="file" value="">
						</div>
					</div>
					<div class="modal-footer">
						<button type="submit" class="btn" id="btnUpload">등록</button>
					</div>
				</form>

			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->



	<!-- 이미지보기 팝업(모달)창 -->
	<div class="modal fade" id="viewModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">이미지보기</h4>
				</div>
				<div class="modal-body">

					<div class="formgroup">
						<img id="viewModelImg">
						<!-- ajax로 처리 : 이미지출력 위치-->
					</div>

					<div class="formgroup">
						<p id="viewModelContent"></p>
					</div>

				</div>
				<form method="" action="">
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
						<button type="button" class="btn btn-danger" id="btnDel" data-no="">삭제</button>
					</div>


				</form>

			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->


</body>


<script type="text/javascript">
	//업로드를 눌렀을때
	$("#btnImgUpload").on("click", function() {
		console.log("모달클릭");
		$('#addModal').modal('show');
	});
	//사진을 클릭했을때
	$(".imgItem").on(
			"click",
			function() {
				console.log("이미지클릭");
				var $this = $(this);
				var no = $this.data('no');
				$.ajax({
					//요청할때
					url : "${pageContext.request.contextPath}/gallery/read",
					type : "post",
					//contentType : "application/json",
					data : {
						no : no
					},
					//응답받을때
					//dataType : "json",
					success : function(galleryVo) {
						console.log(galleryVo)
						$('#viewModelImg').attr(
								'src',
								'${pageContext.request.contextPath}/upload/'
										+ galleryVo.saveName);
						$("#viewModelContent").html('<strong>'+galleryVo.content+'</strong>');
						$('#viewModal').modal('show');
					},
					error : function(XHR, status, error) {
						console.error(status + " : " + error);
					}
				});
			});
	//삭제를 눌렀을때
	$("#btnDel").on("click", function() {
		console.log("삭제클릭");
		var $this = $(this);
		var no = $this.data('no');
		$.ajax({
			//요청할때
			url : "${pageContext.request.contextPath}/gallery/delete",
			type : "post",
			//contentType : "application/json",
			data : {
				no : no
			},
			//응답받을때
			//dataType : "json",
			success : function(int) {	
				
				$('#viewModal').modal('hide');
				$('#l'+no).remove();
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
		
	});
</script>
</html>
