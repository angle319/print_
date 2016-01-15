<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="expires" content="0">
<meta name="Keywords" content="印刷,大量印刷,印刷品質,彩色印刷">
<meta content="中華彩色印刷五十年來堅持印刷品質,專業印刷大量" name="description">
<link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/com.ico">
<title>中華彩色印刷有限公司</title>
<meta name="author" content="angle319">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.css" media="screen">
<script src="${pageContext.request.contextPath}/resources/js/jquery-1.10.2.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/custom.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/custom_content.css">

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="${pageContext.request.contextPath}/resources/js/html5shiv.min.js"></script>
      <script src="${pageContext.request.contextPath}/resources/js/respond.min.js"></script>
      <script src="${pageContext.request.contextPath}/resources/js/carousel.js"></script>
      <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/ie_custom.css"> 
     
    <![endif]-->
</head>
<body class="container" onresize="set_menubar()" context="${pageContext.request.contextPath}">
	<div id="nav_injection"></div>
	<div id="menu_injection" class="col-md-2"></div>
	<div id="_content" class="col-md-10">
		<ul class="breadcrumb" style="margin-top: 3%">
			<li>產品
			<li><a href="product?pid=${page.spid}">${page.name}</a>
			<li class="active">${product.name}</li>

		</ul>
		<div class="row">
			<div id="post_size" class="col-md-8">
				<div class="lanrenzhijia">
					<!-- 大图begin -->
					<div id="preview" class="spec-preview">
						<div class="jqzoom">
							<img style="height: 400px" src="" alt="">
						</div>
					</div>
					<!-- 大图end -->
					<!-- 缩略图begin -->
					<div class="spec-scroll">
						<a class="prev">&lt;</a><a class="next">&gt;</a>
						<div id="s_items_box" class="items">
							<ul style="left: 0px;">
								<c:forEach items="${pic_list}" var="pic_name">
									<li><img id="first_img"
										src="${pageContext.request.contextPath}/${fn:substring(product.contentPic, 1,fn:length(object)-1)}/${pic_name}"
										onmousemove="preview(this);"></li>
								</c:forEach>
							</ul>
						</div>
					</div>
					<!-- 缩略图end -->

				</div>
			</div>
			<div class="col-md-4">
				<table id="detail" border="0" cellspacing="5" cellpadding="5">
					<tr>
						<th style="width: 100%" colspan="2"><h1 class="tbl_title">
								產品規格</h1></th>
					</tr>
					<tr>
						<td style="padding-left: 10%; padding-right: 3%;">■</td>
						<td width="90%">名稱: ${product.name} </td>
					</tr>
					<tr height="60px">
						<td style="padding-left: 10%; padding-right: 3%;">■</td>
						<td width="90%">規格: ${product.specification}</td>
					</tr>
					<tr height="60px">
						<td style="padding-left: 10%; padding-right: 3%;">■</td>
						<td>尺寸: ${product.size}</td>
					</tr>
					<tr height="60px">
						<td style="padding-left: 10%; padding-right: 3%;">■</td>
						<td>裝訂: ${product.bind}</td>
					</tr>
					<tr height="60px">
						<td style="padding-left: 10%; padding-right: 3%;">■</td>
						<td>張數: ${product.contentNum}</td>
					</tr>
					<tr height="60px">
						<td style="padding-left: 10%; padding-right: 3%;">■</td>
						<td>重量: ${product.heaving}</td>
					</tr>
					<tr height="20px"></tr>
				</table>

			</div>
		</div>
		<div class="row">
			<div class="col-md-8">
				<table id="detail_price" cellspacing="5" cellpadding="5"
					style="width: 95%">
					<tr>
						<th colspan="2"><h1 class="tbl_title">注意事項</h1></th>
					</tr>
					<c:forEach items="${note}" var="note">
						<tr>
							<td style="padding-left: 2%; padding-right: 2%;">■</td>
							<td width="95%">${note}</td>
						</tr>
					</c:forEach>
				</table>

			</div>
			<div class="col-md-4">
				<div id="detail_notice">
					<table cellspacing="5" cellpadding="5"
						style="width: 100%; text-align-last: center;" class="table">
						<thead>
							<tr>
								<th colspan="2"><h1 class="tbl_title">價格明細</h1></th>
							</tr>
						</thead>
						<thead>
							<tr>
								<th>數量</th>
								<th>價格</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${price}" var="price">
								<tr>
									<td>${price.quantity}</td>
									<td>${price.price}</td>
								</tr>
							</c:forEach>

						</tbody>
					</table>
					<!-- <table  cellspacing="5" cellpadding="5"
					style="width: 95%">
					<tr>
						<th colspan="4">dsad</th>
					</tr>
					<tr>
						<td class="odd">定價</td>
						<td class="even_border">195</td>
						<td class="odd">1000本</td>
						<td class="even">89</td>
					</tr>
					<tr>
						<td class="odd">100本</td>
						<td class="even_border">110</td>
						<td class="odd">2000本</td>
						<td class="even">85</td>
					</tr>
					<tr>
						<td class="odd">300本</td>
						<td class="even_border">95</td>
						<td class="odd">5000本</td>
						<td class="even">80</td>
					</tr>
					<tr>
						<td class="odd">500本</td>
						<td class="even_border">92</td>
						<td class="odd"></td>
						<td class="even"></td>
					</tr>
				</table> -->
				</div>
			</div>
		</div>
		<div class="row" style="text-align: center">
			<button type="button" class="btn btn-primary" data-toggle="modal"
				data-target="#_modal_order" data-whatever="test" >
				<span class="tbl_title">我要訂購</span>
			</button>
		</div>

	</div>
	<div id="bottom_injection" class="col-md-12"></div>
	<div class="modal fade" id="_modal_order" tabindex="-1" role="dialog"
		aria-labelledby="_modal_label" name="${product.name}" gid="${product.pid}">
		<div class="modal-dialog" role="document" style="padding-top: 100px">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="_modal_label"></h4>
				</div>
				<div class="modal-body">
					<form>
						<div class="form-group">
							<label for="name" class="control-label">產品名稱:</label>
							<labe id="product_name" class="control-label"> </label>
						</div>
						<div class="form-group">
							<label for="quatity-text" class="control-label">您的單位:</label> <input
								type="text" class="form-control" id="order_person"
								style="display: inline; width: 200px;">
						</div>
						<div class="form-group">
							<label for="quatity-text" class="control-label">您的聯絡電話:</label> <input
								type="text" class="form-control" id="phone"
								style="display: inline; width: 200px;"><label
								id="phone_c"></label>
						</div>
						<div class="form-group">
							<label for="quatity-text" class="control-label">您的聯絡信箱:</label> <input
								type="text" class="form-control" id="mail"
								style="display: inline; width: 200px;"><label
								id="mail_c"></label>
						</div>
						<div class="form-group">
							<label for="quatity-text" class="control-label">您的訂購數量:</label> <input
								type="text" class="form-control" id="quatity"
								style="display: inline; width: 100px;"><label
								id="quatity_c"></label>
						</div>
						<div class="form-group">
							<label for="message-text" class="control-label">價格:</label> <label
								id="price" class="control-label"></label>
						</div>
						<div class="form-group">
							<label for="message-text" class="control-label">備註:</label>
							<textarea class="form-control" id="note"></textarea>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">
						取消</button>
					<button id="submit" type="button" class="btn btn-primary">
						送出</button>
				</div>
			</div>
		</div>
	</div>
	<div id="alert" class="modal fade" tabindex="-1" role="dialog"
		aria-labelledby="mySmallModalLabel">
		<div class="modal-dialog modal-sm" style="padding-top: 150px">
			<div class="modal-content,alert-success">
				<div class="modal-header,alert-success">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close" style="padding: 10px;">
						<span aria-hidden="true">×</span>
					</button>

				</div>
				<div class="alert alert-success">
					<strong><p>訂購成功!</p></strong>
					<p>感謝您訂購 ${product.name} 我們會盡速與您聯繫!</p> 
				</div>
			</div>
		</div>
	</div>
