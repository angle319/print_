/**
 * @author angle319
 * @descript viewpic
 */
//鼠标经过预览图片函数
var viewNum;
var countLength;
$(document).ready(function() {
	//$("#image_message").hide();
	var context=$("body").attr("context");
	var k=location.pathname.split('/')
	k=k[k.length-1];
	if(k=='undefined'){
		k='';
	}else{
		k='?pid='+k;
	}
	var temp=""
	if(context.length>1){
		temp=context+"/";
	}else{
		temp="/";
	}
	$("#nav_injection").load(temp+'navbar');
	$("#bottom_injection").load(temp+'bottom');
	$("#menu_injection").load(temp+'menu'+k,function(){
		if($("#menu_injection").children().length<=1){
			$("#menu_injection").remove();
			$("#_content").attr('class','col-md-12');
		}else{
			$("#_content").attr('class','col-md-10');
		}

		});
	$(".img_frame").hide();
	$(".img_frame").each(function() {
		$(this).fadeIn(Math.floor(Math.random() * (3000 - 300 + 1)) + 300);
	});
	$("img").each(function() {
		
		$(this).hide();
		$(this).fadeIn(Math.floor(Math.random() * (3000 - 300 + 1)) + 300);
	});
	/*if($("#menu_injection").children().length<=1){
		$("#menu_injection").remove();
		$("#_content").attr('class','col-md-12');
	}*/
});
function preview(img) {
	$("#preview .jqzoom img").attr("src", $(img).attr("src"));
	$("#preview .jqzoom img").attr("jqimg", $(img).attr("bimg"));
	set_viewer();
}

function set_viewer() {
	viewNum = $("#s_items_box").outerWidth() / $(".spec-scroll .items ul li").outerWidth();
	countLength = ($(".spec-scroll .items ul li").length - viewNum) * $(".spec-scroll .items ul li").eq(0).outerWidth();
	alert(countLength);
	if (countLength < 0) {
		$(".spec-scroll .next").hide();
		$(".spec-scroll .prev").hide();
	} else {
		$(".spec-scroll .next").show();
		$(".spec-scroll .prev").show();
	}
}

//图片预览小图移动效果,页面加载时触发
$(function() {
	var tempLength = 0;
	var moveNum = 2;
	var moveTime = 300;
	viewNum = $("#s_items_box").outerWidth() / 64;
	var scrollDiv = $(".spec-scroll .items ul");
	var scrollItems = $(".spec-scroll .items ul li");
	var moveLength = scrollItems.eq(0).width() * moveNum;
	countLength = (scrollItems.length - viewNum) * scrollItems.eq(0).outerWidth();
	if (countLength > 0) {
		$(".spec-scroll .next").show();
		$(".spec-scroll .prev").show();
	}

	//下一张
	$(".spec-scroll .next").bind("click", function() {
		if (tempLength < countLength) {
			if ((countLength - tempLength) > moveLength) {
				scrollDiv.animate({
					left : "-=" + moveLength + "px"
				}, moveTime);
				tempLength += moveLength;
			} else {
				scrollDiv.animate({
					left : "-=" + (countLength - tempLength) + "px"
				}, moveTime);
				tempLength += (countLength - tempLength);
			}
		}
	});
	//上一张
	$(".spec-scroll .prev").bind("click", function() {
		if (tempLength > 0) {
			if (tempLength > moveLength) {
				scrollDiv.animate({
					left : "+=" + moveLength + "px"
				}, moveTime);
				tempLength -= moveLength;
			} else {
				scrollDiv.animate({
					left : "+=" + tempLength + "px"
				}, moveTime);
				tempLength = 0;
			}
		}
	});

});
function set_menubar() {
	
	if (window.innerWidth <= 992) {
		$("#menu_size").height('auto');
		$("#menu_size").css("padding-top", "0%");
		$("#menu_size").css("padding-bottom", "0%");
	} else {	
		
		var menu=$("#menu_size").outerHeight();
		var content=$("#_content").outerHeight();
		if(content>menu)$("#menu_size").css("height", $("#_content").outerHeight());
	}
}
/**
 * paser db pattern 
 */
function fix_carousel() {
	var f = $(".item");
	f.removeAttr("style");
	f.each(function(i) {
		if (i > 0 && $(this).hasClass('item active')) {
			$(this).removeClass('item active').addClass('item');
		}
	});
}
function set_viewer() {
	$("#preview").height($(".jqzoom").outerHeight());
}

$(function() {
	preview($("#first_img").get());
	fix_carousel()
	//set_menubar();
})

