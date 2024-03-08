<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>Hồng Trà YO</title>
    <meta name="keywords" content="Hồng Trà YO">
    <meta name="description" content="Hồng Trà YO">
    <link rel="canonical" href="https://wujiateavn.com/">
    <meta name="rating" content="general">
    <meta name="robots" content="index,follow">
    <meta name="revisit-after" content="1 days">
    <meta http-equiv="content-language" content="vi">
    <meta property="og:url" content="https://wujiateavn.com/">
    <meta property="og:title" content="Hồng Trà YO">
    <meta property="og:description" content="Hồng Trà YO">
    <meta property="og:image" content="https://wujiateavn.com/files/systems/anh-chia-se-mehh70vp.png">
    <meta property="og:site_name" content="wujiateavn.com">
    <meta property="og:type" content="article">
    <meta name='Search Engines' content='www.google.com, www.google.com.vn, www.yahoo.com'>
    <meta property="og:locale" content="vi_VN">
    <link rel="shortcut icon" type="image/x-icon" href="https://wujiateavn.com/files/systems/favicon-d2tu.ico">
    <link rel="alternate" href="https://wujiateavn.com/" hreflang="vi-vn">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@100;300;400;500;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.2/font/bootstrap-icons.css">
    <link rel="stylesheet" href="css/trangChuCss.css">
    <link rel="stylesheet" href="js/trangChuJS.js">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2" crossorigin="anonymous"></script>
     
    <script language="javascript" src="https://wujiateavn.com/js/jquery-1.12.4.min.js"></script>
    
    <style type="text/css">
	    html{
			scroll-padding-top: 35px;
		}
		
		a{
			text-decoration: none;
		}
    
    </style>
</head>

<body>
    <div id="fb-root"></div>
	
    <!-- Messenger Plugin chat Code -->
    <div id="fb-root"></div>
    <!-- Your Plugin chat code -->
    <div id="fb-customer-chat" class="fb-customerchat"></div>

    <!-- Your SDK code -->
    
	<!-- Include Navbar -->
	
	<jsp:include page="${sessionScope.menu== 'true'?'Staff/navbar.jsp':'navbar.jsp'}" />
	
   	
  	
    <!-- Include Page -->
	<jsp:include page="${page}" />
       
	<script type="text/javascript">
		function thongBao() { 
			var mess = "${param.mess}";
			if(mess.length > 0){
				alert(mess);
			}
		}		
		
		//Thông báo khi đặt hàng
		function thongBaoDH() {
			var messDH = "${param.messageDH}";
			if(messDH.length > 0){
				alert(messDH);
			}
		}
		
		//Thông báo khi thêm vào giỏ hàng
		function thongBaoGH() {
			var messGH = "${param.messageGH}";
			if(messGH.length > 0){
				alert(messGH);
			}
		}
		
		//khởi chạy thông báo
		window.onload = function() {
			thongBao();
			thongBaoDH();
			thongBaoGH();
		};
		
		//Scipt nut tang giam so luong khi dat hang
		$(document).ready(function(){

			var quantitiy=0;
			   $('.quantity-right-plus').click(function(e){
			        
			        // Stop acting like a button
			        e.preventDefault();
			        // Get the field name
			        var quantity = parseInt($('#quantity').val());
			        
			        // If is not undefined
			            
			            $('#quantity').val(quantity + 1);

			          
			            // Increment
			        
			    });

			     $('.quantity-left-minus').click(function(e){
			        // Stop acting like a button
			        e.preventDefault();
			        // Get the field name
			        var quantity = parseInt($('#quantity').val());
			        
			        // If is not undefined
			      
			            // Increment
			            if(quantity>0){
			            $('#quantity').val(quantity - 1);
			            }
			    });
			    
			});
	</script>
   	
    <!-- Include footer -->
	<jsp:include page="footer.jsp" />


    <div class="load_js"></div>
    <style>
        @font-face {
            font-display: swap;
            font-family: 'FontAwesome';
            src: url('https://wujiateavn.com/plugins/fontawesome4/fontawesome-webfont.eot?v=4.7.0');
            src: url('https://wujiateavn.com/plugins/fontawesome4/fontawesome-webfont.eot?#iefix&v=4.7.0') format('embedded-opentype'), url('https://wujiateavn.com/plugins/fontawesome4/fontawesome-webfont.woff2?v=4.7.0') format('woff2'), url('https://wujiateavn.com/plugins/fontawesome4/fontawesome-webfont.woff?v=4.7.0') format('woff'), url('https://wujiateavn.com/plugins/fontawesome4/fontawesome-webfont.ttf?v=4.7.0') format('truetype'), url('https://wujiateavn.com/plugins/fontawesome4/fontawesome-webfont.svg?v=4.7.0#fontawesomeregular') format('svg');
            font-weight: normal;
            font-style: normal
        }
    </style>
    <link rel="stylesheet" href="https://wujiateavn.com/plugins/fontawesome4/font-awesome.min.css">
    <link href="https://wujiateavn.com/css/jquery.fancybox.min.css" rel="stylesheet">
    <script language="javascript" src="https://wujiateavn.com/js/jquery.fancybox.min.js"></script>
    <link rel="stylesheet" type="text/css" href="https://wujiateavn.com/plugins/slick/slick.css">
    <script src="https://wujiateavn.com/plugins/slick/slick.js" type="text/javascript"></script>
    <script language="javascript" src="https://wujiateavn.com/js/jquery.dotdotdot.js"></script>
</body>

</html>