</body>
<!-- <script src="resources/js/_product.js"></script> -->
<script>	function checkQuatity() {
	$("#quatity_c").html('');
	var phone = $.trim($('#quatity').val());
	if (phone == "" || !/.[0-9]{1,10}$/.test(phone) ) {
		$("#quatity_c").html('格式錯誤');
		return false;
	}
	return true;
}
function checkPhone() {
	$("#phone_c").html('');
	var phone = $.trim($('#phone').val());
	if (phone == "" || !/.[0-9]{1,10}$/.test(phone) ||phone.length<7||phone.length>13) {
		$("#phone_c").html('格式錯誤');
		return false;
	}
	return true;
}
function checkMail() {
	$("#mail_c").html('');
	var email = $.trim($('#mail').val());
	if (email == "" || !/.+@.+\.+.[a-zA-Z]{1,4}$/.test(email)) {
		$("#mail_c").html('格式錯誤');
		return false;
	}
	return true;
}
$('#_modal_order').on('show.bs.modal', function(event) {
	var button = $(event.relatedTarget)
	var modal = $(this)
	modal.find('.modal-title').text('訂購   ' + modal.attr('name'))
	modal.find('#product_name').html(modal.attr('name'));
	$('body').removeAttr('style');
})
$("#mail").change(function() {
	checkMail();
});
$("#phone").change(function() {
	checkPhone();
});
$("#quatity").change(function() {
	checkQuatity();
});
$("#submit").click(function() {
	var m=checkMail();
	var p=checkMail();
	var q=checkQuatity();
	if(m&&p&&q){
	$.post("receiveData", {
		pid : $('#_modal_order').attr('pid'),
		name: $("#order_person").val(),
		mail: $("#mail").val(),
		phone: $("#phone").val(),
		quatity : $("#quatity").val(),
		note : $("#note").val()
	}, function(data, status) {
		if(data=='true'){
		$("#alert").modal('show');
		$("#_modal_order").modal('hide');}
	});
	}
});
</script>
</body>
</html>