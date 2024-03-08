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
					<li><a class="menuTitle" href="" title="Khuyến Mãi">Khuyến Mãi</a> </li>
                    <li><a class="menuTitle" href="" title="Thành Viên">Thành Viên&nbsp;<i class="bi bi-caret-down"></i></a> 
                    	<ul class="menuTitle p-0">
                             <li><a class="menuDrop menuTitle" href="">Nhân Viên</a></li>
                             <li><a class="menuDrop menuTitle" href="">Khách Hàng</a></li>
                        </ul>
                    </li>
                    <li><a class="menuTitle" href="/sanPham" title="Sản Phẩm">Sản Phẩm</a></li>
                    <li><a class="menuTitle" href="" title="Topping">Topping</a></li>
                    <li><a class="menuTitle" title="Tài khoản">Tài khoản &nbsp;<i class="bi bi-caret-down"></i></a>
                        <ul class="menuTitle p-0">
                             <li><a id="nameCus" class="menuDrop text-danger" href="/login">${nameCus}</a></li>
                             <li><a class="menuDrop menuTitle" href="/dangKy_Staff">Đăng ký nhân viên</a></li>
                             <li><a class="menuDrop menuTitle" href="/capNhat_Staff">Cập nhật thông tin</a></li>
                             <li><a class="menuDrop menuTitle" href="/doiMatKhau_Staff">Đổi mật khẩu</a></li>
                             <li><a class="menuDrop menuTitle" href="/dangXuat">Đăng xuất</a></li>
                        </ul>
                    </li>
                    
                    <li>
                         <div class="h_cart menuTitle">
                         	<a href="/bill_list" title="Đơn hàng">
                         		<span>Đơn hàng</span>
                         		<figure><i class="bi bi-card-text"></i>
                         		<c:set var="num" value="1"/>
		                     		<c:choose>
									<c:when test="${sessionScope.CXN >= num}">
										<code>${sessionScope.CXN}</code>
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
     