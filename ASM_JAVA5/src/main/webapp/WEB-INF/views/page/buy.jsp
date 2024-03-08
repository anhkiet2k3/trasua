<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>  
        <div class="main">
            <article>
                <!-- GOOGLE BREADCRUMB STRUCTURED DATA -->
                <script type="application/ld+json">
                    {
                        "@context": "http://schema.org",
                        "@type": "BreadcrumbList",
                        "itemListElement": [{
                            "@type": "ListItem",
                            "position": 1,
                            "item": {
                                "@id": "https://wujiateavn.com/",
                                "name": "Trang chủ"
                            }
                        }, {
                            "@type": "ListItem",
                            "position": 2,
                            "item": {
                                "@id": "https://wujiateavn.com/",
                                "name": "Trang chủ"
                            }
                        }, {
                            "@type": "ListItem",
                            "position": 3,
                            "item": {
                                "@id": "/order",
                                "name": "Giỏ hàng của bạn"
                            }
                        }]
                    }
                </script>
                <div class="div_title" style="width: 100%;">
                    <section>
                        <div><a href="https://wujiateavn.com/" title="Trang chủ"><span>Trang chủ</span></a></div><code><i class="bi bi-caret-right-fill"></i> </code>
                        <div><a href="/order" title="Giỏ hàng của bạn"><span>Giỏ hàng của bạn</span></a></div>
                    </section>
                </div>
                <form:form name="frm_order" id="frm_order" action="/buy_confirm" method="POST" modelAttribute="info">
                    <div class="box_pages" style="background: #f3f9fd">
                        <div class="cart_left">
                            <div class="section_left">
	                                <div class="cart_item"><label>Thông tin khách hàng</label>
	                                	<form:input path="customerID" type="hidden" value="${sessionScope.id}"/>
	                                
	                                    <ul>
	                                        <li><form:input path="phone" id="cart_phone" name="_phone" type="text" class="form-control checkPhoneInfo" placeholder="Điện thoại"/></li>
	                                        <li><form:input path="email" id="cart_email" name="_email" type="text" class="form-control" placeholder="Email"/></li>
	                                        <li><form:input path="customerName" id="cart_name" name="_name" type="text" class="form-control" placeholder="Họ tên"/></li>
	                                        <li><form:input path="diaChi" id="cart_address" name="_address" type="text" class="form-control" placeholder="Địa chỉ nhận đơn hàng"/></li>
	                                    </ul>
	                                </div>
	                                <div class="cart_btn d-flex">
	                                    <label><a title="Đăng nhập" class="p-1 nut2 text-primary" href="/login"><i class="bi bi-info-square"></i> Sử dụng tài khoản?</a></label>
	                                    <button th:formaction="@{/buy_confirm}" class="btn btn-primary ms-auto">Hoàn tất đơn hàng</button>
	                                </div>
                            </div>
                            
                        </div>
                        <div class="cart_right pt-2">
                            <div class="section_right">
                            	 
                                <div class="accordion" id="accordionFlushExample">
						    		<c:forEach var="item" items="${sessionScope.cart}">
						    			<form action="" method="post">
						                   	 <div class="accordion-item">
											    <h2 class="accordion-header" id="flush-headingOne">
											      <button class="accordion-button collapsed pl-2 pt-0 pb-0 pr-3" type="button" data-bs-toggle="collapse" data-bs-target="#${item.itemID}" aria-expanded="false" aria-controls="${item.itemID}">
											        <div class="p-0 col-1"><img src="/images/products/${item.productID}.jpg" alt="Sương Sáo Latte"
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
																<code>Topping:</code> ${itemTP.toppingName} (<i class="text-info"><fmt:formatNumber type="number" maxFractionDigits="0" value="${itemTP.price}"/> VND</i>)</span> <br>
															</c:when>	
											      		</c:choose>
											      	</c:forEach>
											      </div>
											    </div>
											  </div>
										  </form>	
										  </c:forEach>
						    			</div>
                                <div class="c_vanchuyen">
                                    <ul>
                                        <li><label>Món (<strong class="text-success">${sessionScope.count}</strong>)</label><code class="fw-bold"><fmt:formatNumber type="number" maxFractionDigits="0" value="${sessionScope.amount}" /> VND</code></li>
                                        <li><label>Topping (<strong class="text-success">${sessionScope.count_topping}</strong>)</label><code class="fw-bold"><fmt:formatNumber type="number" maxFractionDigits="0" value="${sessionScope.amount_topping}" /> VND</code></li>
                                        <li><label>Mã giảm giá</label><code class="fw-bold text-danger">-50.000đ</code></li>
                                        <li><label>Tổng đơn hàng</label><code class="text-primary fw-bold" style="font-size: 22px;"><fmt:formatNumber type="number" maxFractionDigits="0" value="${sessionScope.amount + sessionScope.amount_topping}"/> VND </i></code></li>
                                        <form:input path="price" type="hidden" value="${sessionScope.amount + sessionScope.amount_topping}"/>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
               </form:form>
            </article>
        </div>