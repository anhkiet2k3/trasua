 <%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
     <style>
     .menuTitle{
		transition: ease-in-out 0.2s;
	}
	</style>
 <header>
     <div class="header_center" >
         <section>
             <div class="logo">
                 <a href="/trangChu"><h2 class="mt-4 text-white tt mb-0" title="Hồng Trà YO"> Hồng Trà YO</h2> </a> 
             </div>
             <nav style="height: 90px; ">
                 <ul>
                     <li><a class="menuTitle" href="/gioiThieu" title="Giới thiệu">Giới thiệu</a> </li>
                     <li><a class="menuTitle" href="/category/all" title="Menu thức uống">Menu thức uống&nbsp;<i class="bi bi-caret-down"></i></a>
                     	 <ul class="menuTitle p-0">
                         	<c:forEach var="cate" items="${cates}">
                             <li>
                             	<a class="menuDrop menuTitle" href="/category/${cate.categoryID}">
                             		<h2 class="text-uppercase">${cate.categoryName}</h2>
                             	</a>
                             </li>
                             </c:forEach>
                         </ul>
                     </li>
                     <li><a class="menuTitle" title="Tài khoản">Tài khoản &nbsp;<i class="bi bi-caret-down"></i></a>
                         <ul class="menuTitle p-0">
                             <li><a id="nameCus" class="menuDrop text-danger" href="/login">${nameCus}</a></li>
                             <li><a class="menuDrop menuTitle" href="/buy_history">Lịch sử đơn hàng</a></li>
                             <li><a class="menuDrop menuTitle" href="/dangKy">Đăng ký thành viên</a></li>
                             <li><a class="menuDrop menuTitle" href="/capNhat">Cập nhật thông tin</a></li>
                             <li><a class="menuDrop menuTitle" href="/doiMatKhau">Đổi mật khẩu</a></li>
                             <li><a class="menuDrop menuTitle" href="/dangXuat">Đăng xuất</a></li>
                         </ul>
                     </li>
                     <li><a href="/lienHe" class="menuTitle" title="Liên hệ">Liên hệ</a></li>
                     <li>
                         <div class="h_cart menuTitle">
                         	<a data-bs-toggle="offcanvas" class="menuTitle" href="#offcanvasExample" role="button" aria-controls="offcanvasExample">
                         		<span>Giỏ hàng</span>
                         		<figure><i class="bi bi-cart"></i>
                         		<c:set var="num" value="1"/>
                         		<c:choose>
									<c:when test="${sessionScope.count >= num}">
										<code>${sessionScope.count}</code>
									</c:when>	
									<c:otherwise>
										<code>0</code>
									</c:otherwise>
								</c:choose> 
                         		
                         		</figure>
                         	</a>
                         </div>
                     </li>
                 </ul>
             </nav>
         </section>
     </div> 
 </header>
 
 
 <!-- Include Side Bar -->
<jsp:include page="sideBar.jsp" />
     