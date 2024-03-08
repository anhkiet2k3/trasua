<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>Đăng nhập | Hồng Trà YO</title>
    <meta name="keywords" content="Đăng nhập | Hồng Trà YO">
    <meta name="description" content="Đăng nhập:: Hồng Trà YO">
    <link rel="canonical" href="https://wujiateavn.com/lien-he">
    <meta name="rating" content="general">
    <meta name="robots" content="index,follow">
    <meta name="revisit-after" content="1 days">
    <meta http-equiv="content-language" content="vi">
    <meta property="og:url" content="https://wujiateavn.com/lien-he">
    <meta property="og:title" content="Đăng nhập| Hồng Trà YO">
    <meta property="og:description" content="Đăng nhập:: Hồng Trà YO">
    <meta property="og:image" content="https://wujiateavn.com/files/systems/anh-chia-se-mehh70vp.png">
    <meta property="og:site_name" content="wujiateavn.com">
    <meta property="og:type" content="article">
    <meta name='Search Engines' content='www.google.com, www.google.com.vn, www.yahoo.com'>
    <meta property="og:locale" content="vi_VN">
    <link rel="shortcut icon" type="image/x-icon" href="https://wujiateavn.com/files/systems/favicon-d2tu.ico">
    <link rel="alternate" href="https://wujiateavn.com/lien-he" hreflang="vi-vn">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@100;300;400;500;700&display=swap" rel="stylesheet">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
	<style type="text/css">
		.form-check {
			text-align: left;
		}
	</style>
</head>
<body style="background-color: #508bfc;">
 	<section class="vh-100" style="background-color: #508bfc;">
	  <div class="container py-5 h-100">
	    <div class="row d-flex justify-content-center align-items-center h-100">
	      <div class="col-12 col-md-8 col-lg-6 col-xl-5">
	        <div class="card shadow-2-strong" style="border-radius: 1rem;">
	          <div class="card-body p-5 text-center">
	
				<form class="pb-2" action="/login" method="post">
		            <h3 class="mb-5">Sign in</h3>
					<div class="row">
						<div class="col-6">
							<div class="form-check">
							  <input class="form-check-input" value="false" type="radio" name="role" id="flexRadioDefault2" checked>
							  <label class="form-check-label" for="flexRadioDefault2">Customer</label>
							</div>
						 </div>
						 <div class="col-6">
							<div class="form-check">
							  <input class="form-check-input" value="true" type="radio" name="role" id="flexRadioDefault1">
							  <label class="form-check-label" for="flexRadioDefault1">Staff</label>
							</div>
						</div>
					</div>
		            <br>
		            <div class="form-outline mb-4">
		              <input type="text" value="${phone}" name="phone" class="form-control form-control-lg" placeholder="Username or phone number"/>
		            </div>
		
		            <div class="form-outline mb-4">
		              <input type="password" value="${pass}" name="password" class="form-control form-control-lg" placeholder="Password"/>
		            </div>
		
		            <!-- Checkbox -->
		            <div class="form-check d-flex justify-content-start mb-4">
		              <input class="form-check-input" name="remember" value="true" type="checkbox" id="form1Example3" checked/>
		              <label class="form-check-label" for="form1Example3"> &nbsp Remember me?</label>
		            </div>
		            <div class="row text-center">
                      <h5 class="text-danger">${mess}</h5>
                    </div>
					<div class="row">
		            	<button class="btn btn-primary btn-lg" type="submit">Login</button>
		            </div>
				</form>
	          </div>
	        </div>
	      </div>
	    </div>
	  </div>
	</section>
	
	<script type="text/javascript">
		function thongBao(mess) { 
			alert(mess);
		}		
		
		//Thông báo
		function thongBaoUP() {
			var messUP = "${param.messageUP}";
			if(messUP.length > 0){
				alert(messUP);
			}
		}
		
		//khởi chạy thông báo
		window.onload = function() {
			thongBaoUP();
		};
		
	</script>
	</body>
</html>