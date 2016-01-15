<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
<script src="${pageContext.request.contextPath}/resources/js/custom.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/custom_content.css">

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="${pageContext.request.contextPath}/resources/js/html5shiv.min.js"></script>
      <script src="${pageContext.request.contextPath}/resources/js/respond.min.js"></script>
      <script src="${pageContext.request.contextPath}/resources/js/carousel.js"></script>
      <link rel="stylesheet" href="${pageContext.request.contextPath}/resourcescss/ie_custom.css"> 
     
    <![endif]-->
</head>
<body class="container" onresize="set_menubar()" context="${pageContext.request.contextPath}">
	<div id="nav_injection"></div>
	<div id="menu_injection" class="col-md-2" ></div>
	<div id="_content" class="col-md-10">
		<div class="row">
			<div class="col-md-12">
				<c:forEach items="${html_content}" var="product">
					<div class="col-md-3"
						style="padding-top: 25px; padding-bottom: 25px;">
						<a style="color: white;" href="${pageContext.request.contextPath}/product_detail/${product.spId}/${product.pid}">
							<div id="p_img" class="img_frame">
								<img
									src="${fn:substring(product.mainPic, 1,fn:length(object)-1)}/main.jpg"
									class="img-rounded" alt="Cinque Terre" width="100%">
								<div class="img_descript">
									<p style="color: white;">${product.name}</p>
								</div>
							</div>
						</a>
					</div>
				</c:forEach>
			</div>
		</div>


	</div>
	<div id="bottom_injection" class="col-md-12"></div>
</body>

</html>
