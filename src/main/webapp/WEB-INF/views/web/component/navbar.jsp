<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="pragma" content="no-cache">
<div class="view">
	<a href="${pageContext.request.contextPath}/home"><img alt="Preview" src="${pageContext.request.contextPath}/resources/image/logo.jpg" style="height: 50px"></a>
	<script language="JavaScript">
		$(function() {
			/*$('.dropdown-toggle').click(function() {
				$('.dropdown-menu').hide();
				$(this).next('.dropdown-menu').fadeToggle(500);
			});*/

			/**$(".dropdown-toggle").focus(function(){
				if($('.dropdown-menu').is(":focus")){
					$('.dropdown-menu').show();}
					else if($('.dropdown-toggle').is(":focus")){
						alert('fdfs');
					}
					else{
						$('.dropdown-menu').hide();
					}
					
				});**/

		});
		// Add slideDown animation to dropdown
		$('.dropdown').on(
				'show.bs.dropdown',
				function(e) {
					$(this).find('.dropdown-menu').first().stop(true, true)
							.slideDown();
				});

		// Add slideUp animation to dropdown
		$('.dropdown').on('hide.bs.dropdown', function(e) {
			$(this).find('.dropdown-menu').first().stop(true, true).slideUp();
		});
	</script>

</div>
<div class="row">
	<div class="col-md-12">

		<nav class="navbar navbar-default">
			<div class="container-fluid">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed"
						data-toggle="collapse" data-target="#bs-navbar-collapse">
						<span class="sr-only">Toggle navigation</span><span
							class="icon-bar"></span><span class="icon-bar"></span><span
							class="icon-bar"></span>
					</button>

				</div>
				${navbar}
			</div>
		</nav>

	</div>
</div>


