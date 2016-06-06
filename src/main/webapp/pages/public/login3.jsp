<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge" />
		<meta content="text/html; charset=UTF-8" http-equiv="content-type" />
		<!-- Tell the browser to be responsive to screen width -->
		<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport" />
      	<title>Socle MOE</title>
      	
      	<!-- Bootstrap 3.3.5 -->
      	<link href="<c:url value="/appClient/vendor/bootstrap/css/bootstrap.min.css" />" rel="stylesheet">		
    	<!-- Font Awesome -->
    	<link href="<c:url value="/appClient/vendor/font-awesome/css/font-awesome.min.css" />" rel="stylesheet">
    	<!-- Theme style -->
    	<link href="<c:url value="/appClient/vendor/theme/css/AdminLTE.css" />" rel="stylesheet">
    	<!-- iCheck -->
    	<link href="<c:url value="/appClient/vendor/iCheck/square/blue.css" />" rel="stylesheet">
    	<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    	<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    	<!--[if lt IE 9]>
    		<script src="<c:url value="/appClient/vendor/html5shiv/3.7.3/html5shiv.min.js" />"></script>
    		<script src="<c:url value="/appClient/vendor/respond/1.4.2/respond.min.js" />"></script>        	
    	<![endif]-->
  </head>
  <body class="hold-transition login-page">
  	<div class="login-box">
			<p>Test page Login</p>
	</div> <!-- /.login-box -->
	<!-- jQuery 2.1.4 -->
	<script src="<c:url value="/appClient/vendor/jQuery/2.1.4/jQuery-2.1.4.min.js" />"></script>
    <!-- Bootstrap 3.3.5 -->
    <script src="<c:url value="/appClient/vendor/bootstrap/js/bootstrap.min.js" />"></script>
    <!-- iCheck -->
    <script src="<c:url value="/appClient/vendor/iCheck/icheck.min.js" />"></script>
    <script>
    	$(function () {
    		$('input').iCheck({
    			checkboxClass: 'icheckbox_square-blue',
	          	radioClass: 'iradio_square-blue',
	          	increaseArea: '20%' // optional
	         });
	   });
    </script>
  </body> 
</html>