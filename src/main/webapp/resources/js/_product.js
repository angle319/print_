/**
 * 
 */
	function checkQuatity() {
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
			if(data){
			$("#alert").modal('show');
			$("#_modal_order").modal('hide');}
		});
		}
	});
