<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>Đăng ký| Hồng Trà YO</title>
    <meta name="keywords" content="Đăng ký | Hồng Trà YO">
    <meta name="description" content="Đăng ký:: Hồng Trà YO">
    <link rel="canonical" href="https://wujiateavn.com/lien-he">
    <meta name="rating" content="general">
    <meta name="robots" content="index,follow">
    <meta name="revisit-after" content="1 days">
    <meta http-equiv="content-language" content="vi">
    <meta property="og:url" content="https://wujiateavn.com/lien-he">
    <meta property="og:title" content="Đăng ký| Hồng Trà YO">
    <meta property="og:description" content="Đăng ký:: Hồng Trà YO">
    <meta property="og:image" content="https://wujiateavn.com/files/systems/anh-chia-se-mehh70vp.png">
    <meta property="og:site_name" content="wujiateavn.com">
    <meta property="og:type" content="article">
    <meta name='Search Engines' content='www.google.com, www.google.com.vn, www.yahoo.com'>
    <meta property="og:locale" content="vi_VN">
    <link rel="shortcut icon" type="image/x-icon" href="https://wujiateavn.com/files/systems/favicon-d2tu.ico">
    <link rel="alternate" href="https://wujiateavn.com/lien-he" hreflang="vi-vn">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@100;300;400;500;700&display=swap" rel="stylesheet">
	<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
	<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
	<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
	<style type="text/css">
		@import url(https://fonts.googleapis.com/css?family=Raleway:300,400,600);


body{
    margin: 0;
    font-size: .9rem;
    font-weight: 400;
    line-height: 1.6;
    color: #212529;
    text-align: left;
    background-color: #f5f8fa;
}
html, body {
    margin: 0;
    height: 100%;
}
.navbar-laravel
{
    box-shadow: 0 2px 4px rgba(0,0,0,.04);
}

.navbar-brand , .nav-link, .my-form, .login-form
{
    font-family: Raleway, sans-serif;
}

.my-form
{
    padding-top: 1.5rem;
    padding-bottom: 1.5rem;
}

.my-form .row
{
    margin-left: 0;
    margin-right: 0;
}

.login-form
{
    padding-top: 1.5rem;
    padding-bottom: 1.5rem;
}

.login-form .row
{
    margin-left: 0;
    margin-right: 0;
}
.cusID{
	visibility: hidden;
}
	</style>
	</head>
<body>

<nav class="navbar navbar-expand-lg navbar-light navbar-laravel">
    <div class="container">
    <a class="navbar-brand" href="#">HỒNG TRÀ YO</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav ml-auto">
            <li class="nav-item">
                <a class="nav-link" href="/login">Login</a>
            </li>
        </ul>

    </div>
    </div>
</nav>

<main class="my-form bg-dark">
    <div class="cotainer">
        <div class="row justify-content-center">
            <div class="col-md-8">
                    <div class="card">
                        <div class="card-header">Register</div>
                        <div class="card-body">
                            <form:form name="my-form" action="" method="POST" modelAttribute="cs">
                                <div class="form-group row">
                                    <label for="full_name" class="col-md-4 col-form-label text-md-right">Full Name</label>
                                    <div class="col-md-6">
                                       <form:input type="text" id="full_name" class="form-control" path="fullname"/>
                                    </div>
                                </div>
								
								<div class="form-group row">
                                    <label for="username" class="col-md-4 col-form-label text-md-right">Username</label>
                                    <div class="col-md-6">
                                         <form:input type="text" id="username" path="username" class="form-control"/>
                                    </div>
                                </div>
                                 <div class="form-group row">
                                 	 <label for="gender" class="col-md-4 col-form-label text-md-right">Gender</label>
	                                 <div class="col-md-6 mt-1" id="gender">
										<div class="form-check form-check-inline">
										 	<label class="form-check-label" for="male">Male</label>
										  	<form:radiobutton class="form-check-input ml-2" id="male" name="gender" value="true" path="gender"/>
										</div>
										<div class="form-check form-check-inline">
											<label class="form-check-label" for="female">Female</label>
										  	<form:radiobutton class="form-check-input ml-2" id="female" name="gender" value="false" path="gender"/>							  
										</div>
									</div>
								</div>		
								<div class="form-group row">
                                    <label for="email_address" class="col-md-4 col-form-label text-md-right">E-Mail Address</label>
                                    <div class="col-md-6">
                                         <form:input type="email" id="email_address" class="form-control" path="email" />
                                    </div>
                                </div>				
                                <div class="form-group row">
                                    <label for="phone_number" class="col-md-4 col-form-label text-md-right">Phone Number</label>
                                    <div class="col-md-6">
                                         <form:input type="text" id="phone_number" path="phone" class="form-control"/>
                                    </div>
                                </div>

                                <div class="form-group row">
                                    <label for="present_address" class="col-md-4 col-form-label text-md-right">Present Address</label>
                                    <div class="col-md-6">
                                         <form:input type="text" id="present_address" path="address" class="form-control"/>
                                    </div>
                                </div>

                                <div class="form-group row">
                                    <label for="permanent_address" class="col-md-4 col-form-label text-md-right">Birthday</label>
                                    <div class="col-md-6">
                                         <form:input type="date" pattern="(?:19|20)[0-9]{2}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-9])|(?:(?!02)(?:0[1-9]|1[0-2])-(?:30))|(?:(?:0[13578]|1[02])-31))"
                                         id="permanent_address" class="form-control" path="birthday"/>
                                    </div>
                                </div>		
                                
                                <hr>
                                
								<div class="form-group row">
                                    <label for="nid_number" class="col-md-4 col-form-label text-md-right">Password</label>
                                    <div class="col-md-6">
                                         <form:input type="password" id="nid_number" class="form-control" path="password"/>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="nid_numr" class="col-md-4 col-form-label text-md-right">Password To Confirm</label>
                                    <div class="col-md-6">
                                         <input type="passwordC" id="nid_num" class="form-control" name="confirm"/>
                                    </div>
                                </div>
                                 <div class="col-md-6 offset-md-4">
                                     <button type="submit" class="btn btn-primary">
                                     Register
                                     </button>
                                 </div>
                                </div>
                            </form:form>
                        </div>
                    </div>
            </div>
        </div>
    </div>

</main>
<script type="text/javascript">

		//Thông báo
		function thongBaoUP() {
			var mess = "${message}";
			if(messUP.length > 0){
				alert(messUP);
			}
		}
		
		//khởi chạy thông báo
		window.onload = function() {
			thongBaoUP();
		};
		
	</script>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
</body>
</html>