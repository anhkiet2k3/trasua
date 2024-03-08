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
		                    <h3>DANH SÁCH CÁC ĐƠN ĐẶT HÀNG</h3>
		                    <h3>Page ${bill.number+1} of ${bill.totalPages}</h3>
		                </div>	
						<table class="table table-striped text-center">
						  <thead class="table-dark">
						    <tr>
						      <th scope="col">Mã Bill</th>
						      <th scope="col">Ngày</th>
						      <th scope="col">Giờ</th>
						      <th scope="col">Mã KH</th>
						      <th scope="col">Tên Khách Hàng</th>
						      <th scope="col">Giá</th>
						      <th scope="col">Mã Người Duyệt</th>
						      <th scope="col">Trạng Thái</th>
						      <th scope="col">Xem</th>
						    </tr>
						  </thead>
						  <tbody>	  
							<c:forEach var="item" items="${bill.content}">
								<tr>
									<td>${item.billID}</td>
									<td>${item.ngay}</td>
									<td>${item.gio}</td>
									<td>${item.customerID}</td>
									<td>${item.customerName}</td>
									<td><fmt:formatNumber type="number" maxFractionDigits="0" value="${item.price}" /></td>
									<td>${item.staffID != null?item.staffID:'Chưa Có'}</td>
									<td>${item.statusName}</td>
									<td><a class="btn btn-info text-light" href="/staff_bill_detail/${item.billID}" title="Xem Đơn Hàng"><i class="bi bi-eye"></i> </a></td>
								</tr>
							</c:forEach>
						  </tbody>
						</table> 
						<div class="btn-group col-3 mb-3 d-flex m-auto">
							<a href="/bill_list?p=0" class="btn btn-outline-dark">First</a>
							<a href="/bill_list?p=${bill.number-1}" class="btn btn-outline-dark">Prev</a>
							<a href="/bill_list?p=${bill.number+1}" class="btn btn-outline-dark">Next</a>
							<a href="/bill_list?p=${bill.totalPages-1}" class="btn btn-outline-dark">Last</a>
						</div>
						
						<c:choose>
							<c:when test="${check == true}">
								<div class="box_title mb-3 mt-2">
				                    <h3>Chi Tiết Đơn Hàng ${thongTin.billID}</h3>
				                </div>
								<div class="box_pages">
				                <div class="contact_left p-0 mb-3" style="min-height: 594px;">
				                    <div class="contact_content">
				                        <div class="box_contact mt-0" var="thongTin" items="${thongTin}">
				                            <ul>
				                                <li><code>Mã đơn: </code><span class="fw-semibold" style="color: #eb1c24; font-size: 22px;">${thongTin.billID}</span></li>
				                                <li><code>Trạng Thái: </code><span style="color: #eb1c24">${thongTin.statusName}</span></li>
				                                <li><code>Mã Khách Hàng: </code><span style="color: #eb1c24">${thongTin.customerID}</span></li>
				                                <li><code>Mã Người Duyệt: </code><span style="color: #eb1c24">${thongTin.staffID!= null?thongTin.staffID:'Chưa Có'}</span></li>
				                                <li><code>Thời Gian: </code><span style="color: #eb1c24"><code>ngày</code> ${thongTin.ngay} <code>lúc</code> ${thongTin.gio}</span></li> 
				                                <li><code>Ghi Chú: </code><span>${thongTin.ghiChu}</span></li>
				                                <li><hr></li>      
				                                <li><code>Tên Người Nhận: </code><span>${thongTin.customerName}</span></li>
				                                <li><code>Email: </code><span>${thongTin.email}</span></li>
				                                <li><code>SDT: </code><span> ${thongTin.phone}</span></li>
				                                <li><code>Giao Đến: </code><span>${thongTin.diaChi}</span></li>                   
				                                <li><code>Giá: </code><span style="color: #eb1c24"><fmt:formatNumber type="number" maxFractionDigits="0" value="${thongTin.price}" /> VND</span></li> 
				                                <li>
				                                	<c:choose>
														<c:when test="${thongTin.statusName == 'Chưa Xác Nhận'}">
															<a  href="/staff_confirm/${thongTin.billID}" type="button" title="Xác Nhận Đơn Hàng" class="btn btn-primary text-light">Xác Nhận</a>
														</c:when>	
														<c:when test="${thongTin.statusName == 'Đang Thực Hiện'}">
															<a href="/staff_success/${thongTin.billID}" type="button" title="Hoàn Tất Đơn Hàng" class="btn btn-success text-light">Hoàn Tất</a>
														</c:when>
													</c:choose>   
					                                <c:choose>
														<c:when test="${thongTin.statusName == 'Chưa Xác Nhận'}">
															<button type="button" title="Hủy Đơn Hàng" class="btn btn-danger text-light" data-bs-toggle="modal" data-bs-target="#exampleModal" data-bs-whatever="@mdo">Hủy Đơn Hàng</button>
														</c:when>	
													</c:choose>   
												</li> 
				                            </ul>
				                        </div>
				                    </div>
				                </div>
				                <div class="contact_right" style="min-height: 594px;">
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
							                                <li class="m-0"><code>Số Lượng: </code><span class="text-success">${itemDT.soLuong}</span></li>
							                                <li class="m-0"><code>Tổng tiền (Chưa tính Topping): </code><span>
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
					<c:when test="${auth == false}">
						<div class="box_title text-center mt-5 mb-5">
		                    <h3>VUI LÒNG ĐĂNG NHẬP ĐÚNG VAI TRÒ ĐỂ XEM </h3>
		                    <h3>DANH SÁCH CÁC ĐƠN HÀNG!</h3>
		                </div>	
					</c:when>
				</c:choose>  
			</div>
		</section>
		
	</article>      
     <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="exampleModalLabel">Xác Nhận Hủy Đơn Hàng</h5>
	        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	      </div>
	      <div class="modal-body">
	        <form action="/staff_cancel/${thongTin.billID}" method="POST">
	          <div class="mb-3">
	            <label for="message-text" class="col-form-label">Lí do hủy đơn? <i class="text-danger">*</i></label>
	            <textarea class="form-control" name="liDo" id="message-text"></textarea>
	          </div>
	        
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>
		        <button type="submit" class="btn btn-danger"> Xác Nhận</button>
		      </div>
	      </form>
	    </div>
	  </div>
	</div>
</div>