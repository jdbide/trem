<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<meta content="text/html; charset=UTF-8" http-equiv="content-type" />
	<!-- Tell the browser to be responsive to screen width -->
	<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport" />
	
	<title>Tremas - Login</ui:insert></title>	
	<c:set var="pathContext" value="${pathContext}" />
	
	<!-- Bootstrap 3.3.5 -->
	<link rel="stylesheet" type="text/css" href="${pathContext}/appClient/vendor/bootstrap/css/bootstrap.min.css" />
    <!-- Font Awesome -->
    <link rel="stylesheet" href="${pathContext}/appClient/vendor/font-awesome/css/font-awesome.min.css" />
    <!-- Theme style -->
    <link rel="stylesheet" href="${pathContext}/appClient/vendor/theme/css/AdminLTE.css" />
    <!-- iCheck -->
    <link rel="stylesheet" href="${pathContext}/appClient/vendor/iCheck/square/blue.css" />    	
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
       	<script src="${pathContext}/appClient/vendor/html5shiv/3.7.3/html5shiv.min.js"></script>
       	<script src="${pathContext}/appClient/vendor/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body class="hold-transition login-page">	
	<div class="login-box">
		
		<div class="login-logo">
			<a href="#"><b>Conexxion</b> - Tremas</a>
		</div>
		
		<div class="login-box-body">
			<p class="login-box-msg">Veuillez entrer votre identifiant</p>
		
			<form action="${pathContext}/login" method="post" >								
				<div class="form-group has-feedback">
					<input type="text" class="form-control" name="j_username" placeholder="Cp" autofocus="true">					
           			<span class="glyphicon glyphicon-user form-control-feedback"></span>
				</div>
				<div class="form-group has-feedback">
					<input type="password" class="form-control" name="j_password" placeholder="Password">					
           			<span class="glyphicon glyphicon-lock form-control-feedback"></span>
				</div>
				<% 	
					String error = (String) request.getAttribute("msgErrAuth");
					if (error != null) {
				%>
				<div class="form-group has-feedback alert alert-danger" role="alert">
					<p><%=error%></p>
				</div>
				<% } %>
				
				<div class="row">
			    	<div class="col-xs-8"></div><!-- /.col -->
			        <div class="col-xs-4">
			        	<button type="submit" class="btn btn-primary btn-block btn-flat">Valider</button>			        	
			        </div><!-- /.col -->
		        </div>
			</form>
				
			<a href="#">I forgot my password</a><br/>
        	<a href="#" class="text-center">Register a new membership</a>			
		</div><!-- /.login-box-body -->
		<div style="background: red !important ;margin-bottom: 25px;text-align: center;">
			<div class="col-xs-4" style="text-align: left;">- Avancial -</div><!-- /.col -->
			<div class="col-xs-5" style="text-align: left;">
				Etat du Serveur				
			</div>
			<div class="col-xs-3" style="text-align: right;">				
				<span>
					<i class="fa fa-fw fa-database" style="color: green;" data-toggle="tooltip" data-placement="left" title="Serveur Bdd : actif (27/05/2016)"></i>
					<i class="fa fa-fw fa-database" style="color: red;" data-toggle="tooltip" data-placement="left" title="Serveur Bdd : "></i>
				</span> 
				<span>
					<i class="fa fa-fw fa-calendar-check-o" style="color: green;" data-toggle="tooltip" data-placement="left" title="Tooltip on left"></i>
					<i class="fa fa-fw fa-calendar-times-o" style="color: red;" data-toggle="tooltip" data-placement="left" title="Tooltip on left"></i>
				</span>
			</div><!-- /.col -->
		</div>
	</div> <!-- /.login-box -->

	<!-- jQuery 2.1.4 -->
    <script src="${pathContext}/appClient/vendor/jQuery/2.1.4/jQuery-2.1.4.min.js"></script>
    <!-- Bootstrap 3.3.5 -->
    <script src="${pathContext}/appClient/vendor/bootstrap/js/bootstrap.min.js"></script>
    <!-- iCheck -->
    <script src="${pathContext}/appClient/vendor/iCheck/icheck.min.js"></script>
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