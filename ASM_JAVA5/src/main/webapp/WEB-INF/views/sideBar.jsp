<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>  
<!--  Side Bar -->
    <div class="offcanvas offcanvas-end" style="width: 450px;" data-bs-scroll="true" data-bs-backdrop="true" tabindex="-1" id="offcanvasExample" aria-labelledby="offcanvasExampleLabel">
	  <div class="offcanvas-header bg-dark">
	    <h3 class="offcanvas-title text-light" id="offcanvasExampleLabel">Giỏ Hàng <i class="bi bi-cart-fill"></i></h3>
	    <button type="button" class="btn-close btn-light bg-light" data-bs-dismiss="offcanvas" aria-label="Close"></button>
	  </div>
	  <div class="offcanvas-body bg-light">
	    <div class="mb-3" style="font-family: 'Time New Roman'; font-style: italic;" >
	      Xem và chỉnh sửa tổng quát món bạn đã chọn tại đây.
	    </div>
	    <div style="width: 100%;">
	      <c:choose>
			<c:when test="${sessionScope.count >= 1}">
	    		<div class="accordion" id="accordionFlushExample">
	    		<c:forEach var="item" items="${sessionScope.cart}">
	    			<form action="" method="post">
	                   	 <div class="accordion-item">
						    <h2 class="accordion-header" id="flush-headingOne">
						      <button class="accordion-button collapsed pl-2 pt-0 pb-0 pr-3" type="button" data-bs-toggle="collapse" data-bs-target="#${item.itemID}" aria-expanded="false" aria-controls="${item.itemID}">
						        <div class="p-0 col-1"><img src="/images/products/${item.productID}.jpg" alt="${item.productName}"
		                                width="52" height="67" style="border:0;"></div>
		                                <input type="hidden" name="id" value="${item.productID}">
		                        	<div class="col-9">
			                            <ul style="list-style: none;">
			                                <li class="m-0"><p class="text-danger mb-0 mt-3" style="font-size:18px;">${item.productName}</p></li>
			                                <li class="m-0"><code>Số Lượng: </code><span class="text-success">${item.soLuong}</span> | <a class="text-danger" href="/cart/delete/${item.itemID}"><i class="bi bi-trash-fill"></i> </a>
			                                <li class="m-0"><code>Giá: </code><span>
			                                	<fmt:formatNumber type="number" maxFractionDigits="0" value="${item.price * item.soLuong}" /> VND
										        </span></li>
			                            </ul>
			                        </div>
						      </button>
						    </h2>
						    <div id="${item.itemID}" class="accordion-collapse collapse" aria-labelledby="flush-headingOne" data-bs-parent="#accordionFlushExample">
						      <div class="accordion-body">
						      	<code>Ghi chú:</code> ${item.ghiChu}<br>
						      	<c:forEach var="itemTP" items="${sessionScope.cart_topping}">
						      		 <c:choose>
										<c:when test="${itemTP.itemID == item.itemID}">
											<code>Topping:</code> ${itemTP.toppingName} (<i class="text-info"><fmt:formatNumber type="number" maxFractionDigits="0" value="${itemTP.price}"/> VND</i>)
												| <a class="text-danger" href="/cart_topping/delete/${itemTP.itemToppingID}"><i class="bi bi-trash-fill"></i> </a>
											</span> <br>
										</c:when>	
						      		</c:choose>
						      	</c:forEach>
						      </div>
						    </div>
						  </div>
					  </form>	
					  </c:forEach>
	    			</div>
					
					<div class="d-flex mt-4">
						<span class="mr-auto">Tổng: <i class="fw-bolder text-success">
						<c:set var="num" value="1"/>
			               <c:choose>
							<c:when test="${sessionScope.count >= num}">
								<fmt:formatNumber type="number" maxFractionDigits="0" value="${sessionScope.amount + sessionScope.amount_topping}"/> VND </i></span>
							</c:when>	
							<c:otherwise>
								0 VND  </i></span>
							</c:otherwise>
						</c:choose> 
						
						<a class="btn btn-success ml-auto mr-1" href="/buy_view">Xem</a>
						<a class="btn btn-outline-dark" href="/cart/clear">Xóa hết</a>
					</div>
				</c:when>	
				<c:otherwise>
					<div class="box_title text-center mt-4 mb-4">
	                    <h3>Chưa có sản phẩm!</h3>
	                </div>	
				</c:otherwise>
			</c:choose>  	
		</div>
		
	  </div>
	</div>

	