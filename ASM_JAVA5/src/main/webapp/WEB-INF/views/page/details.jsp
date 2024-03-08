<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
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
                            "@id": "/lien-he",
                            "name": "Thông tin liên hệ"
                        }
                    }]
                }
            </script>
            <div class="div_title" style="width: 100%;">
                <section>
                    <div><a href="/menuThucUong" title="Trang chủ"><span>Sản Phẩm</span></a></div><code><i class="bi bi-caret-right-fill"></i> </code>
                    <div><a href="" title="Thông tin liên hệ"><span>${item.productName}</span></a></div>
                </section>
            </div>
            <div class="box_pages bg-light" style="min-height: 503px;">
                <div class="contact_left">
                    <div class="contact_content">
                        <div class="p_title">
                            <h1>Chi tiết sản phẩm</h1><code></code></div>
                        <div class="box_contact">
                        	<div class="row">
                        	<div class="col-6">
	                        	<form action="cart/add/${item.productID}" method="POST">
		                            <ul>
		                                <li><label>${item.productName}</label></li>
		                                <li><code>Mô tả: </code><span>${item.description}</span></li>
		                                <li><code>Thể loại: </code><span> ${theLoai}</span></li>
		                                <li><code>Size: </code><span> ${item.size ? '960CC' : '720CC'}</span></li>
		                                <li><code>Giá: </code><span style="color: #eb1c24"> <fmt:formatNumber type="number" maxFractionDigits="0" value="${item.price}" /> VND</span></li>
		                                <li>
		                                	<a type="button" class="text-primary" data-bs-toggle="modal" data-bs-target="#exampleModal">
											  + Thêm Topping Cho Món
											</a>
		                                </li>
			                                <!-- Bảng chọn Topping -->
											<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
											  <div class="modal-dialog modal-dialog-scrollable">
											    <div class="modal-content">
											      <div class="modal-header">
											        <h5 class="modal-title" id="exampleModalLabel">Lựa Chọn Topping</h5>
											        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
											      </div>
											      <div class="modal-body pt-0 pb-0">
												         <c:forEach var="tp" items="${listTP}">
												         	 <div class="form-check mt-3 mb-3">
												         	 	<input class="form-check-input" type="checkbox" value="${tp.toppingID}" name="topping" id="${tp.toppingID}">
														        <label class="form-check-label" for="${tp.toppingID}">
														          Topping: &nbsp; <strong class="text-primary">${tp.toppingName} </strong>(<strong class="text-info">+<fmt:formatNumber type="number" maxFractionDigits="0" value="${tp.price}" /> VND</strong>) 
														        </label> 
														      </div>					      
										                 </c:forEach>
											      </div>
											      <div class="modal-footer">
											        <button type="button" class="btn btn-primary" data-bs-dismiss="modal">Hoàn tất</button>
											      </div>
											    </div>
											  </div>
											</div>
		                                <li>
		                                	<div class="input-group">
		                                        <button type="button" class="quantity-left-minus btn btn-primary btn-number"  data-type="minus" data-field="">
		                                          <i class="bi bi-dash"></i>
		                                        </button>
			                                    <input type="text" id="quantity" name="quantity" class="form-control text-center col-4" value="1" min="1" max="100">
		                                        <button type="button" class="quantity-right-plus btn btn-primary btn-number" data-type="plus" data-field="">
		                                            <i class="bi bi-plus"></i>
		                                        </button>
		                                	</div>
		                                </li>
		                                <li>
		                                	<textarea class="form-control" name="ghiChu" maxlength="50" placeholder="Ghi chú cho thức uống..."></textarea>
		                                </li>

		                                <li><button type="submit" class="btn btn-primary"> Thêm vào giỏ hàng</button> </li>
		                            </ul>
	                            </form>
	                          </div>
                              <div class="mb-2 col-6"><img src="/images/products/${item.productID}.jpg" alt="Sương Sáo Latte"
                                width="250" height="320" style="border:0;"></div>
                             </div>
                        </div>
                    </div>
                </div>
                <div class="contact_right">
                      <div class="contact_content mt-2"style="min-height: 503px;">
                        <div class="p_title">
                            <h1>Liên quan đến ${theLoai}</h1><code></code></div>
                        <div class="box_contact pb-3">
	                        <c:forEach var="cate" items="${items}">
	                        	<div class="row bg-light pl-1 pb-1" style="border: 0.5px solid rgb(226, 226, 226); width: 625px;">
		                        	<div class="p-0 mt-1 col-1"><img src="/images/products/${cate.productID}.jpg" alt="Sương Sáo Latte"
		                                width="60" height="80" style="border:0;"></div>
		                        	<div class="col-9">
			                            <ul>
			                                <li class="m-0"><p class="text-danger mb-0 mt-3" style="font-size:20px;">${cate.productName}</p></li>
			                                <li class="m-0"><code>Giá: </code><span>
			                                	<fmt:formatNumber type="number" maxFractionDigits="0" value="${cate.price}" /> VND
										        </span></li>
			                            </ul>
			                        </div>
		                             <div class="col-2 p-0">
		                             	<a href="detail/${cate.productID}" class ="btn btn-primary mt-4">Xem</a>
		                             </div>
	                             </div>
                             </c:forEach>
                        </div>
                    </div>
                </div>
            </div>
        </article>
        
        
    </div>