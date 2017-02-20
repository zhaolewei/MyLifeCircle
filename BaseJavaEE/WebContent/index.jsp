<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<link rel="stylesheet" type="text/css" href="res/css/common.css">
<script type="text/javascript" src="res/js/jquery-1.11.1.js"></script>
<title>图片上传</title>
</head>


<style>
#view {
 width: 300px; height: 300px;
}
</style>

<script type="text/javascript">
	// 	$(function() {
	// 		$(".thumbs a").click(function() {
	// 			var largePath = $(this).attr("href");
	// 			var largeAlt = $(this).attr("title");

	// 			$("#largeImg").attr({
	// 				src : largePath,
	// 				alt : largeAlt
	// 			});
	// 			return false;
	// 		});
	// 	});

	// 	function showView(obj) {
	// 		var str = obj.value;
	// 		document.getElementById("view").innerHTML = "<img src = '" + str + "' />";

	// 		alert("123:"+str);
	// 	}
</script>
<body>
	<form action="doUpload" method="post" enctype="multipart/form-data">
		<input type="file" id="myfile" name="upload" />

		<input type="submit" value="上传">
	</form>


	<hr />
	<!-- 	<h2>图片预览</h2> -->
	<!-- 	<p> -->
	<!-- 		<img id="largeImg" alt="大图片" src="res/images/img1-lg.jpg" /> -->
	<!-- 	</p> -->

	<!-- 	<p class="thumbs"> -->
	<!-- 		<a href="res/images/img2-lg.jpg" title="Image2"> -->
	<!-- 			<img src="res/images/img2-thumb.jpg"> -->
	<!-- 		</a> -->
	<!-- 		<a href="res/images/img3-lg.jpg" title="Image3"> -->
	<!-- 			<img src="res/images/img3-thumb.jpg"> -->
	<!-- 		</a> -->
	<!-- 		<a href="res/images/img4-lg.jpg" title="Image4"> -->
	<!-- 			<img src="res/images/img4-thumb.jpg"> -->
	<!-- 		</a> -->
	<!-- 		<a href="res/images/img5-lg.jpg" title="Image5"> -->
	<!-- 			<img src="res/images/img5-thumb.jpg"> -->
	<!-- 		</a> -->
	<!-- 		<a href="res/images/img6-lg.jpg" title="Image6"> -->
	<!-- 			<img src="res/images/img6-thumb.jpg"> -->
	<!-- 		</a> -->
	<!-- 	</p> -->


</body>
</html>