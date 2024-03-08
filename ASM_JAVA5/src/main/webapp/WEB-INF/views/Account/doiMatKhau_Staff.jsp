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
    <title>Cập nhật thông tin | Hồng Trà YO</title>
    <meta name="keywords" content="Cập nhật thông tin | Hồng Trà YO">
    <meta name="description" content="Cập nhật thông tin: Hồng Trà YO">
    <link rel="canonical" href="https://wujiateavn.com/lien-he">
    <meta name="rating" content="general">
    <meta name="robots" content="index,follow">
    <meta name="revisit-after" content="1 days">
    <meta http-equiv="content-language" content="vi">
    <meta property="og:url" content="https://wujiateavn.com/lien-he">
    <meta property="og:title" content="Cập nhật thông tin | Hồng Trà YO">
    <meta property="og:description" content="Cập nhật thông tin: Hồng Trà YO">
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
		    min-height: 100%;
		}
		html {
		    /* To make use of full height of page*/
		    min-height: 100%;
		    margin: 0;
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
                <a class="nav-link" href="/trangChu">Go Home</a>
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
                        <div class="card-header">Update your password</div>
                        <div class="card-body">
                            <form:form name="my-form" action="" method="POST">
                            
                                <div class="form-group row">
                                    <label for="full_name" class="col-md-4 col-form-label text-md-right">Welcome: </label>
                                    <div class="col-md-6">
      										<h3>${name}</h3>
                                    </div>
                                </div>
								
								<div class="form-group row">
                                    <label for="nid_number" class="col-md-4 col-form-label text-md-right">Current Password</label>
                                    <div class="col-md-6">
                                         <input type="password" id="nid_number" class="form-control" name="current"/>
                                         <hr>
                                    </div> 
                                </div>				
								
								<div class="form-group row">
                                    <label for="nid_number" class="col-md-4 col-form-label text-md-right">New Password</label>
                                    <div class="col-md-6">
                                         <input type="password" id="nid_number" class="form-control" name="new"/>
                                    </div>
                                </div>
								
                                <div class="form-group row">
                                    <label for="nid_number" class="col-md-4 col-form-label text-md-right">Confirm New Password</label>
                                    <div class="col-md-6">
                                         <input type="password" id="nid_number" class="form-control" name="confirm"/>
                                    </div>
                                </div>
                                <div class="col-md-6 offset-md-4">
                                	<a href="/trangChu" class="btn btn-light">
                                    Cancel
                                    </a>
                                    <button type="submit" class="btn btn-primary">
                                    Update
                                    </button>
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
		function thongBao() {
			var mess = "${param.mess}";
			if(mess.length > 0){
				alert(mess);
			}
		}
		
		//khởi chạy thông báo
		window.onload = function() {
			thongBao();
		};
		
	</script>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
</body>
</html>