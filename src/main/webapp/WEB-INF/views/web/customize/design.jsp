

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
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

<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/custom_content.css">

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="${pageContext.request.contextPath}/resources/js/html5shiv.min.js"></script>
      <script src="${pageContext.request.contextPath}/resources/js/respond.min.js"></script>
      <script src="${pageContext.request.contextPath}/resources/js/carousel.js"></script>
      <link rel=stylesheet" href=""${pageContext.request.contextPath}/resources/css/ie_custom.css"> 
     
    <![endif]-->
</head>
<body class="container" onresize="set_menubar()" context="${pageContext.request.contextPath}">
	<div id="nav_injection"></div>
	<div id="menu_injection" class="col-md-2" ></div>
	<div id="_content" class="col-md-10">${html_content}
	<div class="col-md-12">
	<div class="row" style="text-align: center">
			<button type="button" class="btn btn-primary" data-toggle="modal"
				data-target="#_modal_order" data-whatever="test" >
				<span class="tbl_title">聯繫我們</span>
			</button>
		</div>
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
<script src="${pageContext.request.contextPath}/resources/js/custom.js"></script>
<script>
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
</html>
