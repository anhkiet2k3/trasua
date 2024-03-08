<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>  
<div class="main pt-3 pb-4">
	<article>
		<section>
			<div class="m-auto">
				<c:choose>
					<c:when test="${auth == true}">
		                <div class="box_title mb-3">
		                    <h3>DANH SÁCH CÁC ĐƠN ĐẶT HÀNG CỦA BẠN</h3>
		                </div>	
						<table class="table table-striped text-center">
						  <thead class="table-dark">
						    <tr>
						      <th scope="col">Mã Bill</th>
						      <th scope="col">Ngày</th>
						      <th scope="col">Giờ</th>
						      <th scope="col">Tên Khách Hàng</th>
						      <th scope="col">Giá</th>
						      <th scope="col">Trạng Thái</th>
						      <th scope="col">Tùy Chọn</th>
						    </tr>
						  </thead>
						  <tbody>	  
							<c:forEach var="item" items="${bill.content}">
								<tr>
									<td>${item.billID}</td>
									<td>${item.ngay}</td>
									<td>${item.gio}</td>
									<td>${item.customerName}</td>
									<td><fmt:formatNumber type="number" maxFractionDigits="0" value="${item.price}" /></td>
									<td>${item.statusName}</td>
									<td><a class="btn btn-info text-light" href="/billDetail/${item.billID}" title="Xem Đơn Hàng"><i class="bi bi-eye"></i> </a></td>
								</tr>
							</c:forEach>
						  </tbody>
						</table> 
						<div class="btn-group col-3 mb-3 d-flex m-auto">
							<a href="/buy_history?p=0" class="btn btn-outline-dark">First</a>
							<a href="/buy_history?p=${bill.number-1}" class="btn btn-outline-dark">Prev</a>
							<a href="/buy_history?p=${bill.number+1}" class="btn btn-outline-dark">Next</a>
							<a href="/buy_history?p=${bill.totalPages-1}"" class="btn btn-outline-dark">Last</a>
						</div>
						
						<c:choose>
							<c:when test="${check == true}">
								<div class="box_title mb-3 mt-2">
				                    <h3>Chi Tiết Đơn Hàng ${thongTin.billID}</h3>
				                </div>
								<div class="box_pages">
				                <div class="contact_left p-0 mb-3" style="min-height: 535px;">
				                    <div class="contact_content">
				                        <div class="box_contact mt-0" var="thongTin" items="${thongTin}">
				                            <ul>
				                                <li><code>Mã đơn: </code><span class="fw-semibold" style="color: #eb1c24; font-size: 22px;">${thongTin.billID}</span></li>
				                                <li><code>Trạng Thái: </code><span style="color: #eb1c24">${thongTin.statusName}</span></li>
				                                <li><code>Mã Khách Hàng: </code><span style="color: #eb1c24">${thongTin.customerID}</span></li>
				                                <li><code>Mã Người Duyệt: </code>${thongTin.staffID}</span></li>
				                                <li><code>Thời Gian: </code><span style="color: #eb1c24"><code>ngày</code> ${thongTin.ngay} <code>lúc</code> ${thongTin.gio}</span></li> 
				                                <li><hr></li>      
				                                <li><code>Tên Người Nhận: </code><span>${thongTin.customerName}</span></li>
				                                <li><code>Email: </code><span>${thongTin.email}</span></li>
				                                <li><code>SDT: </code><span> ${thongTin.phone}</span></li>
				                                <li><code>Giao Đến: </code><span>${thongTin.diaChi}</span></li>
				                                <li><code>Giá: </code><span style="color: #eb1c24"><fmt:formatNumber type="number" maxFractionDigits="0" value="${thongTin.price}" /> VND</span></li> 
				                                <c:choose>
													<c:when test="${thongTin.statusName == 'Chưa Xác Nhận'}">
														<a class="btn btn-danger text-light" title="Hủy Đơn Hàng" href="/cancel/${thongTin.billID}">Hủy Đơn Hàng </a>
													</c:when>	
												</c:choose>    
				                            </ul>
				                        </div>
				                    </div>
				                </div>
				                <div class="contact_right" style="min-height: 535px;">
				                    <aside>
				                    	<div class="box_contact pb-3 mt-0">
				                    	<div class="accordion" id="accordionFlushExample">
				                    	<c:forEach var="itemDT" items="${listDT}">
				                    	 <div class="accordion-item">
										    <h2 class="accordion-header" id="flush-headingOne">
										      <button class="accordion-button collapsed pl-2 pt-2 pb-2 pr-4" type="button" data-bs-toggle="collapse" data-bs-target="#${itemDT.billDetailID}" aria-expanded="false" aria-controls="${itemDT.billDetailID}">
										        <div class="p-0 col-1"><img src="/images/products/${itemDT.productID}.jpg" alt="Sương Sáo Latte"
						                                width="87" height="107" style="border:0;"></div>
						                        	<div class="col-9">
							                            <ul>
							                                <li class="m-0"><p class="text-danger mb-0 mt-3" style="font-size:20px;">${itemDT.productName}</p></li>
							                                <li class="m-0"><code>Số Lượng: </code><span class="text-success">${itemDT.soLuong} Ly</span></li>
							                                <li class="m-0"><code>Tổng Tiền (Chưa tính Topping): </code><span>
							                                	<fmt:formatNumber type="number" maxFractionDigits="0" value="${itemDT.price}" /> VND
														         </span></li>
							                            </ul>
							                        </div>
										      </button>
										    </h2>
										    <div id="${itemDT.billDetailID}" class="accordion-collapse collapse" aria-labelledby="flush-headingOne" data-bs-parent="#accordionFlushExample">
										      <div class="accordion-body">
										      		<code>Ghi chú:</code> ${itemDT.ghiChu}<br>
											      	<c:forEach var="itemTP" items="${listTP}">
											      		 <c:choose>
															<c:when test="${itemTP.billDetailID == itemDT.billDetailID}">
																<code>Topping:</code> ${itemTP.toppingName} (<i class="text-info"><fmt:formatNumber type="number" maxFractionDigits="0" value="${itemTP.price}"/> VND</i>)</span> <br>
															</c:when>	
											      		</c:choose>
											      	</c:forEach>
										      </div>
										    </div>
										  </div>
										  </c:forEach>
										  </div> 
				                        </div>
				                    </aside>
				                </div>
				            </div>
		            	</c:when>	
						</c:choose>  
					</c:when>	
					<c:otherwise>
						<div class="box_title text-center mt-5 mb-5">
		                    <h3>VUI LÒNG ĐĂNG NHẬP ĐỂ XEM DANH SÁCH</h3>
		                    <h3>ĐẶT HÀNG CỦA BẠN!</h3>
		                </div>	
					</c:otherwise>
				</c:choose>  
			</div>
		</section>
		
	</article>      
        
</div>